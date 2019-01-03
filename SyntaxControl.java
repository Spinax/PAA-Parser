import java.io.IOException;
import java.util.Stack;

public class SyntaxControl {
	
	public void SyntaxCheck() throws IOException {}
	
	public void ParMatching(String str) {
		boolean match;
	    Stack<Character> stack = new Stack<Character>();
	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        if(c == '(')
	            stack.push(c);
	        else if(c == ')')
	            if(stack.empty())
	                match = false;
	            else if(stack.peek() == '(')
	                stack.pop();
	            else
	                match = false;
	    }
	    match = stack.empty();
	    if (!match) {
			System.out.println("Parentesi non bilanciate!");
			System.exit(1);
		}
	}
	
}
