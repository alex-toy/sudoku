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
		
		
		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		JButton b1 = new JButton("1");
		b1.addActionListener(sPanel.new NumActionListener());
		add(b1, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		JButton b2 = new JButton("2");
		b2.addActionListener(sPanel.new NumActionListener());
		add(b2, gc);
		

		

		// //////////Second row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		//gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		JButton b3 = new JButton("3");
		b3.addActionListener(sPanel.new NumActionListener());
		add(b3, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		JButton b4 = new JButton("4");
		b4.addActionListener(sPanel.new NumActionListener());
		add(b4, gc);
		
		
		
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton b5 = new JButton("5");
		b5.addActionListener(sPanel.new NumActionListener());
		add(b5, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		JButton b6 = new JButton("6");
		b6.addActionListener(sPanel.new NumActionListener());
		add(b6, gc);
		
		
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton b7 = new JButton("7");
		b7.addActionListener(sPanel.new NumActionListener());
		add(b7, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		JButton b8 = new JButton("8");
		b8.addActionListener(sPanel.new NumActionListener());
		add(b8, gc);
		
		
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton b9 = new JButton("9");
		b9.addActionListener(sPanel.new NumActionListener());
		add(b9, gc);
		
		
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		



					
		add(buttonSelectionPanel);
		
	
			
	}
	
	
	public void setFormListener(FormListener listener){
	}

}







