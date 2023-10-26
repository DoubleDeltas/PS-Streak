import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, S, A[], sum, ans;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		S = Integer.parseInt(tk.nextToken());

		A = new int[N];
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<N; i++)
			A[i] = Integer.parseInt(tk.nextToken());
		
		ans = Integer.MAX_VALUE;
		sum = A[0];
		
		int q = 0;
		for (int p=0; p<N; p++) {
			while (q < N-1 && sum < S) sum += A[++q];
			if (sum < S) break;
			ans = Math.min(ans, q - p + 1);
			sum -= A[p];
		}
		
		if (ans == Integer.MAX_VALUE)
			ans = 0;
		System.out.println(ans);
	}
}