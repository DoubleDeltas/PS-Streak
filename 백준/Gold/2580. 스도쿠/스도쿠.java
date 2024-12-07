import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int[][] board = new int[9][9];
    static int[] rowflag = new int[9];
    static int[] colflag = new int[9];
    static int[][] blockflag = new int[3][3];
    static boolean end;

    public static void main(String[] args) throws Exception {
        for (int i=0; i<9; i++) {
            tk = new StringTokenizer(rd.readLine());
            for (int j=0; j<9; j++) {
                set(i, j, Integer.parseInt(tk.nextToken()));
            }
        }

        bfs(0, 1);
    }

    static boolean check(int i, int j, int n) {
        if ((rowflag[i] >> n & 1) == 1) return false;
        if ((colflag[j] >> n & 1) == 1) return false;
        if ((blockflag[i/3][j/3] >> n & 1) == 1) return false;
        return true;
    }

    static void bfs(int idx, int n) {
        if (end) return;
        if (idx == 81) {
            print();
            end = true;
            return;
        }
        if (n == 10) return;

        int y = idx / 9;
        int x = idx % 9;

        if (board[y][x] != 0) {	// by-pass
            bfs(idx + 1, n);
            return;
        }
        if (check(y, x, n)) {
            set(y, x, n);
            bfs(idx + 1, 1);
        }
        reset(y, x);
        bfs(idx, n + 1);
    }

    static void print() {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void set(int y, int x, int n) {
        board[y][x] = n;
        rowflag[y] |= 1 << board[y][x];
        colflag[x] |= 1 << board[y][x];
        blockflag[y/3][x/3] |= 1 << board[y][x];
    }

    static void reset(int y, int x) {
        rowflag[y] &= ~(1 << board[y][x]);
        colflag[x] &= ~(1 << board[y][x]);
        blockflag[y/3][x/3] &= ~(1 << board[y][x]);
        board[y][x] = 0;
    }
}
