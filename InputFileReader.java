import java.io.*;
import java.util.Scanner;

public class InputFileReader {
	
	private BufferedReader text = null;
	private String tmpFile = "tempFile.txt";
	
	public void readFile(String filePath) throws FileNotFoundException {
		FileReader file = null;
		try {
			removeEmptyLines(filePath);
			file = new FileReader(tmpFile);
			}
		catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			System.exit(1);
		}
		
		text = new BufferedReader(file);
		File tmp = new File("tmepFile.txt");
		tmp.delete();
	}

	public BufferedReader getText() {
		return text;
	}
	
	
	private void removeEmptyLines(String filePath) throws FileNotFoundException {
		Scanner file;
		PrintWriter writer;
		
		try {
			file = new Scanner(new File(filePath));
			writer = new PrintWriter(tmpFile);
			
			while(file.hasNext()) {
				String line = file.nextLine();
				if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
			}
			
			file.close();
            writer.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("Input file not found.");
	    }
		
	}
	
	
	
	
	
	
	

}
