import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static final int RIPEN = 1, UNRIPEN = 0, EMPTY = -1;

    static int ans;

    static final int[] dz = {1, -1, 0, 0, 0, 0};
    static final int[] dx = {0, 0, 1, -1, 0, 0};
    static final int[] dy = {0, 0, 0, 0, 1, -1};

    static int M, N, H;
    static int[][][] map;
    static int cnt, tomato;

    static Queue<Data> q = new ArrayDeque<>();
    static boolean[][][] enqueued;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        M = Integer.parseInt(tk.nextToken());   // x: 열
        N = Integer.parseInt(tk.nextToken());   // y: 행
        H = Integer.parseInt(tk.nextToken());   // z: 높이

        map = new int[H][N][M];
        enqueued = new boolean[H][N][M];

        for (int z=0; z<H; z++) {
            for (int y=0; y<N; y++) {
                tk = new StringTokenizer(rd.readLine());
                for (int x=0; x<M; x++) {
                    map[z][y][x] = Integer.parseInt(tk.nextToken());
                    if (map[z][y][x] == RIPEN) {
                        q.offer(new Data(z, y, x));
                        enqueued[z][y][x] = true;
                        cnt++;
                    }
                    if (map[z][y][x] != EMPTY)
                        tomato++;
                }
            }
        }

        while (true) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Data data = q.poll();
                int z = data.z;
                int y = data.y;
                int x = data.x;
                for (int d=0; d<6; d++) {
                    int nz = z + dz[d];
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (map[nz][ny][nx] == EMPTY) continue;
                    if (enqueued[nz][ny][nx]) continue;

                    q.offer(new Data(nz, ny, nx));
                    enqueued[nz][ny][nx] = true;
                    map[nz][ny][nx] = RIPEN;
                    cnt++;
                }
            }
            if (q.isEmpty()) break;
            ans++;
        }

        if (cnt < tomato)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    static class Data {
        int z, y, x;
        Data(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
}
