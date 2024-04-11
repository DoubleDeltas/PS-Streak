import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M, A[];
	static int[] buf;
	static Set<String> set = new HashSet<>();

	static StringBuilder sb2;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		A = new int[N];
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<N; i++)
			A[i] = Integer.parseInt(tk.nextToken());
		
		Arrays.sort(A);
		
		buf = new int[M];
		
		for (int i=0; i<N; i++)
			perm(i, 0);

		System.out.println(sb);
	}

	static void perm(int idx, int d) {
		if (d == M) {
			sb2 = new StringBuilder();
			for (int i=0; i<M; i++) {
				sb2.append(buf[i]).append(' ');
			}
			sb2.append('\n');
			
			String s = sb2.toString();
			if (set.contains(s)) return;
			sb.append(s);
			set.add(s);
			return;
		}
		
		buf[d] = A[idx];

		for (int i=0; i<N; i++) {
			if (buf[d] > A[i]) continue;
			perm(i, d + 1);
		}
		
	}
}