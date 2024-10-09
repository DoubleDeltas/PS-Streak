import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tk = null;

    static int T;
    static int A, B, C;
    static int max;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(rd.readLine());

        for (int i=1; i<=T; i++) {
            tk = new StringTokenizer(rd.readLine());
            A = Integer.parseInt(tk.nextToken());
            B = Integer.parseInt(tk.nextToken());
            C = Integer.parseInt(tk.nextToken());

            max = Math.max(A, Math.max(B, C));

            if (A + B + C - max <= max) {
                sb.append("Case #").append(i).append(": invalid!\n");
            }
            else if (A == B && B == C) {
                sb.append("Case #").append(i).append(": equilateral\n");
            }
            else if (A == B || B == C || C == A) {
                sb.append("Case #").append(i).append(": isosceles\n");
            }
            else {
                sb.append("Case #").append(i).append(": scalene\n");
            }
        }
        System.out.println(sb);
    }
}
