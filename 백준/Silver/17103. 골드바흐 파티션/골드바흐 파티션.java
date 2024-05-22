import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int[] primes = new int[500000];
    static int primeCnt = 0;
    static boolean[] isPrime = new boolean[1000001];

    static int T;

    public static void main(String[] args) throws Exception {
        // calc prime to 1000000
        primes[primeCnt++] = 2;
        isPrime[2]=true;
        I_LOOP: for (int i=3; i<1000000; i++) {
            int j=0;
            while (primes[j]*primes[j] <= i) {
                if (i % primes[j] == 0)
                    continue I_LOOP;
                j++;
            }
            primes[primeCnt++] = i;
            isPrime[i] = true;
        }

        T = Integer.parseInt(rd.readLine());
        for (int t=0; t<T; t++) {
            int ans = 0;
            int n = Integer.parseInt(rd.readLine());
            int i=0;
            while (primes[i] <= n/2) {
                if (isPrime[n - primes[i]]) ans++;
                i++;
            }

            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}