package lff.jtxt2pdf.gui.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColoredTableRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -563429720083070027L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (row % 2 == 0) {
			setBackground(Color.white);
		}
		if (row % 2 == 1) {
			setBackground(new Color(242, 255, 255));
		}
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}

}
