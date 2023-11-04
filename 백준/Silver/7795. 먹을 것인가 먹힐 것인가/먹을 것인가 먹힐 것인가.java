import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, N, M, ans;
	static int[] A, B;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t=1; t<=T; t++) {
			ans = 0;
			
			tk = new StringTokenizer(rd.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());
			
			A = new int[N];
			tk = new StringTokenizer(rd.readLine());
			for (int i=0; i<N; i++)
				A[i] = Integer.parseInt(tk.nextToken());

			B = new int[M];
			tk = new StringTokenizer(rd.readLine());
			for (int i=0; i<M; i++)
				B[i] = Integer.parseInt(tk.nextToken());
			
			Arrays.sort(B);
			for (int i=0; i<N; i++) {
				int idx = binsearch(B, A[i]);
				ans += idx;
			}
			sb.append(ans).append('\n');
		}

		System.out.println(sb);
	}
	
	static int binsearch(int[] arr, int n) {
		int l = 0;
		int r = arr.length;
		int m;
		while (l < r) {
			m = (l + r) / 2;
			if (n <= B[m])
				r = m;
			else
				l = m + 1;
		}
		return l;
	}
}