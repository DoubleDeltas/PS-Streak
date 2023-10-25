import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, R, G, B;
	static long[][][][] memo;	// [n][r][g][b]
	static long[] f2 = {1, 2, 6, 20, 70, 252};	// (2n)!/(n!)^2
	static long[] f3 = {1, 6, 90, 1680};		// (3n)!/(n!)^3
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		G = Integer.parseInt(tk.nextToken());
		B = Integer.parseInt(tk.nextToken());
		
		memo = new long[N+1][R+1][G+1][B+1];
		for (int i=0; i<=N; i++)
		for (int r=0; r<=R; r++)
		for (int g=0; g<=G; g++)
		for (int b=0; b<=B; b++)
			memo[i][r][g][b] = -1;	// uncalculated
		
		System.out.println(f(N, R, G, B));
	}
	
	static long f(int n, int r, int g, int b) {
		if (r < 0 || g < 0 || b < 0) return 0;
		if (n == 0) return 1;
		if (memo[n][r][g][b] != -1) return memo[n][r][g][b];
		long ret = 0;
		ret += f(n-1, r-n, g, b) + f(n-1, r, g-n, b) + f(n-1, r, g, b-n);
		if (n % 2 == 0) {
			int n2 = n / 2;
			ret += f2[n2] * (f(n-1, r-n2, g-n2, b) + f(n-1, r, g-n2, b-n2) + f(n-1, r-n2, g, b-n2));
		}
		if (n % 3 == 0) {
			int n3 = n / 3;
			ret += f3[n3] * f(n-1, r-n3, g-n3, b-n3);
		}
		return memo[n][r][g][b] = ret;
	}
}