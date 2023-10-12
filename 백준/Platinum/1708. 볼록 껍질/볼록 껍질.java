import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, ans;
	static Point[] points;
	static int[] st;
	static int size;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		points = new Point[N];
		for (int i=0; i<N; i++) {
			tk = new StringTokenizer(rd.readLine());
			long x = Integer.parseInt(tk.nextToken());
			long y = Integer.parseInt(tk.nextToken());
			points[i] = new Point(x, y);
		}
		st = new int[N];
		
		
		Arrays.sort(points, (p1, p2) -> p1.y != p2.y ? Long.compare(p1.y, p2.y) : Long.compare(p1.x, p2.x));
		Arrays.sort(points, 1, N, (p1, p2) -> ccw(points[0], p1, p2) < 0 ? -1 : 1 );

		push(0);
		push(1);
		for (int i=2; i<N; i++) {
			while (size >= 2 && ccw(points[st[size-2]], points[st[size-1]], points[i]) >= 0) pop();
			push(i);
		}
		
		ans = size;
		
		System.out.println(ans);
	}
	
	static class Point {
		long x, y;
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
	// 반시계면 >0, 직선이면 0, 시계면 <0
	static long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
	}
	
	static void push(int x) {
		st[size++] = x;
	}
	
	static int pop() {
		return st[--size];
	}
}