import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TreeBuilder {

	private ArrayList<String[]> instructions = new ArrayList<String[]>();
	private ArrayList<ExprTree> instructionTrees = new ArrayList<ExprTree>();
	
	private String arrayToString(String[] V) {
		String temp = "";
		for (String x : V) {
			temp += x;
		}
		return temp;
	}
	private void createInstructions(String line) throws IOException  {
		SyntaxControl sc = new SyntaxControl();
		line = line.substring(1, line.length()-1);
		line = line.replace("(", " ( ");
		line = line.replace(")", " ) ");
		String[] expr = line.split(" ");
		if(!sc.ParMatching(arrayToString(expr))) {
			System.out.println("Input non accettato! \nIl file di input deve essere composto da un' istruzione per riga.");
			System.exit(1);
		}
		for ( String x : expr ) 
			System.out.println(x);
		System.out.println("corretto");
		sc.SyntaxCheck(expr);
		
		System.out.println("----");
		instructions.add(expr);
	} 
	
	public void readInstructions(BufferedReader text) throws IOException {
		SyntaxControl sc = new SyntaxControl();
		String line = text.readLine();
		if(!sc.ParMatching(line)) {
			System.out.println("Parentesi non bilanciate!");
			System.exit(1);
		}
		while (line != null) {
			createInstructions(line);
			line = text.readLine();
		}
	}
	
	public void createTrees() {
		int OpCode;
		TokenCodes TC = new TokenCodes();
		for (int i = 0; i < instructions.size(); i++) {
			ExprTree ET = new ExprTree();
			for (int j = 0; j < instructions.get(i).length; j++) {
				//System.out.println("Sto leggendo : " + instructions.get(i)[j]);
				//System.out.println("Sono nel nodo : " + ET.getCurrentNode());
				OpCode = TC.identifyToken(instructions.get(i)[j]);
				addNode(ET,OpCode,instructions.get(i)[j]);
				ET.printBinaryTree(ET.getRoot(), 0);
				//System.out.println("\n\n ");
			}
			//ET.printBinaryTree(ET.getRoot(), 0);
			System.out.println("-----------");
			instructionTrees.add(ET);
		}
	}

	private void addNode(ExprTree ET, int opCode, String value) {
		System.out.println("Sono nel nodo : " + ET.getCurrentNode());
		System.out.println(ET.getCurrentNode().getFather());
		//System.out.println("Sto valutando -" + value + "- nel nodo " + ET.getCurrentNode() + " " + opCode);
		switch (opCode) {
		case 1 : 
			ET.getRoot().setValue("GET");
			break;
		case 2 :
			ET.getRoot().setValue("SET");
			break;
		case 3 :
			ET.getCurrentNode().setValue("ADD");
			break;
		case 4 : 
			ET.getCurrentNode().setValue("SUB");
			break;
		case 5 : 
			ET.getCurrentNode().setValue("MUL");
			break;
		case 6 :
			ET.getCurrentNode().setValue("DIV");
			break;
		case 7 :
			if (ET.getCurrentNode().getRC().getValue() == " ") {
				//TokenNode temp = new TokenNode(ET.getCurrentNode());
				ET.setCurrentNode(ET.getCurrentNode().getRC());
				ET.getCurrentNode().setRC(new TokenNode(" ",null,null,ET.getCurrentNode()));
				ET.getCurrentNode().setLC(new TokenNode(" ",null,null,ET.getCurrentNode()));
			}
			else {
				//TokenNode temp = new TokenNode(ET.getCurrentNode());
				ET.setCurrentNode(ET.getCurrentNode().getLC());
				ET.getCurrentNode().setRC(new TokenNode(" ",null,null,ET.getCurrentNode()));
				ET.getCurrentNode().setLC(new TokenNode(" ",null,null,ET.getCurrentNode()));
			}
			break;
		case 8 :
			ET.setCurrentNode(ET.getCurrentNode().getFather());
			break;
		case 9 :
			break;
		default :
			if(ET.getCurrentNode().getRC().getValue() == " " )
				ET.getCurrentNode().getRC().setValue(value);
			else
				ET.getCurrentNode().getLC().setValue(value);
		}
		//System.out.println(ET.getCurrentNode().getValue() + " lch : " + ET.getCurrentNode().getLC().getValue()+ " rch : " + ET.getCurrentNode().getRC().getValue());
	}
	
}
