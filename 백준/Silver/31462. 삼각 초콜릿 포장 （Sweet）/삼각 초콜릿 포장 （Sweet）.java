import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static boolean[][] isBlue;

    static boolean[][] isChecked;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(rd.readLine());

        isBlue = new boolean[N][N];
        for (int y=0; y<N; y++) {
            char[] s = rd.readLine().toCharArray();
            for (int x=0; x<=y; x++) {
                isBlue[y][x] = s[x] == 'B';
            }
        }
        isChecked = new boolean[N][N];

        for (int y=0; y<N; y++) {
            for (int x=0; x<=y; x++) {
                if (isChecked[y][x])
                    continue;
                if (isBlue[y][x]) {
                    if (validBlue(y, x)) {
                        isChecked[y][x] = isChecked[y][x + 1] = isChecked[y + 1][x + 1] = true;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
                else {
                    if (validRed(y, x)) {
                        isChecked[y][x] = isChecked[y + 1][x] = isChecked[y + 1][x + 1] = true;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(1);
    }

    static boolean validRed(int y, int x) {
        return checkRed(y + 1, x) && checkRed(y + 1, x + 1);
    }

    static boolean validBlue(int y, int x) {
        return checkBlue(y, x + 1) && checkBlue(y + 1, x + 1);
    }

    static boolean checkBlue(int y, int x) {
        return checkCell(y, x) && isBlue[y][x] && !isChecked[y][x];
    }

    static boolean checkRed(int y, int x) {
        return checkCell(y, x) && !isBlue[y][x] && !isChecked[y][x];
    }

    static boolean checkCell(int y, int x) {
        return y < N && x <= y;
    }
}