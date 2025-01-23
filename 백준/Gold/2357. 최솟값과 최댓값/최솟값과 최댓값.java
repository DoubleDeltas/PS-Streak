import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static final int MIN_ID = Integer.MAX_VALUE;
    static final int MAX_ID = Integer.MIN_VALUE;
    static int[] mintree, maxtree;

    static int N, M, U;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        U = getNearest2sPower(N);

        mintree = new int[2*U];
        maxtree = new int[2*U];

        for (int i=1; i<U; i++)
            mintree[i] = MIN_ID;
        for (int i=U; i<U+N; i++)
            mintree[i] = Integer.parseInt(rd.readLine());
        for (int i=U+N; i<2*U; i++)
            mintree[i] = MIN_ID;


        for (int i=1; i<U; i++)
            maxtree[i] = MAX_ID;
        for (int i=U; i<U+N; i++)
            maxtree[i] = mintree[i];
        for (int i=U+N; i<2*U; i++)
            maxtree[i] = MAX_ID;

        for (int i=2*U-1; i>=2; i--) {
            mintree[i / 2] = Math.min(mintree[i / 2], mintree[i]);
            maxtree[i / 2] = Math.max(maxtree[i / 2], maxtree[i]);
        }

        for (int i=0; i<M; i++) {
            tk = new StringTokenizer(rd.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            sb
                    .append(getMin(0, U-1, a-1, b-1, 1))
                    .append(' ')
                    .append(getMax(0, U-1, a-1, b-1, 1))
                    .append('\n');
        }

        System.out.println(sb);
    }

    static int getNearest2sPower(int n) {
        if (n == 1) return 1;
        return Integer.highestOneBit(n-1) << 1;
    }

    static int getMin(int s, int e, int a, int b, int idx) {
        if (e < a || b < s)
            return MIN_ID;
        if (a <= s && e <= b)
            return mintree[idx];
        int m = (s + e) / 2;
        return Math.min(getMin(s, m, a, b, idx * 2), getMin(m+1, e, a, b, idx * 2 + 1));
    }

    static int getMax(int s, int e, int a, int b, int idx) {
        if (e < a || b < s)
            return MAX_ID;
        if (a <= s && e <= b)
            return maxtree[idx];
        int m = (s + e) / 2;
        return Math.max(getMax(s, m, a, b, idx * 2), getMax(m+1, e, a, b, idx * 2 + 1));
    }
}