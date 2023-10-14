import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;
	
	static final int MOD = 1000000;
	
	static int N, ans;
	static int[][][] memo;	// [day][late][absent]

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		memo = new int[N+1][2][3];
		
		memo[1][0][0] = 1;
		memo[1][1][0] = 1;
		memo[1][0][1] = 1;
		
		for (int i=2; i<=N; i++) {
			// O
			for (int l=0; l<2; l++) for (int a=0; a<3; a++)
				memo[i][l][0] = add(memo[i][l][0], memo[i-1][l][a]);
			// A
			for (int l=0; l<2; l++) for (int a=1; a<3; a++)
				memo[i][l][a] = add(memo[i][l][a], memo[i-1][l][a-1]);
			// L
			for (int l=1; l<2; l++) for (int a=0; a<3; a++)
				memo[i][l][0] = add(memo[i][l][0], memo[i-1][l-1][a]);
		}

		for (int l=0; l<2; l++) for (int a=0; a<3; a++)
			ans = add(ans, memo[N][l][a]);
		
		System.out.println(ans);
	}
	
	static int add(int a, int b) {
		return (a + b) % MOD;
	}
}