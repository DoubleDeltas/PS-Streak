import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int N, M;
    static boolean[][] map;
    static boolean[][] visited, visited2;
    static int[][] size, ans, no;
    static Set<Integer> set = new HashSet<>(4);

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        map = new boolean[N+1][M+1];
        for (int y=1; y<=N; y++) {
            char[] s = rd.readLine().toCharArray();
            for (int x=1; x<=M; x++) {
                map[y][x] = s[x-1] == '1';
            }
        }
        visited = new boolean[N+1][M+1];
        visited2 = new boolean[N+1][M+1];
        size = new int[N+1][M+1];
        ans = new int[N+1][M+1];
        no = new int[N+1][M+1];

        int n = 0;
        for (int y=1; y<=N; y++) {
            for (int x=1; x<=M; x++) {
                if (map[y][x] || visited[y][x]) continue;
                int value = dfsCheck(y, x);
                n++;
                dfsSet(y, x, value, n);
            }
        }

        for (int y=1; y<=N; y++) {
            for (int x=1; x<=M; x++) {
                if (!map[y][x]) continue;
                ans[y][x] = 1;
                set.clear();
                for (int d=0; d<4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny <= 0 || ny > N || nx <= 0 || nx > M) continue;
                    if (map[ny][nx]) continue;
                    if (set.contains(no[ny][nx])) continue;
                    ans[y][x] += size[ny][nx];
                    set.add(no[ny][nx]);
                }
                ans[y][x] %= 10;
            }
        }

        for (int y=1; y<=N; y++) {
            for (int x=1; x<=M; x++) {
                if (map[y][x])
                    sb.append(ans[y][x]);
                else
                    sb.append(0);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int dfsCheck(int y, int x) {
        if (y <= 0 || y > N || x <= 0 || x > M) return 0;
        if (visited[y][x]) return 0;
        if (map[y][x]) return 0;
        visited[y][x] = true;
        int result = 1;
        for (int d=0; d<4; d++) {
            result += dfsCheck(y + dy[d], x + dx[d]);
        }
        return result % 10;
    }

    static void dfsSet(int y, int x, int value, int n) {
        if (y <= 0 || y > N || x <= 0 || x > M) return;
        if (visited2[y][x]) return;
        if (map[y][x]) return;
        visited2[y][x] = true;
        size[y][x] = value;
        no[y][x] = n;
        for (int d=0; d<4; d++) {
            dfsSet(y + dy[d], x + dx[d], value, n);
        }
    }
}