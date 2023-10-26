import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N;
	static long min, ansA, ansB;
	static long[] val;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		
		val = new long[N];
		
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<N; i++) {
			val[i] = Long.parseLong(tk.nextToken());
		}

		min = Long.MAX_VALUE;
		int p = 0;
		int q = N - 1;
		while (p < q) {
			long sum = val[p] + val[q];
			long cur = Math.abs(sum);
			if (cur < min) {
				min = cur;
				ansA = val[p];
				ansB = val[q];
			}
			if (sum == 0) break;
			else if (sum > 0) q--;
			else p++;
		}

		sb.append(ansA).append(' ').append(ansB);
		System.out.println(sb);
	}
}