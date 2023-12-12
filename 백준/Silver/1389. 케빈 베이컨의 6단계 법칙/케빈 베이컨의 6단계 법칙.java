import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, A, B, ans, minsum;
	static int[][] dist;
	
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		dist = new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				dist[i][j] = i == j ? 0 : INF;
			}
		}
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			A = Integer.parseInt(tk.nextToken());
			B = Integer.parseInt(tk.nextToken());
			dist[A][B] = dist[B][A] = 1;
		}
		
		// floyd-warshall
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		minsum = INF;
		for (int i=1; i<=N; i++) {
			int sum = 0;
			for (int j=1; j<=N; j++)
				sum += dist[i][j];
			if (sum < minsum) {
				ans = i;
				minsum = sum;
			}
		}
		
		System.out.println(ans);
	}
}