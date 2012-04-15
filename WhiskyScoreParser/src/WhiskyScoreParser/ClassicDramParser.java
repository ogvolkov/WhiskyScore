package WhiskyScoreParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;

public class ClassicDramParser {
	private ArrayList<WhiskyScore> scores = new ArrayList<WhiskyScore>();
	
	public void parse(InputStream stream) throws IOException{
		scores.clear();
		PdfReader pdfReader = null;						
		
		try{
			pdfReader = new PdfReader(stream);
			OrderedPdfTextListener textListener = new OrderedPdfTextListener(5.0f);
			PdfContentStreamProcessor contentProcessor = new PdfContentStreamProcessor(textListener);
			
			int pages = pdfReader.getNumberOfPages();
						
			for(int page=2; page <= pages; page++){
				// retrieve page's data
				PdfDictionary pageDictionary = pdfReader.getPageN(page);
				PdfDictionary resourceDictionary = pageDictionary.getAsDict(PdfName.RESOURCES);				
				byte[] pageData = ContentByteUtils.getContentBytesForPage(pdfReader, page);
				
				// parse page data into text lines
				textListener.clear();
				contentProcessor.processContent(pageData, resourceDictionary);
				
				// parse whisky scores from found lines				
				for(List<String> line: textListener.getLines()){
					processLine(line);
				}
			}
		}
		finally{
			if (pdfReader != null)
			{
				pdfReader.close();
			}
		}
	}	
	
	public Iterable<WhiskyScore> getScores(){
		return scores;		
	}
	
	private void processLine(List<String> line){
		if (line.size() < 2) return;
		
		String firstCell = line.get(0);	
		if (!isFloat(firstCell)) return; // TODO: handle multi-line cells
		
		String name = line.get(1);
		// temp hack: exclude incorrectly parsed strings
		if (!isCorrectWhiskyName(name)) return;
		
		if(line.size() > 2){
			String description = line.get(2);
			if (description != null){
				name = name + " " + description;
			}
		}
		
		scores.add(new WhiskyScore(name, firstCell));
	}
	
	private static Boolean isFloat(String text){		
		try{
			Float.parseFloat(text.replace(',', '.'));
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	private static Boolean isCorrectWhiskyName(String text){		
		return Character.isUpperCase(text.charAt(0)) && Character.isUpperCase(text.charAt(1));
	}	
}