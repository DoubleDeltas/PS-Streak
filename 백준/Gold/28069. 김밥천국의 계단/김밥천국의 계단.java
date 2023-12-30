import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, K;
	static Queue<Integer> q = new ArrayDeque<>();
	static boolean[] enqueued;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		
		enqueued = new boolean[N*2];
		
		q.offer(0);
		enqueued[0] = true;

		int d = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i=0; i<size; i++) {
				int n = q.poll();
				if (n == N) {
					System.out.println("minigimbob");
					return;
				}
				if (!enqueued[n+1]) {
					q.offer(n+1);
					enqueued[n+1] = true;
				}
				int m = n + n / 2;
				if (m <= N && !enqueued[m]) {
					q.offer(m);
					enqueued[m] = true;
				}
			}
			d++;
			if (d > K) break;
		}
		System.out.println("water");
	}
}
