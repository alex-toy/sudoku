import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	
	private JButton helloButton;
	private JButton goodbyeButton;
	
	private StringListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		helloButton = new JButton("hello");
		helloButton.addActionListener(this);
		add(helloButton);
		
		goodbyeButton = new JButton("bye");
		goodbyeButton.addActionListener(this);
		add(goodbyeButton);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		
	}

	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		
		if( clicked == helloButton) {
			if(textListener != null) {
				textListener.textEmitted("hello\n");
			}
			
		} else if(clicked == goodbyeButton) {
			if(textListener != null) {
				textListener.textEmitted("good bye\n");
			}
		}
		
	}

}
