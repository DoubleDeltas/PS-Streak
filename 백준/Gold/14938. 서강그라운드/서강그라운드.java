import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int n, m, r, t[], ans = 0;
    static boolean[] visited;

    static int[][] dist;

    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        r = Integer.parseInt(tk.nextToken());

        t = new int[n+1];
        tk = new StringTokenizer(rd.readLine());
        for (int i=1; i<=n; i++)
            t[i] = Integer.parseInt(tk.nextToken());

        dist = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                dist[i][j] = i == j ? 0 : INF;
            }
        }

        for (int i=0; i<r; i++) {
            tk = new StringTokenizer(rd.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());
            int l = Integer.parseInt(tk.nextToken());
            dist[a][b] = dist[b][a] = Math.min(dist[a][b], l);
        }

        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i=1; i<=n; i++) {
            int sum = 0;
            for (int j=1; j<=n; j++) {
                if (dist[i][j] <= m)
                    sum += t[j];
            }
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}