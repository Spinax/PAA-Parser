import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

public class TreeVisitor {

	private ExprTree T;
	private Hashtable<String,Integer> VarTab = new Hashtable<String,Integer>();
	private ArrayList<Integer> Ops = new ArrayList<Integer>();
	private int position = 0;
	String tmpVar = "";
	
	TreeVisitor(ExprTree T) {
		this.T = T;
		this.T.setCurrentNode(T.getRoot());
	}
	
	public void postorderIter() {
		if( T.getCurrentNode() == null ) 
			return;
 
		Stack<TokenNode> s = new Stack<TokenNode>( );
		TokenNode current = T.getCurrentNode();
		
		while(true) {
			if(current != null) {
				if(current.getRC() != null) 
					s.push(current.getRC());
				s.push(current);
				current = current.getLC();
				continue;
			}
 
			if(s.isEmpty()) 
				return;
			current = s.pop();
 
			if( current.getRC() != null && ! s.isEmpty() && current.getRC() == s.peek()) {
				s.pop();
				s.push(current);
				current = current.getRC();
			} 
			else {
				System.out.println(current.getValue());
				createOps(current.getValue());
				current = null;
				position++;
			//for (Integer x : Ops)
			//	System.out.print(x+"--");
			//System.out.println();
				System.out.println("x = " + VarTab.get("x"));
			}
		}
	}
	private void createOps(String val) {
		
		if (isANum(val)) {
			//System.out.println("NUM");
			Ops.add(position, Integer.parseInt(val));
		}
		
		else if (isAVar(val)) {
			if (VarTab.containsKey(tmpVar)) {
				System.out.println("conosco questa var");
				Ops.set(position, VarTab.get(val));
			}
			else {
				System.out.println("non conosco questa var");
				tmpVar = val;
			}
			position--;
		}
		
		else if (val.equals("ADD")) {
			Ops.add(position, Ops.get(position-2)+Ops.get(position-1));	
			Ops.remove(position-1);
			Ops.remove(position-2);
			position -= 2;
		}
		
		else if (val.equals("SUB")) {
			Ops.add(position, Ops.get(position-2)-Ops.get(position-1));	
			Ops.remove(position-1);
			Ops.remove(position-2);
			position -= 2;
		}
		
		else if (val.equals("MUL")) {
			Ops.add(position, Ops.get(position-2)*Ops.get(position-1));
			Ops.remove(position-1);
			Ops.remove(position-2);
			position -= 2;
		}
		
		else if (val.equals("DIV")) {
			if (Ops.get(position-1) == 0 ) {
				System.out.println("Divisione per zero!");
				System.exit(1);
			}
			else {
				Ops.add(position, Ops.get(position-2)/Ops.get(position-1));	
				Ops.remove(position-1);
				Ops.remove(position-2);
				position -= 2;
			}
		}	
		
		else if (val == "GET") {
			if ( Ops.get(position-2) == null) {
				System.out.println("Risultato : " + Ops.get(position-1));
				position = 0;
			}
			else {
				System.out.println("Risultato : " + Ops.get(position-2));
				position = 0;
			}
		}
		
		else if (val == "SET") {	
			VarTab.put(tmpVar, Ops.get(position-1));
			position = 0;
		}
	}
	
	private boolean isAVar(String v) {
		return (v.matches("^[\\$_a-zA-Z]+[\\$_\\w]*$")
				&& !v.equals("ADD")
				&& !v.equals("SUB")
				&& !v.equals("MUL")
				&& !v.equals("DIV")
				&& !v.equals("GET")
				&& !v.equals("SET"));
	}
	
	private boolean isANum(String v) {
		return v.matches("[0-9]+");
	}
}
