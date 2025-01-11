import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N, M, K;

    static int[][] S;   // strength

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static PriorityQueue<Coord> q = new PriorityQueue<>();
    static boolean[][] enqueued;

    static int mined = 0;
    static int ans = 1;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        S = new int[N][M];
        for (int y=0; y<N; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<M; x++) {
                S[y][x] = Integer.parseInt(tk.nextToken());
            }
        }
        enqueued = new boolean[N][M];

        // y = 0
        for (int x=0; x<M; x++) enqueue(0, x);

        // x = 0 or M-1, y > 0
        for (int y=1; y<N; y++) {
            enqueue(y, 0);
            enqueue(y, M-1);
        }

        while (mined < K) {
            Coord coord = q.poll();
            int y = coord.y;
            int x = coord.x;
            ans = Math.max(ans, S[y][x]);
            mined++;

            for (int d=0; d<4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (enqueued[ny][nx]) continue;

                enqueue(ny, nx);
            }
        }

        System.out.println(ans);
    }

    static void enqueue(int y, int x) {
        q.offer(new Coord(y, x));
        enqueued[y][x] = true;
    }

    static class Coord implements Comparable<Coord> {
        int y, x;

        Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Coord o) {
            return Integer.compare(S[this.y][this.x], S[o.y][o.x]);
        }
    }
}