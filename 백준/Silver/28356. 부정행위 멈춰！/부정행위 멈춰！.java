import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, k;
	static int[][] pattern = {{1, 2}, {3, 4}};
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		if (N == 1 && M == 1) {
			sb.append("1\n1");
		} else if (N == 1) {
			sb.append("2\n");
			for (int i=0; i<M; i++) {
				sb.append(i%2+1).append(' ');
			}
		} else if (M == 1) {
			sb.append("2\n");
			for (int i=0; i<N; i++) {
				sb.append(i%2+1).append('\n');
			}
		} else {
			sb.append("4\n");
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					sb.append(pattern[i%2][j%2]).append(' ');
				}
				sb.append('\n');
			}
		}
			
		System.out.println(sb);
	}
}