package lff.jtxt2pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import lff.jtxt2pdf.utility.FileUtility;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class Processor {
	
	public static void process(Option option) {
		
		out("Ready to convert. ");
		out(option.toString());
		Document.compress = false;		
		Document document = new Document(new Rectangle(option.getRealWidth(), option.getRealHeight()), 10, 10, 10, 10);

		try {
			Font font = getFont(option);
			PdfWriter.getInstance(document, new FileOutputStream(option.target));
			document.open();
			renderFile(document, font, option.encode, option.source);
			document.close();		
			
			File t = new File(option.target);
			out("File " + t.getAbsolutePath() + " Generated. Size is " + t.length()/1024 + " KBytes.");
		} catch (Exception e) {
			out("fatal exception:  " + e.getMessage());
		}
	}

	private static Font getFont(Option option) throws DocumentException,
			IOException {
		BaseFont bfComic;
		bfComic = BaseFont.createFont(option.fontFolder + option.fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bfComic, option.size);
		if (option.isBold) {
			font.setStyle(Font.BOLD);
		}
		return font;
	}
	
	private static void renderFile(Document document, Font font, String encode, String fileName)  {
		File f = new File(fileName);
		InputStreamReader reader = null;
		try {
			if (encode.equalsIgnoreCase("")) {
				reader = new InputStreamReader(new FileInputStream(f));
			} else {
				reader = new InputStreamReader(new FileInputStream(f), encode);
			}
		} catch (FileNotFoundException e) {
			out("File not found!");
			return;
		} catch (UnsupportedEncodingException e) {
			out("Encode " + encode + " is not valid!");
			return;
		}
		
		long lc = 0;
		try {
			lc = FileUtility.countLines(f);
			out("Total Lines: " + lc);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BufferedReader br = new BufferedReader(reader);
		
		try {
			int c = 0;
			String line = br.readLine();
			int old_percent = 0;
			while (line != null) {
				//out(line);
				document.add(new Paragraph(line, font));
				c++;
				int percent = (int)(c /((float)lc) * 100);
				if (percent != old_percent && percent % 10 == 0) {
					out(percent + "% Finished...");
					old_percent = percent;
				}
				line = br.readLine();
			}

		} catch (Exception e) {
			
		} finally {
			try {
				br.close();
			} catch (IOException e) {}
			try {
				reader.close();
			} catch (IOException e) {}
		}
	}	
	
	private static void out(String msg) {
		
	}
}
