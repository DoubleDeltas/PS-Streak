import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int m, Q, n, x, ans;

    static int[][] st;    // sparce table

    public static void main(String[] args) throws Exception {
        m = Integer.parseInt(rd.readLine());

        // sparce table
        // st[0][x] = f_1(x), st[1][x] = f_2(x), st[2][x] = f_4(x), ... , st[b][x] = f_{2^b}(x)
        // f_n(x) = f_({...d_2d_1d_0}_2)(x) = (...(f_{2^d_2}(f_{2^d_1}(f_{2^d_0}(x)))...)
        // Note: 2^19 > 500_000 = max(n)
        st = new int[19][m+1];

        tk = new StringTokenizer(rd.readLine());
        for (int i=1; i<=m; i++) {
            st[0][i] = Integer.parseInt(tk.nextToken());
        }

        for (int k=1; k<=18; k++) {
            for (int i=1; i<=m; i++) {
                st[k][i] = st[k-1][st[k-1][i]]; // f_n(x) = f_{n/2}(f_{n/2}(x))
            }
        }

        Q = Integer.parseInt(rd.readLine());
        for (int i=0; i<Q; i++) {
            tk = new StringTokenizer(rd.readLine());
            n = Integer.parseInt(tk.nextToken());
            x = Integer.parseInt(tk.nextToken());

            ans = x;
            for (int j=0; j<=18; j++) {
                if (((n >> j) & 1) == 1) {
                    ans = st[j][ans];
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}