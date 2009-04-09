package lff.jtxt2pdf.gui;

import java.io.File;
import java.io.IOException;

import lff.jtxt2pdf.gui.data.Entry;
import lff.jtxt2pdf.gui.model.ListTableModel;
import lff.jtxt2pdf.utility.FileUtility;

public class AddFileCallback implements IFileChooserCallbak {

	private ListTableModel model;
	
	public AddFileCallback(ListTableModel model) {
		this.model = model;
	}
	
	public void chooseFile(File f) {
		String name = f.getAbsolutePath();
		int lines = -1;
		try {
			lines = FileUtility.countLines(f);
		} catch (IOException e) {
		}
		Entry ent = new Entry(name, lines);
		model.AddRow(ent);
	}

}
