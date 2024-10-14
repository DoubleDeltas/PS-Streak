import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N, M;
    static char[][] map;    // 구슬은 제외

    static Marble R, B, r, b;

    static int ans = Integer.MAX_VALUE;
    static int ansmask;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int RED = 1, BLUE = -1;

    static Marble[] m = new Marble[2];

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        map = new char[N][M];
        for (int y=0; y<N; y++) {
            char[] s = rd.readLine().toCharArray();
            for (int x=0; x<M; x++) {
                if (s[x] == 'R') {
                    R = new Marble(y, x, RED);
                    continue;
                }
                else if (s[x] == 'B') {
                    B = new Marble(y, x, BLUE);
                    continue;
                }
                map[y][x] = s[x];
            }
        }

        r = new Marble(0, 0, RED);
        b = new Marble(0, 0, BLUE);
        for (int mask=0; mask < (1 << 20); mask++) {
            r.y = R.y; r.x = R.x;
            b.y = B.y; b.x = B.x;
            for (int i=0; i < 10; i++) {
                int dir = (mask >> (2*i)) & 0b11;
                int result = tilt(dir); // 1: clear, 0: no end, -1: fail
                if (result == RED) {
                    if (i+1 < ans) {
                        ans = i+1;
                        ansmask = mask;
                    }
                    break;
                }
                else if (result == BLUE) {
                    break;
                }
            }
        }
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<ans; i++) {
            sb.append("DURL".charAt((ansmask >> (2*i)) & 0b11));
        }
        System.out.println(sb);
    }

    static class Marble {
        int y, x, color;
        Marble(int y, int x, int color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }

    // return: is game clear?
    static int tilt(int dir) {
        if (
                (dx[dir] < 0) && (r.x < b.x)
                || (dx[dir] > 0) && (b.x < r.x)
                || (dy[dir] < 0) && (r.y < b.y)
                || (dy[dir] > 0) && (b.y < r.y)
        ) {
            m[0] = r; m[1] = b;
        }
        else {
            m[0] = b; m[1] = r;
        }

        boolean red = false;
        boolean blue = false;
        for (int i=0; i<2; i++) {
            while (true) {
                int ny = m[i].y + dy[dir];
                int nx = m[i].x + dx[dir];
                if (map[ny][nx] == 'O') {
                    if (m[i].color == RED)
                        red = true;
                    if (m[i].color == BLUE)
                        blue = true;
                }
                if (map[ny][nx] == '#')
                    break;
                if (ny == m[1-i].y && nx == m[1-i].x)
                    break;
                m[i].y = ny;
                m[i].x = nx;
            }
        }
        if (blue) return BLUE;
        if (red) return RED;
        return 0;
    }
}
