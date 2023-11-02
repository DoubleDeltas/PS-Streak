import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int R, C, M, ans;
	static Shark[] sharks;
	static int[][] map;
	
	// 0: dummy, 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
	static int[] dy = { 0, -1,  1,  0,  0};
	static int[] dx = { 0,  0,  0,  1, -1};
	static int[] opp = {0, 2, 1, 4, 3};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		map = new int[R+1][C+1];
		sharks = new Shark[M];
		
		// 맵 초기화
		for (int r=1; r<=R; r++) {
			for (int c=1; c<=C; c++) {
				map[r][c] = -1;
			}
		}
		
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			int r = Integer.parseInt(tk.nextToken());
			int c = Integer.parseInt(tk.nextToken());
			int s = Integer.parseInt(tk.nextToken());	// 속력
			int d = Integer.parseInt(tk.nextToken());	// 이동 방향
			int z = Integer.parseInt(tk.nextToken());	// 크기
			
			map[r][c] = i;
			sharks[i] = new Shark(i, r, c, s, d, z);
		}
		
		for (int x=1; x<=C; x++) {	// 낚시왕 움직임
			// 상어 잡기
			for (int y=1; y<=R; y++) {
				if (map[y][x] != -1) {
					ans += sharks[map[y][x]].z;
					sharks[map[y][x]] = null;
					break;
				}
			}
			
			// 상어 이동
			for (Shark shark: sharks) {
				if (shark == null) continue;
				int s = shark.s;

				if (shark.d == 1 || shark.d == 2) {
					for (int i=0; i < s % (2*(R-1)); i++) {
						int nr = shark.r + dy[shark.d];
						if (nr < 1 || nr > R) {
							shark.d = opp[shark.d];
							nr = shark.r + dy[shark.d];
						}
						shark.r = nr;
					}
				}
				else {
					for (int i=0; i < s % (2*(C-1)); i++) {
						int nc = shark.c + dx[shark.d];
						if (nc < 1 || nc > C) {
							shark.d = opp[shark.d];
							nc = shark.c + dx[shark.d];
						}
						shark.c = nc;
					}
				}
			}
			
			// 맵 초기화
			for (int r=1; r<=R; r++) {
				for (int c=1; c<=C; c++) {
					map[r][c] = -1;
				}
			}
			
			// 맵 재구성하며 잡아먹기
			for (Shark shark: sharks) {
				if (shark == null) continue;
				if (map[shark.r][shark.c] != -1) {
					Shark rival = sharks[map[shark.r][shark.c]];
					if (shark.z > rival.z) {
						sharks[rival.id] = null;
						map[shark.r][shark.c] = shark.id;
					}
					else {
						sharks[shark.id] = null;
					}
				}
				else {
					map[shark.r][shark.c] = shark.id;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static class Shark {
		int id, r, c, s, d, z;

		public Shark(int id, int r, int c, int s, int d, int z) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}