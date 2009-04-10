package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import lff.jtxt2pdf.utility.I18NUtility;

public class AddFolderListener implements ActionListener {

	JFileChooser chooser;
	IFolderChooserCallback callback = null;
	
	public AddFolderListener(IFolderChooserCallback callback) {
		this.callback = callback;
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	public void actionPerformed(ActionEvent e) {
		 int returnVal = chooser.showOpenDialog(null);
	     if(returnVal == JFileChooser.APPROVE_OPTION) {
             File f = chooser.getSelectedFile();
             if (checked(f)) {
            	 callback.chooseFolder(f);
             }
	     }

	}
	
	/**
	 * Check input file.
	 * @param f File object of the input file.
	 * @return If something is wrong, return false.
	 */
	private boolean checked(File f) {
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
