import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, n;
	static int[][] S, memo;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t=0; t<T; t++) {
			n = Integer.parseInt(rd.readLine());
			S = new int[2][n+2];
			memo = new int[2][n+2];
			
			for (int k=0; k<2; k++) {
				tk = new StringTokenizer(rd.readLine());
				for (int i=2; i<=n+1; i++)
					S[k][i] = Integer.parseInt(tk.nextToken());
			}
			
			for (int i=2; i<=n+1; i++) {
				memo[0][i] = max(memo[0][i-2], memo[1][i-2], memo[1][i-1]) + S[0][i];
				memo[1][i] = max(memo[0][i-2], memo[1][i-2], memo[0][i-1]) + S[1][i];
			}
			
			sb.append(Math.max(memo[0][n+1], memo[1][n+1])).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int max(int a, int b, int c) {
		int r = a;
		r = Math.max(r, b);
		r = Math.max(r, c);
		return r;
	}
}