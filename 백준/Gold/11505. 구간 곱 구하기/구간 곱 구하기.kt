const val MOD = 1_000_000_007

fun main() {
    val sb: StringBuilder = StringBuilder()

    val (N, M, K) = readLine()!!.split(" ").map { it.toInt() }

    var segtree: SegmentTree<Int> = ObjectSegmentTree(N, {a, b -> ((a.toLong() * b) % MOD).toInt()}, 1)


    for (i: Int in 0 until N)
        segtree.update(i, readln().toInt())

    for (i: Int in 0 until M+K) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        if (a == 1)
            segtree.update(b-1, c);
        else {
            val result: Int = segtree.query(b-1, c-1);
            sb.append(result).append('\n')
        }
    }

    print(sb)
}

interface SegmentTree<T> {
    fun update(idx: Int, newValue: T)
    fun query(start: Int, end: Int): T
}

class ObjectSegmentTree<T>(dataSize: Int, private val operator: (T, T) -> T, private val identity: T)
    : SegmentTree<T>
{
    private val tree: Array<Any?>
    private val u: Int = if (dataSize == 1) 1 else Integer.highestOneBit(dataSize - 1) shl 1

    init {
        tree = Array<Any?>(2*u) { identity }
    }

    override fun update(idx: Int, newValue: T) {
        var i = u + idx
        tree[i] = newValue
        while (i > 1) {
            i /= 2
            tree[i] = operator(get(i * 2), get(i * 2 + 1))
        }
    }

    override fun query(start: Int, end: Int): T {
        return search(0, u - 1, start, end, 1)
    }

    private fun search(start: Int, end: Int, a: Int, b: Int, idx: Int): T {
        if (end < a || b < start) return identity
        if (a <= start && end <= b) return get(idx)
        val mid = (start + end) / 2
        return operator(
            search(start, mid, a, b, idx * 2),
            search(mid + 1, end, a, b, idx * 2 + 1)
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun get(i: Int): T {
        return tree[i] as T
    }
}