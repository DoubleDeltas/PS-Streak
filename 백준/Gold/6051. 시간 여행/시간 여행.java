import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K;
	static char c;
	static Node[] hist;
	static Node head;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		hist = new Node[N+1];
		head = new Node(-1, null);
		
		for (int i=1; i<=N; i++) {
			tk = new StringTokenizer(rd.readLine());
			c = tk.nextToken().charAt(0);
			if (tk.hasMoreTokens())
				K = Integer.parseInt(tk.nextToken());
			
			hist[i] = head;
			switch (c) {
			case 'a':
				head = new Node(K, head);
				break;
			case 's':
				head = head.prev;
				break;
			case 't':
				head = hist[K];
				break;
			}
			sb.append(head.n).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static class Node {
		int n;
		Node prev;
		
		Node(int n, Node prev) {
			this.n = n;
			this.prev = prev;
		}
	}
}