import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N;
	static List<List<Integer>> graph = new ArrayList<>();
	
	static int[] run, cop1, cop2;
	static boolean[] isLeaf;
	static int a, b, c;
	
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		for (int i=0; i<=N; i++)
			graph.add(new ArrayList<Integer>());
		
		for (int i=0; i<N-1; i++) {
			tk = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		isLeaf = new boolean[N+1];
		run = new int[N+1];
		cop1 = new int[N+1];
		cop2 = new int[N+1];
		
		tk = new StringTokenizer(rd.readLine());
		a = Integer.parseInt(tk.nextToken());
		b = Integer.parseInt(tk.nextToken());
		c = Integer.parseInt(tk.nextToken());
		
		searchLeaf(a, run);
		searchLeaf(b, cop1);
		searchLeaf(c, cop2);
		
		for (int i=1; i<=N; i++) {
			if (!isLeaf[i]) continue;
			if (run[i] < cop1[i] && run[i] < cop2[i]) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
	}
	
	static void searchLeaf(int start, int[] mem) {
		visited = new boolean[N+1];
		dfs(start, 0, mem);
	}
	
	static void dfs(int cur, int depth, int[] mem) {
		visited[cur] = true;
		isLeaf[cur] = true;
		for (int n: graph.get(cur)) {
			if (visited[n]) continue;
			isLeaf[cur] = false;
			dfs(n, depth+1, mem);
		}
		if (isLeaf[cur])
			mem[cur] = depth;
	}
}