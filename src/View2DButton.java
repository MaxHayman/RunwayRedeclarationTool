import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class View2DButton extends JButton implements ActionListener{
	
	Controller controller;
	World world;
	
	public View2DButton(Controller controller, World world) {
		super("Open Top-down 2D View");
		
		this.controller = controller;
		this.world = world;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		new Frame2D(world, controller);
	}

}
