import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, n, K, A[];
	static int[] B;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		tk = new StringTokenizer(rd.readLine());
		B = new int[10001];
		for (int i=0; i<N; i++) {
			K = Integer.parseInt(tk.nextToken());
			if (B[K] == 0) n++;
			B[K]++;
		}
		A = new int[n];
		int j = 0;
		for (int i=1; i<=10000; i++) {
			if (B[i] > 0) A[j++] = i;
		}
		
		tgt = new int[M];
		
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
		
		for (int i=0; i<n; i++) {
			if (B[A[i]] == 0) continue;
			
			tgt[tgtIdx] = i;
			B[A[i]]--;
			comb(tgtIdx + 1);
			B[A[i]]++;
		}
		
	}
}