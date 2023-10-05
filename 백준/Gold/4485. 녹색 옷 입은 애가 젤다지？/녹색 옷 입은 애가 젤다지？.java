import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int t, N;
	static int[][] map;
	static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));
	static int[][] dist;	// 0 0 -> y x
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		while (true) {
			t++;
			N = Integer.parseInt(rd.readLine());
			if (N == 0) break;
			
			map = new int[N][N];
			for (int y=0; y<N; y++) {
				tk = new StringTokenizer(rd.readLine());
				for (int x=0; x<N; x++) {
					map[y][x] = Integer.parseInt(tk.nextToken());
				}
			}

			dist = new int[N][N];
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					dist[y][x] = INF;
				}
			}
			dist[0][0] = 0;
			
			pq.offer(new Node(0, 0, 0));
			
			while (!pq.isEmpty()) {
				Node node = pq.poll();
				int y = node.y;
				int x = node.x;
				int w = node.w;

				if (dist[y][x] < w) continue;
				
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
					
					if (w + map[y][x] < dist[ny][nx]) {
						dist[ny][nx] = w + map[y][x];
						pq.offer(new Node(ny, nx, dist[ny][nx]));
					}
				}
			}
			sb.append("Problem ").append(t).append(": ").append(dist[N-1][N-1] + map[N-1][N-1]).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node {
		int y, x, w;
		Node(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
	}
}