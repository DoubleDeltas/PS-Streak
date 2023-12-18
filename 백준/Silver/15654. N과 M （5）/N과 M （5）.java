import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, A[];
	static int[] tgt;
	static boolean[] used;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		tk = new StringTokenizer(rd.readLine());
		A = new int[N];
		for (int i=0; i<N; i++)
			A[i] = Integer.parseInt(tk.nextToken());
		
		tgt = new int[M];
		used = new boolean[N];
		
		Arrays.sort(A);
		comb(0);
		
		System.out.println(sb);
	}
	
	static void comb(int tgtIdx) {
		if (tgtIdx == M) {
			for (int i=0; i<M; i++) {
				sb.append(A[tgt[i]]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (used[i]) continue;
			tgt[tgtIdx] = i;
			used[i] = true;
			comb(tgtIdx + 1);
			used[i] = false;
		}
		
	}
}