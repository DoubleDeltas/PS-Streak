import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, K, Z, ans;
	static char[][] map;
	static char[] word;
	static int[][][] X;
	
	// up right down left
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());

		map = new char[N][];
		for (int y=0; y<N; y++) {
			map[y] = rd.readLine().toCharArray();
		}
		word = rd.readLine().toCharArray();
		Z = word.length;
		
		X = new int[Z][N][M];
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				X[Z-1][y][x] = 1;
			}
		}

		for (int z=Z-2; z>=0; z--) {
			for (int y=0; y<N; y++) {
				for (int x=0; x<M; x++) {
					for (int d=0; d<4; d++) {
						for (int k=1; k<=K; k++) {
							int ny = y + dy[d] * k;
							int nx = x + dx[d] * k;
							if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
							if (map[ny][nx] == word[z+1] && map[y][x] == word[z]) {
								X[z][y][x] += X[z+1][ny][nx];
							}
						}
					}
				}
			}
		}
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				ans += X[0][y][x];
			}
		}
		
		System.out.println(ans);
	}
}