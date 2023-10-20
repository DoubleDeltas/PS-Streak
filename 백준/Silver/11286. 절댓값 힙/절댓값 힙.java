import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	
	public static void main(String[] args) throws Exception {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			int absdiff = Math.abs(a) - Math.abs(b);
			return absdiff != 0 ? absdiff : a - b;
		});
		
		N = Integer.parseInt(rd.readLine());
		
		for (int i=0; i<N; i++) {
			int x = Integer.parseInt(rd.readLine());
			if (x != 0)
				pq.add(x);
			else {
				Integer n = pq.poll();
				if (n == null)
					System.out.println(0);
				else
					System.out.println(n);
			}
		}
	}
}