import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, A[], S, s;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		A = new int[N];
		tk = new StringTokenizer(rd.readLine());
		for (int i=0; i<N; i++)
			A[i] = Integer.parseInt(tk.nextToken());
		
		s = S = Integer.parseInt(rd.readLine());
		
		for (int i=0; i<N; i++) {
			int m = find(i, Math.min(i + s, N - 1));
			serialRevSwap(i, m);
			s -= m - i;
			if (s == 0) break;
		}
		
		for (int i=0; i<N; i++)
			sb.append(A[i]).append(' ');
		
		System.out.println(sb);
	}
	
	static int find(int s, int e) {
		int idx = s;
		for (int i=s+1; i<=e; i++) {
			if (A[i] > A[idx]) idx = i;
		}
		return idx;		
	}
	
	static void swap(int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	
	static void serialRevSwap(int s, int e) {
		for (int i=e; i>=s+1; i--) {
			swap(i, i-1);
		}
	}
}