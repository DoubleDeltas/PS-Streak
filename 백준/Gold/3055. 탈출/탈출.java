import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int R, C, sy, sx;
	static int[][] time;	// 해당 칸에 물이 차기까지 남은 시간(분)
	static Queue<Coord> q = new ArrayDeque<>();
	static boolean[][] enqueued;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		
		time = new int[R][C];
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				time[i][j] = Integer.MAX_VALUE;
			}
		}
		
		enqueued = new boolean[R][C];
		for (int i=0; i<R; i++) {
			char[] s = rd.readLine().toCharArray();
			for (int j=0; j<C; j++) {
				switch (s[j]) {
				case 'S':	// 고슴도치 (출발지)
					sy = i; sx = j;
					break;
				case 'D':	// 비버 굴 (목적지)
					time[i][j] = -2;
					break;
				case '*':	// 물
					q.add(new Coord(i, j));
					enqueued[i][j] = true;
					break;
				case 'X':	// 돌
					time[i][j] = -1;
					break;
				default:
					break;
				}
			}
		}
			
		// 물이 차는 시간 계산
		int t = 0;			
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i=0; i<size; i++) {
				Coord coord = q.poll();
				int y = coord.y;
				int x = coord.x;
				
				time[y][x] = t;
				
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
					if (enqueued[ny][nx]) continue;
					if (time[ny][nx] < 0) continue;	// 돌 또는 비버굴
					q.offer(new Coord(ny, nx));
					enqueued[ny][nx] = true;
				}
			}
			t++;
		}
		
		enqueued = new boolean[R][C];
		q.offer(new Coord(sy, sx));
		enqueued[sy][sx] = true;
		
		// update
		t = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i=0; i<size; i++) {
				Coord coord = q.poll();
				int y = coord.y;
				int x = coord.x;
				
				if (time[y][x] == -2) {		// 비버굴 찾음
					System.out.println(t);
					return;
				}
				
				if (t >= time[y][x]) continue;	// 물에 잠김
				
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
					if (enqueued[ny][nx]) continue;
					if (time[ny][nx] == -1) continue;	// 돌
					q.offer(new Coord(ny, nx));
					enqueued[ny][nx] = true;
				}
			}
			t++;
		}
		System.out.println("KAKTUS");
	}
	
	static class Coord {
		int y, x;
		Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}