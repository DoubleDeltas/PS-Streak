import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K;
	static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.d - n2.d);
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		
		visited = new boolean[200000];
		
		pq.add(new Node(N, 0));
		
		while (true) {
			Node node = pq.poll();
			int p = node.p;
			int d = node.d;
			
			if (p == K) {
				System.out.println(d);
				return;
			}
			
			if (visited[p]) continue;
			visited[p] = true;
			
			if (p > 0)
				pq.offer(new Node(p - 1, d + 1));
			if (p < K) {
				pq.offer(new Node(p + 1, d + 1));
				pq.offer(new Node(p * 2, d));
			}
		}
	}
	
	static class Node {
		int p, d;
		Node(int p, int d) {
			this.p = p;
			this.d = d;
		}
	}
	
}