package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class LoadTemplateListener implements ActionListener {
	
	JFileChooser chooser;
	IFileChooserCallback callback = null;
	
	public LoadTemplateListener(IFileChooserCallback callback) {
		this.callback = callback;
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File("template"));
		FileFilter filter = new PropertiesFileFilter();
		chooser.setFileFilter(filter);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		 int returnVal = chooser.showOpenDialog(null);
	     if(returnVal == JFileChooser.APPROVE_OPTION) {
             File f = chooser.getSelectedFile();
             callback.chooseFile(f);
	     }
	}

}

class PropertiesFileFilter extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getAbsolutePath().toLowerCase();
		return name.endsWith(".properties");
	}

	public String getDescription() {
		return "Template Files";
	}
}