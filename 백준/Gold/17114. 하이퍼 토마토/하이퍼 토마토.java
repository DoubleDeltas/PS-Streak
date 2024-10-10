import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = null;

    static final int DIM = 11;
    static final int RIPEN = 1, UNRIPEN = 0, EMPTY = -1;

    static int ans;

    static int[] W = new int[DIM+1];
    static int[] P = new int[DIM+1];    // P[i] = W[1]*W[2]*...*W[i], P[DIM] = size
    static int[] map;
    static int cnt, tomato;

    static Queue<Integer> q = new ArrayDeque<>();
    static boolean[] enqueued;

    public static void main(String[] args) throws Exception {
        W[0] = 0;
        P[0] = 1;
        tk = new StringTokenizer(rd.readLine());
        for (int d=1; d<=DIM; d++) {
            W[d] = Integer.parseInt(tk.nextToken());
            P[d] = P[d-1] * W[d];
        }

        map = new int[P[DIM]];
        enqueued = new boolean[P[DIM]];

        for (int y=0; y<P[DIM]/W[1]; y++) {
            tk = new StringTokenizer(rd.readLine());
            for (int x=0; x<W[1]; x++) {
                int idx = y * W[1] + x;
                map[idx] = Integer.parseInt(tk.nextToken());
                if (map[idx] == RIPEN) {
                    q.offer(idx);
                    enqueued[idx] = true;
                    cnt++;
                }
                if (map[idx] != EMPTY)
                    tomato++;
            }
        }

        while (true) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                int x = q.poll();
                for (int d=1; d<=DIM; d++) {
                    for (int sgn=-1; sgn<=1; sgn+=2) {
                        int dx = sgn * P[d-1];
                        int nx = x + dx;
                        if ((P[d] + x) / P[d] != (P[d] + nx) / P[d]) continue;
                        if (map[nx] == EMPTY) continue;
                        if (enqueued[nx]) continue;

                        q.offer(nx);
                        enqueued[nx] = true;
                        map[nx] = RIPEN;
                        cnt++;
                    }
                }
            }
            if (q.isEmpty()) break;
            ans++;
        }

        if (cnt < tomato)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
