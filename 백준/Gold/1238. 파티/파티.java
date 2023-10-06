import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, X, ans;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.w - n2.w);
	static int[] dist;
	
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		X = Integer.parseInt(tk.nextToken());
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			int t = Integer.parseInt(tk.nextToken());
			graph.get(a).add(new Node(b, t));
		}
		
		for (int i=1; i<=N; i++) {
			ans = Math.max(ans, dijkstra(i, X) + dijkstra(X, i));
		}
		
		System.out.println(ans);
	}
	
	static class Node {
		int v, w;
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static int dijkstra(int s, int e) {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		
		pq.clear();
		pq.offer(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.v == e) return cur.w;
			
			for (Node nei: graph.get(cur.v)) {
				if (cur.w + nei.w < dist[nei.v]) {
					dist[nei.v] = cur.w + nei.w;
					pq.offer(new Node(nei.v, dist[nei.v]));
				}
			}
		}
		return -1;
	}
}