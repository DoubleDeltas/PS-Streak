import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0001 <= 1번 도시방문
// 1010 <= 2번, 4번 도시 방문
// 1101 <= 1, 3, 4번 도시 방문

// memoi[i][j]: 현재 i번 도시에 있고, 거쳐온 도시가 j일 때, 남은 도시를 방문하는 데 필요한 최소 비용
// memoi[3][1100101] => 1, 3, 6, 7 도시를 거쳐 현재 3번 도시일 때, 남은 2, 4, 5를 방문하는 데 드는 최소비용
// memoi[2][1100111] + W[3][2]
// memoi[4][1101101] + W[3][4]
// memoi[5][1110101] + W[3][5] 중 최솟값 선택

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, INF = 999_999_999;
	static int[][] W, memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(rd.readLine());
		W = new int[N][N];
		
		memo = new int[N][1 << N];
		
		for (int i=0; i<N; i++) {
			tk = new StringTokenizer(rd.readLine());
			for (int j=0; j<N; j++) {
				W[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j < 1 << N; j++) {
				memo[i][j] = -1;
			}
		}
		
		System.out.println(f(0, 0b1));
	}
	
	static int f(int cur, int flag) {
		if (memo[cur][flag] != -1) return memo[cur][flag];
		if (flag == (1 << N) - 1) {
			if (W[cur][0] == 0) return INF;
			return memo[cur][flag] = W[cur][0];
		}
		memo[cur][flag] = INF;
		for (int i=1; i<N; i++) {
			if ((flag & (1 << i)) != 0) continue;
			if (W[cur][i] == 0) continue;
			memo[cur][flag] = Math.min(memo[cur][flag], f(i, flag | (1 << i)) + W[cur][i]);
		}
		return memo[cur][flag];
	}
}