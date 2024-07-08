import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final short[] OPS = {
            0b0_000_000_111,  // 1행 뒤집기
            0b0_000_111_000,  // 2행 뒤집기
            0b0_111_000_000,  // 3행 뒤집기
            0b0_001_001_001,  // 1열 뒤집기
            0b0_010_010_010,  // 2열 뒤집기
            0b0_100_100_100,  // 3열 뒤집기
            0b0_100_010_001,  // 좌하 대각선
            0b0_001_010_100,  // 우하 대각선
    };

    static int T, ans;

    static short ori;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(rd.readLine());
        for (int t=1; t<=T; t++) {
            ori = 0;
            ans = Integer.MAX_VALUE;

            // input
            for (int y=0; y<3; y++) {
                char[] s = rd.readLine().toCharArray();
                for (int x=0; x<3; x++) {
                    ori |= s[2*x] == 'H' ? (short) (1 << (y*3+x)) : 0;
                }
            }

            for (int mask=0; mask<(1 << 8); mask++) {
                byte opcode = (byte) mask;
                int result = flip(opcode);
                if (result == (1 << 9) - 1 || result == 0)
                    ans = Math.min(ans, Integer.bitCount(mask));
            }
            if (ans == Integer.MAX_VALUE)
                sb.append("-1\n");
            else
                sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    static short flip(byte opcode) {
        short ret = ori;
        for (int i=0; i<8; i++) {
            if (((opcode >> i) & 1) == 1)
                ret ^= OPS[i];
        }
        return ret;
    }
}