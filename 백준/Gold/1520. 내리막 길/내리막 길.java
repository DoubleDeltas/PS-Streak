import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int M, N;
    static int[][] map;

    static int[][] memo;    // memo[y][x] = (y,x)~(M-1,N-1)까지 가기 위한 경우의 수

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        M = Integer.parseInt(tk.nextToken());
        N = Integer.parseInt(tk.nextToken());

        map = new int[M][N];
        memo = new int[M][N];

        for (int y=0; y<M; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<N; x++) {
                map[y][x] = Integer.parseInt(tk.nextToken());
                memo[y][x] = -1;
            }
        }

        memo[M-1][N-1] = 1;
        System.out.println(dfs(0, 0));
    }

    static int dfs(int y, int x) {
        if (memo[y][x] != -1) return memo[y][x];
        int sum = 0;
        for (int d=0; d<4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
            if (map[y][x] <= map[ny][nx]) continue;
            sum += dfs(ny, nx);
        }
        return memo[y][x] = sum;
    }
}
