import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Solution {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int[] VALUE = new int[128];
	
	static int T, N, K, len, ans, qt, msw;	// quarter, most significant weight
	static char[] hex;
	static int[] dec = new int[4];
	static int[] tmp = new int[4];
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		init();
		
		T = Integer.parseInt(rd.readLine());
		for (int t = 1; t <= T; t++) {			
			tk = new StringTokenizer(rd.readLine());
			N = Integer.parseInt(tk.nextToken());
			K = Integer.parseInt(tk.nextToken());
			hex = rd.readLine().toCharArray();

			qt = N/4;
			msw = 1 << (4 * (qt - 1));	// 16^(qt - 1)
			
			for (int i=0; i<N; i++) {
				dec[i/qt] *= 16;
				dec[i/qt] += VALUE[hex[i]];
			}
			for (int i=0; i<4; i++) add(dec[i]);
			
			for (int i=0; i<qt-1; i++) {
				for (int j=0; j<4; j++) tmp[j] = dec[j] % 16;
				for (int j=0; j<4; j++) dec[j] = dec[j] / 16 + tmp[(j-1+4) % 4] * msw;
				
				for (int j=0; j<4; j++) add(dec[j]);
			}
			
			list.sort((a, b) -> -Integer.compare(a, b));
			
			ans = list.get(K-1);
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
			
			Arrays.fill(dec, 0);
			list.clear();
		}
		System.out.println(sb);
	}
	
	static void init() {
		for (int i='0'; i<='9'; i++) {
			VALUE[i] = i - '0';
		}
		for (int i='A'; i<='F'; i++) {
			VALUE[i] = 10 + i - 'A';
		}
	}
	
	static void add(int n) {
		if (!list.contains(n)) list.add(n);
	}
}