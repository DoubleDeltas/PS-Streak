import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N, M;
	static char[] S, T;
	static int[][] memo; // memo[i][j]: S[0~i], T[0~i]의 LCS 최대 길이
	static Node[][] nodes;
	
	public static void main(String[] args) throws Exception {
		S = rd.readLine().toCharArray();
		T = rd.readLine().toCharArray();
		N = S.length;
		M = T.length;
		
		memo = new int[N+1][M+1];
		nodes = new Node[N+1][M+1];
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if (S[i-1] == T[j-1]) {
					memo[i][j] = memo[i-1][j-1] + 1;
					nodes[i][j] = new Node(S[i-1], nodes[i-1][j-1]);
				} else {
					if (memo[i-1][j] > memo[i][j-1]) {
						memo[i][j] = memo[i-1][j];
						nodes[i][j] = nodes[i-1][j];
					} else {
						memo[i][j] = memo[i][j-1];
						nodes[i][j] = nodes[i][j-1];
					}
				}
			}
		}

		Node n = nodes[N][M];
		while (n != null) {
			sb.insert(0, n.c);
			n = n.prev;
		}
		
		System.out.println(memo[N][M]);
		System.out.println(sb);
	}
	
	static class Node {
		char c;
		Node prev;
		Node(char c, Node prev) {
			this.c = c;
			this.prev = prev;
		}
	}
}