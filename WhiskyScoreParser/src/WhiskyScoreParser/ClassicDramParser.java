package WhiskyScoreParser;

import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;

public class ClassicDramParser {
	public void Parse(InputStream stream) throws IOException{	
		PdfReader pdfReader = null;		
		
		try{
			pdfReader = new PdfReader(stream);
			OrderedPdfTextListener textListener = new OrderedPdfTextListener(5.0f);
			PdfContentStreamProcessor contentProcessor = new PdfContentStreamProcessor(textListener);
			
			int pages = 2/*pdfReader.getNumberOfPages()*/;
						
			for(int page=2; page <= pages; page++){
				textListener.clear();
				
				PdfDictionary pageDictionary = pdfReader.getPageN(page);
				PdfDictionary resourceDictionary = pageDictionary.getAsDict(PdfName.RESOURCES);
				
				byte[] pageData = ContentByteUtils.getContentBytesForPage(pdfReader, page);
				contentProcessor.processContent(pageData, resourceDictionary);
				
				for(Iterable<String> line: textListener.getLines()){
					for(String text: line){
						System.out.print(text);
						System.out.print(" ");
					}
					System.out.println();
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
	
	

}

