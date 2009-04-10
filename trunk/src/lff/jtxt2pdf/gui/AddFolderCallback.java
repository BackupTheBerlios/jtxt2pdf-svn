package lff.jtxt2pdf.gui;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;

import lff.jtxt2pdf.gui.data.Entry;
import lff.jtxt2pdf.gui.model.ListTableModel;
import lff.jtxt2pdf.utility.FileUtility;

public class AddFolderCallback implements IFolderChooserCallback, Runnable {

	private ListTableModel model;
	private File root;
	private static final int DEPTH = 10;
	private MainDialog dialog;
	
	
	public AddFolderCallback(MainDialog dialog) {
		this.model = dialog.getListTableModel();
		this.dialog = dialog;
	}
	
	public void chooseFolder(File f) {
		this.root = f;
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int depth = 0;
		getFiles(root, depth);
		dialog.setCursor(Cursor.getDefaultCursor());
	}

	private void getFiles(File current, int depth) {
		if (depth == DEPTH) {
			return;
		}
		if (!current.canRead()) {
			return;
		}
		File[] files = current.listFiles();
		if (files == null) {
			return;
		}
		for (int i=0; i<files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				getFiles(f, depth + 1);
			}
			String fn = f.getAbsolutePath();
			if (fn != null && fn.toLowerCase().endsWith(".txt")) {
				String name = fn;
				int lines = -1;
				try {
					 lines = FileUtility.countLines(f);
				} catch (IOException e) {
					// log it!
				}
				Entry entry = new Entry(name, lines);
				model.AddRow(entry);
			}
		}
	}

}
