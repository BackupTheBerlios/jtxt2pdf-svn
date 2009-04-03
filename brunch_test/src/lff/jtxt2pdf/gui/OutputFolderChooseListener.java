package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import lff.jtxt2pdf.utility.I18NUtility;

public class OutputFolderChooseListener implements ActionListener {
	
	JFileChooser chooser;
	IFolderChooserCallback callback;
	
	public OutputFolderChooseListener(IFolderChooserCallback callback) {
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
	 * Check output folder.
	 * @param f File object of the output folder.
	 * @return If something is wrong, return false.
	 */
	private boolean checked(File f) {
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
