import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int n, m, A, B, cnt;
	static int[][] graph;
	static int[] dist, path;
	static boolean[] visited;
	
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(rd.readLine());
		m = Integer.parseInt(rd.readLine());
		
		graph = new int[n+1][n+1];
		for (int i=0; i<=n; i++) {
			for (int j=0; j<=n; j++) {
				graph[i][j] = i == j ? 0 : INF;
			}
		}
		
		for (int i=0; i<m; i++) {
			tk = new StringTokenizer(rd.readLine());
			int s = Integer.parseInt(tk.nextToken());
			int e = Integer.parseInt(tk.nextToken());
			int w = Integer.parseInt(tk.nextToken());
			graph[s][e] = Math.min(graph[s][e], w);
		}
		
		tk = new StringTokenizer(rd.readLine());
		A = Integer.parseInt(tk.nextToken());
		B = Integer.parseInt(tk.nextToken());
		
		
		dist = new int[n+1];
		Arrays.fill(dist, INF);
		dist[A] = 0;
		visited = new boolean[n+1];

		path = new int[n+1];
		
		int mi = 0;
		do {
			// find min unvisited dist idx
			mi = 0;
			for (int i=1; i<=n; i++) {
				if (!visited[i] && dist[i] < dist[mi]) {
					mi = i;
				}
			}
			
			visited[mi] = true;
			
			// dist update
			for (int i=1; i<=n; i++) {
//				dist[i] = Math.min(dist[i], dist[mi] + graph[mi][i]);
				if (dist[mi] + graph[mi][i] < dist[i]) {
					dist[i] = dist[mi] + graph[mi][i];
					path[i] = mi;
				}
			}
		} while (mi != B);
		
		// path backtracking
		int i = B;
		while (i != 0) {
			cnt++;
			sb.insert(0, ' ').insert(0, i);
			i = path[i];
		}
		
		System.out.println(dist[B]);
		System.out.println(cnt);
		System.out.println(sb);
	}
}