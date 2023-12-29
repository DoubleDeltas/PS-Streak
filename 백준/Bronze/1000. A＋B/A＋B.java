import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;
	
	static int A, B;

	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		A = Integer.parseInt(tk.nextToken());
		B = Integer.parseInt(tk.nextToken());

		System.out.println(A + B);
	}
}