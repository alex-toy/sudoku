package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton saveButton;
	private JButton refreshButton;
	
	private ToolBarListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		saveButton = new JButton("save");
		saveButton.addActionListener(this);
		add(saveButton);
		
		refreshButton = new JButton("refresh");
		refreshButton.addActionListener(this);
		add(refreshButton);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		
	}

	public void setToolbarListener(ToolBarListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		
		if( clicked == saveButton) {
			if(textListener != null) {
				textListener.saveEventOccured();
			}
			
		} else if(clicked == refreshButton) {
			if(textListener != null) {
				textListener.refreshEventOccured();
			}
		}
		
	}

}
