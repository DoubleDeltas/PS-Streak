import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M;
	static boolean[][] graph;
	static int[] path;
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		M = Integer.parseInt(rd.readLine());
		
		graph = new boolean[N+1][N+1];
		for (int i=1; i<=N; i++) {
			tk = new StringTokenizer(rd.readLine());
			for (int j=1; j<=N; j++) {
				graph[i][j] = tk.nextToken().charAt(0) == '1';
			}
		}
		
		path = new int[M];
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<M; i++)
			path[i] = Integer.parseInt(tk.nextToken());
		
		makeSet();
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (graph[i][j])
					union(i, j);
			}
		}
		
		for (int i=0; i<M-1; i++) {
			if (findSet(path[i]) != findSet(path[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static void makeSet() {
		parent = new int[N+1];
		for (int i=1; i<=N; i++)
			parent[i] = i;
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px == py) return;
		parent[py] = px;
	}
}