import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, s, ans;
	static int[] table;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		table = new int[N+1];
		for (int i=1; i<=N; i++) {
			table[i] = Integer.parseInt(rd.readLine());
		}
		visited = new boolean[N+1];
		
		for (int i=1; i<=N; i++) {
			Arrays.fill(visited, false);
			s = i;
			if (dfs(table[i])) {
				ans++;
				sb.append(i).append('\n');
			}
		}
		
		System.out.println(ans);
		System.out.println(sb);
	}
	
	static boolean dfs(int n) {
		if (n == s) return true;
		if (visited[n]) return false;
		visited[n] = true;
		return dfs(table[n]);
	}
}