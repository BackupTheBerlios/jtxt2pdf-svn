package lff.jtxt2pdf.gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import lff.jtxt2pdf.gui.model.ListTableModel;

public class TablePopMenu {
	
	static JPopupMenu mnuTable = new JPopupMenu();
	static JPopupMenu mnuTableData = new JPopupMenu();
	static JPopupMenu mnuTableRemove = new JPopupMenu();
	static {
		mnuTable.add(new JMenuItem("Add File"));
		mnuTable.add(new JMenuItem("Add Folder"));
		mnuTableData.add(new JMenuItem("Add File"));
		mnuTableData.add(new JMenuItem("Add Folder"));
		mnuTableData.add(new JMenuItem("Remove"));
		mnuTableData.add(new JMenuItem("Remove All"));	

		mnuTableRemove.add(new JMenuItem("Add File"));
		mnuTableRemove.add(new JMenuItem("Add Folder"));
		mnuTableRemove.add(new JMenuItem("Remove All"));	
	}
	
	public static JPopupMenu getMenu(JTable table) {
		ListTableModel model = (ListTableModel)table.getModel();
		if (model.getRowCount() == 0) {
			return mnuTable;
		}
		if (table.getSelectedRowCount() == 0 ) {
			return mnuTableRemove;
		} 
		return mnuTableData;
	}
}
