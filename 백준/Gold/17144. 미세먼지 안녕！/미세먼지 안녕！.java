import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int R, C, T, ans, cy;
	static int[][] A, tmp;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static List<Coord> ccwPath = new ArrayList<>();
	static List<Coord> cwPath = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		T = Integer.parseInt(tk.nextToken());
		
		A = new int[R][C];
		tmp = new int[R][C];
		
		for (int r=0; r<R; r++) {
			tk = new StringTokenizer(rd.readLine());
			for (int c=0; c<C; c++) {
				A[r][c] = tmp[r][c] = Integer.parseInt(tk.nextToken());
				if (cy == 0 && A[r][c] == -1) {
					cy = r;
				}
			}
		}
		
		initCcwPath();
		initCwPath();
		
		for (int t=0; t<T; t++) {
			spread();
			commit();
			circulate(ccwPath);
			circulate(cwPath);
			commit();
		}
		
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				if (A[r][c] != -1) ans += A[r][c];
			}
		}
		
		System.out.println(ans);
	}

	// 공기청정기는 항상 1열, 위 아래로 2칸 이상 떨어져있음 
	static void initCcwPath() {
		int y = cy;
		int x = 0;
		for (int i=0; i<C-1; i++) {
			x++;
			ccwPath.add(new Coord(y, x));
		}
		for (int i=0; i<cy; i++) {
			y--;
			ccwPath.add(new Coord(y, x));
		}
		for (int i=0; i<C-1; i++) {
			x--;
			ccwPath.add(new Coord(y, x));
		}
		for (int i=0; i<cy-1; i++) {
			y++;
			ccwPath.add(new Coord(y, x));
		}
	}
	
	static void initCwPath() {
		int y = cy+1;
		int x = 0;
		for (int i=0; i<C-1; i++) {
			x++;
			cwPath.add(new Coord(y, x));
		}
		for (int i=0; i<R-(cy+1)-1; i++) {
			y++;
			cwPath.add(new Coord(y, x));
		}
		for (int i=0; i<C-1; i++) {
			x--;
			cwPath.add(new Coord(y, x));
		}
		for (int i=0; i<R-(cy+1)-2; i++) {
			y--;
			cwPath.add(new Coord(y, x));
		}
	}
	
	static void commit() {
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				A[r][c] = tmp[r][c];
			}
		}
	}
	
	static void spread() {
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				int s = A[r][c] / 5;
				for (int d=0; d<4; d++) {
					int nr = r + dy[d];
					int nc = c + dx[d];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if (A[nr][nc] == -1) continue;
					
					tmp[nr][nc] += s;
					tmp[r][c] -= s;
				}
			}
		}
	}
	
	static void circulate(List<Coord> path) {
		int prev = 0;
		for (Coord coord: path) {
			int y = coord.y;
			int x = coord.x;
			tmp[y][x] = prev;
			prev = A[y][x];		
		}
	}
	
	static class Coord {
		int y, x;
		Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}