package WhiskyScoreParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Parser {
	public static void main(String[] args) throws IOException {
		InputStream input;
			
		String path = "D:\\development\\WhiskyScore\\WhiskyScoreParser\\res\\ClassicdramMonitor_20120312.pdf";
		
		input = new FileInputStream(path);
		ClassicDramParser parser = new ClassicDramParser();
		parser.Parse(input);
	}
}
