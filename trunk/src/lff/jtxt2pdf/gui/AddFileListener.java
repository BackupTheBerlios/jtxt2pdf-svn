package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import lff.jtxt2pdf.utility.I18NUtility;

public class AddFileListener implements ActionListener {

	JFileChooser chooser;
	IFileChooserCallbak callback = null;
	
	public AddFileListener(IFileChooserCallbak callback) {
		this.callback = callback;
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
		chooser.setFileFilter(filter);
	}
	
	public void actionPerformed(ActionEvent e) {
		 int returnVal = chooser.showOpenDialog(null);
	     if(returnVal == JFileChooser.APPROVE_OPTION) {
             File f = chooser.getSelectedFile();
             if (checked(f)) {
            	 callback.chooseFile(f);
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
		if (!f.canRead()) {
			JOptionPane.showMessageDialog(null, 
					I18NUtility.getMessage("md.error.addfile.readable"),
					I18NUtility.getMessage("md.error"), 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
