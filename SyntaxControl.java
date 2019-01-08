import java.io.IOException;
import java.util.Stack;

public class SyntaxControl {
	
	public void SyntaxCheck(String[] V) throws IOException {
		TokenCodes tk = new TokenCodes();
		int pos = 0;
		if (!V[pos].equals("SET") && !V[pos].equals("GET") ) {
			System.out.println("Errore di sintassi! \nOgni istruzione deve iniziare con SET o GET.");
			System.exit(1);
		}
		
		/*if (V[pos] != "(" || V[pos] != ")" || V[pos] != " ") 
			if (V[pos] == "SET") {
				if(tk.identifyToken(V[pos+1]) != 0)
					System.out.println("Errore di sintassi alla posizione " + pos+1 + ".\nNome di variabile richiesto.");
				else if(V[pos+1].matches() )
			}
		
			//skippo () e spazio
			//se v[0] è set  
	*/
	}
	
	public boolean ParMatching(String str) {
	    Stack<Character> stack = new Stack<Character>();
	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);
	        if(c == '(')
	            stack.push(c);
	        else if(c == ')')
	            if(stack.empty())
	                return false;
	            else if(stack.peek() == '(')
	                stack.pop();
	            else
	                return false;
	    }
	    return stack.empty();   
	}
	
}
