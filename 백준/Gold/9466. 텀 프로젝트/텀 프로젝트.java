import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;
	
	static int T, n, ans;
	static int[] A;
	
	static Queue<Integer> q = new ArrayDeque<>();
	static int[] indeg;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(rd.readLine());
			
			A = new int[n+1];
			indeg = new int[n+1];
			tk = new StringTokenizer(rd.readLine());
			for (int i=1; i<=n; i++) {
				A[i] = Integer.parseInt(tk.nextToken());
				indeg[A[i]]++;
			}

			ans = 0;
			q.clear();
			for (int i=1; i<=n; i++) {
				if (indeg[i] == 0) {
					q.add(i);
					ans++;
				}
			}
			
			while (!q.isEmpty()) {
				int n = q.poll();
				indeg[A[n]]--;
				if (indeg[A[n]] == 0) {
					q.add(A[n]);
					ans++;
				}
			}
			
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}


}