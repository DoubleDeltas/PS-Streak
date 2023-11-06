import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int n, m, a, b, c;
	static int[][] dist;
	
	static final int INF = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(rd.readLine());
		m = Integer.parseInt(rd.readLine());
		
		dist = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				dist[i][j] = i == j ? 0 : INF;
			}
		}
		
		for (int i=0; i<m; i++) {
			tk = new StringTokenizer(rd.readLine());
			a = Integer.parseInt(tk.nextToken());
			b = Integer.parseInt(tk.nextToken());
			c = Integer.parseInt(tk.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		for (int k=1; k<=n; k++) {
			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (dist[i][j] == INF)
					sb.append("0 ");
				else
					sb.append(dist[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}