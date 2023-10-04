import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N, M, V, E, ans;
    static int[][] map;    // -1: 미결정, 0: 바다, 1 이상: 섬
    static int[][] graph;	// 다리 그래프 인접행렬
    
    // prim algorithm
    static int[] dist;
    static boolean[] visited;
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int INF = Integer.MAX_VALUE / 2;
    
    public static void main(String[] args) throws Exception {
        tk = new StringTokenizer(rd.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        
        map = new int[N][M];
        for (int y=0; y<N; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<M; x++) {
                map[y][x] = tk.nextToken().charAt(0) == '1' ? -1 : 0;
            }
        }
        
        // map에 섬 번호 붙이기
        for (int y=0; y<N; y++) {
            for (int x=0; x<M; x++) {
                if (map[y][x] == -1) {
                    setIsland(y, x, ++V);
                }
            }
        }
        
        // V: 섬 개수
        
        // dist 초기화
        graph = new int[V+1][V+1];
        for (int i=1; i<=V; i++) {
            for (int j=1; j<=V; j++) {
                graph[i][j] = (i == j) ? 0 : INF;
            }
        }
        
        // 연결 확인
        for (int y=0; y<N; y++) {
            for (int x=0; x<M; x++) {
                int src = map[y][x];
                if (src <= 0) continue;
                for (int d=0; d<4; d++) {
                    int[] result = measure(y, x, d);
                    if (result == null)            continue;
                    int dest = result[0];
                    int len = result[1];
                    if (src == dest)            continue;
                    if (len < 2)                continue;
                    if (graph[src][dest] <= len) continue;
                    
                    if (graph[src][dest] == INF) E++;
                    graph[src][dest] = graph[dest][src] = len;
                }
            }
        }
        
        // E: 연결 가능한 다리 수

        // Prim - O(V^2)
        // 초기화
        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        
        visited = new boolean[V+1];
        
        for (int i=1; i<=V; i++) {
            // 미선택 최소 가중치 간선 선택
            int minIdx = -1;
            int minDist = INF;
            for (int j=1; j<=V; j++) {
            	if (!visited[j] && dist[j] < minDist) {
            		minDist = dist[j];
            		minIdx = j;
            	}
            }
            
            if (minIdx == -1) {		// MST 구성 불가능
                System.out.println(-1);
                return;
            }
            
            visited[minIdx] = true;
            ans += minDist;
            for (int j=1; j<=V; j++) {
            	dist[j] = Math.min(dist[j], graph[minIdx][j]);
            }
        }
        
        System.out.println(ans);
        return;
    }
    
    // dfs
    static void setIsland(int y, int x, int idx) {
        map[y][x] = idx;
        for (int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (map[ny][nx] != -1) continue;
            setIsland(ny, nx, idx);
        }
    }
    
    // 다리를 놓았을 떄의 길이와 연결된 섬 번호
    static int[] measure(int y, int x, int dir) {
        int cy = y;
        int cx = x;
        int d = 0;
        while (true) {
            cy += dy[dir];
            cx += dx[dir];
            if (cy < 0 || cy >= N || cx < 0 || cx >= M) return null;
            if (map[cy][cx] != 0) {
                return new int[] {map[cy][cx], d};
            }
            d++;
        }
    }
}