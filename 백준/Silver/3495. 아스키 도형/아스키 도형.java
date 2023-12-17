import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, ans;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		map = new char[N][];
		for (int y=0; y<N; y++)
			map[y] = rd.readLine().toCharArray();
		
		for (int y=0; y<N; y++) {
			boolean in = false;
			int cnt = 0;
			for (int x=0; x<M; x++) {
				if (map[y][x] != '.') {
					if (!in) {
						in = true;
						cnt = 1;
					} else {
						in = false;
						ans += cnt;
					}
				} else {
					if (in) cnt++;
				}
			}
		}
		
		System.out.println(ans);
	}
}