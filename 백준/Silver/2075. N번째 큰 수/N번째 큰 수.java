import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(6);

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());

        tk = new StringTokenizer(rd.readLine());
        for (int i=0; i<N; i++) {   // 1st line
            int a = Integer.parseInt(tk.nextToken());
            pq.offer(a);
        }

        for (int i=1; i<N; i++) {   // rest n-1 lines
            tk = new StringTokenizer(rd.readLine());
            for (int j=0; j<N; j++) {
                int a = Integer.parseInt(tk.nextToken());
                pq.offer(a);
                pq.poll();
            }
        }

        System.out.println(pq.poll());  // nth greatest
    }
}
