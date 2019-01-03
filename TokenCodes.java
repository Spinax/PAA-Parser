
public class TokenCodes {
	
	private static final String[] OPCodes = new String[] { "GET" , "SET" , "ADD" , "SUB" , "MUL" , "DIV" , "(" , ")" , ""};
	
	public int identifyToken(String tk) {
		int count = 1;
		for ( String s : OPCodes ) {  //controllo se il token appartiene alle op possibili e nel caso ritorno un numero per sapere qual'e'
			if (s.equals(tk)) 
				return count;
			else
				count++;
		}
		return 0;
	}
}