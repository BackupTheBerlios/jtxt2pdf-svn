package lff.jtxt2pdf.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtility {
	
	public static String converTail(String name) {
		int pos = -1;
		for (int i=name.length() - 1; i>=0; i--) {
			if (name.charAt(i) == '.') {
				pos = i;
				break;
			} //end if
		} //end for i
		String t = name.substring(0, pos);
		return t + ".pdf";
	}
	
	/**
	 * How many lines are in the file.
	 * @param file Target File
	 * @return how many lines of the file.
	 * @throws IOException
	 */
    public static int countLines(File file) throws IOException
    {
        Reader reader = new InputStreamReader(new FileInputStream(file));
        
        int lineCount = 0;
        char[] buffer = new char[4096];
        for (int charsRead = reader.read(buffer); charsRead >= 0; charsRead = reader.read(buffer))
        {
            for (int charIndex = 0; charIndex < charsRead ; charIndex++)
            {
                if (buffer[charIndex] == '\n') {
                    lineCount++;
                }
            }
        }
        reader.close();
        return lineCount;
    }	
    

}
