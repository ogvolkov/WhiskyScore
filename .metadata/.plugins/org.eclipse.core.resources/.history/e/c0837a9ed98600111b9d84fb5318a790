package WhiskyScoreParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.itextpdf.text.pdf.parser.*;

public class OrderedPdfTextListener implements RenderListener{
		private final float yTolerance;
	
		private final ArrayList<PdfTextBlock> textBlocks = new ArrayList<PdfTextBlock>();
		
		public OrderedPdfTextListener(float yTolerance)
		{
			this.yTolerance = yTolerance;
		}
		
		public void clear()
		{
			textBlocks.clear();
		}
		
		public Iterable<Iterable<String>> getLines()
		{
			ArrayList<Iterable<String>> result = new ArrayList<Iterable<String>>();
			
			// sort result in order left to right, top to bottom but take in account possible Y deviation
			Collections.sort(textBlocks, new Comparator<PdfTextBlock>(){
				public int compare(PdfTextBlock first, PdfTextBlock second){
					if (first.getY() < second.getY() - yTolerance) return -1;
					if (first.getY() > second.getY() + yTolerance) return +1;
					if (first.getX() < second.getX()) return -1;
					if (first.getX() > second.getX()) return +1;
					return 0;
				}
			});
			
			// extract line of text from sorted text blocks 
			ArrayList<String> currentLine = new ArrayList<String>();
			float currentLineY = 0, currentBlockY;
			String currentBlockText;
			
			for(PdfTextBlock textBlock: textBlocks){
				currentBlockY = textBlock.getY();
				currentBlockText = textBlock.getText();
				
				if(currentLine.isEmpty()){
					// start a new line
					currentLine.add(currentBlockText);
					currentLineY = currentBlockY;
				}
				else{				
					if (currentBlockY > currentLineY + yTolerance){
						// line ended
						result.add(currentLine);
						currentLine = new ArrayList<String>();
						
						// start a new line
						currentLine.add(currentBlockText);
						currentLineY = currentBlockY;
					}
					else{
						// add to current line
						currentLine.add(currentBlockText);
					}
				}
			}
			
			if (!currentLine.isEmpty()){
				result.add(currentLine);
			}				
			
			return result;
		}		
		
		@Override
		public void renderText(TextRenderInfo renderInfo) {
			String text = renderInfo.getText();			
			Vector origin = renderInfo.getBaseline().getStartPoint();
			textBlocks.add(new PdfTextBlock(text, origin.get(Vector.I2), origin.get(Vector.I1)));			
		}

		@Override
		public void beginTextBlock() {					
		}

		@Override
		public void endTextBlock() {			
		}

		@Override
		public void renderImage(ImageRenderInfo arg0) {			
		}	
}
