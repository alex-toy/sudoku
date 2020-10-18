import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	
	
	
	public MainFrame() {
		super("hello world");
		
		setLayout(new BorderLayout());
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
		formPanel = new FormPanel();
		toolbar = new Toolbar();
		textPanel = new TextPanel();
		setJMenuBar(createMenuBar());
		toolbar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmpCat();
				String gender = e.getGender();
				
				textPanel.appendText(name + " : " + occupation + " / " + ageCat + " / " + empCat + " / " + gender + "\n");
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		
		setMinimumSize(new Dimension(1000, 1000));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setMinimumSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}

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
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		});
		
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
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
		
		return menuBar;
	}

}







