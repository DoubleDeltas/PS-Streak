import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, S, D, ans;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		S = Integer.parseInt(tk.nextToken());
		D = Integer.parseInt(tk.nextToken());

		for (int i=0; i<=N; i++)
			graph.add(new ArrayList<>());
		
		for (int i=0; i<N-1; i++) {
			tk = new StringTokenizer(rd.readLine());
			int x = Integer.parseInt(tk.nextToken());
			int y = Integer.parseInt(tk.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		visited = new boolean[N+1];
		dfs(S);
        ans *= 2;
		System.out.println(ans);
	}
	
	static int dfs(int v) {
		if (visited[v]) return 0;
		visited[v] = true;
		int d = 0;
		for (int n: graph.get(v)) {
			int result = dfs(n);
			if (result > D) ans++;
			d = Math.max(d, result);
		}
		return d + 1;
	}
}