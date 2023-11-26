import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, T;
	static int[] K, S;
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		T = Integer.parseInt(tk.nextToken());

		K = new int[N+1];
		S = new int[N+1];
		for (int i=1; i<=N; i++) {
			tk = new StringTokenizer(rd.readLine());
			K[i] = Integer.parseInt(tk.nextToken());
			S[i] = Integer.parseInt(tk.nextToken());
		}
		
		memo = new int[N+1][T+1];
		for (int i=1; i<=N; i++) {
			for (int t=1; t<=T; t++) {
				if (K[i] > t)
					memo[i][t] = memo[i-1][t];
				else
					memo[i][t] = Math.max(memo[i-1][t-K[i]] + S[i], memo[i-1][t]);
			}
		}
		
		System.out.println(memo[N][T]);
	}
}