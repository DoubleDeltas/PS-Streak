import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, ans;
	static boolean[][] map;
	static Queue<Data> q = new ArrayDeque<>();
	static boolean[][][] enqueued;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		map = new boolean[N+1][M+1];
		for (int y=1; y<=N; y++) {
			char[] s = rd.readLine().toCharArray();
			for (int x=1; x<=M; x++) {
				map[y][x] = s[x-1] == '1';
			}
		}
		enqueued = new boolean[2][N+1][M+1];
		
		ans = Integer.MAX_VALUE;
		
		// bfs
		enqueued[1][1][1] = true;
		q.offer(new Data(1, 1, 1, true));
		while (!q.isEmpty()) {
			Data data = q.poll();
			int y = data.y;
			int x = data.x;
			int cnt = data.cnt;
			boolean breakable = data.breakable;
			
			if (y == N && x == M) {
				ans = Math.min(ans, cnt);
			}
			
			for (int d=0; d<4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (ny <= 0 || ny > N || nx <= 0 || nx > M) continue;
				if (enqueued[breakable ? 1 : 0][ny][nx]) continue;
				if (map[ny][nx] && breakable) {
					enqueued[0][ny][nx] = true;
					q.offer(new Data(ny, nx, cnt + 1, false));
				}
				if (!map[ny][nx]) {
					enqueued[breakable ? 1 : 0][ny][nx] = true;
					q.offer(new Data(ny, nx, cnt + 1, breakable));
				}
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static class Data {
		int y, x, cnt;
		boolean breakable;
		Data(int y, int x, int cnt, boolean breakable) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.breakable = breakable;
		}
	}
}