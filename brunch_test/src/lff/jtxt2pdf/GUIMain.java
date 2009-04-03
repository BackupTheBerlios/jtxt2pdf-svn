package lff.jtxt2pdf;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;

import lff.jtxt2pdf.gui.MainDialog;

public class GUIMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new PlasticLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		
		MainDialog md = new MainDialog();
		center(md);
		md.setModal(true);
		md.setResizable(false);
		md.setVisible(true);		
	}

	private static void center(MainDialog md) {
		Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		md.setLocation(width / 2 - md.getWidth() /2 , height / 2 - md.getHeight() / 2);
		
	}

}
