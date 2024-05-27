import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, K;
    static int[][] T;

    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    static int sum;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        T = new int[N][N];
        for (int i=0; i<N; i++) {
            tk = new StringTokenizer(rd.readLine());
            for (int j=0; j<N; j++) {
                T[i][j] = Integer.parseInt(tk.nextToken());
            }
        }
        visited = new boolean[N];

        // floyd warshall
        for (int k=0; k<N; k++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
                }
            }
        }

        // 이제부터 T는 경유 상관 없는 최단시간
        sum = 0;
        dfs(K, 0);
        System.out.println(ans);
    }

    static void dfs(int v, int depth) {
        if (depth == N-1) {
            ans = Math.min(ans, sum);
            return;
        }

        visited[v] = true;
        for (int i=0; i<N; i++) {
            if (visited[i]) continue;
            sum += T[v][i];
            dfs(i, depth + 1);
            sum -= T[v][i];
        }
        visited[v] = false;
    }
}