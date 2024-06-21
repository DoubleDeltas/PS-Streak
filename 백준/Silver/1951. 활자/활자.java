import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

    static final int MOD = 1234567;
    static int N;

    static int ans = 0;

    static final int[] TEN_POW = {
            1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000
    };

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());

        // ans = sum{1~9} 1 + sum{10~90} 2 + ...
        ans = add(ans, clamp(0, 9, N));
        for (int i=2; i<=9; i++) {                  // i =  2,   3,    4, ...
            int max = TEN_POW[i] - TEN_POW[i-1];    //     90, 900, 9000, ...
            int x = N - (TEN_POW[i-1] - 1);         // N - {9,  99,  999, ...}
            ans = add(ans, times(clamp(0, max, x), i));
        }
        ans = add(ans, times(Math.max(0, N - 999_999_999), 10));

        System.out.println(ans);
    }

    static int add(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }

    static int times(int a, int b) {
        return (int) (((long) a * b) % MOD); // assumed to may overflow
    }

    static int clamp(int min, int max, int x) {
        return Math.max(min, Math.min(max, x));
    }
}