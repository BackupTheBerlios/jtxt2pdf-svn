package lff.jtxt2pdf.template;

public class Template {
	
	private static final float ratio = 2.83f;
	
	
	private int width;
	private int height;
	private int topMargin;
	private int bottomMargin;
	private int leftMargin;
	private int rightMargin;
	
	private boolean isBold = false;
	private String font = null;
	private int style;
	private int size;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public Template() {
		this.width = 400;
		this.height = 400;
		this.topMargin = 10;
		this.bottomMargin = 10;
		this.leftMargin = 10;
		this.rightMargin = 10;
		
		font = "C:/windows/fonts/arial.ttf";
		size = 10;
		style = 0;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if (height == -1) {
			return;
		}
		this.height = height;
	}
	
	public int getTopMargin() {
		return topMargin;
	}
	
	public void setTopMargin(int topMargin) {
		if (topMargin == -1) {
			return;
		}
		this.topMargin = topMargin;
	}
	
	public int getBottomMargin() {
		return bottomMargin;
	}
	
	public void setBottomMargin(int bottomMargin) {
		if (bottomMargin == -1) {
			return;
		}
		this.bottomMargin = bottomMargin;
	}
	
	public int getLeftMargin() {
		return leftMargin;
	}
	
	public void setLeftMargin(int leftMargin) {
		if (leftMargin == -1) {
			return;
		}
		this.leftMargin = leftMargin;
	}
	
	public int getRightMargin() {
		return rightMargin;
	}
	
	public void setRightMargin(int rightMargin) {
		if (rightMargin == -1) {
			return;
		}
		this.rightMargin = rightMargin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRealWidth() {
		return width * ratio;
	}
	public float getRealHeight() {
		return height * ratio;
	}
	
	
	public boolean isBold() {
		return isBold;
	}
	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		if (size != -1) {
			this.size = size;
		}
	}
	
}
