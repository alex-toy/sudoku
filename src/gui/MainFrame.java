package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	
	
	public MainFrame() {
		super("Sudoku");
		
		controller = new Controller();
		
		setLayout(new BorderLayout());
		
			
		setJMenuBar(createMenuBar());
		
		
		
		toolbar = new Toolbar();
		textPanel = new TextPanel();
		toolbar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});
		add(toolbar, BorderLayout.NORTH);
		
		
		
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
				System.out.println(row);
			}
		});
		add(tablePanel, BorderLayout.CENTER);
		
		
		
		formPanel = new FormPanel();
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		add(formPanel, BorderLayout.WEST);
		
		
		
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("db");
		prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefaults(user, password, port);
		
		
		
		
		setMinimumSize(new Dimension(500, 500));
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//private void setMinimumSize(int i, int j) {
		// TODO Auto-generated method stub
		
	//}

	
	
	
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		fileMenu.add(exportDataItem);
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				String mess = "Enter your user name";
				String conf = "Enter user name";
				String text = JOptionPane.showInputDialog(MainFrame.this, mess, conf, JOptionPane.OK_CANCEL_OPTION|JOptionPane.INFORMATION_MESSAGE);
				System.out.println(text);
				
				String message = "Do you really want to quit the application ?";
				String confirm = "Confirm exit";
				int action = JOptionPane.showConfirmDialog(MainFrame.this, message, confirm, JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_CANCEL_OPTION) {
					System.exit(0);
				}
			}
		});
		fileMenu.add(exitItem);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", 
								"error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data from file", 
								"error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		JMenu showMenu = new JMenu("show");
		windowMenu.add(showMenu);
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person form");
		showFormItem.setSelected(true);
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		showMenu.add(showFormItem);
		JMenuItem prefsItem = new JMenuItem("preferences");
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				prefsDialog.setVisible(true);
			}
		});
		windowMenu.add(prefsItem);
		
		return menuBar;
	}

}












