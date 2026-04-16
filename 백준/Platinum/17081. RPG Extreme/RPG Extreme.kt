import Direction.*
import GameProgress.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KClass

data class Vec2I(val y: Int, val x: Int) {
    operator fun unaryMinus() = Vec2I(-y, -x)
    operator fun plus(that: Vec2I) = Vec2I(this.y + that.y, this.x + that.x)
    operator fun minus(that: Vec2I) = Vec2I(this.y - that.y, this.x - that.x)
}

enum class Direction {
    UP, LEFT, RIGHT, DOWN
}

fun StringTokenizer.nextInt(): Int = this.nextToken().toInt()

fun main() {
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val tk = StringTokenizer(rd.readLine())
    val row = tk.nextInt()
    val column = tk.nextInt()
    val world: World = WorldImpl(Vec2I(row, column))

    var numOfMonster = 0
    var numOfBox = 0
    var playerLoc: Vec2I? = null

    val boardInput = Array(row) { CharArray(column) }
    for (y in 0..<row) {
        boardInput[y] = rd.readLine().toCharArray()
        for (x in 0..<column) {
            val loc = Vec2I(y, x)
            when (boardInput[y][x]) {
                '&', 'M' -> numOfMonster++
                'B' -> numOfBox++
                '@' -> playerLoc = loc
                '#' -> world.place(loc, Wall)
                '^' -> world.place(loc, SpikeTrap)
            }
        }
    }

    val keyInput = rd.readLine().map {
        when (it) {
            'U' -> UP
            'D' -> DOWN
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw IllegalArgumentException()
        }
    }

    for (i in 0..<numOfMonster) {
        val tk = StringTokenizer(rd.readLine())
        val loc = Vec2I(tk.nextInt()-1, tk.nextInt()-1)
        val isBoss = boardInput[loc.y][loc.x] == 'M'
        val monster = with(tk) {
            Monster(nextToken(), nextInt(), nextInt(), nextInt(), nextInt(), isBoss)
        }

        world.place(loc, monster)
    }

    for (i in 0..<numOfBox) {
        val tk = StringTokenizer(rd.readLine())
        val loc = Vec2I(tk.nextInt()-1, tk.nextInt()-1)
        val item: Item = with(tk) {
            when (nextToken()) {
                "W" -> Weapon(nextInt())
                "A" -> Armor(nextInt())
                "O" -> when (nextToken()) {
                    "HR" -> HpRegeneration
                    "RE" -> Reincarnation
                    "CO" -> Courage
                    "EX" -> Experience
                    "DX" -> Dexterity
                    "HU" -> Hunter
                    "CU" -> Cursed
                    else -> throw IllegalArgumentException()
                }
                else -> throw IllegalArgumentException()
            }
        }
        world.place(loc, ItemBox(item))
    }

    if (playerLoc == null)
        throw IllegalArgumentException("no player found in the map")

    val game = Game(Player(playerLoc), world, keyInput)
    game.start()
    game.print(System.out)
}

class Game(
    val player: Player,
    val world: World,
    val keyInput: Iterable<Direction>
) {
    val eventSystem: EventSystem = EventSystemImpl()

    var progress: GameProgress = NOT_STARTED
    var turn: Int = 0
    val spawnPoint: Vec2I = player.location

    var playingBattle: Battle? = null

    init {
        eventSystem.registerListener(PreEvent::class) { preEvent ->
            val ornaments = player.inventory.ornaments.toList()  // shallow copy
            for (ornament in ornaments) {
                eventSystem.broadcast(ornament.eventSystem, preEvent.cause!!)
            }
        }
        eventSystem.registerListener(PlayerMoveEvent::class) { event ->
            eventSystem.broadcast(world.getTileAt(event.nextLoc).eventSystem, event)
        }
        eventSystem.registerListener(PlayerMoveEvent::class) { event ->
            player.location = event.nextLoc
        }
        eventSystem.registerListener(PlayerStepEvent::class) { event ->
            eventSystem.broadcast(world.getTileAt(event.game.player.location).eventSystem, event)
        }
        eventSystem.registerListener(PlayerExpGainEvent::class) { event ->
            player.exp += event.amount
            if (player.exp >= player.maxExp) {
                eventSystem.trigger(PlayerLevelUpEvent(this, event))
            }
        }
        eventSystem.registerListener(PlayerLevelUpEvent::class) {
            with(player) {
                level++
                exp = 0
                maxHp += 5
                baseAtt += 2
                baseDef += 2
                hp = maxHp
            }
        }
        eventSystem.registerListener(PlayerItemObtainEvent::class) { event ->
            val inventory = player.inventory
            val item = event.item
            if (item is Weapon)
                inventory.weapon = item
            else if (item is Armor)
                inventory.armor = item
            else if (item is Ornament) {
                if (inventory.ornaments.size < 4 && item !in inventory.ornaments) {
                    inventory.ornaments.add(item)
                }
            }
        }
        eventSystem.registerListener(BattleStartEvent::class) { event ->
            val battle = event.battle
            playingBattle = battle
            battle.play()
        }
        eventSystem.registerListener(BattleEndEvent::class) { event ->
            playingBattle?.isEnded = true
            playingBattle = null
        }
        eventSystem.registerListener(DamageEvent::class) { event ->
            event.victim.hp -= event.currentDamage
            if (event.victim.hp <= 0) {
                if (event.victim is Player)
                    eventSystem.trigger(PlayerDeathEvent(this, event, event.victim))
                else if (event.victim is Monster)
                    eventSystem.trigger(MonsterDeathEvent(this, event, event.victim))
            }
        }
        eventSystem.registerListener(MonsterDeathEvent::class) { event ->
            if (playingBattle != null) {
                eventSystem.trigger(BattleWinEvent(this, event))
                eventSystem.trigger(BattleEndEvent(this, event, playingBattle!!))
            }
            eventSystem.trigger(PlayerExpGainEvent(this, event, event.victim.dropExp))
            world.place(event.game.player.location, Emptiness)
            if (event.victim.isBoss) {
                eventSystem.trigger(GameEndEvent(this, event))
            }
        }
        eventSystem.registerListener(PlayerDeathEvent::class) { event ->
            if (event.cause is DamageEvent)
                player.killer = event.cause.attacker
            if (playingBattle != null) {
                eventSystem.trigger(BattleEndEvent(this, event, playingBattle!!))
            }
            eventSystem.trigger(GameEndEvent(this, event))
        }
        eventSystem.registerListener(GameEndEvent::class) { event ->
            progress = when (event.cause) {
                is MonsterDeathEvent -> PLAYER_WON
                is PlayerDeathEvent -> PLAYER_LOST
                else -> throw IllegalStateException("Why game just ended?")
            }
        }
    }

    fun print(out: PrintStream) {
        val (worldY, worldX) = world.size
        val tileMap = StringBuilder()
        for (y in 0..<worldY) {
            if (y > 0) tileMap.append('\n')
            tileMap.append('|')
            for (x in 0..<worldX) {
                val loc = Vec2I(y, x)
                if (loc == player.location && !player.killed)
                    tileMap.append(player.icon)
                else
                    tileMap.append(world.getTileAt(loc).icon)
            }
        }

        val result = when (progress) {
            PLAYER_WON -> "YOU WIN!"
            PLAYER_LOST -> "YOU HAVE BEEN KILLED BY ${player.killer?.name}.."
            else -> "Press any key to continue."
        }

        out.println("""
            $tileMap
            |Passed Turns : $turn
            |LV : ${player.level}
            |HP : ${max(0, player.hp)}/${player.maxHp}
            |ATT : ${player.baseAtt}+${player.bonusAtt}
            |DEF : ${player.baseDef}+${player.bonusDef}
            |EXP : ${player.exp}/${player.maxExp}
            |$result
        """.trimMargin("|"))
    }

    fun start() {
        if (progress != NOT_STARTED)
            throw IllegalStateException("The game is already started!")

        progress = PLAYING

        for (key in keyInput) {
            if (progress.ended) break
            turn++
            eventSystem.trigger(PlayerMoveEvent(this, null, player.location, key))
            if (progress.ended) break
            eventSystem.trigger(PlayerStepEvent(this, null))
        }
    }
}

enum class GameProgress(val ended: Boolean) {
    NOT_STARTED(false), PLAYING(false), PLAYER_WON(true), PLAYER_LOST(true);
}

interface World {
    val size: Vec2I
    fun getTileAt(loc: Vec2I): Tile
    fun place(loc: Vec2I, tile: Tile)
    fun isValidLocation(loc: Vec2I): Boolean
}

class WorldImpl(override val size: Vec2I) : World {
    // Don't ask why I implemented tile-grid with Map.
    val tiles: MutableMap<Vec2I, Tile> = mutableMapOf()

    override fun getTileAt(loc: Vec2I): Tile {
        if (!isValidLocation(loc))
            return Wall     // virtual wall
        return tiles[loc] ?: Emptiness
    }

    override fun place(loc: Vec2I, tile: Tile) {
        if (!isValidLocation(loc)) throw IllegalArgumentException()
        tiles[loc] = tile
    }

    override fun isValidLocation(loc: Vec2I): Boolean {
        val (y, x) = loc
        return !(y < 0 || y >= size.y || x < 0 || x >= size.x)
    }
}

interface EventSystem {
    fun <E : Event> trigger(event: E)
    fun <E : Event> registerListener(eventType: KClass<E>, listener: EventListener<E>)
    fun broadcast(target: EventSystem, event: Event)
}

class EventSystemImpl : EventSystem {
    private val listeners: MutableMap<KClass<out Event>, MutableList<EventListener<*>>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    override fun <E : Event> trigger(event: E) {
        if (event !is PreEvent) {
            trigger(PreEvent(event.game, event))
        }
        for (rawListener in listeners[event::class] ?: emptyList()) {
            if (event.cancelled) continue
            (rawListener as EventListener<E>).listen(event)
        }
    }

    override fun <E : Event> registerListener(eventType: KClass<E>, listener: EventListener<E>) {
        if (eventType !in listeners) {
            listeners[eventType] = LinkedList<EventListener<*>>()
        }
        listeners[eventType]?.add(listener)
    }

    override fun broadcast(target: EventSystem, event: Event) {
        target.trigger(event)
    }
}

interface Event {
    val game: Game
    val cancelled: Boolean
    val cause: Event?
}

abstract class EventBase(
    override val game: Game,
    override val cause: Event?
) : Event {
    override val cancelled = false
}

interface CancellableEvent : Event {
    override var cancelled: Boolean
}

abstract class CancellableEventBase(
    override val game: Game,
    override val cause: Event?
) : EventBase(game, cause), CancellableEvent
{
    override var cancelled: Boolean = false
}

class PreEvent(game: Game, cause: Event) : EventBase(game, cause)

class PlayerMoveEvent(game: Game, cause: Event?, val prevLoc: Vec2I, val key: Direction)
    : CancellableEventBase(game, cause)
{
    val nextLoc
        get() = prevLoc + when (key) {
            UP      -> Vec2I(-1,  0)
            DOWN    -> Vec2I(+1,  0)
            LEFT    -> Vec2I( 0, -1)
            RIGHT   -> Vec2I( 0, +1)
        }
}

class PlayerStepEvent(game: Game, cause: Event?) : EventBase(game, cause)

class PlayerExpGainEvent(game: Game, cause: Event?, var amount: Int) : CancellableEventBase(game, cause)

class PlayerLevelUpEvent(game: Game, cause: Event?): CancellableEventBase(game, cause)

class BattleStartEvent(game: Game, cause: Event?, monster: Monster) : EventBase(game, cause) {
    val battle = Battle(game, game.player, monster)
}

class BattleEndEvent(game: Game, cause: Event?, val battle: Battle) : EventBase(game, cause)

class BattleWinEvent(game: Game, cause: Event?) : EventBase(game, cause)

class DamageEvent(game: Game, cause: Event?, val attacker: Attacker, val victim: Mortal, var currentDamage: Int)
    : CancellableEventBase(game, cause)

class MonsterDeathEvent(game: Game, cause: Event?, val victim: Monster)
    : CancellableEventBase(game, cause)

class PlayerDeathEvent(game: Game, cause: Event?, val victim: Player)
    : CancellableEventBase(game, cause)

class PlayerItemObtainEvent(game: Game, cause: Event?, var item: Item) : CancellableEventBase(game, cause)

class GameEndEvent(game: Game, cause: Event?) : EventBase(game, cause)


fun interface EventListener<E : Event> {
    fun listen(event: E)
}


interface Tile {
    val icon: Char
    val eventSystem: EventSystem
}

object Emptiness : Tile {
    override val icon = '.'
    override val eventSystem = EventSystemImpl()
}

object Wall : Tile {
    override val icon = '#'
    override val eventSystem = EventSystemImpl()

    init {
        eventSystem.registerListener(PlayerMoveEvent::class) { event ->
            event.cancelled = true
        }
    }
}

interface Attacker {
    val name: String
    val att: Int
}

interface Mortal {
    var hp: Int
    val maxHp: Int
    val def: Int

    val killed: Boolean
    var killer: Attacker?
}

object SpikeTrap : Tile, Attacker {
    override val icon = '^'
    override val name = "SPIKE TRAP"
    override val att = 5
    override val eventSystem = EventSystemImpl()

    init {
        eventSystem.registerListener(PlayerStepEvent::class) { event ->
            val game = event.game
            game.eventSystem.trigger(
                DamageEvent(game, event, this, game.player, att)
            )
        }
    }
}

abstract class Creature : Mortal, Attacker {
    override val killed: Boolean
        get() = hp <= 0

    override var killer: Attacker? = null
}

class Monster(
    override val name: String,
    override val att: Int,
    override val def: Int,
    override val maxHp: Int,
    val dropExp: Int,
    val isBoss: Boolean
) : Creature(), Tile {
    override val icon = if (isBoss) 'M' else '&'
    override var hp = maxHp
    override val eventSystem = EventSystemImpl()

    init {
        eventSystem.registerListener(PlayerStepEvent::class) { event ->
            event.game.eventSystem.trigger(BattleStartEvent(event.game, event, this))
        }
    }
}

class Player(var location: Vec2I) : Creature() {
    val icon = '@'
    override val name = "PLAYER"

    var level: Int = 1
    var baseAtt: Int = 2
    var baseDef: Int = 2
    val bonusAtt: Int
        get() = inventory.weapon.att
    val bonusDef: Int
        get() = inventory.armor.def

    var exp = 0
    val maxExp
        get() = 5 * level

    val inventory: PlayerInventory = PlayerInventoryImpl()

    override var maxHp = 20
    override var hp = maxHp
    override val att
        get() = baseAtt + bonusAtt

    override val def
        get() = baseDef + bonusDef
}

interface PlayerInventory {
    var weapon: Weapon
    var armor: Armor
    var ornaments: MutableSet<Ornament>
}

class PlayerInventoryImpl : PlayerInventory {
    override var weapon: Weapon = Weapon(0)
    override var armor: Armor = Armor(0)
    override var ornaments: MutableSet<Ornament> = mutableSetOf()
}

interface Item
data class Weapon(val att: Int) : Item
data class Armor(val def: Int) : Item

interface Ornament : Item {
    val eventSystem: EventSystem
}

abstract class OrnamentBase : Ornament {
    override val eventSystem = EventSystemImpl()
}


object HpRegeneration : OrnamentBase() {
    init {
        eventSystem.registerListener(BattleWinEvent::class) { event ->
            with(event.game.player) { hp = min(hp + 3, maxHp) }
        }
    }
}

object Reincarnation : OrnamentBase() {
    init {
        eventSystem.registerListener(PlayerDeathEvent::class) { event ->
            event.cancelled = true

            val game = event.game
            if (game.playingBattle != null) {
                val battle = game.playingBattle!!
                with(battle.monster) { hp = maxHp }
                game.eventSystem.trigger(BattleEndEvent(game, event, battle))
            }

            with(event.victim) {
                hp = maxHp
                location = game.spawnPoint
                inventory.ornaments.remove(Reincarnation)
            }
        }
    }
}

object Courage : OrnamentBase() {
    init {
        eventSystem.registerListener(DamageEvent::class) { event ->
            with(event) {
                val battle = game.playingBattle ?: return@registerListener
                if (attacker is Player && battle.round == 1) {
                    val amp = if (Dexterity in game.player.inventory.ornaments) 3 else 2
                    currentDamage = max(1, amp * attacker.att - victim.def)
                }
            }
        }
    }
}


object Experience : OrnamentBase() {
    init {
        eventSystem.registerListener(PlayerExpGainEvent::class) { event ->
            with(event) { amount = amount * 120 / 100 }
        }
    }
}

object Dexterity : OrnamentBase() {
    init {
        eventSystem.registerListener(DamageEvent::class) { event ->
            with(event) {
                if (victim is Player && attacker is SpikeTrap) { currentDamage = 1 }
            }
        }
    }
}

object Hunter : OrnamentBase() {
    init {
        eventSystem.registerListener(BattleStartEvent::class) { event ->
            if (event.battle.monster.isBoss) {
                with(event.game.player) { hp = maxHp }
            }
        }
        eventSystem.registerListener(DamageEvent::class) { event ->
            val battle = event.game.playingBattle ?: return@registerListener
            if (battle.monster.isBoss && battle.round == 1 && event.attacker is Monster) {
                event.currentDamage = 0
            }
        }
    }
}

object Cursed : OrnamentBase()

class ItemBox(val item: Item) : Tile {
    override val icon = 'B'
    override val eventSystem = EventSystemImpl()

    init {
        eventSystem.registerListener(PlayerStepEvent::class) { event ->
            // let player equip item and destroy this
            val game = event.game
            game.eventSystem.trigger(
                PlayerItemObtainEvent(game, event, item)
            )
            game.world.place(event.game.player.location, Emptiness)
        }
    }
}

class Battle(val game: Game, val player: Player, val monster: Monster) {
    var isEnded = false
    var round: Int = 0

    fun play() {
        while (true) {
            if (isEnded) return
            round++
            game.eventSystem.trigger(
                DamageEvent(game, null, player, monster, max(1, player.att - monster.def))
            )
            if (isEnded) return
            game.eventSystem.trigger(
                DamageEvent(game, null, monster, player, max(1, monster.att - player.def))
            )
        }
    }
}