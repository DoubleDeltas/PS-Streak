import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static char[] T, P;
	static int n, m, ans;
	static int[] pi;

	public static void main(String[] args) throws Exception {
		T = rd.readLine().toCharArray();
		P = rd.readLine().toCharArray();
		n = T.length;
		m = P.length;
		
		if (n < m) {
			System.out.println(0);
			return;
		}
		
		// pi[] 만들기
		pi = new int[m];
		int j = 0;
		for (int i=1; i<m; i++) {
			while (j > 0 && P[i] != P[j]) j = pi[j-1];
			if (P[i] == P[j]) pi[i] = ++j;
		}
		
		// T와 P 비교
		j = 0;
		for (int i=0; i<n; i++) {
			while (j > 0 && T[i] != P[j]) j = pi[j-1];
			if (T[i] == P[j]) {
				j++;
				if (j == m) {
					ans++;
					sb.append(i-m+1 + 1).append(' ');
					j = pi[j-1];
				}
			}
		}
		
		System.out.println(ans);
		System.out.println(sb);
	}
}