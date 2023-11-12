import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int n, m;
	static long[] t, T;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
		
		tk = new StringTokenizer(rd.readLine());
		t = new long[n+1];
		T = new long[n+1];
		for (int i=1; i<=n; i++) {
			t[i] = Long.parseLong(tk.nextToken());
			T[i] = T[i-1] + t[i];
		}
		
		for (int i=1; i<=n; i++) {
			ans = Math.max(ans, T[i] - T[Math.max(i-m, 0)]);
		}
		
		System.out.println(ans);
	}
}