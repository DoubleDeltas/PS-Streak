import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N;
	static int[] tgt;
	static boolean[] isPrime, used;
	static final int MAX = 5000 * 5001 / 2;
	
	public static void main(String[] args) throws Exception {
		isPrime = new boolean[MAX+1];

		for (int i=2; i<=MAX; i++)
			isPrime[i] = true;
			
		for (int i=2; i<=MAX; i++) {
			for (int j=i*2; j<=MAX; j+=i) {
				isPrime[j] = false;
			}
		}
		
		N = Integer.parseInt(rd.readLine());
		
		tgt = new int[N];
		used = new boolean[N+1];

		perm(0, 0);
		sb.append("NO");
		
		System.out.println(sb);
	}
	
	static void perm(int ti, int sum) {
		  if (isPrime[sum])
		    return;
		  if (ti == N) {
		    sb.append("YES\n");
		    for (int i=0; i<N; i++)
		    	sb.append(tgt[i]).append(' ');
		    System.out.println(sb);
		    System.exit(0);
		  }
		  
		  for (int i=1; i<=N; i++) {
			  if (used[i]) continue;
		      tgt[ti] = i;
		      used[i] = true;
		      perm(ti + 1, sum + i);
		      used[i] = false;
		  }
	}
}