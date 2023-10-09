import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, ans;

	static int[] memo;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(rd.readLine());
		
		// i = 0..9에 대해 memo = 0;
		
		memo = new int[Math.max(10, N)+1];
		
		for (int i=10; i<=N; i++) {
			for (int ps: properSubstrings(i)) {
				if (memo[i - ps] == 0) {// i - ps가 나오면 지게 됨
					memo[i] = ps;
					break;
				}
			}
		}
		
		if (memo[N] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(memo[N]);
		}

	}
	
	static List<Integer> properSubstrings(int n) {
		SortedSet<Integer> set = new TreeSet<>();
		int[] digits = new int[7];
		int tmp = n;
		for (int i=0; i<7; i++) {
			digits[6-i] = tmp % 10;
			tmp /= 10;
		}
		
		for (int i=0; i<7; i++) {
			for (int j=i; j<7; j++) {
				int num = 0;
				for (int k=i; k<=j; k++) {
					num = num * 10 + digits[k];
				}
				set.add(num);
			}
		}
		
		set.remove(0);	// 0과 자기 자신은 미포함
		set.remove(n);
		
		List<Integer> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		
		return list;
	}
}