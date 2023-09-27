import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int T, n;
	static int[] x;
	static int[] y;
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(rd.readLine());
		for (int t=1; t<=T; t++) {
			n = Integer.parseInt(rd.readLine());
			
			x = new int[n+2];
			y = new int[n+2];
			for (int i=0; i<n+2; i++) {
				tk = new StringTokenizer(rd.readLine());
				x[i] = Integer.parseInt(tk.nextToken());
				y[i] = Integer.parseInt(tk.nextToken());
			}
			
			adjList.clear();
			for (int i=0; i<n+2; i++) adjList.add(new ArrayList<>());
			for (int i=0; i<n+2; i++) {
				for (int j=i+1; j<n+2; j++) {
					if (Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]) <= 1000) { 
						adjList.get(i).add(j);
						adjList.get(j).add(i);
					}
				}
			}
			
			bfs();
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] enqueued = new boolean[n+2];
		
		q.offer(0);
		enqueued[0] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			if (v == n+1) {
				sb.append("happy\n");
				return;
			}
			
			for (int n: adjList.get(v)) {
				if (enqueued[n]) continue;
				q.offer(n);
				enqueued[n] = true;
			}
		}
		sb.append("sad\n");
	}
}