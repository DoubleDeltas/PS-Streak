import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static double x, y, X, Y, c0;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		x = Double.parseDouble(tk.nextToken());
		y = Double.parseDouble(tk.nextToken());
		c0 = Double.parseDouble(tk.nextToken());
		
		X = x*x;
		Y = y*y;
		
		double l = 0.0;
		double r = Math.min(x, y);
		while (r - l > 10e-4) {
			double m = (l + r) * 0.5;
			double M = m*m;
			double a = Math.sqrt(X - M);
			double b = Math.sqrt(Y - M);
			double c = a * b / (a + b);
			if (c > c0) l = m;
			else r = m;
		}
		
		System.out.println(l);
	}
}
