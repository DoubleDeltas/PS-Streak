import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int INF = Integer.MAX_VALUE / 2;
	
	static int TC, N, M, W, S, E, T;
	static List<Edge> edgeList = new ArrayList<>();
	static int[] dist;

	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(rd.readLine());
		TC_LOOP: for (int tc = 1; tc <= TC; tc++) {
			tk = new StringTokenizer(rd.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());
			W = Integer.parseInt(tk.nextToken());
			
			edgeList.clear();
			dist = new int[N+1];
			
			for (int i=0; i<M; i++) {
				tk = new StringTokenizer(rd.readLine());
				S = Integer.parseInt(tk.nextToken());
				E = Integer.parseInt(tk.nextToken());
				T = Integer.parseInt(tk.nextToken());
				edgeList.add(new Edge(S, E, T));
				edgeList.add(new Edge(E, S, T));
			}
			for (int i=0; i<W; i++) {
				tk = new StringTokenizer(rd.readLine());
				S = Integer.parseInt(tk.nextToken());
				E = Integer.parseInt(tk.nextToken());
				T = Integer.parseInt(tk.nextToken());
				edgeList.add(new Edge(S, E, -T));
			}
			
			// bellman-ford
			dist[1] = 0;
			for (int i=2; i<=N; i++) dist[i] = INF;
			
			// N-1번 반복
			for (int i=0; i<N-1; i++) {			
				// 모든 간선 확인
				for (Edge e: edgeList) {
					dist[e.v2] = Math.min(dist[e.v2], dist[e.v1] + e.w);
				}
			}

			// N번째 반복에서 거리가 갱신되는 정점이 있다면 음수 사이클 존재
			for (Edge e: edgeList) {
				if (dist[e.v1] + e.w < dist[e.v2]) {
					sb.append("YES\n");
					continue TC_LOOP;
				}
			}
			
			sb.append("NO\n");
		}
		System.out.println(sb);
	}
	
	static class Edge {
		int v1, v2, w;
		Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
}