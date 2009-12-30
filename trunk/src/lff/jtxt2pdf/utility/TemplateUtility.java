package lff.jtxt2pdf.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lff.jtxt2pdf.template.Template;

public class TemplateUtility {

	private static TemplateUtility instance = new TemplateUtility();

	private static Map<String, Template> map = new HashMap<String, Template>();

	public static TemplateUtility getInstance() {
		return instance;
	}

	public Template load(File f) {
		if (!f.exists()) {
			return null;
		}
		Properties p = new Properties();
		try {
			p.load(new FileReader(f));
			if (p == null) {
				return null;
			}
			Template t = new Template();
			t.setHeight(getIntValue(p, "Height"));
			t.setWidth(getIntValue(p, "Width"));
			t.setTopMargin(getIntValue(p, "TopMargin"));
			t.setLeftMargin(getIntValue(p, "LeftMargin"));
			t.setRightMargin(getIntValue(p, "RightMargin"));
			t.setBottomMargin(getIntValue(p, "BottomMargin"));

			String s = p.getProperty("Font");
			if (s != null) {
				t.setFont(s);
			}
			
			t.setSize(getIntValue(p, "Size"));
			t.setBold(getBooleanValue(p, "Bold"));
			
			String name = f.getName();
			map.put(name, t);

			t.setName(name);
			return t;

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return null;
	}

	private boolean getBooleanValue(Properties p, String key) {
		String value = p.getProperty(key);
		if (value == null) {
			return false;
		}
		value = value.toLowerCase();
		return value.equals("true") || value.equals("t") || value.equals("y") ||
			   value.equals("yes");
	}

	public int getIntValue(Properties p, String key) {
		String s = p.getProperty(key);
		if (s == null) {
			return -1;
		}
		try {
			int t = Integer.valueOf(s);
			return t;
		} catch (Exception e) {
			return -1;
		}
	}
}
