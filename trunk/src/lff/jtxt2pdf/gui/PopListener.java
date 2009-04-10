package lff.jtxt2pdf.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.JTable;

import lff.jtxt2pdf.gui.model.ListTableModel;

public class PopListener implements MouseListener {

	JTable table;
	
	public PopListener(JTable table) {
		this.table = table;
	}
	
	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) { 
			JPopupMenu mnu = TablePopMenu.getMenu(table);
			mnu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) { 
			JPopupMenu mnu = TablePopMenu.getMenu(table);
			mnu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}
