import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N, M, K, ans;
    static boolean[][] map;
    static Queue<Data> q = new ArrayDeque<>();
    static boolean[][][][] enqueued;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        map = new boolean[N+1][M+1];
        for (int y=1; y<=N; y++) {
            char[] s = rd.readLine().toCharArray();
            for (int x=1; x<=M; x++) {
                map[y][x] = s[x-1] == '1';
            }
        }
        enqueued = new boolean[2][K+1][N+1][M+1];

        ans = Integer.MAX_VALUE;

        // bfs
        enqueued[1][K][1][1] = true;
        q.offer(new Data(1, 1, 1, K));
        while (!q.isEmpty()) {
            Data data = q.poll();
            int y = data.y;
            int x = data.x;
            int cnt = data.cnt;
            int k = data.k;

            if (y == N && x == M) {
                ans = Math.min(ans, cnt);
            }

            for (int d=0; d<4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny <= 0 || ny > N || nx <= 0 || nx > M) continue;
                if (map[ny][nx] && k > 0 && cnt % 2 == 1) {
                    if (enqueued[0][k-1][ny][nx]) continue;
                    enqueued[0][k-1][ny][nx] = true;
                    q.offer(new Data(ny, nx, cnt + 1, k-1));
                }
                if (!map[ny][nx]) {
                    if (enqueued[1-cnt%2][k][ny][nx]) continue;
                    enqueued[1-cnt%2][k][ny][nx] = true;
                    q.offer(new Data(ny, nx, cnt + 1, k));
                }
            }
            if (cnt % 2 == 0) {
                if (enqueued[1][k][y][x]) continue;
                enqueued[1][k][y][x] = true;
                q.offer(new Data(y, x, cnt + 1, k));
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static class Data {
        int y, x, cnt, k;
        Data(int y, int x, int cnt, int k) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.k = k;
        }
    }
}