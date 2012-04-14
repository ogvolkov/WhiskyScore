package WhiskyScoreParser;

public class PdfTextBlock{
	private final float x;
	
	private final float y;
	
	private final String text;
	
	public PdfTextBlock(String text, float x, float y)	
	{		
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public String getText()
	{
		return text;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}	
}
