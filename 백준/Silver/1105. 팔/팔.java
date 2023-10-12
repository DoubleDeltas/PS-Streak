import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;
	
	static int L, R, ans;

	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		L = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		
		while (true) {
			if (L == 0 && R == 0) break;
			if (L != R) ans = 0;
			if (L % 10 == 8 && R % 10 == 8) ans++;
			L /= 10;
			R /= 10;
		}
		
		System.out.println(ans);
	}
}