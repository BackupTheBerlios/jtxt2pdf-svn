package lff.jtxt2pdf.gui;

import java.io.File;

import javax.swing.JTextField;

public class OutputFolderCallback implements IFolderChooserCallback {

	JTextField edt;
	
	public OutputFolderCallback(JTextField edt) {
		this.edt = edt;
	}
	
	public void chooseFolder(File f) {
		edt.setText(f.getAbsolutePath());
	}

}
