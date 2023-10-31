import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
		
		makeSet();
		
		for (int i=1; i<=m; i++) {
			tk = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			if (!union(a, b)) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(0);
	}
	
	static void makeSet() {
		parent = new int[n];
		for (int i=0; i<n; i++)
			parent[i] = i;
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px == py) return false;
		parent[px] = py;
		return true;
	}
}