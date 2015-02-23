import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ControlFrame extends JFrame {
	
	Controller controller;
	Container pane;
	RunwayComboBox runwayComboBox;

	public ControlFrame(Controller controller) {
		super("Runway Redclaration Tool");
		this.controller = controller;
		
		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		runwayComboBox = new RunwayComboBox(controller);
		
		//first line:
		c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0;
		c.weightx = 1;
		pane.add(new ViewComboBox(), c);
		c.gridx = 1;
		pane.add(runwayComboBox, c);
		this.updateRunways();
		c.gridx = 2;
		pane.add(new JButton("Add Runway"), c);
		c.gridx = 3;
		pane.add(new JButton("Add Obstacle"), c);
	}
	
	public void updateRunways() {
		runwayComboBox.update();
	}

}
