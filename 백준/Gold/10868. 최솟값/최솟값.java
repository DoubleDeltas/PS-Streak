import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static final int ID = Integer.MAX_VALUE;
    static int[] segtree;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());

        int N = getNearest2sPower(n);

        segtree = new int[2*N];

        for (int i=1; i<N; i++)
            segtree[i] = ID;
        for (int i=N; i<N+n; i++)
            segtree[i] = Integer.parseInt(rd.readLine());
        for (int i=N+n; i<2*N; i++)
            segtree[i] = ID;


        for (int i=2*N-1; i>=2; i--)
            segtree[i/2] = Math.min(segtree[i/2], segtree[i]);

        for (int i=0; i<m; i++) {
            tk = new StringTokenizer(rd.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            sb.append(getMin(0, N-1, a-1, b-1, 1)).append('\n');
        }

        System.out.println(sb);
    }

    static int getNearest2sPower(int n) {
        if (n == 1) return 1;
        return Integer.highestOneBit(n-1) << 1;
    }

    static int getMin(int s, int e, int a, int b, int idx) {
        if (e < a || b < s)
            return ID;
        if (a <= s && e <= b)
            return segtree[idx];
        int m = (s + e) / 2;
        return Math.min(getMin(s, m, a, b, idx * 2), getMin(m+1, e, a, b, idx * 2 + 1));
    }
}