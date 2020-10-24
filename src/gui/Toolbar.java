package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar  implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton saveButton;
	private JButton refreshButton;
	private ToolBarListener textListener;
	
	
	
	public Toolbar() {
		
		setFloatable(false);
		
		saveButton = new JButton();
		saveButton.setIcon(createIcon("/images/Save16.gif"));
		saveButton.setToolTipText("Save");
		saveButton.addActionListener(this);
		add(saveButton);
		
		refreshButton = new JButton();
		refreshButton.setIcon(createIcon("/images/Refresh16.gif"));
		refreshButton.setToolTipText("Refresh");
		refreshButton.addActionListener(this);
		add(refreshButton);
		
	}
	
	
	
	private ImageIcon createIcon(String path){
		   ImageIcon icon;
		   URL url = getClass().getResource(path);
		   if(url == null){
		      System.out.println("the url is actually " + url);
		      System.err.println("unable to load image: " + path);
		      return null;
		   }
		   else {
		      icon = new ImageIcon(url);
		      return icon;
		   }
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
