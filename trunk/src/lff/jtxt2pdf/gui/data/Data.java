package lff.jtxt2pdf.gui.data;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private static List<Entry> list = new ArrayList<Entry>();
	private static Data instance = new Data();
	private Data() {
		
	}
	public static synchronized Data getInstance() {
		return instance;
	}
	public static synchronized List<Entry> getData() {
		return list;
	}	
}

