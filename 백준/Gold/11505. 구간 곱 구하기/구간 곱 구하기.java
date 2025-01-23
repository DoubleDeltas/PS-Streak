import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, M, K;
    static int U;

    static final int ID = 1;
    static final int MOD = 1_000_000_007;
    static int[] segtree;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        if (N == 1) U = 1;
        else U = Integer.highestOneBit(N-1) << 1;

        segtree = new int[2*U];

        Arrays.fill(segtree, ID);
        for (int i=0; i<N; i++)
            updateSeg(i, Integer.parseInt(rd.readLine()));

        for (int i=0; i<M+K; i++) {
            tk = new StringTokenizer(rd.readLine());
            char a = tk.nextToken().charAt(0);
            int b = Integer.parseInt(tk.nextToken());
            int c = Integer.parseInt(tk.nextToken());
            if (a == '1')
                updateSeg(b-1, c);
            else
                sb.append(getProduct(0, U-1, b-1, c-1, 1)).append('\n');
        }
        System.out.println(sb);
    }

    static void updateSeg(int idx, int to) {
        int i = U+idx;
        segtree[i] = to;
        while (i > 1) {
            i /= 2;
            segtree[i] = times(segtree[i*2], segtree[i*2+1]);
        }
    }

    static int getProduct(int s, int e, int a, int b, int idx) {
        if (e < a || b < s)
            return ID;
        if (a <= s && e <= b)
            return segtree[idx];
        int m = (s + e) / 2;
        return times(getProduct(s, m, a, b, idx * 2), getProduct(m+1, e, a, b, idx * 2 + 1));
    }

    static int times(int a, int b) {
        return (int) (((long) a * b) % MOD);
    }
}