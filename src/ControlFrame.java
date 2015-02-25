import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ControlFrame extends JFrame implements ComponentListener {

	Controller controller;
	World world;
	Display display;
	Container mainPane, displayPane = new JPanel();
	RunwayComboBox runwayComboBox;
	ObstacleComboBox obstacleComboBox;

	public ControlFrame(Controller controller, World world) {
		super("Runway Redclaration Tool");
		this.controller = controller;
		this.world = world;
		display = new Display2D(world);

		mainPane = new Container();
		this.setContentPane(mainPane);
		mainPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		runwayComboBox = new RunwayComboBox(controller);
		obstacleComboBox = new ObstacleComboBox(controller);

		//main pane:
		//first line:
		c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = c.NORTH;
		c.weightx = 1;
		c.gridwidth = 3;
		mainPane.add(runwayComboBox, c);
		this.updateRunways();
		c.gridx = 3;
		c.gridheight = 4;
		mainPane.add(displayPane, c);
		
		//second line
		c.gridheight = 1;
		c.gridy = 1;
		c.gridx = 0;
		mainPane.add(obstacleComboBox, c);
		this.updateObstacles();
		
		//third line:
		c.gridwidth = 1;
		c.gridy = 2;
		c.gridx = 0;
		mainPane.add(new AddRunwayButton(), c);
		c.gridx = 1;
		mainPane.add(new AddObstacleButton(), c);
		c.gridx = 2;
		mainPane.add(new View2DButton(), c);

		//forth line:
		c.gridy = 3;
		c.gridx = 0;
		mainPane.add(new RmvRunwayButton(), c);
		c.gridx = 1;
		mainPane.add(new RmvObstacleButton(), c);
		c.gridx = 2;
		mainPane.add(new JButton("Open Side-on 2D View"), c);

		//fith line:
		c.weighty = 1;
		c.gridy = 4;
		c.gridx = 0;
		mainPane.add(new EditRunwayButton(), c);
		c.gridx = 1;
		mainPane.add(new EditObstacleButton(), c);
		c.gridx = 2;
		mainPane.add(new JButton("Open 3D View"), c);
		
		//display pane:
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 100;
		c.weighty = 100;
		c.fill = c.BOTH;
		this.addKeyListener(display);
		this.addComponentListener(this);
		Controller.eventManager.addEventNotify(EventManager.EventName.UPDATE_DISPLAY, this, "updateDisplay");
		displayPane.add(display, c);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public void updateRunways() {
		runwayComboBox.update();
	}

	public void updateObstacles() {
		obstacleComboBox.update();
	}
	
	public void updateDisplay() {
		System.out.println("updating display");
		display.repaint();
	}

	public void componentResized(ComponentEvent e) {
		System.out.println("Resized: " + display.getWidth() + " " + display.getHeight());
	}
	
	//Needed for component listener:
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
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

	private class AddObstacleButton extends JButton implements ActionListener{

		public AddObstacleButton() {
			super("Add Obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new AddObstacleFrame(controller);
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
	
	private class RmvRunwayButton extends JButton implements ActionListener{

		public RmvRunwayButton() {
			super("Remove Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			controller.removeRunway((Runway) runwayComboBox.getSelectedItem()); 
		}

	}
	
	private class EditRunwayButton extends JButton implements ActionListener{

		public EditRunwayButton() {
			super("Edit Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new EditRunwayFrame(controller, (Runway) runwayComboBox.getSelectedItem());
		}

	}
	
	private class RmvObstacleButton extends JButton implements ActionListener{

		public RmvObstacleButton() {
			super("Remove Obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			controller.removeObstacle((Obstacle) obstacleComboBox.getSelectedItem()); 
		}

	}
	
	private class EditObstacleButton extends JButton implements ActionListener{

		public EditObstacleButton() {
			super("Edit Obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new EditObstacleFrame(controller, (Obstacle) obstacleComboBox.getSelectedItem());
		}

	}
}
