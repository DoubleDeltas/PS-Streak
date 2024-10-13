import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, K;
    static int[][] cur, ini, ori;
    static int[] bullets;

    static int[] tgt;
    static int ans = 0;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());
        K = Integer.parseInt(rd.readLine());

        ori = new int[N][N];
        cur = new int[N][N];
        ini = new int[N][N];
        for (int y=0; y<N; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<N; x++) {
                ori[y][x] = Integer.parseInt(tk.nextToken());
            }
        }

        bullets = new int[K];
        tk = new StringTokenizer(rd.readLine());
        for (int i=0; i<K; i++) {
            bullets[i] = Integer.parseInt(tk.nextToken());
        }

        tgt = new int[K];
        perm(0);

        System.out.println(ans);
    }

    static void perm(int tgtIdx) {
        if (tgtIdx == K) {
            ans = Math.max(ans, simulate());
            return;
        }
        for (int i=0; i<N; i++) {
            tgt[tgtIdx] = i;
            perm(tgtIdx + 1);
        }
    }

    static int simulate() {
        // copy board
        for (int y=0; y<N; y++) {
            System.arraycopy(ori[y], 0, cur[y], 0, N);
            System.arraycopy(ori[y], 0, ini[y], 0, N);
        }

        int score = 0;
        for (int i=0; i<K; i++) {
            for (int x=0; x<N; x++) {
                int y = tgt[i];
                if (ini[y][x] == 0)
                    continue;
                score += hit(y, x, bullets[i]);
                break;
            }
        }

        return score;
    }

    static int hit(int y, int x, int dmg) {
        if (ini[y][x] == 0)
            return 0;

        int scorePlus = ini[y][x];
        if (ini[y][x] >= 10) {
            // bonus target
            ini[y][x] = cur[y][x] = 0;
            return scorePlus;
        }
        else {
            cur[y][x] -= dmg;
            if (cur[y][x] <= 0) {
                int motherHp = ini[y][x];
                ini[y][x] = 0;  // die
                for (int d=0; d<4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if (ini[ny][nx] != 0) continue; // 그 자리에 과녁이 있음
                    ini[ny][nx] = cur[ny][nx] = motherHp / 4;
                }
                return scorePlus;
            }
        }
        return 0;
    }

}