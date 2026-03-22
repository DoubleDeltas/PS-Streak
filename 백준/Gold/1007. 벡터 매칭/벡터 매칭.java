import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int T;
    static int N;
    static int[] X, Y;

    static double ans;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(rd.readLine());
        for (int t=0; t<T; t++) {
            ans = Double.MAX_VALUE;

            N = Integer.parseInt(rd.readLine());

            X = new int[N];
            Y = new int[N];

            for (int i=0; i<N; i++) {
                tk = new StringTokenizer(rd.readLine());
                X[i] = Integer.parseInt(tk.nextToken());
                Y[i] = Integer.parseInt(tk.nextToken());
            }

            for (int mask = 0; mask < (1 << N); mask++) {
                if (Integer.bitCount(mask) != N/2) continue;

                // sum vector
                double sx = 0;
                double sy = 0;
                for (int i=0; i<N; i++) {
                    if (((mask >> i) & 1) == 1) {
                        // (X[i], Y[i]) to be some vector's start point
                        sx -= X[i];
                        sy -= Y[i];
                    }
                    else {
                        // endpoint
                        sx += X[i];
                        sy += Y[i];
                    }
                }
                ans = Math.min(ans, Math.sqrt(sx*sx + sy*sy));
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
