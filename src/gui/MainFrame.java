package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.prefs.Preferences;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import gui.SudokuPanel.NumActionListener;
import services.computationlogic.SudokuSolver;

public class MainFrame extends JFrame {
	
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
	
	private SudokuPanel sudokuPanel;
	//private SudokuGrid sudokuGrid;
	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	
	private PrefsDialog anwers;
	
	
	private JButton ResBtn;
	
	
	public MainFrame() {
		super("Sudoku");
		this.sPanel = new SudokuPanel();
		
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
		
		setMinimumSize(new Dimension(1200, 600));
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	

	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
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
		fileMenu.add(importDataItem);
		
		
		
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
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
		fileMenu.add(exportDataItem);
		
		
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String message = "Do you really want to quit the application ?";
				String confirm = "Confirm exit";
				int action = JOptionPane.showConfirmDialog(MainFrame.this, message, confirm, JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_CANCEL_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for(WindowListener listener: listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
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
		
		
		JMenu file = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		JMenuItem sixBySixGame = new JMenuItem("6 By 6 Game");
		sixBySixGame.addActionListener(new NewGameListener(SudokuPuzzleType.SIXBYSIX,30));
		JMenuItem nineByNineGame = new JMenuItem("9 By 9 Game");
		nineByNineGame.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE,26));
		JMenuItem twelveByTwelveGame = new JMenuItem("12 By 12 Game");
		twelveByTwelveGame.addActionListener(new NewGameListener(SudokuPuzzleType.TWELVEBYTWELVE,20));
		newGame.add(sixBySixGame);
		newGame.add(nineByNineGame);
		newGame.add(twelveByTwelveGame);
		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
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
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void refreshEventOccured() {
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
			}
		});
		add(toolbar, BorderLayout.PAGE_START);
	} 
	
	
	
	private void underMenuPanel() {
		
		formularyPanel();
		
		sudokuPanel();
		
	}
	
	
	
	private void formularyPanel() {
		
		formPanel = new FormPanel(this.sPanel);
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				controller.addPerson(e);
				tablePanel.refresh();
			}
		});
		add(formPanel, BorderLayout.WEST);
	
		
	}
	
	

	private class NewGameListener implements ActionListener {

		private SudokuPuzzleType puzzleType;
		private int fontSize;
		
		public NewGameListener(SudokuPuzzleType puzzleType,int fontSize) {
			this.puzzleType = puzzleType;
			this.fontSize = fontSize;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(puzzleType,fontSize);
		}
	}
	
	
	
	public void rebuildInterface(SudokuPuzzleType puzzleType,int fontSize) {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		for(String value : generatedPuzzle.getValidValues()) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(40,40));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}
	
	
	
	private void sudokuPanel() {
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));

		
		
		int [][][] board = new int[9][9][9];
		for(int j=0;j<9;j++){  
			for(int i=0;i<9;i++){
				String str = sPanel.getPuzzle().getBoard()[j][i];
				int n = 0;
				if(str.length() == 1) { n = Integer.parseInt(str); board[j][i][n-1] = n; }
		    }
	    }
		
		
		windowPanel.add(sPanel);
		//windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);
		
		
		sPanel.setFontSize(26);
		
		
		ResBtn = new JButton("Resolve");
		ResBtn.setMnemonic(KeyEvent.VK_R);
		ResBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				SudokuSolver slv = new SudokuSolver(board);
				boolean is_solved = slv.resolve();
				System.out.println(is_solved);
				slv.display();
			}
		});
		add(ResBtn, BorderLayout.EAST);
		
		
	}
	

}












