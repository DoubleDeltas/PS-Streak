import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd
		= new BufferedReader(new InputStreamReader(System.in));	// 입력을 받기 위한 BufferedReader를 생성한다.
	static StringBuilder sb = new StringBuilder();				// 출력을 저장하기 위한 StringBuilder를 생성한다.
	static StringTokenizer tk = null;							// 입력 데이터를 분리하기 위한 StringTokenizer를 생성한다.
	
	static char[] codes = {'|', '-', '+', '1', '2', '3', '4', '.'};	// 각 블록을 나타내는 기호
	static int[] blockIdxes = new int[128];							// 각 기호에 해당하는 블록 index
	// blockIdxes['|'] = 0
	// blockIdxes['-'] = 1
	
	// 각 도로의 연결면을 나타내는 비트열의 배열을 생성한다. 각 인덱스 i는 roads[i]를 나타내며,
	// 비트열의 각 0, 1, 2, 3번 비트는 위, 오른쪽, 아래, 왼쪽과 연결되어 있는지의 여부를 가리킨다.
	static int[] sides = {0b0101, 0b1010, 0b1111, 0b0110, 0b0011, 0b1001, 0b1100, 0b0000};
	static int[] roads = new int[16];							// 연결 면에 따른 도로 인덱스 (잘못된 경우는 무시)
	
	static int[] dy = { -1,  0,  1,  0 };						// 주변 네 방향의 Y 좌표
	static int[] dx = {  0,  1,  0, -1 };						// 주변 네 방향의 X 좌표
	
	static int R, C, my, mx, dir, cy, cx;					// 정수 데이터를 입력받을 변수들을 선언한다.
	static char[][] map;										// 도면 정보를 입력받을 변수를 선언한다.
		
	public static void main(String[] args) throws Exception {
		for (int i=0; i<8; i++) blockIdxes[codes[i]] = i;		// blockIdxes를 초기화 한다.
		for (int i=0; i<8; i++) roads[sides[i]] = i;			// roads를 초기화 한다.
		tk = new StringTokenizer(rd.readLine());			// 입력을 받아 공백 문자로 분리한다.
		R = Integer.parseInt(tk.nextToken());				// 행 개수 입력 받는다.
		C = Integer.parseInt(tk.nextToken());				// 열 개수 입력 받는다.
		
		map = new char[R][C];
		for (int y=0; y<R; y++) {							// 각 줄을
			map[y] = rd.readLine().toCharArray();			// 입력받아 map에 반영한다.
			// M 좌표 찾기
			for (int x=0; x<C; x++) {
				if (map[y][x] == 'M') {
					my = y;
					mx = x;
				}
			}
		}
			
		// M에서 진행할 방향과 첫 도로 찾기
		for (int i=0; i<4; i++) {
			int ny = my + dy[i];
			int nx = mx + dx[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			int opp = (i + 2) % 4;
			int otherSide = sides[blockIdxes[map[ny][nx]]];
			if (((otherSide >> opp) & 1) == 1) {
				dir = i;
				cy = ny;
				cx = nx;
			}
		}
		
		// .을 찾을 때까지 도로를 따라간다.
		int side;
		while (map[cy][cx] != '.') {
			if (map[cy][cx] != '+') {	// +면 방향 변화 없이 직진, 아니라면...
				side = sides[blockIdxes[map[cy][cx]]];	// 현재 블록의 도로 연결면
				side &= ~(1 << ((dir + 2) % 4));		// 들어온 방향의 연결면 지우기
				for (int i=0; i<4; i++) {
					if ((side & 1 << i) != 0) {			// 이후 이 방향이 연결되어 있다면
						dir = i;						// 다음 방향을 그 방향으로
						break;
					}
				}
			}
			cy += dy[dir];								// 한칸 움직임
			cx += dx[dir];
		}
		
		// cy, cx 위치에 끼울 알맞은 도로를 찾는다.
		side = 0;
		for (int i=0; i<4; i++) {
			int ny = cy + dy[i];
			int nx = cx + dx[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			int opp = (i + 2) % 4;
			int otherSide = sides[blockIdxes[map[ny][nx]]];
			if (((otherSide >> opp) & 1) == 1) {		// 만약 비교하는 블록의 타일이 현재 블록과 연결되려 한다면,
				side |= 1 << i;							// 그 방향으로 연결면을 추가한다.
			}
		}
		// 최종 계산된 연결면에 맞는 타일이 정답이다.
		char roadCode = codes[roads[side]];
		sb.append(cy+1).append(' ').append(cx+1).append(' ').append(roadCode).append('\n');
		
		System.out.println(sb);
	}
}
