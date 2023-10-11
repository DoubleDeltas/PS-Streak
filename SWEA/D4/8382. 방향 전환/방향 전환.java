import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, x1, y1, x2, y2, ans;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t = 1; t <= T; t++) {
			tk = new StringTokenizer(rd.readLine());
			x1 = Integer.parseInt(tk.nextToken());
			y1 = Integer.parseInt(tk.nextToken());
			x2 = Integer.parseInt(tk.nextToken());
			y2 = Integer.parseInt(tk.nextToken());
			
			int xm = (x2 + y2 + x1 - y1) / 2;
			int ym = (x2 + y2 - x1 + y1) / 2;
			int xn = (x1 + y1 + x2 - y2) / 2;
			int yn = (x1 + y1 - x2 + y2) / 2;
			
			ans = Integer.MAX_VALUE;
			ans = Math.min(ans, dist(x1, y1, xm, ym) + dist(xm, ym, x2, y2));
			ans = Math.min(ans, dist(x1, y1, xn, yn) + dist(xn, yn, x2, y2));

			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
}