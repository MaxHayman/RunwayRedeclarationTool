import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ControlFrame extends JFrame {
	
	Controller controller;
	World world;
	Container pane;
	RunwayComboBox runwayComboBox;

	public ControlFrame(Controller controller, World world) {
		super("Runway Redclaration Tool");
		this.controller = controller;
		this.world = world;
		
		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		runwayComboBox = new RunwayComboBox(controller);
		
		//first line:
		c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.weightx = 1;
		pane.add(runwayComboBox, c);
		this.updateRunways();
		c.gridx = 1;
		pane.add(new JButton("Add Runway"), c);
		c.gridx = 2;
		pane.add(new JButton("Add Obstacle"), c);
		
		//second line:
		c.gridx = 0; c.gridy = 1;
		pane.add(new View2DButton(controller, world), c);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void updateRunways() {
		runwayComboBox.update();
	}

}
