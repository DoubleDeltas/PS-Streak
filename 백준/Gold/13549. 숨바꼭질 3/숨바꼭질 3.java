import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K;
	static Deque<Node> dq = new ArrayDeque<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		
		visited = new boolean[200000];
		
		dq.offerFirst(new Node(N, 0));
		
		while (true) {
			Node node = dq.pollFirst();
			int p = node.p;
			int d = node.d;
			
			if (p == K) {
				System.out.println(d);
				return;
			}
			
			if (visited[p]) continue;
			visited[p] = true;
			
			if (p > 0)
				dq.offerLast(new Node(p - 1, d + 1));
			if (p < K) {
				dq.offerLast(new Node(p + 1, d + 1));
				dq.offerFirst(new Node(p * 2, d));
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