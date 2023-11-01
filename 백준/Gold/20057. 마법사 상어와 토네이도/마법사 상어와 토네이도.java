import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, cy, cx, d, p, ans;
	static int[][] A;
	static int[][] B = new int[5][5];
	
	static int[][] pct = {
			{ 0,  0,  2,  0,  0},
			{ 0, 10,  7,  1,  0},
			{ 5,  0,  0,  0,  0},
			{ 0, 10,  7,  1,  0},
			{ 0,  0,  2,  0,  0},
	};
	
	static int[] dy = { 0,  1,  0, -1};
	static int[] dx = {-1,  0,  1,  0};
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		A = new int[N][N];
		for (int y=0; y<N; y++) {
			tk = new StringTokenizer(rd.readLine());
			for (int x=0; x<N; x++) {
				A[y][x] = Integer.parseInt(tk.nextToken());
			}
		}
		
		cy = cx = N/2;
		for (int i=0; i < 2*N - 1; i++) {
			d = i % 4;
			p = Math.min((i / 2) + 1, N - 1);
			for (int j=0; j<p; j++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				blow(ny, nx, d);
				cy = ny;
				cx = nx;
			}
		}
		
		System.out.println(ans);
		
	}
	
	static void blow(int ey, int ex, int d) {
		int ny, nx;
		B[2][2] = A[ey][ex];
		for (int dy=-2; dy<=2; dy++) {
			for (int dx=-2; dx<=2; dx++) {
				ny = ey + dy;
				nx = ex + dx;
				int ds = (int) A[ey][ex] * rpct(d, dy, dx) / 100;
				B[dy+2][dx+2] += ds;
				B[2][2] -= ds;
			}
		}
		
		// commit
		for (int dy=-2; dy<=2; dy++) {
			for (int dx=-2; dx<=2; dx++) {
				if (dy == 0 && dx == 0) continue;
				ny = ey + dy;
				nx = ex + dx;
				if (ny < 0 || ny >= N || nx < 0 || nx >= N)
					ans += B[dy+2][dx+2];
				else
					A[ny][nx] += B[dy+2][dx+2];
				B[dy+2][dx+2] = 0;
			}
		}
		
		// alpha
		ny = ey + dy[d];
		nx = ex + dx[d];
		if (ny < 0 || ny >= N || nx < 0 || nx >= N)
			ans += B[2][2];
		else
			A[ny][nx] += B[2][2];
		
		A[ey][ex] = 0;
	}
	
	static int rpct(int d, int dy, int dx) {
		int y = dy + 2;
		int x = dx + 2;
		switch (d) {
		case 0:
			return pct[y][x];
		case 1:
			return pct[x][4-y];
		case 2:
			return pct[4-y][4-x];
		case 3:
			return pct[4-x][y];
		}
		return -999;
	}
	
} 