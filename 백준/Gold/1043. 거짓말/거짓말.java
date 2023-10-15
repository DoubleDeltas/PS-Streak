import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tk = null;

	static int N, M, k, ans;
	static List<List<Integer>> queries = new ArrayList<>();
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		// 진실 아는 사람 세팅
		makeSet();
		tk = new StringTokenizer(rd.readLine());
		int k = Integer.parseInt(tk.nextToken());
		for (int i=0; i<k; i++) {
			int p = Integer.parseInt(tk.nextToken());
			parent[p] = 0;
		}
		
		// 입력 저장
		for (int i=0; i<M; i++) {
			List<Integer> party = new ArrayList<>();
			
			tk = new StringTokenizer(rd.readLine());
			k = Integer.parseInt(tk.nextToken());
			for (int j=0; j<k; j++) {
				int p = Integer.parseInt(tk.nextToken());
				party.add(p);
			}
			queries.add(party);
		}
		
		// union-find
		// 파티에 참여한 모든 사람을 같은 집합에 묶음
		for (List<Integer> party: queries) {
			int prev = party.get(0);
			k = party.size();
			for (int j=1; j<k; j++) {
				int p = party.get(j);
				union(prev, p);
				prev = p;
			}
		}
		
		// 집합 
		Q_LOOP: for (List<Integer> party: queries) {
			for (int p: party) {
				if (findSet(p) == 0) continue Q_LOOP;
			}
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static void makeSet() {
		parent = new int[N+1];
		for (int i=1; i<=N; i++)
			parent[i] = i;
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	// union은 작은 번호로 병합해야 함
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px == py) return false;
		if (px < py) parent[py] = px;
		else parent[px] = py;
		return true;
	}
}