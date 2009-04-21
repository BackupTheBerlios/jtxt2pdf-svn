package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import lff.jtxt2pdf.utility.I18NUtility;

public class FontChooseListener implements ActionListener {

	JFileChooser chooser;
	IFileChooserCallback callback = null;
	
	public FontChooseListener(IFileChooserCallback callback) {
		this.callback = callback;
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filter = new TTFFileFilter();
		chooser.setFileFilter(filter);
	}
	
	/**
	 * Check Font Folder. Must be readable!
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

	public void actionPerformed(ActionEvent e) {
		 int returnVal = chooser.showOpenDialog(null);
	     if(returnVal == JFileChooser.APPROVE_OPTION) {
             File f = chooser.getSelectedFile();
             if (checked(f)) {
            	 callback.chooseFile(f);
             }
	     }
		
	}

}


class TTFFileFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getAbsolutePath().toLowerCase();
		return name.endsWith(".ttf");
	}

	public String getDescription() {
		return "Font Files";
	}
	
}