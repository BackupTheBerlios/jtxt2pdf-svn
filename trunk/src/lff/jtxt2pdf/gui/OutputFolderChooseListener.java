package lff.jtxt2pdf.gui;

import java.io.File;

import javax.swing.JOptionPane;

import lff.jtxt2pdf.utility.I18NUtility;

public class OutputFolderChooseListener extends FolderChooseListener {
	
	public OutputFolderChooseListener(IFolderChooserCallback callback) {
		super(callback);
	}
	
	/**
	 * Check output folder.
	 * @param f File object of the output folder.
	 * @return If something is wrong, return false.
	 */
	public boolean checked(File f) {
		//check writing right
		if (!f.canWrite()) {
			JOptionPane.showMessageDialog(null, 
					I18NUtility.getMessage("md.error.output.writable"),
					I18NUtility.getMessage("md.error"), 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
