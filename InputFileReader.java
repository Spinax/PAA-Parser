import java.io.*;


public class InputFileReader {
	
	private BufferedReader text = null;
	
	public void readFile(String filePath) throws FileNotFoundException {
		FileReader file = null;
		try {
			file = new FileReader(filePath);
			}
		catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			System.exit(1);
		}
		text = new BufferedReader(file);
	}

	public BufferedReader getText() {
		return text;
	}
	
	
	
	
	
	
	
	

}
