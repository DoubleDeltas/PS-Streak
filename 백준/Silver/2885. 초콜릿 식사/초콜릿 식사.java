import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

    static int K, len, split;

    public static void main(String[] args) throws Exception {
        K = Integer.parseInt(rd.readLine());

        len = smallestPow2(K);
        // Integer.numberOfTrailingZeros(2^k) = lg(2^k) = k for 2^k.
        split = Integer.numberOfTrailingZeros(len / Integer.lowestOneBit(K));

        System.out.println(len + " " + split);
    }

    // find smallest 2^k s.t 2^k >= x.
    static int smallestPow2(int x) {
        if (x == 1) return 1;
        return Integer.highestOneBit(x - 1) << 1;
    }
}