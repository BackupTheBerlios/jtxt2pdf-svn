package lff.jtxt2pdf.utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NUtility {
	
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("lff.jtxt2pdf.i18n.messages");
		} catch (Exception e) {
			rb = ResourceBundle.getBundle("lff.jtxt2pdf.i18n.messages", Locale.ENGLISH);
		}
	}
	
	
    public static String getMessage(String key) {
    	try {
    		return rb.getString(key);
    	} catch (Exception e) {
    		return key;
    	}
    }
}
