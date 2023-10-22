import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, A[], M, S, E;
	static boolean memo[][];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		A = new int[N+1];
		tk = new StringTokenizer(rd.readLine());
		for (int i=1; i<=N; i++)
			A[i] = Integer.parseInt(tk.nextToken());
		
		memo = new boolean[N+1][N+1];
		for (int i=1; i<=N; i++) {
			int l, r;
			l = r = i;
			while (l >= 1 && r <= N && A[l] == A[r])
				memo[l--][r++] = true;
			l = i;
			r = i + 1;
			while (l >= 1 && r <= N && A[l] == A[r])
				memo[l--][r++] = true;
		}
		
		M = Integer.parseInt(rd.readLine());
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			S = Integer.parseInt(tk.nextToken());
			E = Integer.parseInt(tk.nextToken());
			sb.append(memo[S][E] ? 1 : 0).append('\n');
		}
		
		System.out.println(sb);
	}
}