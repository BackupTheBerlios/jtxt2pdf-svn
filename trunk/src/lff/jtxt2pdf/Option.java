package lff.jtxt2pdf;

public class Option {
	
	private static final float ratio = 2.83f;

	private static Option DEFAULT_OPINION = new Option(); 
	
	static {
		//unit: mm
		DEFAULT_OPINION.width = 400;
		DEFAULT_OPINION.height = 600;
	}
	
	/*
		Width/Height for some devices(mm):
		iRex iLiad : 122/147
		
	 
	 
	 */
	
	public String fontFolder = "c:/windows/fonts/";
	
	public String fontName = "simhei.ttf";
	
	public boolean isBold = false;
	
	public int size = 12;
	
	public String source = null;
	
	public String target = null;
	
	public boolean overwrite = false;
	
	public String encode = "";
	
	public boolean compress = true;
	
	public int width;
	public int height;
	
	public String toString() {
		return "Convert Setting:\n" +
			   "   Page Width:" + (int)(width) + " mm \n" +
			   "   Page Height:" + (int)(height) + " mm \n" +
			   "   Font Folder:" + fontFolder + "\n" +
			   "   Font Name:" + fontName + "\n" +
			   "   Font Bold:" + isBold + "\n" +
			   "   Font Size:" + size + "\n" +
			   "   Source file:" + source + "\n" +
			   "   Target file:" + target + "\n" + 
			   "   Source Encode:" + (encode.equalsIgnoreCase("") ? "Not set" : encode) + "\n" +
			   "   Compress File:" + compress + "\n" + 
			   "   -------------------";	
	}
	
	public static Option getDefaultOpinion() {
		return DEFAULT_OPINION ;
	}
	
	public float getRealWidth() {
		return width * ratio;
	}
	public float getRealHeight() {
		return height * ratio;
	}
}
