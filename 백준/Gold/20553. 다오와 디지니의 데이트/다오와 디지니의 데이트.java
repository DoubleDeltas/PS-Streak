import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, T;
	static int[] h;
	static long[] p, P;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		T = Integer.parseInt(tk.nextToken());
		
		h = new int[N+1];
		tk = new StringTokenizer(rd.readLine());
		for (int i=1; i<=N; i++) {
			h[i] = Integer.parseInt(tk.nextToken());
		}
		
		p = new long[N-1];
		P = new long[N-1];
		
		for (int i=0; i<N-1; i++) p[i] = h[i+2] + h[i+1];
		
		P[0] = p[0];
		for (int i=1; i<N-1; i++) P[i] = P[i-1] + p[i];

		ans = Math.max(0, p[0] * (T/2));
		for (int i=1; i<N-1; i++) {
			if (i > T/2) break;
			ans = Math.max(ans, P[i-1] + p[i] * (T/2 - i));
		}
		
		System.out.println(ans);
	}
}