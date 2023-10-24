import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static final int INF = Integer.MAX_VALUE / 2;
	static int[][] P = {
			{INF, 2, 2, 2, 2},
			{INF, 1, 3, 4, 3},
			{INF, 3, 1, 3, 4},
			{INF, 4, 3, 1, 3},
			{INF, 3, 4, 3, 1}
	};
	
	static int N, ans;
	static int[] O = new int[100_002];
	static int[][][] memo;	// [len][L][R]
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		while (tk.hasMoreTokens()) {
			O[++N] = Integer.parseInt(tk.nextToken());
		}
		N--;
		
		memo = new int[N+1][5][5];
		for (int l=0; l<=4; l++)
			for (int r=0; r<=4; r++)
				memo[0][l][r] = INF;
		memo[0][0][0] = 0;
		
		for (int i=1; i<=N; i++) {
			for (int l=0; l<=4; l++) {
				for (int r=0; r<=4; r++) {
					memo[i][l][r] = INF;
					if (l == O[i]) {
						for (int pl=0; pl<=4; pl++)
							memo[i][l][r] = Math.min(memo[i][l][r], memo[i-1][pl][r] + P[pl][l]);	
					}
					if (r == O[i]) {
						for (int pr=0; pr<=4; pr++)
							memo[i][l][r] = Math.min(memo[i][l][r], memo[i-1][l][pr] + P[pr][r]);	
					}	
				}
			}
		}
		
		ans = INF;
		for (int l=0; l<=4; l++) {
			for (int r=0; r<=4; r++) {
				ans = Math.min(ans, memo[N][l][r]);
			}
		}
		System.out.println(ans);
	}
}