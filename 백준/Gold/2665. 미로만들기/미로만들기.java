import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int n;
    static boolean[][] map;	// 1=true=흰색, 0=false=검은색
    static boolean[][] enqueued;
    static ArrayDeque<Data> deque = new ArrayDeque<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(rd.readLine());

        map = new boolean[n][n];
        for (int y=0; y<n; y++) {
            char[] in = rd.readLine().toCharArray();
            for (int x=0; x<n; x++) {
                map[y][x] = in[x] == '1';
            }
        }
        enqueued = new boolean[n][n];

        deque.add(new Data(0, 0, 0));
        enqueued[0][0] = true;

        while (true) {
            Data data = deque.pollFirst();
            int y = data.y;
            int x = data.x;
            int d = data.d;
            if (y == n-1 && x == n-1) {
                System.out.println(d);
                return;
            }
            for (int i=0; i<4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                if (enqueued[ny][nx]) continue;

                if (map[ny][nx])
                    deque.offerFirst(new Data(ny, nx, d));
                else
                    deque.offerLast(new Data(ny, nx, d + 1));
                enqueued[ny][nx] = true;
            }
        }
    }

    static class Data{
        int y, x, d;
        Data(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}