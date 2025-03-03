import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    static String A, B;
    
    public static void main(String[] args) throws Exception {
        A = rd.readLine();
        B = rd.readLine();
        if ("null".equals(A)) A = null;
        if ("null".equals(B)) B = null;
        
        try {
            sb.append(A.equals(B)).append('\n');
        } catch (NullPointerException npe) {
            sb.append("NullPointerException\n");
        }
        
        try {
            sb.append(A.equalsIgnoreCase(B)).append('\n');
        } catch (NullPointerException npe) {
            sb.append("NullPointerException\n");
        }
        
        System.out.println(sb);
    }
}