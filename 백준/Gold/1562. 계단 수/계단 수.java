import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, ans;
	static int[][][] memo;	// [len][flags][last_digit]

	static final int MOD = 1_000_000_000;
	static final int ALL = 0b11_1111_1111;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		memo = new int[N+1][ALL+1][10];

		// 0은 제외
		for(int i=1; i<=9; i++) {
			memo[1][1 << i][i] = 1;
		}
		
		for (int i=2; i<=N; i++) {
			for (int m=0; m<=ALL; m++) {
				for (int j=0; j<10; j++) {
					if ((m & 1 << j) == 0) continue;	// j-th bit is 0
					if (j < 9) {
						memo[i][m][j] = (memo[i][m][j] + memo[i-1][m & ~(1 << j)][j+1]) % MOD;
						memo[i][m][j] = (memo[i][m][j] + memo[i-1][m][j+1]) % MOD;
					}
					if (j > 0) {
						memo[i][m][j] = (memo[i][m][j] + memo[i-1][m & ~(1 << j)][j-1]) % MOD;
						memo[i][m][j] = (memo[i][m][j] + memo[i-1][m][j-1]) % MOD;
					}
				}
			}
		}
		
		for (int i=0; i<10; i++) {
			ans = (ans + memo[N][ALL][i]) % MOD;
		}
				
		System.out.println(ans);
	}
}