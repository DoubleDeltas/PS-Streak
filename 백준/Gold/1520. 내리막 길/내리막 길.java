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

    static int[][] memo;    // memo[y][x] = (0,0)~(y,x)까지 가기 위한 경우의 수
    static boolean[][][] enqueued;  // enqueued[y][x][d]: (y, x)에 d 방향으로 들어온 적이 있음

    static PriorityQueue<Coord> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        M = Integer.parseInt(tk.nextToken());
        N = Integer.parseInt(tk.nextToken());

        map = new int[M][N];
        enqueued = new boolean[M][N][4];
        memo = new int[M][N];

        for (int y=0; y<M; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<N; x++) {
                map[y][x] = Integer.parseInt(tk.nextToken());
            }
        }

        memo[0][0] = 1;
        pq.offer(new Coord(0, 0));

        while (!pq.isEmpty()) {
            Coord coord = pq.poll();
            int y = coord.y;
            int x = coord.x;

            for (int d=0; d<4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
                if (enqueued[y][x][d]) continue;
                if (map[y][x] <= map[ny][nx]) continue;

                memo[ny][nx] += memo[y][x];
                enqueued[y][x][d] = true;
                pq.offer(new Coord(ny, nx));
            }
        }

        System.out.println(memo[M-1][N-1]);
    }

    static class Coord implements Comparable<Coord> {
        int y, x;
        Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Coord o) {
            return -Integer.compare(map[y][x], map[o.y][o.x]);
        }
    }
}
