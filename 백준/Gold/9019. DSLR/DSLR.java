import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;
	static StringBuilder buf = new StringBuilder();
	
	static int A, B;
    
	static final char[] NAME = {'D', 'S', 'L', 'R'};
		
    static int[][] f;
    
    static Queue<Node> q = new ArrayDeque<>();
    static boolean[] enqueued = new boolean[10000];
    
    public static void main(String[] args) throws Exception {
        f = new int[10000][4];
        for (int i=0; i<10000; i++) {
            f[i][0] = (2 * i) % 10000;
            f[i][1] = (i + 9999) % 10000;
            f[i][2] = (i % 1000) * 10 + i / 1000;
            f[i][3] = (i / 10) + (i % 10) * 1000;
        }

        int T = Integer.parseInt(rd.readLine());
        for (int t=1; t<=T; t++) {        	
        	tk = new StringTokenizer(rd.readLine());
            A = Integer.parseInt(tk.nextToken());
            B = Integer.parseInt(tk.nextToken());
            bfs();
        }
        System.out.println(sb);
    }
    
    static void bfs() {
        Arrays.fill(enqueued, false);
        q.clear();
        
        for (int i=0; i<4; i++) {
        	int x = f[A][i];
            q.add(new Node(x, i, null));
            enqueued[x] = true;
        }
        
        while (true) {
        	Node node = q.poll();
        	
        	int n = node.n;
        	if (n == B) {
        		print(node);
        		break;
        	}
        	
        	for (int i=0; i<4; i++) {
        		int x = f[n][i];
        		if (enqueued[x]) continue;
        		enqueued[x] = true;
        		q.add(new Node(x, i, node));
        	}
        }
    }
    
    static void print(Node node) {
    	buf.setLength(0);
    	while (node != null) {
    		buf.insert(0, NAME[node.op]);
    		node = node.prev;
    	}
    	sb.append(buf).append('\n');
    }

	static class Node {
		int n;
		int op;
		Node prev;
		
		Node(int n, int op, Node prev) {
			this.n = n;
			this.op = op;
			this.prev = prev;
		}
	}
}