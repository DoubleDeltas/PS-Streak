import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, C;
	static int[] x;
	
	static final int G = 1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());

		x = new int[N+1];
		x[0] = -G;
		for (int i=1; i<=N; i++) {
			x[i] = Integer.parseInt(rd.readLine());
		}
		
		Arrays.sort(x);
		
		int l = 1;
		int r = G + 1;
		int m = 0;
		while (l < r) {
			m = (l + r) / 2;
			
			int left = C;
			boolean ok = false;
			int last = -G;
			for (int i=1; i<=N; i++) {
				if (x[i] - last >= m) {
					left--;
					last = x[i];
				}
				if (left == 0) {
					ok = true;
					break;
				}
			}
			
			if (ok) l = m + 1;
			else	r = m;
		}
		
		System.out.println(r - 1);
	}
}