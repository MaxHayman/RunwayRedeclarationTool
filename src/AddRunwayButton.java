import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class AddRunwayButton extends JButton implements ActionListener{
	
	Controller controller;
	
	public AddRunwayButton(Controller controller) {
		super("Add Runway");
		this.controller = controller;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		new AddRunwayFrame(controller);
	}

}
