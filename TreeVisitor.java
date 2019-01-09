import java.util.ArrayList;
import java.util.Stack;

public class TreeVisitor {

	private ExprTree T;
	private ArrayList<Integer> Ops = new ArrayList<Integer>();
	private int position = 0;
	
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
				//System.out.println(current.getValue());
				createOps(current.getValue());
				current = null;
				position++;
				//for (Integer x : Ops)
				//	System.out.print(x+"--");
				System.out.println();
			}
		}
	}
	private void createOps(String val) {
		//System.out.println(Ops.size());
		//System.out.println(position);
		
		if (isANum(val)) {
			Ops.add(position, Integer.parseInt(val));
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
			System.out.println("Risultato : " + Ops.get(position-2));
			position = 0;
		}
		//else if (val == "SET") {
			//aggiungere nella hashmap il val precedente
		//}
	}
	
	private boolean isAVar(String v) {
		return v.matches("^[\\$_a-zA-Z]+[\\$_\\w]*$");
	}
	
	private boolean isANum(String v) {
		return v.matches("[0-9]+");
	}
}
