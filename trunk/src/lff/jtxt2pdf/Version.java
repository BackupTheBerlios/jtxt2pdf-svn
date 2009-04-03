package lff.jtxt2pdf;

import java.util.Locale;
import java.util.ResourceBundle;

public class Version {
	
	
	static String BUILD_STRING="";
	
	static {
		ResourceBundle buildBundle = ResourceBundle.getBundle("lff.jtxt2pdf.version", Locale.ENGLISH);
		BUILD_STRING = buildBundle.getString("build.number");
	}
	
	public static final String getVersion() {
		return "1.0 Build " + getBuild();
	}

	private static String getBuild() {
		if (!BUILD_STRING.equals("")) {
			return BUILD_STRING;
		} else {
			return "DEBUG VERSION";
		}
	}

}
