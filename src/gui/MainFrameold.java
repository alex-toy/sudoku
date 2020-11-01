package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrameold extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private MessagePanel messagePanel;
	
	
	private SudokuGrid sudokuGrid;
	
	
	public MainFrameold() {
		super("Sudoku");
		
		controller = new Controller();
		
		setLayout(new BorderLayout());
		
		
		setJMenuBar(createMenuBar());
		
		
		toolBar();
		
		
		underMenuPanel();
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				controller.disconnect();
				dispose();
				System.gc();
			}
		});
		
				
		setMinimumSize(new Dimension(500, 500));
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	

	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrameold.this, "Cannot connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
	
		fileMenu(menuBar);
		
		windowMenu(menuBar);
		
		return menuBar;
	}
	
	
	
	private void fileMenu(JMenuBar menuBar) {
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.addSeparator();
		menuBar.add(fileMenu);
		
		
		
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showOpenDialog(MainFrameold.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrameold.this, "Could not load data from file", 
								"error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		fileMenu.add(importDataItem);
		
		
		
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showSaveDialog(MainFrameold.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrameold.this, "Could not save data from file", 
								"error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		fileMenu.add(exportDataItem);
		
		
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String message = "Do you really want to quit the application ?";
				String confirm = "Confirm exit";
				int action = JOptionPane.showConfirmDialog(MainFrameold.this, message, confirm, JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_CANCEL_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for(WindowListener listener: listeners) {
						listener.windowClosing(new WindowEvent(MainFrameold.this, 0));
					}
				}
			}
		});
		fileMenu.add(exitItem);
		
		
		
	}
	
	
	
	private void windowMenu(JMenuBar menuBar) {
		
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		
		//show
		JMenu showMenu = new JMenu("show");
		windowMenu.add(showMenu);
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person form");
		showFormItem.setSelected(true);
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if(menuItem.isSelected()) {
					splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
				}
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		showMenu.add(showFormItem);
		
		//preferences
		JMenuItem prefsItem = new JMenuItem("preferences");
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				prefsDialog.setVisible(true);
			}
		});
		windowMenu.add(prefsItem);
		
		
		//window opened after clicking on preferences
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
		
	} 
	
	
		
	private void toolBar() {
		
		toolbar = new Toolbar();
		new TextPanel();
		toolbar.setToolbarListener(new ToolBarListener() {
			
			public void saveEventOccured() {
				connect();
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrameold.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void refreshEventOccured() {
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrameold.this, "Unable to load from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
			}
		});
		add(toolbar, BorderLayout.PAGE_START);
	} 
	
	
	
	private void underMenuPanel() {
		
		formularyPanel();
		
		//tablePanel();
		
		//ongletPanel();
		
		//splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabPane);
		//splitPane.setOneTouchExpandable(true);
		//add(splitPane, BorderLayout.CENTER);
		
		
		sudokuPanel();
		
	}
	
	
	
	private void formularyPanel() {
		
		formPanel = new FormPanel();
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		add(formPanel, BorderLayout.WEST);
		
	}
	
	
	
	private void tablePanel() {
		
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
				System.out.println(row);
			}
		});
		add(tablePanel, BorderLayout.CENTER);
		
	}
	
	
	
	private void ongletPanel() {
		
		tabPane = new JTabbedPane();
		tabPane.addTab("Person Database", tablePanel);
		messagePanel = new MessagePanel();
		tabPane.addTab("Messages", messagePanel);
		
	}
	
	
	
	private void sudokuPanel() {
		
		sudokuGrid = new SudokuGrid();
		//add(sudokuGrid, BorderLayout.EAST);
		
	}
	
	

}












