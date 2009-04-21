package lff.jtxt2pdf.gui;

import java.io.File;

import javax.swing.JOptionPane;

import lff.jtxt2pdf.utility.I18NUtility;

public class AddFolderListener extends FolderChooseListener {

	public AddFolderListener(IFolderChooserCallback callback) {
		super(callback);
	}
	
	
	/**
	 * Check input file.
	 * @param f File object of the input file.
	 * @return If something is wrong, return false.
	 */
	public boolean checked(File f) {
		//check reading right
		if (!f.canRead()) {
			JOptionPane.showMessageDialog(null, 
					I18NUtility.getMessage("md.error.addfolder.readable"),
					I18NUtility.getMessage("md.error"), 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
