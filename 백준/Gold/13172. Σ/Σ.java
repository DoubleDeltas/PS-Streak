import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int M, N, S, ans;

	static final int X = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		M = Integer.parseInt(rd.readLine());
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			N = Integer.parseInt(tk.nextToken());
			S = Integer.parseInt(tk.nextToken());
			
			ans = add(ans, times(S, pow(N, X-2)));
		}
		
		System.out.println(ans);
	}
	
	static int add(int a, int b) {
		return (a + b) % X;
	}
	
	static int times(int a, int b) {
		return (int) ((long) a * b % X);
	}
	
	static int pow(int a, int n) {
		if (n == 1) return a;
		
		int r = pow(a, n/2);
		r = times(r, r);
		if (n % 2 == 1)
			r = times(r, a);
		return r;
	}
}