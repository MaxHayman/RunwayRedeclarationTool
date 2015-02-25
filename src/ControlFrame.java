import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ControlFrame extends JFrame {

	Controller controller;
	World world;
	//Display display;
	Container pane = new JPanel();
	JLabel labelTORA = new JLabel("TORA: "), labelTODA = new JLabel("TODA: "), labelASDA = new JLabel("ASDA: "), labelLDA = new JLabel("LDA: ");
	RunwayComboBox runwayComboBox;
	ObstacleComboBox obstacleComboBox;

	//ISSUE: Display doesn't expand properly
	//ISSUE: Display doesn't pick up arrow keys
	public ControlFrame(Controller controller, World world) {
		super("Runway Redeclaration Tool");
		this.controller = controller;
		this.world = world;

		this.setContentPane(pane);
		this.setResizable(false);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		runwayComboBox = new RunwayComboBox(controller);
		obstacleComboBox = new ObstacleComboBox(controller);

		c.anchor = GridBagConstraints.NORTHEAST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		
		//form pane:
		//first line, runway combo box:
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(runwayComboBox, c);
		this.updateRunways();
		
		//second line, obstacle combo box
		c.gridy++;
		pane.add(obstacleComboBox, c);
		this.updateObstacles();
		
		//third line, add buttons and 2D view button:
		c.gridwidth = 1;
		c.gridy++;
		c.gridx = 0;
		pane.add(new AddRunwayButton(), c);
		c.gridx = 1;
		pane.add(new AddObstacleButton(), c);
		c.gridx = 2;
		pane.add(new View2DButton(), c);

		//forth line, remove buttons and 2D side-on view:
		c.gridy++;
		c.gridx = 0;
		pane.add(new RemoveRunwayButton(), c);
		c.gridx = 1;
		pane.add(new RemoveObstacleButton(), c);
		c.gridx = 2;
		pane.add(new JButton("Open Side-on 2D View"), c);

		//fith line, edit buttons and 3D view:
		c.gridy++;
		c.gridx = 0;
		pane.add(new EditRunwayButton(), c);
		c.gridx = 1;
		pane.add(new EditObstacleButton(), c);
		c.gridx = 2;
		pane.add(new JButton("Open 3D View"), c);
		
		//display pane:
		c.gridx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridy++;
		pane.add(labelTORA, c);
		c.gridy++;
		pane.add(labelTODA, c);
		c.gridy++;
		pane.add(labelASDA, c);
		c.gridy++;
		pane.add(labelLDA, c);
		
		Controller.eventManager.addEventNotify(EventManager.EventName.UPDATE_DISPLAY, this, "update");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void update() {
		updateRunways();
		updateObstacles();
		updateLabels();
	}

	//tells the runway combo box to recheck the runways and redisplay its list
	public void updateRunways() {
		runwayComboBox.update();
	}

	//tells the obstacle combo box to recheck the obstacles on the current runway and redisplay its list
	public void updateObstacles() {
		obstacleComboBox.update();
	}
	
	//sets all the calculation labels to the new values:
	public void updateLabels(){
		if (controller.viewingRunway()){ //only update if there's a selected item
			Runway runway = controller.getCurrentRunway();
			labelTORA.setText("TORA: " + runway.getTORA());
			labelTODA.setText("TODA: " + runway.getTODA());
			labelASDA.setText("ASDA: " + runway.getASDA());
			labelLDA.setText("LDA: " + runway.getLDA());
		}
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
	
	private class RemoveRunwayButton extends JButton implements ActionListener{

		public RemoveRunwayButton() {
			super("Remove Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			controller.removeRunway((Runway) controller.getCurrentRunway()); 
		}

	}
	
	private class EditRunwayButton extends JButton implements ActionListener{

		public EditRunwayButton() {
			super("Edit Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new EditRunwayFrame(controller, (Runway) controller.getCurrentRunway());
		}

	}
	
	private class RemoveObstacleButton extends JButton implements ActionListener{

		public RemoveObstacleButton() {
			super("Remove Obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			controller.removeObstacle((Obstacle) obstacleComboBox.getSelectedItem()); 
			controller.updateLabels();
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
