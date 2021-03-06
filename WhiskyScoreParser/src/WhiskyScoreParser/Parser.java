package WhiskyScoreParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Parser {
	public static void main(String[] args) throws IOException {
		InputStream input;
			
		String path = "D:\\development\\WhiskyScore\\WhiskyScoreParser\\res\\ClassicdramMonitor_20120312.pdf";
		
		input = new FileInputStream(path);
		ClassicDramParser parser = new ClassicDramParser();
		parser.parse(input);
		
		for(WhiskyScore score: parser.getScores()){			
			System.out.print(score.getName());
			System.out.print(";");
			System.out.println(score.getRating());
		}		
	}
}
