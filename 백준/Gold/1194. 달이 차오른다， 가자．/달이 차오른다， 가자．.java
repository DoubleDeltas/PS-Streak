import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, sy, sx;
	static char[][] map;
	static Queue<Node> q = new ArrayDeque<>();
	static boolean[][][] enqueued;	// [key_flag][y][x]
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		map = new char[N][];
		enqueued = new boolean[1 << 6][N][M];
		
		for (int i=0; i<N; i++) {
			map[i] = rd.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				if (map[i][j] == '0') {
					sy = i;
					sx = j;
				}
			}
		}
		
		q.offer(new Node(0, sy, sx, 0));
		enqueued[0][sy][sx] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int flag = node.flag;
			int y = node.y;
			int x = node.x;
			int move = node.move;
			
			if (map[y][x] == '1') {
				System.out.println(move);
				return;
			}
			if ('a' <= map[y][x] && map[y][x] <= 'f')
				flag |= 1 << (map[y][x] - 'a');
			
			for (int d=0; d<4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (enqueued[flag][ny][nx]) continue;
				if (map[ny][nx] == '#') continue;
				if ('A' <= map[ny][nx] && map[ny][nx] <= 'F' && (flag & 1 << (map[ny][nx] - 'A')) == 0) continue;
				
				q.offer(new Node(flag, ny, nx, move + 1));
				enqueued[flag][ny][nx] = true;
			}
		}
		
		System.out.println(-1);
	}
	
	static class Node {
		int flag, y, x, move;
		Node(int flag, int y, int x, int move) {
			this.flag = flag;
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
}