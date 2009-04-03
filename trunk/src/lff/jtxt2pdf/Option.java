package lff.jtxt2pdf;

public class Option {
	
	private static final double ratio = 2.83;
	
	public String fontFolder = "c:/windows/fonts/";
	
	public String fontName = "simhei.ttf";
	
	public boolean isBold = false;
	
	public int size = 12;
	
	public String source = null;
	
	public String target = null;
	
	public boolean overwrite = false;
	
	public String encode = "";
	
	public boolean compress = true;
	
	//default by iliad
	public int width = 345;
	public int height = 417;
	
	public String toString() {
		return "Convert Setting:\n" +
			   "   Page Width:" + (int)(width/ratio) + " mm \n" +
			   "   Page Height:" + (int)(height/ratio) + " mm \n" +
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
}
