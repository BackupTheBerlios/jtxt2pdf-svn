package lff.jtxt2pdf.gui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ListTableModel implements TableModel {

	public void addTableModelListener(TableModelListener l) {

	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		return "File";
	}

	public int getRowCount() {
		return 50;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return "<>";
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void removeTableModelListener(TableModelListener l) {

	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {

	}

}
