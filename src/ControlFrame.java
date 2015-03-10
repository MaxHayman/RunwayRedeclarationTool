import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ControlFrame extends JFrame { //implements ComponentListener {

	Controller controller;
	World world;
	//Display display;
	JPanel formPane = new JPanel(), calculationsPane = new JPanel();//, displayPane = new JPanel();
	JLabel labelTORA = new JLabel("TORA: "), labelTODA = new JLabel("TODA: "), labelASDA = new JLabel("ASDA: "), labelLDA = new JLabel("LDA: ");
	JTextArea calculationsBox = new JTextArea();
	RunwayComboBox runwayComboBox;
	ObstacleComboBox obstacleComboBox;
	ArrayList<Obstacle> savedObstacles = new ArrayList<Obstacle>();

	public ControlFrame(Controller controller, World world) {
		super("Runway Redclaration Tool");
		this.controller = controller;
		this.world = world;
		addDefaultSavedObstacles();
		//display = new Display2D(world);
		//display.setPreferredSize(new Dimension(500, 150));

		JPanel mainPane = (JPanel) this.getContentPane();
		GridBagConstraints c = new GridBagConstraints();

		runwayComboBox = new RunwayComboBox(controller);
		obstacleComboBox = new ObstacleComboBox(controller);

		mainPane.setLayout(new GridBagLayout());
		formPane.setLayout(new GridBagLayout());
		calculationsPane.setLayout(new GridBagLayout());
		JLabel titleLabel = new JLabel("Runway Redeclaration Program");
		titleLabel.setFont(titleLabel.getFont().deriveFont(32.0f));
		c.gridwidth = c.REMAINDER;
		mainPane.add(titleLabel, c);
		c.gridwidth = 1;
		c.gridy = 1;
		c.fill = c.BOTH;
		mainPane.add(formPane, c);
		c.gridx = 1;
		mainPane.add(calculationsPane, c);
		
		//Form panel
		//Runway
		JPanel runwayPane = new JPanel();
		runwayPane.setLayout(new GridBagLayout());
		c.weightx = 1;
		c.gridy = 0;
		c.gridx = 0;
		formPane.add(runwayPane, c);
		runwayPane.setBorder(BorderFactory.createTitledBorder("Runway"));
		c.gridwidth = 3;
		runwayPane.add(runwayComboBox, c);
		c.gridwidth = 1;
		c.gridy = 1;
		runwayPane.add(new AddRunwayButton(), c);
		c.gridx = 1;
		runwayPane.add(new EditRunwayButton(), c);
		c.gridx = 2;
		runwayPane.add(new RmvRunwayButton(), c);
		
		//Obstacles
		JPanel obstaclePane = new JPanel();
		obstaclePane.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 1;
		formPane.add(obstaclePane, c);
		obstaclePane.setBorder(BorderFactory.createTitledBorder("Obstacle"));
		c.gridwidth = 3;
		c.gridy = 0;
		obstaclePane.add(obstacleComboBox, c);
		c.gridwidth = 1;
		c.gridy = 1;
		obstaclePane.add(new AddObstacleButton(), c);
		c.gridx = 1;
		obstaclePane.add(new EditObstacleButton(), c);
		c.gridx = 2;
		obstaclePane.add(new RmvObstacleButton(), c);
		
		//Display
		JPanel displayPane = new JPanel();
		displayPane.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 2;
		formPane.add(displayPane, c);
		displayPane.setBorder(BorderFactory.createTitledBorder("Display"));
		c.gridwidth = 1;
		c.gridy = 0;
		displayPane.add(new View2DButton(), c);
		c.gridx = 1;
		displayPane.add(new JButton("Side-on 2D View"), c);
		c.gridx = 2;
		displayPane.add(new JButton("3D View"), c);
		
		//Calculations Panel
		calculationsPane.setBorder(BorderFactory.createTitledBorder("Calculations"));
		c.gridx = 0;
		c.gridy = 0;
		c.fill = c.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		calculationsBox.setPreferredSize(new Dimension(200, 100));
		calculationsBox.setEditable(false);
		calculationsBox.setLineWrap(true);
		JScrollPane calculationsScroll = new JScrollPane(calculationsBox);
		calculationsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		calculationsPane.add(calculationsScroll, c);
		c.weightx = 0;
		c.weighty = 0;
		c.gridy++;
		calculationsPane.add(labelTORA, c);
		c.gridy++;
		calculationsPane.add(labelTODA, c);
		c.gridy++;
		calculationsPane.add(labelASDA, c);
		c.gridy++;
		calculationsPane.add(labelLDA, c);
		
		
//		c.anchor = c.NORTHEAST;
//		mainPane.add(formPane, c);
//		//c.gridx = 1;
//		//c.fill = c.BOTH;
//		//mainPane.add(displayPane, c);
//		//form pane:
//		//first line:
//		
//		c.gridx = 0; c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 1;
//		c.gridwidth = 3;
//		formPane.add(runwayComboBox, c);
//		this.updateRunways();
//		
//		//second line
//		c.gridy = 2;
//		formPane.add(obstacleComboBox, c);
//		this.updateObstacles();
//		
//		//third line:
//		c.gridwidth = 1;
//		c.gridy = 3;
//		c.gridx = 0;
//		formPane.add(new AddRunwayButton(), c);
//		c.gridx = 1;
//		formPane.add(new AddObstacleButton(), c);
//		c.gridx = 2;
//		formPane.add(new View2DButton(), c);
//
//		//forth line:
//		c.gridy = 4;
//		c.gridx = 0;
//		formPane.add(new RmvRunwayButton(), c);
//		c.gridx = 1;
//		formPane.add(new RmvObstacleButton(), c);
//		c.gridx = 2;
//		formPane.add(new JButton("Open Side-on 2D View"), c);
//
//		//fith line:
//		c.weighty = 1;
//		c.gridy = 5;
//		c.gridx = 0;
//		formPane.add(new EditRunwayButton(), c);
//		c.gridx = 1;
//		formPane.add(new EditObstacleButton(), c);
//		c.gridx = 2;
//		formPane.add(new JButton("Open 3D View"), c);
//		
//		//sixth+:
//		c.gridy = 6;
//		c.gridx = 0;
//		formPane.add(labelTORA, c);
//		c.gridy = 6;
//		formPane.add(labelTODA, c);
//		c.gridy = 7;
//		formPane.add(labelASDA, c);
//		c.gridy = 8;
//		formPane.add(labelLDA, c);
		
//		//display pane:
//		displayPane.setLayout(new GridBagLayout());
//		c.gridx = 0;
//		c.gridy = 0;
//		c.fill = c.BOTH;
//		//this.addKeyListener(display);
//		this.addComponentListener(this);
//		Controller.eventManager.addEventNotify(EventManager.EventName.UPDATE_DISPLAY, this, "updateDisplay");
//		//displayPane.add(display, c);
//		c.gridy = 1;
//		displayPane.add(labelTORA, c);
//		c.gridy = 2;
//		displayPane.add(labelTODA, c);
//		c.gridy = 3;
//		displayPane.add(labelASDA, c);
//		c.gridy = 4;
//		displayPane.add(labelLDA, c);

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
	
	public void updateLabels(){
		if (runwayComboBox.getSelectedItem() != null){
			Runway runway = (Runway) runwayComboBox.getSelectedItem();
			labelTORA.setText("TORA: " + runway.getTORA());
			labelTODA.setText("TODA: " + runway.getTODA());
			labelASDA.setText("ASDA: " + runway.getASDA());
			labelLDA.setText("LDA: " + runway.getLDA());
		}
	}
	
	public void printToCalculations(String s){
		calculationsBox.append(s + "\n");
	}
	
	public void clearCalculations(){
		calculationsBox.setText("");
	}
	
	public void addDefaultSavedObstacles(){
		savedObstacles.add(new Obstacle(5, 5, 5, 5, 5, 5, "Five"));
	}
	
//	public void updateDisplay() {
//		System.out.println("updating display");
//	//	display.repaint();
//	}

//	public void componentResized(ComponentEvent e) {
//		//System.out.println("Resized: " + display.getWidth() + " " + display.getHeight());
//	}
	
	//Needed for component listener:
	
//	@Override
//	public void componentHidden(ComponentEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void componentMoved(ComponentEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void componentShown(ComponentEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	
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
			new AddObstacleFrame(controller, savedObstacles);
		}

	}

	private class View2DButton extends JButton implements ActionListener{

		public View2DButton() {
			super("Top-down 2D View");
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
			controller.updateLabels();
		}

	}
	
	private class EditObstacleButton extends JButton implements ActionListener{

		public EditObstacleButton() {
			super("Edit Obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new EditObstacleFrame(controller, (Obstacle) obstacleComboBox.getSelectedItem(), savedObstacles);
		}

	}
}
