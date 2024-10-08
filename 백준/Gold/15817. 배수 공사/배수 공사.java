import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, x;
    static int[] L, C;

    static int[][] memo;    // memo[i][j] = i번 파이프까지 봤을 때 길이가 j가 되는 경우의 수

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        x = Integer.parseInt(tk.nextToken());

        L = new int[N+1];
        C = new int[N+1];
        memo = new int[N+1][x+1];

        for (int i=1; i<=N; i++) {
            tk = new StringTokenizer(rd.readLine());
            L[i] = Integer.parseInt(tk.nextToken());
            C[i] = Integer.parseInt(tk.nextToken());
        }

        // process
        memo[0][0] = 1;

        for (int i=1; i<=N; i++) {                  // item idx
            for (int j=0; j<=x; j++) {              // current total length
                for (int k=0; k <= C[i]; k++) {     // number of pipe i to contain
                    int lk = L[i] * k;
                    if (j >= lk) {
                        memo[i][j] += memo[i-1][j-lk];
                    }
                }
            }
        }

        System.out.println(memo[N][x]);
    }
}
