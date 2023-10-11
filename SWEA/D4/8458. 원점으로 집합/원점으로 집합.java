import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, N, D, sum, ans;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t = 1; t <= T; t++) {
			D = 0;
			
			N = Integer.parseInt(rd.readLine());
			
			boolean stop = false;
			for (int i=0; i<N; i++) {
				tk = new StringTokenizer(rd.readLine());
				int x = Integer.parseInt(tk.nextToken());
				int y = Integer.parseInt(tk.nextToken());
				int d = Math.abs(x) + Math.abs(y);
				if (i > 0 && d % 2 != D % 2) {
					stop = true;
					continue;
				}
				D = Math.max(D, d);
			}
			
			if (stop) {
				sb.append('#').append(t).append(" -1\n");
				continue;
			}

			sum = 0;
			ans = 0;
			while (sum < D) sum += ++ans;
			
			if ((sum - D) % 2 == 1) ans = (ans - 1) / 2 * 2 + 3;
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}