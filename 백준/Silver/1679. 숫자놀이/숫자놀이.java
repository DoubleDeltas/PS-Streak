import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K, A[], maxA, memo[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		A = new int[N];
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(tk.nextToken());
			maxA = Math.max(maxA, A[i]);
		}
		
		K = Integer.parseInt(rd.readLine());
		
		memo = new int[maxA * K + 1 + 1];
		for (int i=1; i<memo.length; i++) {
			memo[i] = Integer.MAX_VALUE;
			for (int j=0; j<N; j++) {
				if (A[j] <= i) {
					memo[i] = Math.min(memo[i], memo[i-A[j]] + 1);
				}
			}
			if (memo[i] > K) {
				if (i % 2 == 0)
					sb.append("holsoon");
				else
					sb.append("jjaksoon");
				sb.append(" win at ");
				sb.append(i);

				System.out.println(sb);
				return;
			}
		}
	}
}