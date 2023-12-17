import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M;
	static char[][] map;
	static int[][][] G;
	
	//  ur 0 dr 1 dl 2 ul 3
	static int[] dy = {-1, 1, 1, -1};
	static int[] dx = {1, 1, -1, -1};
	static int INF = Integer.MAX_VALUE / 2;
	
	static Deque<Node> dq = new ArrayDeque<>();
	static int[][] depth;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		G = new int[N+1][M+1][4];
		for (int y=0; y<N+1; y++) {
			for (int x=0; x<M+1; x++) {
				for (int d=0; d<4; d++) {
					G[y][x][d] = 1;
				}
			}
		}
		depth = new int[N+1][M+1];
		for (int i=0; i<N+1; i++)
			for (int j=0; j<M+1; j++)
				depth[i][j] = Integer.MAX_VALUE;
		
		map = new char[N][];
		for (int y=0; y<N; y++)
			map[y] = rd.readLine().toCharArray();
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (map[y][x] == '\\')
					G[y][x][1] = G[y+1][x+1][3] = 0;
				else
					G[y+1][x][0] = G[y][x+1][2] = 0;
			}
		}
		
		dq.offerFirst(new Node(0, 0, 0));
		depth[0][0] = 0;
		
		while (!dq.isEmpty()) {
			Node node = dq.pollFirst();
			int y = node.y;
			int x = node.x;
			int d = node.d;
			
			if (y == N && x == M) {
				System.out.println(d);
				return;
			}
			
			for (int i=0; i<4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || ny >= N+1 || nx < 0 || nx >= M+1) continue;
				
				if (G[y][x][i] == 1) {
					if (depth[ny][nx] <= d+1) continue;
					dq.offerLast(new Node(ny, nx, d+1));
					depth[ny][nx] = d+1;
				}
				else {
					if (depth[ny][nx] <= d) continue;
					dq.offerFirst(new Node(ny, nx, d));
					depth[ny][nx] = d;
				}
			}
		}
		
		System.out.println("NO SOLUTION");
		
	}
	
	static class Node {
		int y, x, d;
		Node(int y, int x, int d) {
			this.y= y;
			this.x= x;
			this.d=d;
		}
	}
}