import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int M, N;
    static int[][] map, in;

    static int[][] memo;    // memo[y][x] = (0,0)~(y,x)까지 가기 위한 경우의 수

    static Queue<Coord> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        M = Integer.parseInt(tk.nextToken());
        N = Integer.parseInt(tk.nextToken());

        map = new int[M][N];
        in = new int[M][N];
        memo = new int[M][N];

        for (int y=0; y<M; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<N; x++) {
                map[y][x] = Integer.parseInt(tk.nextToken());
            }
        }

        for (int y=0; y<M; y++) {
            for (int x=0; x<N; x++) {
                for (int d=0; d<4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
                    if (map[ny][nx] > map[y][x])
                        in[y][x]++;
                }
            }
        }

        memo[0][0] = 1;
        
        for (int y=0; y<M; y++)
            for (int x=0; x<N; x++)
                if (in[y][x] == 0)
                    q.offer(new Coord(y, x));

        while (!q.isEmpty()) {
            Coord coord = q.poll();
            int y = coord.y;
            int x = coord.x;

            for (int d=0; d<4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
                if (map[y][x] > map[ny][nx]) {
                    memo[ny][nx] += memo[y][x];
                    in[ny][nx]--;
                    if (in[ny][nx] == 0)
                        q.offer(new Coord(ny, nx));
                }
            }
        }

        System.out.println(memo[M-1][N-1]);
    }

    static class Coord {
        int y, x;
        Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
