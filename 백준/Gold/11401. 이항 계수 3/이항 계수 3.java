import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, N, R, ans;

	static int P = 1000000007;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		
		// comb(N, R) = N! / R!(N-R)!, 분모를 A, 분자를 B라 하자.
		// 구하는 값 A/B = A * B^-1를 알기 위해 B의 모듈로 역원 B^-1 을 구해야 한다.
		// B*(B^-1) = 1이고, 페르마의 소정리에서 B^(P-1) = B * B^(P-2) = 1이므로
		// B^-1 = B^(P-2).
		// 즉, A * B^(P-2)의 모듈로를 구하면 답이다.
		int A = fact(N);
		int B = mul(fact(R), fact(N-R));
		
		ans = mul(A, pow(B, P - 2));
		System.out.println(ans);
	}
	
	static int mul(int a, int b) {
		return (int) ((long) a * b % P);
	}
	
	static int fact(int n) {
		int r = 1;
		for (int i=2; i<=n; i++) {
			r = mul(r, i);
		}
		return r;
	}
	
	static int pow(int a, int b) {
		if (b == 1) return a;
		int r;
		int X = pow(a, b/2);
		r = mul(X, X);
		if (b % 2 == 1) r = mul(r, a);
		return r;
	}
}