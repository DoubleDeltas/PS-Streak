import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		map = new int[N][M];
		for (int y=0; y<N; y++) {
			tk = new StringTokenizer(rd.readLine());
			for (int x=0; x<M; x++) {
				map[y][x] = Integer.parseInt(tk.nextToken());
			}
		}
		visited = new boolean[N][M];
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == 3) {
			ans = Math.max(ans, simulate());
			return;
		}
		if (srcIdx == N*M) return;
		int y = srcIdx / M;
		int x = srcIdx % M;
		if (map[y][x] != 0) {
			comb(srcIdx + 1, tgtIdx);
			return;
		}
		
		map[y][x] = 1;
		comb(srcIdx + 1, tgtIdx + 1);
		map[y][x] = 0;
		comb(srcIdx + 1, tgtIdx);
	}
	
	static int simulate() {
		for (int y=0; y<N; y++)
			for (int x=0; x<M; x++)
				visited[y][x] = false;

		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (map[y][x] == 2)
					dfs(y, x);
			}
		}
		
		int r=0;
		for (int y=0; y<N; y++)
			for (int x=0; x<M; x++)
				if (map[y][x] == 0 && !visited[y][x]) r++;
		return r;
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if (visited[ny][nx]) continue;
			if (map[ny][nx] == 1) continue;
			dfs(ny, nx);
		}
	}
}