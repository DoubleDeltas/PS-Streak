import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, H, min, cnt;
	static int[] u, d, U, D;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		H = Integer.parseInt(tk.nextToken());

		u = new int[H+1];
		d = new int[H+1];
		for (int i=0; i<N; i++) {
			int s = Integer.parseInt(rd.readLine());
			if (i % 2 == 0) d[s]++;
			else u[H-s+1]++;
		}
		
		U = new int[H+1];
		D = new int[H+1];
		for (int i=H-1; i>0; i--)
			D[i] = D[i+1] + d[i];
		for (int i=2; i<=H; i++) {
			U[i] = U[i-1] + u[i];
		}
		
		min = Integer.MAX_VALUE;
		for (int i=1; i<=H; i++) {
			int c = U[i] + D[i];
			if (min > c) {
				min = c;
				cnt = 1;
			}
			else if (min == c) {
				cnt++;
			}
		}
		
		System.out.println(min + " " + cnt);
	}
}