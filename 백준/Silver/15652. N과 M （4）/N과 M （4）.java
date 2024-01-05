import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		tgt = new int[M];
		dfs(0, 1);
		
		System.out.println(sb);
	}
	
	static void dfs(int tgtIdx, int n) {
		if (tgtIdx == M) {
			for (int i=0; i<M; i++) {
				sb.append(tgt[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		if (n == N+1) return;
		
		tgt[tgtIdx] = n;

		dfs(tgtIdx + 1, n);
		dfs(tgtIdx, n+1);
	}
}