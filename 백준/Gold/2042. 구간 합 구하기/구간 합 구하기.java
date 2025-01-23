import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, M, K;
    static int U;

    static final int ID = 0;
    static long[] segtree;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        if (N == 1) U = 1;
        else U = Integer.highestOneBit(N-1) << 1;

        segtree = new long[2*U];

        for (int i=0; i<N; i++)
            update(i, Long.parseLong(rd.readLine()));

        for (int i=0; i<M+K; i++) {
            tk = new StringTokenizer(rd.readLine());
            char a = tk.nextToken().charAt(0);
            int b = Integer.parseInt(tk.nextToken());
            long c = Long.parseLong(tk.nextToken());
            if (a == '1')
                update(b-1, c);
            else
                sb.append(getSum(0, U-1, b-1, (int) c-1, 1)).append('\n');
        }

        System.out.println(sb);
    }

    static void update(int idx, long to) {
        int i = U+idx;
        long prev = segtree[i];
        segtree[i] = to;
        while (i > 1) {
            segtree[i/2] = segtree[i/2] - prev + to;
            i /= 2;
        }
    }

    static long getSum(int s, int e, int a, int b, int idx) {
        if (e < a || b < s)
            return ID;
        if (a <= s && e <= b)
            return segtree[idx];
        int m = (s + e) / 2;
        return getSum(s, m, a, b, idx * 2) + getSum(m+1, e, a, b, idx * 2 + 1);
    }
}