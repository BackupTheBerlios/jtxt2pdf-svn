package lff.jtxt2pdf.gui;

import java.io.File;

import lff.jtxt2pdf.template.Template;
import lff.jtxt2pdf.utility.TemplateUtility;

public class LoadTemplateCallback implements IFileChooserCallback {

	private MainDialog dlg;
	
	public LoadTemplateCallback(MainDialog dlg) {
		this.dlg = dlg;
	}
	
	public void chooseFile(File f) {
		Template t = TemplateUtility.getInstance().load(f);
		if (t != null) {
			dlg.setTemplate(t);
		}
	}

}
