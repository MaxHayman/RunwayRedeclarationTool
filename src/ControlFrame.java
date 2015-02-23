import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		pane.add(new AddRunwayButton(), c);
		c.gridx = 2;
		pane.add(new JButton("Add Obstacle"), c);
		
		//second line:
		c.gridx = 0; c.gridy = 1;
		pane.add(new View2DButton(), c);
		c.gridx = 1;
		pane.add(new JButton("Open Side-on 2D View"), c);
		c.gridx = 2;
		pane.add(new JButton("Open 3D View"), c);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void updateRunways() {
		runwayComboBox.update();
	}
	
	private class AddRunwayButton extends JButton implements ActionListener{
		
		public AddRunwayButton() {
			super("Add Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new AddRunwayFrame(controller);
		}

	}
	
	private class View2DButton extends JButton implements ActionListener{
		
		public View2DButton() {
			super("Open Top-down 2D View");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new Frame2D(world, controller);
		}

	}

}
