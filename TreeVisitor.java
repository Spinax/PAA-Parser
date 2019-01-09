import java.util.ArrayList;
import java.util.Stack;

public class TreeVisitor {

	ExprTree T;
	ArrayList<Integer> Ops = new ArrayList<Integer>();
	int pos = 0;
	
	TreeVisitor(ExprTree T) {
		this.T = T;
		this.T.setCurrentNode(T.getRoot());
	}
	
	public void postorderIter() {
		if( T.getCurrentNode() == null ) 
			return;
 
		Stack<TokenNode> s = new Stack<TokenNode>( );
		TokenNode current = T.getCurrentNode();
		int pos = 0;
		
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
				createOps(current.getValue(),pos);
				current = null;
				pos++;
				//for (Integer x : Ops)
				//	System.out.print(x+"-");
			}
		}
	}
	private void createOps(String val, int position) {
		//System.out.println(Ops.size());
		//System.out.println(position);

		if (isANum(val)) {
			Ops.add(position, Integer.parseInt(val));
		}
		
		else if (val.equals("ADD")) {
			Ops.add(position, Ops.get(position-2)+Ops.get(position-1));	
		}
		
		else if (val.equals("SUB")) {
			Ops.add(position, Ops.get(position-2)-Ops.get(position-1));	
		}
		
		else if (val.equals("MUL")) {
			Ops.add(position, Ops.get(position-2)*Ops.get(position-1));	
		}
		
		else if (val.equals("DIV")) {
			if (Ops.get(position-1) == 0 ) {
				System.out.println("Divisione per zero!");
				System.exit(1);
			}
			else
				Ops.add(position, Ops.get(position-2)/Ops.get(position-1));	
		}
		else if (val == "GET") {
			System.out.println(Ops.get(position-2));
			pos = 0;
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
