import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, min;
	static ArrayList<ArrayList<Integer>> adjList;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 맨 앞 N
			N = Integer.parseInt(st.nextToken());
			// 인접행렬
			adjList = new ArrayList<>();
			for (int i=0; i<N; i++) {
				adjList.add(new ArrayList<>());
			}
			
			for (int i=0; i<N; i++) {		// 행
				for (int j=0; j<N; j++) {	// 열
					int n = Integer.parseInt(st.nextToken());
					if (n == 1) adjList.get(i).add(j);
				}
			}

			min = Integer.MAX_VALUE;
			// 각 정점(사람) 별로 각자 최단경로를 구해서 min 갱신 
			for (int i=0; i<N; i++) {
				bfs(i);
			}
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int V) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		
		visit[V] = true;
		queue.offer(new Node(V, 0));
		
		int dist = 0;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			// node.v <- adjList.get(node.v)
			for (int v: adjList.get(node.v)) {
				if (visit[v]) continue;
				dist += node.cnt + 1;
				if (dist >= min) return;	// 가지치기
				
				visit[v] = true;
				queue.offer(new Node(v, node.cnt + 1));
			}
		}
		
		min = Math.min(min, dist);
	}
	
	static class Node {
		int v, cnt;
		Node(int v, int cnt) {
			this.v = v;
			this.cnt= cnt;
		}
	}
}
