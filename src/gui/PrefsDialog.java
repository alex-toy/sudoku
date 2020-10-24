package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog {
	
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	private PrefsListener prefsListener;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PrefsDialog(JFrame parent) {
		super(parent, "preferences", false);
		
		layoutControls();
		
		setSize(400, 300);
		setLocationRelativeTo(parent);
	}
	
	
	public void setDefaults(String user, String password, Integer port) {
		userField.setText(user);
		passField.setText(password);
		portSpinner.setValue(port);
	}

	public void setPrefsListener(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;
	}
	
	
	private void layoutControls() {
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new GridBagLayout());
		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		///First row//////////////////////////////////////////////////////
		controlsPanel.add(new JLabel("User : "), gc);
		//second column
		gc.gridx++;
		userField = new JTextField(10);
		controlsPanel.add(userField, gc);
		
		
		///next row//////////////////////////////////////////////////////
		gc.gridy++;
		gc.gridx = 0;
		controlsPanel.add(new JLabel("Password : "), gc);
		//second column
		gc.gridx++;
		passField = new JPasswordField(10);
		passField.setEchoChar('*');
		controlsPanel.add(passField, gc);
				
				
		///Next row//////////////////////////////////////////////////////
		gc.gridy++;
		gc.gridx = 0;
		controlsPanel.add(new JLabel("Port : "), gc);
		//second column
		gc.gridx++;
		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);
		controlsPanel.add(portSpinner, gc);
		
		
		///buttons panel//////////////////////////////////////////////////////
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Integer port = (Integer) portSpinner.getValue();
			String user = userField.getText();
			char[] pwd = passField.getPassword();
			if(prefsListener != null) {
				prefsListener.preferencesSet(user, new String(pwd), port);
			}
			setVisible(false);
		}
		});
		
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		buttonPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

}









