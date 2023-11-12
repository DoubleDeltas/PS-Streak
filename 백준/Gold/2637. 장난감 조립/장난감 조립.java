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

	static int N, M, X, Y, K;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[][] ingred;
	
	static int[] parts, indeg;
	static Queue<Integer> q = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		M = Integer.parseInt(rd.readLine());
		
		ingred = new int[N+1][N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		indeg = new int[N+1];
		
		for (int i=0; i<M; i++) {
			tk = new StringTokenizer(rd.readLine());
			X = Integer.parseInt(tk.nextToken());
			Y = Integer.parseInt(tk.nextToken());
			K = Integer.parseInt(tk.nextToken());
			
			graph.get(X).add(Y);
			ingred[X][Y] = K;
			indeg[Y]++;
		}
		parts = new int[N+1];
		parts[N] = 1;
		
		q.offer(N);
		while (!q.isEmpty()) {
			int v = q.poll();

			for (int i=1; i<=N; i++)
				parts[i] += ingred[v][i] * parts[v];
			parts[v] = 0;
			
			for (int n: graph.get(v)) {
				if (--indeg[n] == 0 && !graph.get(n).isEmpty()) {
					q.offer(n);
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (graph.get(i).isEmpty()) {	// basic
				sb.append(i).append(' ').append(parts[i]).append('\n');
			}
		}
		
		System.out.println(sb);
	}
}