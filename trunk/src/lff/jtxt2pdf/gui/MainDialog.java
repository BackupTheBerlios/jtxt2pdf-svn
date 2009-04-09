package lff.jtxt2pdf.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lff.jtxt2pdf.Version;
import lff.jtxt2pdf.gui.model.ListTableModel;
import lff.jtxt2pdf.gui.render.ColoredTableRender;
import lff.jtxt2pdf.utility.I18NUtility;

public class MainDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnlFile;
	private JPanel pnlConfig;
	private JTabbedPane tabMain;
	private JMenuItem menuExit;
	private JMenu menuFiles;
	private JMenuBar mbMain;
	private JButton btnExit;
	private JButton btnAddFile;
	private JButton btnAddFolder;
	private JLabel lblOutput;
	private JTextField edtOutput;
	private JButton btnChooseOutput;
	private JCheckBox chkSubFolder;
	private JTable tblData;
	private JScrollPane scData;
	private ListTableModel listTableModel = new ListTableModel();
	
	private ActionListener exitAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(confirmExit())  {
		        System.exit(0);
			}
		}
	};
	
	public MainDialog() {
		initComponents();
	}


	private void initComponents() {
		setLayout(null);
		add(getTabMain());
		setJMenuBar(getMenuBar());
		setSize(655, 500);
		setVisible(false);
		setTitle("jTxt2PDF " + Version.getVersion());
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(578, 400, 50, 25);
		add(btnExit);
		btnExit.addActionListener(exitAction);
		
		btnAddFile = new JButton(I18NUtility.getMessage("md.addfiles"));
		btnAddFile.setBounds(10, 67, 90, 25);
		pnlFile.add(btnAddFile);
		
		btnAddFolder = new JButton(I18NUtility.getMessage("md.addfolder"));
		btnAddFolder.setBounds(120, 67, 90, 25);
		pnlFile.add(btnAddFolder);
		
		lblOutput = new JLabel("Output Folder");
		lblOutput.setBounds(15, 0, 100, 40);
		pnlFile.add(lblOutput);
		
		edtOutput = new JTextField("");
		edtOutput.setBounds(10, 35, 535, 20);
		pnlFile.add(edtOutput);
		
		btnChooseOutput = new JButton("...");
		btnChooseOutput.setBounds(560, 35, 30, 20);
		btnChooseOutput.addActionListener(new OutputFolderChooseListener(
				new OutputFolderCallback(this.edtOutput)));
		pnlFile.add(btnChooseOutput);
		
		chkSubFolder = new JCheckBox();
		chkSubFolder.setText("Include SubFolders");
		chkSubFolder.setBounds(231, 69, 160, 26);
		chkSubFolder.setSelected(true);
		pnlFile.add(chkSubFolder);

		
		tblData = new JTable(listTableModel);
		tblData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tblData.getColumn(tblData.getColumnName(0)).setPreferredWidth(450);
		//tblData.getColumn(tblData.getColumnName(1)).setPreferredWidth(200);
		for (int i = 0; i < tblData.getColumnCount(); i++)    {    
			tblData.getColumn(tblData.getColumnName(i)).setCellRenderer(new ColoredTableRender());
        }
		
		scData = new JScrollPane(tblData);
		scData.setBounds(10, 100, 580, 240);
		pnlFile.add(scData);
		
		
		
		btnAddFile.addActionListener(new AddFileListener(new AddFileCallback(listTableModel)));
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {

			}
			public void windowClosing(WindowEvent e) {
				if(confirmExit())  {
			        System.exit(0);
				}
			}

			public void windowDeactivated(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});
	}

	private JMenuBar getMenuBar() {
		if (mbMain == null) {
			mbMain = new JMenuBar();
			mbMain.add(getFilesMenu());
			mbMain.add(getHelpMenu());
		}
		return mbMain;
	}
	
	private JMenu getHelpMenu() {
		JMenu menuHelp = new JMenu();
		menuHelp.setText("Help");
		menuHelp.add(getAboutMenu());
		return menuHelp;
	}	

	private JMenu getFilesMenu() {
		if (menuFiles == null) {
			menuFiles = new JMenu();
			menuFiles.setText("Files");
			menuFiles.add(getExitMenu());
		}
		return menuFiles;
	}

	private JMenuItem getAboutMenu() {
		JMenuItem menuAbout = new JMenuItem();
		menuAbout.setText("About");
		return menuAbout;
	}
	
	private JMenuItem getExitMenu() {
		if (menuExit == null) {
			menuExit = new JMenuItem();
			menuExit.setText("Exit");
			menuExit.addActionListener(exitAction);
		}
		return menuExit;
	}

	private JTabbedPane getTabMain() {
		if (tabMain == null) {
			tabMain = new JTabbedPane();
			tabMain.addTab(I18NUtility.getMessage("md.files"), getFilesPanel());
			tabMain.addTab(I18NUtility.getMessage("md.configs"), getConfigPanel());
			tabMain.setBounds(5, 0, 610, 380);
		}
		return tabMain;
	}

	private JPanel getConfigPanel() {
		if (pnlConfig == null) {
			pnlConfig = new JPanel();
			pnlConfig.setLayout(null);
		}
		return pnlConfig;
	}

	private JPanel getFilesPanel() {
		if (pnlFile == null) {
			pnlFile = new JPanel();
			pnlFile.setVisible(false);
			pnlFile.setLayout(null);
		}
		return pnlFile;
	}


	private boolean confirmExit() {
		return JOptionPane.showConfirmDialog(null, 
				I18NUtility.getMessage("md.exit.confirm.message"),
				I18NUtility.getMessage("md.exit.confirm.title"),
				JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION;		
	}


	public void chooseFolder(File f) {
		
	}
}
