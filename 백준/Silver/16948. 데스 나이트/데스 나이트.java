import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;
	
	static int N, r1, c1, r2, c2;
	static Queue<Node> q = new ArrayDeque<>();
	static boolean[][] enqueued;
	
	static int[] dy = {-2, -2,  0,  0,  2,  2};
	static int[] dx = {-1,  1, -2,  2, -1,  1};
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		tk = new StringTokenizer(rd.readLine());
		r1 = Integer.parseInt(tk.nextToken());
		c1 = Integer.parseInt(tk.nextToken());
		r2 = Integer.parseInt(tk.nextToken());
		c2 = Integer.parseInt(tk.nextToken());
		
		enqueued = new boolean[N][N];
		
		q.offer(new Node(r1, c1, 0));
		enqueued[r1][c1] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;
			int t = node.t;
			if (y == r2 && x == c2) {
				System.out.println(t);
				return;
			}
			for (int i=0; i<6; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (enqueued[ny][nx]) continue;
				q.offer(new Node(ny, nx, t + 1));
				enqueued[ny][nx] = true;
			}
		}
		
		System.out.println(-1);
	}
	
	static class Node {
		int y, x, t;
		Node(int y, int x, int t) {
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}
}