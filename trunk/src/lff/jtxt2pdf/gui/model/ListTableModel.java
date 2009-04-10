package lff.jtxt2pdf.gui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import lff.jtxt2pdf.gui.data.Data;
import lff.jtxt2pdf.gui.data.Entry;

public class ListTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4957391436357079627L;

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "File";
		} else {
			return "Lines";
		}
	}

	public int getRowCount() {
		return Data.getData().size();
	}

	public void AddRow(Entry ent) {
		Data.getData().add(ent);
		fireTableRowsInserted(Data.getData().size() - 1, Data.getData().size());
		//fireTableDataChanged();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return Data.getData().get(rowIndex).name;
		} else {
			int i = Data.getData().get(rowIndex).lines;
			if (i != -1) {
				return String.valueOf(i);
			}
			return "Unknown";
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void removeTableModelListener(TableModelListener l) {

	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {

	}

}
