import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N;
    static int[][] map;
    static int py, px; // professor y, x
    static int sy, sx; // seong-gyu y, x

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());

        map = new int[N][N];
        for (int y=0; y<N; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<N; x++) {
                map[y][x] = Integer.parseInt(tk.nextToken());
                switch (map[y][x]) {
                    case 5:
                        py = y;
                        px = x;
                        break;
                    case 2:
                        sy = y;
                        sx = x;
                        break;
                }
            }
        }

        System.out.println(isFurtherThan5(px, py, sx, sy) && moreThan3InBox(px, py, sx, sy) ? 1 : 0);
    }

    static boolean isFurtherThan5(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return dx * dx + dy * dy >= 5 * 5;  // by Euclidean distance
    }

    static boolean moreThan3InBox(int x1, int y1, int x2, int y2) {
        int cnt = 0;
        // WLOG x1 < x2 and y1 < y2
        if (x2 < x1) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y2 < y1) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        for (int y=y1; y<=y2; y++) {
            for (int x=x1; x<=x2; x++) {
                if (map[y][x] == 1) {
                    cnt++;
                }
            }
        }
        return cnt >= 3;
    }
}