import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, ans;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		map = new char[N][];
		for (int y=0; y<N; y++) {
			map[y] = rd.readLine().toCharArray();
		}
		visited = new boolean[N][M];
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (visited[y][x]) continue;
				right(y, x);
				down(y, x);
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static void right(int y, int x) {
		if (x >= M || visited[y][x] || map[y][x] != '-') return;
		visited[y][x] = true;
		right(y, x+1);
	}
	
	static void down(int y, int x) {
		if (y >= N || visited[y][x] || map[y][x] != '|') return;
		visited[y][x] = true;
		down(y+1, x);
	}
}