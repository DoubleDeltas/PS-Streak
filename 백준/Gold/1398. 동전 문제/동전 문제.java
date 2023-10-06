import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int T, ans;
	static long P;
	
	static int[] memo = new int[100];
	
	public static void main(String[] args) throws Exception {
		memo[0] = 0;
		for (int i=1; i<10; i++)		memo[i] = memo[i-1] + 1;
		for (int i=10; i<25; i++)		memo[i] = min(memo[i-1], memo[i-10]) + 1;
		for (int i=25; i<100; i++)		memo[i] = min(memo[i-1], memo[i-10], memo[i-25]) + 1;
		
		T = Integer.parseInt(rd.readLine());
		for (int t=1; t<=T; t++) {
			ans = 0;
			
			P = Long.parseLong(rd.readLine());
			
			for (int i=0; i<8; i++) {
				ans += memo[(int) (P % 100)];
				P /= 100;
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static int min(Integer... A) {
		int min = Integer.MAX_VALUE;
		for (int i=0; i<A.length; i++) {
			min = Math.min(min, A[i]);
		}
		return min;
	}
}