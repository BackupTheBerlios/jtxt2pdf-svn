package lff.jtxt2pdf.gui;

import java.io.File;

import javax.swing.JTextField;

public class FontCallback implements IFileChooserCallback {

	JTextField edt;
	
	public FontCallback(JTextField edt) {
		this.edt = edt;
	}

	public void chooseFile(File f) {
		edt.setText(f.getAbsolutePath());
	}

}
