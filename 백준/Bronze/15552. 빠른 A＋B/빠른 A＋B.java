import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tk = null;
	
    public static void main(String[] args) throws Exception {
    	int T = Integer.parseInt(rd.readLine());
    	for (int t=0; t<T; t++) {
    		tk = new StringTokenizer(rd.readLine());
    		int a = Integer.parseInt(tk.nextToken());
    		int b = Integer.parseInt(tk.nextToken());
    		
        	wr.append(String.format("%d%n", a+b));
    	}
    	wr.flush();
    }
}
