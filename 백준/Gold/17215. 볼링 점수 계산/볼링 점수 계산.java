import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

	static int n, ans;
	static char[] in;
	static int[] fr;
	
	public static void main(String[] args) throws Exception {
		
		in = rd.readLine().toCharArray();
		n = in.length;
		
		fr = new int[n];
		
		int f = 1;
		int r = 1;
		int psc = 0;
		for (int i=0; i<n; i++) {
			fr[i] = f;
			int sc = 0;
			switch (in[i]) {
			case '-':
				sc = 0;
				break;
			case 'S':
				sc = 10;
				break;
			case 'P':
				sc = 10 - psc;
				break;
			default:
				sc = in[i] - '0';
				break;
			}
			ans += sc;
			if (i-2 >= 0 && fr[i-2] < 10 && in[i-2] == 'S')
				ans += sc;
			if (i-1 >= 0 && fr[i-1] < 10 && (in[i-1] == 'S' || in[i-1] == 'P'))
				ans += sc;
			if (in[i] == 'S' || r == 2) {
				f++;
				r = 1;
			}
			else r++;
			psc = sc;
		}
		

		System.out.println(ans);
	}
}