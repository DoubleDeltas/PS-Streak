import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, E, a, b, c, v1, v2;
	static long l1, l2, l3, l4, l5, pl1, pl2, ans;
	static long[][] graph;	// 인접행렬
	static long[] dist;
	static boolean[] visited;
	
	static long INF = Long.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		
		graph = new long[N+1][N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				graph[i][j] = INF;
			}
		}
		
		for (int i=0; i<E; i++) {
			tk = new StringTokenizer(rd.readLine());
			a = Integer.parseInt(tk.nextToken());
			b = Integer.parseInt(tk.nextToken());
			c = Integer.parseInt(tk.nextToken());
			graph[a][b] = graph[b][a] = c;
		}
		
		tk = new StringTokenizer(rd.readLine());
		v1 = Integer.parseInt(tk.nextToken());
		v2 = Integer.parseInt(tk.nextToken());
		
		dist = new long[N+1];
		visited = new boolean[N+1];
		
		l1 = dijkstra(1, v1);
		l2 = dijkstra(v1, v2);
		l3 = dijkstra(v2, N);
		l4 = dijkstra(1, v2);
		l5 = dijkstra(v1, N);

		pl1 = (l1 == INF || l2 == INF || l3 == INF) ? INF : l1 + l2 + l3;
		pl2 = (l4 == INF || l2 == INF || l5 == INF) ? INF : l4 + l2 + l5;
				
		ans = Math.min(pl1, pl2);
		System.out.println(ans == INF ? -1 : ans);
	}
	
	// O(n^2) dijkstra from a to b
	static long dijkstra(int a, int b) {
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dist[a] = 0;
		
		for (int i=1; i<=N; i++) {
			// 가장 가까운 미탐색 인접 정점 k 찾기
			int k = 0;
			long minDist = INF;
			for (int j=1; j<=N; j++) {
				if (!visited[j] && dist[j] < minDist) {
					k = j;
					minDist = dist[k];
				}
			}
			
			visited[k] = true;
			
			// dist[j]와 dist[k] + graph[k][j] 비교 및 갱신
			for (int j=1; j<=N; j++) {
				dist[j] = Math.min(dist[j], dist[k] + graph[k][j]);
			}
		}
		
		return dist[b];
	}
}