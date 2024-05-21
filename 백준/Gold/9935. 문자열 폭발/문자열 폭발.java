import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static char[] s, x;
    static int l;
    static MyStack stack = new MyStack();

    public static void main(String[] args) throws Exception {
        s = rd.readLine().toCharArray();
        x = rd.readLine().toCharArray();
        l = x.length;

        for (char c : s) {
            stack.push(c);
            if (stack.check(x))
                stack.erase(l);
        }
        while (stack.size > 0) {
            sb.append(stack.pop());     // 역방향으로 append
        }

        if (sb.length() == 0)
            System.out.println("FRULA");
        else
            System.out.println(sb.reverse());
    }

    static class MyStack {
        char[] data = new char[1000000];
        int size = 0;

        void push(char c) {
            data[size++] = c;
        }

        void erase(int cnt) {
            size -= cnt;
        }

        char pop() {
            if (size == 0) return 0xFF;
            return data[--size];
        }

        boolean check(char[] x) {
            if (size < l) return false;
            for (int i=1; i<=l; i++) {
                if (data[size-i] != x[l-i])
                    return false;
            }
            return true;
        }
    }
}