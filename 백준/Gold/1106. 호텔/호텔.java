import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int C, N, maxp, ans;
	static int[] co, p;
	static int[] memo;	// 고객 i명을 늘이기 위한 최소비용
	
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		C = Integer.parseInt(tk.nextToken());
		N = Integer.parseInt(tk.nextToken());
		
		
		co = new int[N];
		p = new int[N];
		for (int i=0; i<N; i++) {
			tk = new StringTokenizer(rd.readLine());
			co[i] = Integer.parseInt(tk.nextToken());
			p[i] = Integer.parseInt(tk.nextToken());
			maxp = Math.max(maxp, p[i]);
		}

		memo = new int[C + maxp];
		
		for (int i=1; i < C + maxp; i++) {
			memo[i] = INF;
			for (int j=0; j<N; j++) {
				if (i < p[j]) continue;
				memo[i] = Math.min(memo[i], memo[i - p[j]] + co[j]);
			}
		}
		
		ans = INF;
		for (int i = C; i < C + maxp; i++) {
			ans = Math.min(ans, memo[i]);
		}
		
		System.out.println(ans);
	}
}