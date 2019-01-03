
public class TokenNode {
	
	private String value;
	private TokenNode LC;
	private TokenNode RC;
	private TokenNode father;
	
	public TokenNode(String value, TokenNode lC, TokenNode rC, TokenNode father) {
		this.value = value;
		LC = lC;
		RC = rC;
		this.father = father;
	}
	
	public TokenNode(TokenNode n) {
		value = n.value;
		LC = n.LC;
		RC = n.RC;
		father = n.father;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TokenNode getLC() {
		return LC;
	}

	public void setLC(TokenNode lC) {
		LC = lC;
	}

	public TokenNode getRC() {
		return RC;
	}

	public void setRC(TokenNode rC) {
		RC = rC;
	}

	public TokenNode getFather() {
		return father;
	}

	public void setFather(TokenNode father) {
		this.father = father;
	}
}
