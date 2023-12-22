import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, h, w, ans;
	static char[][] map;
	static int keys;
	
	static Queue<Coord> q = new ArrayDeque<>();
	static boolean[][] enqueued;
	static List<List<Coord>> L = new ArrayList<>();
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		for (int i=0; i<26; i++) {
			L.add(new ArrayList<>());
		}
		
		T = Integer.parseInt(rd.readLine());
		for (int t=0; t<T; t++) {
			ans = 0;
			q.clear();
			for (int i=0; i<26; i++) {
				L.get(i).clear();
			}
			
			tk = new StringTokenizer(rd.readLine());
			h = Integer.parseInt(tk.nextToken());
			w = Integer.parseInt(tk.nextToken());
			
			map = new char[h][];
			for (int y=0; y<h; y++) {
				map[y] = rd.readLine().toCharArray();
			}
			enqueued = new boolean[h][w];
			
			keys = 0;
			char[] s = rd.readLine().toCharArray();
			if (s[0] != '0') {
				for (char c: s) keys |= 1 << (c - 'a');
			}
			
			for (int y=0; y<h; y++) {
				enqueue(y, 0);
				enqueue(y, w-1);
			}
			for (int x=1; x<w-1; x++) {
				enqueue(0, x);
				enqueue(h-1, x);
			}
			
			while (!q.isEmpty()) {
				Coord coord = q.poll();
				int y = coord.y;
				int x = coord.x;
				
				for (int d=0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny < 0 || ny >= h || nx < 0 || nx >= w) continue;
					if (enqueued[ny][nx]) continue;
					enqueue(ny, nx);
				}
			}
			
			sb.append(ans).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void enqueue(int y, int x) {
		if (map[y][x] == '*') return;
		Coord coord = new Coord(y, x);
		
		if (map[y][x] == '.') {
			q.offer(coord);
			enqueued[y][x] = true;
		}
		if (map[y][x] == '$') {
			ans++;
			q.offer(coord);
			enqueued[y][x] = true;
		}
		if ('a' <= map[y][x] && map[y][x] <= 'z') {
			int idx = map[y][x] - 'a';
			keys |= 1 << idx;
			for (Coord c: L.get(map[y][x] - 'a')) {
				q.offer(c);
				enqueued[c.y][c.x] = true;
			}
			q.offer(coord);
			enqueued[y][x] = true;
		}
		if ('A' <= map[y][x] && map[y][x] <= 'Z') {
			int idx = map[y][x] - 'A';
			if ((keys >> idx & 1) == 1) {
				q.offer(new Coord(y, x));
				enqueued[y][x] = true;
			}
			else
				L.get(idx).add(coord);
		}
	}
	
	static class Coord {
		int y, x;
		public Coord(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}