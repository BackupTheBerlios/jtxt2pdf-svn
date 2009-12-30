package lff.jtxt2pdf.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lff.jtxt2pdf.IProgessNotify;
import lff.jtxt2pdf.Option;
import lff.jtxt2pdf.Processor;
import lff.jtxt2pdf.Progress;
import lff.jtxt2pdf.Version;
import lff.jtxt2pdf.gui.data.Data;
import lff.jtxt2pdf.gui.model.ListTableModel;
import lff.jtxt2pdf.gui.render.ColoredTableRender;
import lff.jtxt2pdf.template.Template;
import lff.jtxt2pdf.utility.I18NUtility;
import lff.jtxt2pdf.utility.TemplateUtility;

public class MainDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnlFile;
	private JPanel pnlConfig;
	private JTabbedPane tabMain;
	private JMenuItem menuExit;
	private JMenu menuFiles;
	private JMenuBar mbMain;
	private JButton btnExit;
	private JButton btnStart;
	private JButton btnAddFile;
	private JButton btnAddFolder;
	private JLabel lblOutput;
	private JTextField edtOutput;
	private JButton btnChooseOutput;
	private JCheckBox chkSubFolder;
	private JTable tblData;
	private JScrollPane scData;
	private ListTableModel listTableModel = new ListTableModel();
	
	//config tab
	private JLabel lblTemplate;
	private JLabel lblCurrentTemplate;
	private JButton btnLoadTemplate;
	private JButton btnSaveTemplate;
	
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JTextField edtWidth;
	private JTextField edtHeight;
	
	//margin
	private JLabel lblTopMargin;
	private JLabel lblLeftMargin;
	private JLabel lblRightMargin;
	private JLabel lblBottomMargin;
	private JTextField edtTopMargin;
	private JTextField edtLeftMargin;
	private JTextField edtRightMargin;
	private JTextField edtBottomMargin;
	
	//font config
	private JLabel lblFont;
	private JTextField edtFont;
	private JButton edtChooseFont;
	private JLabel lblFontSize;
	private JTextField edtFontSize;
	
	private JProgressBar  progressBar;
	
	private Template template;
	
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
		setSize(628, 500);
		setVisible(false);
		setTitle("jTxt2PDF " + Version.getVersion());
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(565, 400, 50, 25);
		add(btnExit);
		btnExit.addActionListener(exitAction);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(500, 400, 50, 25);
		add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		
		
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
		Font t = edtOutput.getFont().deriveFont(Font.BOLD);
		edtOutput.setFont(t);
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

		for (int i = 0; i < tblData.getColumnCount(); i++)    {    
			tblData.getColumn(tblData.getColumnName(i)).setCellRenderer(new ColoredTableRender());
        }
		
		scData = new JScrollPane(tblData);
		scData.setBounds(10, 100, 580, 240);
		pnlFile.add(scData);

		tblData.addMouseListener(new PopListener(tblData));
		scData.addMouseListener(new PopListener(tblData));
		
		btnAddFile.addActionListener(new AddFileListener(new AddFileCallback(listTableModel)));
		btnAddFolder.addActionListener(new AddFolderListener(new AddFolderCallback(this)));
		
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
		
		setDefaultOutputFolder();
		
		//config panel
		
		//Template Controls. 
		createTemplateComponents();
		//Width/Height Controls.
		createWHComponents();		
		//Margin Setting Components.
		createMarginComponents();		
		//Font config
		createFontComponents();
		//Progress bar
		createProgressBar();
		
		loadDefaultTemplate();
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
	    
	    progressBar.setBounds(10, 405, 400, 20);
	    
	    add(progressBar);
	}


	private void loadDefaultTemplate() {
		File dt = new File("template/default.properties");
		if (!dt.exists()) {
			setTemplate(new Template());
			return;
		}
		Template t = TemplateUtility.getInstance().load(dt);
		setTemplate(t);
	}


	private void createFontComponents() {
		lblFont = new JLabel("Font:");
		lblFont.setBounds(20, 220, 50, 40);
		pnlConfig.add(lblFont);
		
		edtFont = new JTextField();
		edtFont.setBounds(65, 230, 320, 20);
		pnlConfig.add(edtFont);
		
		edtChooseFont = new JButton();
		edtChooseFont.setText("...");
		edtChooseFont.setBounds(400, 230, 30, 20);
		edtChooseFont.addActionListener(new FontChooseListener(new FontCallback(edtFont)));
		pnlConfig.add(edtChooseFont);
		
		lblFontSize = new JLabel("Font Size:");
		lblFontSize.setBounds(20, 260, 80, 40);
		pnlConfig.add(lblFontSize);
		
		edtFontSize = new JTextField();
		edtFontSize.setBounds(90, 270, 100, 20);
		pnlConfig.add(edtFontSize);
		
	}


	private void createTemplateComponents() {
		lblTemplate = new JLabel(I18NUtility.getMessage("md.template"));
		lblTemplate.setBounds(20, 10, 50, 40);
		pnlConfig.add(lblTemplate);
		
		lblCurrentTemplate = new JLabel(I18NUtility.getMessage("md.none"));
		
		lblCurrentTemplate.setBounds(75, 10, 150, 40);
		pnlConfig.add(lblCurrentTemplate);	
		
		btnLoadTemplate = new JButton(I18NUtility.getMessage("md.load"));
		btnLoadTemplate.setBounds(250, 20, 40, 20);
		pnlConfig.add(btnLoadTemplate);
		btnLoadTemplate.addActionListener(new LoadTemplateListener(new LoadTemplateCallback(this)));
		
		
		
		btnSaveTemplate = new JButton(I18NUtility.getMessage("md.save"));
		btnSaveTemplate.setBounds(300, 20, 40, 20);
		pnlConfig.add(btnSaveTemplate);
	}


	private void createWHComponents() {
		lblWidth = new JLabel(I18NUtility.getMessage("md.width"));
		lblWidth.setBounds(20, 50, 50, 40);
		pnlConfig.add(lblWidth);
		
		edtWidth = new JTextField();
		edtWidth.setText(String.valueOf(Option.getDefaultOpinion().width));
		edtWidth.setBounds(65, 60, 40, 20);
		pnlConfig.add(edtWidth);

		lblHeight = new JLabel(I18NUtility.getMessage("md.height"));
		lblHeight.setBounds(120, 50, 50, 40);
		pnlConfig.add(lblHeight);
		
		edtHeight = new JTextField();
		edtHeight.setText(String.valueOf(Option.getDefaultOpinion().height));
		edtHeight.setBounds(165, 60, 40, 20);
		pnlConfig.add(edtHeight);
	}


	private void createMarginComponents() {
		
		lblTopMargin = new JLabel(I18NUtility.getMessage("md.margin.top"));
		lblTopMargin.setBounds(70, 100, 80, 40);
		pnlConfig.add(lblTopMargin);
		
		edtTopMargin = new JTextField();
		edtTopMargin.setBounds(160, 110, 40, 20);
		pnlConfig.add(edtTopMargin);

		lblLeftMargin = new JLabel(I18NUtility.getMessage("md.margin.left"));
		lblLeftMargin.setBounds(20, 135, 80, 40);
		pnlConfig.add(lblLeftMargin);
		
		edtLeftMargin = new JTextField();
		edtLeftMargin.setBounds(100, 145, 40, 20);
		pnlConfig.add(edtLeftMargin);		
		
		
		lblRightMargin = new JLabel(I18NUtility.getMessage("md.margin.right"));
		lblRightMargin.setBounds(160, 135, 80, 40);
		pnlConfig.add(lblRightMargin);
		
		edtRightMargin = new JTextField();
		edtRightMargin.setBounds(240, 145, 40, 20);
		pnlConfig.add(edtRightMargin);		
		
		lblBottomMargin = new JLabel(I18NUtility.getMessage("md.margin.bottom"));
		lblBottomMargin.setBounds(70, 180, 80, 40);
		pnlConfig.add(lblBottomMargin);
		
		edtBottomMargin = new JTextField();
		edtBottomMargin.setBounds(160, 190, 40, 20);
		pnlConfig.add(edtBottomMargin);
	}

	private void setDefaultOutputFolder() {
		String s = System.getProperty("user.dir");
		edtOutput.setText(s);
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


	public ListTableModel getListTableModel() {
		return this.listTableModel;
	}


	public boolean getIncludeSubFolder() {
		return chkSubFolder.isSelected();
	}


	public void setTemplate(Template t) {
		this.template = t;
		applyTemplate(t);
	}


	private void applyTemplate(Template t) {
		
		this.edtHeight.setText(String.valueOf(t.getHeight()));
		this.edtWidth.setText(String.valueOf(t.getWidth()));
		
		this.edtLeftMargin.setText(String.valueOf(t.getLeftMargin()));
		this.edtRightMargin.setText(String.valueOf(t.getRightMargin()));
		this.edtTopMargin.setText(String.valueOf(t.getTopMargin()));		
		this.edtBottomMargin.setText(String.valueOf(t.getRightMargin()));
		
		this.edtFont.setText(t.getFont());
		this.edtFontSize.setText(String.valueOf(t.getSize()));
		
		this.lblCurrentTemplate.setText(t.getName());
		
	}
	
	private void start() {
		int count = Data.getData().size();
		
		if (count == 0) {
			JOptionPane.showMessageDialog(this, "No file selected!");
			return;
		}
		
		List<String> sources = new ArrayList<String>(count);
		for (int i=0; i<count; i++) {
			sources.add(Data.getData().get(i).name);
		}
		Processor p = new Processor(new IProgessNotify() {
			
			public void init() {
				progressBar.setValue(0);
				progressBar.setString("");
			}
			public void notify(Progress msg) {
				System.out.println("** set to " + msg.percengage + " " + msg.msg);
				progressBar.setValue(msg.percengage);
				progressBar.setString(msg.msg);
				Dimension d = progressBar.getSize();
				Rectangle rect = new Rectangle(0,0, d.width, d.height);
				progressBar.paintImmediately(rect);				
			}
		});
		p.process(this.template, this.edtOutput.getText(), sources);
	}


	public void notify(Progress msg) {
		
	}
}
