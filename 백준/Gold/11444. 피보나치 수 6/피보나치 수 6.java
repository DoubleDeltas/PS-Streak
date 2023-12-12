import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int[][] C = new int[2][2];
	static int[][] T = new int[2][2];
	
	static final int[][] F = {{1, 1}, {1, 0}};
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		long n = Long.parseLong(rd.readLine());
		pow(F, n, C);
		System.out.println(C[0][1]);
	}
	
	static int add(int a, int b) {
		return (a + b) % MOD;
	}
	
	static int times(int a, int b) {
		return (int) (((long) a*b) % MOD);
	}
	
	static void copy(int[][] t, int[][] c) {
		c[0][0] = t[0][0];
		c[0][1] = t[0][1];
		c[1][0] = t[1][0];
		c[1][1] = t[1][1];
	}
	
	static void mult(int[][] a, int[][] b, int[][] c) {
		T[0][0] = add(times(a[0][0], b[0][0]), times(a[0][1], b[1][0]));
		T[0][1] = add(times(a[0][0], b[0][1]), times(a[0][1], b[1][1]));
		T[1][0] = add(times(a[1][0], b[0][0]), times(a[1][1], b[1][0]));
		T[1][1] = add(times(a[1][0], b[0][1]), times(a[1][1], b[1][1]));
		copy(T, c);
	}
	
	static void pow(int[][] a, long n, int[][] c) {
		if (n == 1) {
			copy(a, c);
			return;
		}
		pow(a, n/2, c);
		mult(c, c, c);
		if (n % 2 == 1)
			mult(c, a, c);
	}
}