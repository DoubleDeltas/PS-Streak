import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N;
    static int[] l;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());

        l = new int[N*N];
        for (int i=0; i<N; i++) {
            tk = new StringTokenizer(rd.readLine());
            for (int j=0; j<N; j++) {
                l[i*N+j] = Integer.parseInt(tk.nextToken());
            }
        }

        Arrays.sort(l);
        System.out.println(l[N*N-N]);
    }
}
