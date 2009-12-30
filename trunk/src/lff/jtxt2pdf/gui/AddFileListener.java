package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import lff.jtxt2pdf.utility.I18NUtility;

public class AddFileListener implements ActionListener {

	JFileChooser chooser;
	IFileChooserCallback callback = null;
	
	public AddFileListener(IFileChooserCallback callback) {
		this.callback = callback;
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filter = new TxtFileFilter();
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
	 * Check input file.
	 * @param f File object of the input file.
	 * @return If something is wrong, return false.
	 */
	private boolean checked(File f) {
		//check reading right
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

class TxtFileFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getAbsolutePath().toLowerCase();
		return name.endsWith(".txt");
	}

	public String getDescription() {
		return "Text Files";
	}
}