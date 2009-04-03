package lff.jtxt2pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

public class Test {

	/**
	 * @param args
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		Document document = new Document(); 
		PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
		document.open();
		Paragraph cTitle = new Paragraph("This is chapter 1");
		Chapter chapter = new Chapter(cTitle, 1);
		Paragraph sTitle = new Paragraph("This is section 1 in chapter 1");
		Section section = chapter.addSection(sTitle, 1);
		
		document.add(cTitle);
		document.add(section);
		
		
		document.close();
	}

}
