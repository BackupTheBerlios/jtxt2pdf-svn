package lff.jtxt2pdf.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lff.jtxt2pdf.gui.model.ProgressTableModel;
import lff.jtxt2pdf.gui.render.ColoredTableRender;

public class ProgressDialog extends JDialog {
	
	
	private JProgressBar  progressBar;
	
	public static void main(String[] argu) {
		ProgressDialog d = new ProgressDialog();
		d.setModal(true);
		d.setVisible(true);
	}
	
	public ProgressDialog() {
		initComponents();
		T t  = new T(progressBar);
		t.start();
	}

	private void initComponents() {
		setLayout(null);	
		setSize(500,300);
		createProgressBar();
	}
	
	private void createProgressBar() {
		progressBar = new JProgressBar();
	    progressBar.setMinimum(0);
	    progressBar.setMaximum(100);
	    progressBar.setValue(0);

	    progressBar.setStringPainted(true);
	    progressBar.setBorderPainted(true);

	    progressBar.setPreferredSize(new Dimension(250,30));
	    progressBar.setBackground(Color.WHITE);
	    progressBar.setForeground(Color.GREEN);	
	    progressBar.setVisible(true);
	    
	    progressBar.setBounds(10, 10, 400, 20);
	    
	    add(progressBar);
	}
}


class T extends Thread {
	
	JProgressBar bar;
	
	public T(JProgressBar bar) {
		this.bar = bar;
	}
	public void run() {
		int i=0;
		while (i <= 100) {
			bar.setValue(i);
			i++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}