import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M;
	static char[] S, T;
	static int[][] memo; // memo[i][j]: S[0~i], T[0~i]의 LCS 최대 길이
	
	public static void main(String[] args) throws Exception {
		S = rd.readLine().toCharArray();
		T = rd.readLine().toCharArray();
		N = S.length;
		M = T.length;
		
		memo = new int[N+1][M+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if (S[i-1] == T[j-1])
					memo[i][j] = memo[i-1][j-1] + 1;
				else
					memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
			}
		}

		System.out.println(memo[N][M]);
	}
}