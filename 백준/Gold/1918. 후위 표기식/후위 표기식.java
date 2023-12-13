import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tk = null;
    
    static Stack<String> operands = new Stack<>();
	static Stack<Operator> operators = new Stack<>();
    
    public static void main(String[] args) throws Exception {    	
    	char[] expr = rd.readLine().toCharArray();
    	
    	for (char ch: expr) {
    		if ('A' <= ch && ch <= 'Z') {
    			operands.push(String.valueOf(ch));
    		}
    		else {
    			if (ch == '(') {
    				Operator.parenthesis++;
    				continue;
    			}
    			if (ch == ')') {
    				Operator.parenthesis--;
    				continue;
    			}
    			
    			Operator newOp = new Operator(ch);
    			while (operators.size > 0 && operators.top().order >= newOp.order)
    				calcOne();
    			operators.push(newOp);
    		}
    	}
    	
    	while (operators.size > 0)
    		calcOne();
    	
    	wr.append(operands.pop());
        wr.flush();
    }
    
    static void calcOne() {
		String b = operands.pop();
		String a = operands.pop();
		Operator op = operators.pop();
		operands.push(a + b + op.ch);
    }
}

class Stack<T> {
	Object[] data = new Object[100];
	int size = 0;
	
	void push(T s) {
		data[size++] = s;
	}
	
	T pop() {
		if (size <= 0)
			return null;
		return (T) data[--size];
	}
	
	T top() {
		return (T) data[size - 1];
	}
}

class Operator {
	static int parenthesis = 0;
	
	char ch;
	int order;
	Operator (char ch) throws Exception {
		this.ch = ch;
		switch (ch) {
		case '*':
		case '/':
			order = 2;
			break;
		case '+':
		case '-':
			order = 1;
			break;
		}
		order += parenthesis * 10;
	}
}