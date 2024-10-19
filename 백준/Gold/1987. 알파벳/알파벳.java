import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;
	
	static int[] DY = {1, 0, -1, 0};
	static int[] DX = {0, 1, 0, -1};
	
	static int R, C;
	static char[][] table;
	static boolean visited[][];
	static boolean used[] = new boolean['Z'+1];
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		
		table = new char[R][];
		for (int y=0; y<R; y++) {
			table[y] = rd.readLine().toCharArray();
		}
		
		visited = new boolean[R][C];

		System.out.println(dfs(0, 0, 1));
	}
	
	static int dfs(int y, int x, int d) {
		char ch = table[y][x];
		if (visited[y][x]) return d-1;
		if (used[ch]) return d-1;
		
		visited[y][x] = true;
		used[ch] = true;
		
		int max = d;
		for (int i=0; i<4; i++) {
			int ny = y + DY[i];
			int nx = x + DX[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			max = Math.max(max, dfs(ny, nx, d+1));
		}
		
		visited[y][x] = false;
		used[ch] = false;
		return max;
	}
}