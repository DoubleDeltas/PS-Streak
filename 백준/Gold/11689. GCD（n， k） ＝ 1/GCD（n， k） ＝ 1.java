import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

    static final int M = 1_000_000;   // sqrt of max N(10^12)
    static boolean[] isPrime = new boolean[M+1];
    static long n;

    public static void main(String[] args) throws Exception {
        // use Eratosthenes' sieve
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i=2; i <= M; i++) {
            if (isPrime[i]) {
                for (int j=i*2; j <= M; j += i) isPrime[j] = false;
            }
        }

        n = Long.parseLong(rd.readLine());
        System.out.println(phi(n));
    }

    // 1. for prime number p: phi(p^n) = p^n - p^(n-1) = p^(n-1) * (p-1)
    // 2. for coprime natural numbers a, b: phi(ab) = phi(a) * phi(b)
    static long phi(long n) {
        if (n == 1) return 1;

        long ret = 1;
        long m = n;
        boolean isNPrime = true;
        for (int i=2; i<=Math.min(n, M); i++) {
            if (!isPrime[i]) continue;
            int x = 0;
            while (m % i == 0) {
                x++;
                m /= i;
                isNPrime = false;
            }
            if (x > 0) {
                // multiply p^(x-1) * (p - 1)
                for (int j = 0; j < x-1; j++) ret *= i;
                ret *= i - 1;
            }
        }
        if (isNPrime)
            return n-1;
        return ret * phi(m);
    }
}