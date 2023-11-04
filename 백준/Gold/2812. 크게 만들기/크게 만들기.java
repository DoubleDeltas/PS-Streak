import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K ,k;
	static Node head, tail, cur;
	static char[] tmp;
	static int n;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = n = Integer.parseInt(tk.nextToken());
		K = k = Integer.parseInt(tk.nextToken());

		tail = head = new Node(Integer.MAX_VALUE, null);
		
		tmp = rd.readLine().toCharArray();
		for (char c: tmp) {
			head = new Node(c - '0', head);
		}
		
		cur = tail.next;
		while (cur.next != null && k > 0) {
			if (cur.n < cur.next.n) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				cur = cur.prev;
				
				n--;
				k--;
				if (k == 0) break;
			}
			else cur = cur.next;
		}
		
		cur = tail.next;
		for (int i=0; i<n-k; i++) {
			if (cur == null) break;
			sb.append(cur.n);
			cur = cur.next;
		}
		
		System.out.println(sb);
	}
	
	static class Node {
		int n;
		Node prev, next;
		Node(int n, Node prev) {
			this.n = n;
			this.prev = prev;
			if (prev != null)
				prev.next = this;
		}
	}
}