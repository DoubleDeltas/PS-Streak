fun main() {
    readln()

    val prog = Program()
    parse(prog.root)
    prog.run()
}

fun parse(parent: BlockStmt) {
    while (true) {
        val line = readln().trim()

        if (line == "STOP") return

        if (line.startsWith("REPEAT")) {
            val cnt = line.split(' ').filter { it.isNotEmpty() }[1].toInt()
            val repeat = Repeat(parent.prog, parent, cnt)
            parse(repeat)
            parent.appendStmt(repeat)
            continue
        }

        if (line.startsWith("PRINT")) {
            val idx = line.split(' ')[1][0] - 'a'
            parent.appendStmt(Print(parent.prog, parent, idx))
            continue
        }

        else {
            val words = line.split(' ')
            val lhs: Int = words[0][0] - 'a'
            val expr = Vec()
            for (i in 2..<words.size step 2) {
                val sgn = if (words[i-1] == "-") -1 else 1
                val word = words[i]
                val lastLetter = word[word.length - 1]
                val (mag, idx) =
                    if (lastLetter in 'a'..'z')
                        (if (word.length == 1) 1 else (word.substring(0, word.length - 1).toInt())) to lastLetter - 'a'
                    else word.toInt() to 26
                expr[idx] = expr[idx] mplus sgn * mag
            }
            parent.appendStmt(Assignment(parent.prog, parent, lhs, expr))
            continue
        }
    }
}

const val MOD = 10000
infix fun Int.mplus(that: Int) = ((this + MOD) + (that + MOD)) % MOD
infix fun Int.mtimes(that: Int) = ((this + MOD) * (that + MOD)) % MOD

class Vec(val vals: IntArray = IntArray(27)) {
    operator fun get(i: Int) = vals[i]
    operator fun set(i: Int, value: Int) { vals[i] = value % MOD }
}

class Mat(val cols: Array<Vec> = Array(27) { Vec() }) {
    operator fun get(i: Int, j: Int) = cols[i][j]
    operator fun set(i: Int, j: Int, value: Int) { cols[i][j] = value % MOD }

    infix fun mtimes(that: Mat) = Mat().also {
        for (i in 0..<27) {
            for (j in 0..<27) {
//                it[i, j] = (0..<27).reduce { acc, k -> acc mplus (this[i, k] mtimes that[k, j]) }
                var acc = 0
                for (k in 0..<27) {
                    acc = acc mplus (this[i, k] mtimes that[k, j])
                }
                it[i, j] = acc
            }
        }
    }

    infix fun mtimes(that: Vec) = Vec().also {
        for (i in 0..<27) {
//            it[i] = (0..<27).reduce { acc, k -> acc mplus (this[i, k] mtimes that[k]) }   // why not working?
            var acc = 0
            for (k in 0..<27) {
                acc = acc mplus (this[i, k] mtimes that[k])
            }
            it[i] = acc
        }
    }

    infix fun mpow(n: Int): Mat {
        if (n==0)
            return eye()
        if (n==1)
            return this

        val powHalf = mpow(n/2)
        var result = powHalf mtimes powHalf
        if (n % 2 == 1)
           result = this mtimes result
        return result
    }

    companion object {
        fun eye() = Mat()
            .also { mat -> (0..<27).forEach { k -> mat[k, k] = 1 } }
        fun assigner(idx: Int, expr: Vec) = eye()
            .also { mat -> (0..<27).forEach { j -> mat[idx, j] = expr[j] } }
    }
}

interface Stmt {
    val prog: Program
    val parent: Stmt?
    val pure: Boolean
    fun run()
    fun evaluate()
    fun assigner(): Mat
}

interface BlockStmt : Stmt {
    val stmts: List<Stmt>
    fun appendStmt(stmt: Stmt)
}

abstract class StmtBase(
    override val prog: Program,
    override val parent: Stmt?
): Stmt {
    override fun run() = evaluate()
}

abstract class BlockStmtBase(prog: Program, parent: Stmt?) : StmtBase(prog, parent), BlockStmt {
    override val stmts: MutableList<Stmt> = mutableListOf()
    override var pure = true
    override fun appendStmt(stmt: Stmt) {
        stmts += stmt
        if (!stmt.pure) pure = false
    }

    override fun run() {
        if (pure) {
            prog.mem = assigner() mtimes prog.mem
        }
        else {
            evaluate()
        }
    }

    override fun assigner(): Mat {
        if (!pure) throw IllegalStateException()
        return stmts.map { it.assigner() }.reduce { acc, mat -> mat mtimes acc }
    }
}

class Program {
    var mem = Vec().also { it[26] = 1 }
    val root = Root(this)
    fun run() {
        root.run()
    }
}

class Root(prog: Program) : BlockStmtBase(prog, null) {
    override fun evaluate() {
        stmts.forEach { it.run() }
    }
}

class Assignment(prog: Program, parent: Stmt?, val lhs: Int, val expr: Vec) : StmtBase(prog, parent) {
    override val pure: Boolean = true

    override fun evaluate() {
        prog.mem = assigner() mtimes prog.mem
    }

    override fun assigner(): Mat = Mat.assigner(lhs, expr)
}

class Print(prog: Program, parent: Stmt?, val idx: Int) : StmtBase(prog, parent) {
    override val pure: Boolean = false

    override fun evaluate() {
        println("${'a' + idx} = ${prog.mem[idx]}")
    }

    override fun assigner(): Mat = throw IllegalStateException()
}

class Repeat(prog: Program, parent: Stmt?, val cnt: Int): BlockStmtBase(prog, parent) {
    override fun evaluate() {
        repeat(cnt) { stmts.forEach { stmt -> stmt.run() } }
    }

    override fun assigner(): Mat = super.assigner() mpow cnt
}

