import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, A, B, C, ans;
	static List<Edge> edges = new ArrayList<>();
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			A = Integer.parseInt(tk.nextToken());
			B = Integer.parseInt(tk.nextToken());
			C = Integer.parseInt(tk.nextToken());
			edges.add(new Edge(A, B, C));
		}
		
		makeSet();
		
		edges.sort((e1, e2) -> e1.w - e2.w);
		
		// kruskal
		int idx = 0;
		int cnt = 0;
		while (cnt < N-2) {
			Edge e = edges.get(idx++);
			if (union(e.v1, e.v2)) {
				ans += e.w;
				cnt++;
			}
		}
		
		System.out.println(ans);
	}
	
	static class Edge {
		int v1, v2, w;
		
		Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
	
	static void makeSet() {
		parents = new int[N+1];
		for (int i=1; i<=N; i++)
			parents[i] = i;
	}
	
	static int findSet(int x) {
		if (parents[x] == x) return x;
		else return parents[x] = findSet(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px == py) return false;
		if (px > py)
			parents[px] = py;
		else
			parents[py] = px;
		return true;
	}
}