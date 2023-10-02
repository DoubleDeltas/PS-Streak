import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;
	
	// 윗면(흰색), 아랫면(노란색), 앞면(빨간색), 뒷면(오렌지색), 왼쪽면(초록색), 오른쪽(파란색)
	static char[] colors = new char[54];
	
	static int T, n;
	static int[][][] spin = {
			{		// U+
				{ 0,  1,  2,  5,  8,  7,  6,  3, 29, 28, 27, 47, 46, 45, 20, 19, 18, 38, 37, 36},
				{ 2,  5,  8,  7,  6,  3,  0,  1, 47, 46, 45, 20, 19, 18, 38, 37, 36, 29, 28, 27}
			}, {	// U-
				{ 0,  1,  2,  5,  8,  7,  6,  3, 29, 28, 27, 47, 46, 45, 20, 19, 18, 38, 37, 36},
				{ 6,  3,  0,  1,  2,  5,  8,  7, 38, 37, 36, 29, 28, 27, 47, 46, 45, 20, 19, 18}
			}, {	// D+
				{ 9, 10, 11, 14, 17, 16, 15, 12, 24, 25, 26, 51, 52, 53, 33, 34, 35, 42, 43, 44},
				{11, 14, 17, 16, 15, 12,  9, 10, 51, 52, 53, 33, 34, 35, 42, 43, 44, 24, 25, 26}
			}, {	// D-
				{ 9, 10, 11, 14, 17, 16, 15, 12, 24, 25, 26, 51, 52, 53, 33, 34, 35, 42, 43, 44},
				{15, 12,  9, 10, 11, 14, 17, 16, 42, 43, 44, 24, 25, 26, 51, 52, 53, 33, 34, 35}
			}, {	// F+
				{18, 19, 20, 23, 26, 25, 24, 21,  6,  7,  8, 45, 48, 51, 11, 10,  9, 44, 41, 38},
				{20, 23, 26, 25, 24, 21, 18, 19, 45, 48, 51, 11, 10,  9, 44, 41, 38,  6,  7,  8}
			}, {	// F-
				{18, 19, 20, 23, 26, 25, 24, 21,  6,  7,  8, 45, 48, 51, 11, 10,  9, 44, 41, 38},
				{24, 21, 18, 19, 20, 23, 26, 25, 44, 41, 38,  6,  7,  8, 45, 48, 51, 11, 10,  9}
			}, {	// B+
				{27, 28, 29, 32, 35, 34, 33, 30,  2,  1,  0, 36, 39, 42, 15, 16, 17, 53, 50, 47},
				{29, 32, 35, 34, 33, 30, 27, 28, 36, 39, 42, 15, 16, 17, 53, 50, 47,  2,  1,  0} 
			}, {	// B-
				{27, 28, 29, 32, 35, 34, 33, 30,  2,  1,  0, 36, 39, 42, 15, 16, 17, 53, 50, 47},
				{33, 30, 27, 28, 29, 32, 35, 34, 53, 50, 47,  2,  1,  0, 36, 39, 42, 15, 16, 17}
			}, {	// L+
				{36, 37, 38, 41, 44, 43, 42, 39,  0,  3,  6, 18, 21, 24,  9, 12, 15, 35, 32, 29},
				{38, 41, 44, 43, 42, 39, 36, 37, 18, 21, 24,  9, 12, 15, 35, 32, 29,  0,  3,  6}
			}, {	// L-
				{36, 37, 38, 41, 44, 43, 42, 39,  0,  3,  6, 18, 21, 24,  9, 12, 15, 35, 32, 29},
				{42, 39, 36, 37, 38, 41, 44, 43, 35, 32, 29,  0,  3,  6, 18, 21, 24,  9, 12, 15}
			}, {	// R+
				{45, 46, 47, 50, 53, 52, 51, 48,  8,  5,  2, 27, 30, 33, 17, 14, 11, 26, 23, 20},
				{47, 50, 53, 52, 51, 48, 45, 46, 27, 30, 33, 17, 14, 11, 26, 23, 20,  8,  5,  2}
			}, {	// R-
				{45, 46, 47, 50, 53, 52, 51, 48,  8,  5,  2, 27, 30, 33, 17, 14, 11, 26, 23, 20},
				{51, 48, 45, 46, 47, 50, 53, 52, 26, 23, 20,  8,  5,  2, 27, 30, 33, 17, 14, 11}
			}
	};
		
	public static void main(String[] args) throws Exception {
		init();
		T = Integer.parseInt(rd.readLine());
		for (int t=0; t<T; t++) {
			initCube();
			
			n = Integer.parseInt(rd.readLine());
			tk = new StringTokenizer(rd.readLine());
			for (int i=0; i<n; i++) {
				char[] dir = tk.nextToken().toCharArray();
				int idx = idx(dir);
				spin(idx);
			}
			printTop();
		}
		System.out.println(sb);
	}
	

	static int[] spinIdxes = new int['Z'+1];
	static void init() {
		spinIdxes['U'] = 0;
		spinIdxes['D'] = 2;
		spinIdxes['F'] = 4;
		spinIdxes['B'] = 6;
		spinIdxes['L'] = 8;
		spinIdxes['R'] = 10;
	}
	
	static final char[] INIT_COLORS = {'w', 'y', 'r', 'o', 'g', 'b'};
	static void initCube() {
		for (int i=0; i<54; i++) {
			colors[i] = INIT_COLORS[i/9];
		}
	}
	
	static int idx(char[] dir) {
		return spinIdxes[dir[0]] + (dir[1] == '+' ? 1 : 0);
	}

	static char[] tmps = new char[20];
	static void spin(int idx) {
		for (int i=0; i<20; i++) {
			tmps[i] = colors[spin[idx][1][i]];
		}
		for (int i=0; i<20; i++) {
			colors[spin[idx][0][i]] = tmps[i];
		}
	}
	
	static void printTop() {
		sb.append(colors[0]).append(colors[1]).append(colors[2]).append('\n')
		  .append(colors[3]).append(colors[4]).append(colors[5]).append('\n')
		  .append(colors[6]).append(colors[7]).append(colors[8]).append('\n');
	}
}