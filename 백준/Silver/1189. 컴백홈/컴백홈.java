import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int R, C, K;
    static boolean[][] map;  // 'T'인지 여부

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static Queue<Node> q = new ArrayDeque<>();
    static int ans;

    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        R = Integer.parseInt(tk.nextToken());
        C = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        map = new boolean[R][C];
        for (int y=0; y<R; y++) {
            char[] s = rd.readLine().toCharArray();
            for (int x=0; x<C; x++) {
                map[y][x] = s[x] == 'T';
            }
        }

        // bfs until depth K-1
        q.add(new Node(R-1, 0, 0x0));

        for (int depth=1; depth<=K-1; depth++) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Node node = q.remove();
                int y = node.y;
                int x = node.x;
                int flags = node.flags;

                for (int d=0; d<4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                    if (map[ny][nx]) continue;
                    if (isVisited(flags, ny, nx)) continue;

                    q.add(new Node(ny, nx, flags | (1 << toFlagIdx(y, x))));
                }
            }
        }

        // depth K일 때 좌표가 y=0, x=C-1인 노드 개수 세기
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.y == 0 && node.x == C-1) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static class Node {
        int y, x, flags;
        Node(int y, int x, int flags) {
            this.y = y;
            this.x = x;
            this.flags = flags;
        }
    }

    static int toFlagIdx(int y, int x) {
        return y * C + x;
    }

    static boolean isVisited(int flags, int y, int x) {
        return ((flags >> toFlagIdx(y, x)) & 1) == 1;
    }
}