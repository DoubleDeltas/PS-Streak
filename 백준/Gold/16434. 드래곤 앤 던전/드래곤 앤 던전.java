import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tk = null;

	static int N;
	static long ATK, atk, curHp;
	static Room[] rooms;
	
	public static void main(String[] args) throws Exception {
		tk = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(tk.nextToken());
		ATK = Integer.parseInt(tk.nextToken());
		
		rooms = new Room[N+1];
		for (int i=1; i<=N; i++) {
			tk = new StringTokenizer(rd.readLine());
			int t = Integer.parseInt(tk.nextToken());
			int a = Integer.parseInt(tk.nextToken());
			int h = Integer.parseInt(tk.nextToken());
			rooms[i] = new Room(t, a, h);
		}
		
		long l = 1L;
		long r = 123_456L * 1_000_000L * 1_000_000L + 1L;
		long m = -1L;
		while (l < r) {
			m = (l + r) / 2;
			if (simulate(m))
				r = m;
			else
				l = m + 1;
		}

		System.out.println(l);
	}
	
	static boolean simulate(long maxHp) {
		atk = ATK;
		curHp = maxHp;
		for (int i=1; i<=N; i++) {
			Room room = rooms[i];
			switch (room.t) {
			case 1:
				long enemyAtk = room.a;
				long enemyHp = room.h;
				
				long hit = (enemyHp + atk - 1) / atk;
				long enemyHit = (curHp + enemyAtk - 1) / enemyAtk;
				if (hit <= enemyHit)
					curHp -= (hit - 1) * enemyAtk;
				else
					return false;
				break;
			default:
				atk += room.a;
				curHp = Math.min(curHp + room.h, maxHp);
				break;	
			}
		}
		return true;
	}
	
	static class Room {
		int t, a, h;
		Room(int t, int a, int h) {
			this.t = t;
			this.a = a;
			this.h = h;
		}
	}
}