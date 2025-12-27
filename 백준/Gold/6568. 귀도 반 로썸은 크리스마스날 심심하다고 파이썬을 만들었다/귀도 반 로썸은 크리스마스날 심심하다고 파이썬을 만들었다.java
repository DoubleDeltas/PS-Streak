import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[] mem = new int[32]; // 8*32 bits
    static int acc; // 8-bit
    static int pc;  // 5-bit

    public static void main(String[] args) throws Exception {
        W_LOOP: while (true) {
            init();
            for (int i=0; i<32; i++) {
                String s = rd.readLine();
                if (s == null) break W_LOOP;
                mem[i] = Integer.parseInt(s, 2);
            }
            P_LOOP: while (true) {
                int inst = mem[pc];
                int op = inst >> 5;
                int x = inst & 0x1F;
                pc = (pc + 1) & 0x1F;
                switch (op) {
                    case 0b000: // STA x
                        mem[x] = acc;
                        break;
                    case 0b001: // LDA x
                        acc = mem[x];
                        break;
                    case 0b010: // BEQ x
                        if (acc == 0) pc = x;
                        break;
                    case 0b011: // NOP
                        // no-op
                        break;
                    case 0b100: // DEC
                        acc = (acc - 1) & 0xFF;
                        break;
                    case 0b101: // INC
                        acc = (acc + 1) & 0xFF;
                        break;
                    case 0b110: // JMP x
                        pc = x;
                        break;
                    case 0b111: // HLT
                        break P_LOOP;
                }
            }
            String s = String.format("%8s", Integer.toBinaryString(acc)).replace(' ', '0');
            sb.append(s).append('\n');
        }
        System.out.println(sb);
    }

    static void init() {
        Arrays.fill(mem, (byte) 0);
        acc = 0;
        pc = 0;
    }
}