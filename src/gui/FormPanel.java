package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gui.SudokuPanel.NumActionListener;

public class FormPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1249778354605130689L;

	private JButton oKBtn;
	private SudokuPanel sPanel;
	
	private JPanel buttonSelectionPanel;
	
	
	public FormPanel(SudokuPanel s) {
		
		this.sPanel = s;
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		
		oKBtn = new JButton("OK");
		oKBtn.setMnemonic(KeyEvent.VK_O);
		oKBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("test ok");
			}
		});
		
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));
		
		
		
		Border innerBorder = BorderFactory.createTitledBorder("add person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		
	}
	
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////First row///////////////////////////////////////
		
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		
		JButton b1 = new JButton("1");
		b1.addActionListener(sPanel.new NumActionListener());
		b1.setPreferredSize(new Dimension(40,40));
		buttonSelectionPanel.add(b1);
		
			
		JButton b2 = new JButton("2");
		b2.addActionListener(sPanel.new NumActionListener());
		b2.setPreferredSize(new Dimension(40,40));
		buttonSelectionPanel.add(b2);
		
		
		JButton b3 = new JButton("3");
		b3.addActionListener(sPanel.new NumActionListener());
		b3.setPreferredSize(new Dimension(40,40));
		buttonSelectionPanel.add(b3);
		
				
		add(buttonSelectionPanel);
			
	}
	
	
	public void setFormListener(FormListener listener){
	}

}

class AgeCategory {
	
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
	
	
}





