import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, max, sum, ans, cnt, left;
	static int[] A;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		A = new int[N];
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(rd.readLine());
			max = Math.max(max, A[i]);
			sum += A[i];
		}
		
		int l = max;
		int r = sum;
		int m = 0;
		while (l < r) {
			m = (l + r) / 2;
			
			cnt = 0;
			left = 0;
			for (int i=0; i<N; i++) {
				if (left < A[i]) {
					left = m;
					cnt++;
				}
				left -= A[i];
			}
			
			if (cnt > M) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}
		
		System.out.println(l);
	}
}