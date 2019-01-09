import java.io.IOException;


public class Main {
	
	public static void main(String[] args) throws IOException {
		InputFileReader fr  = new InputFileReader();
		fr.readFile(args[0]);
		TreeBuilder T = new TreeBuilder();
		T.readInstructions(fr.getText());
		T.createTrees();
		for (int i = 0; i < T.getInstructionTrees().size(); i++) {
			TreeVisitor ET = new TreeVisitor(T.getInstructionTrees().get(i));
			ET.postorderIter();
			System.out.println("\n");
		}
		
		
		
	
		/*
		}*/
   //una volta controllate le parentesi il resto del controllo sintattico si puo' fare sul vettore
		//i numeri e i nomi var sono sempre nodi foglia
	}

}
