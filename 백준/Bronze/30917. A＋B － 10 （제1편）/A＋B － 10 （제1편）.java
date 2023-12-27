import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

	static int res, A, B;
	
	public static void main(String[] args) throws Exception {
		for (int i=1; i<=9; i++) {
			wr.append("? A " + i + "\n");
			wr.flush();
			
			res = Integer.parseInt(rd.readLine());
			if (res == 1) {
				A = i;
				break;
			}
		}
		
		for (int i=1; i<=9; i++) {
			wr.append("? B " + i + "\n");
			wr.flush();
			
			res = Integer.parseInt(rd.readLine());
			if (res == 1) {
				B = i;
				break;
			}
		}
		
		wr.append("! ");
		wr.append(Integer.toString(A + B));
		wr.flush();
	}
}