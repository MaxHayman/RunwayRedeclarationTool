import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddObstacleFrame extends JFrame{

	Controller controller;
	Container pane;
	JTextField nameField, xSizeField, ySizeField, zSizeField, xLocField, yLocField, angleField;
	GridBagConstraints c;
	JFrame frame;
	ArrayList<Obstacle> savedObstacles;
	SavedObstacleComboBox savedObstacleComboBox;

	public AddObstacleFrame(Controller controller, ArrayList<Obstacle> savedObstacles) {
		super("Add Obstacle");
		this.controller = controller;
		this.savedObstacles = savedObstacles;
		savedObstacleComboBox = new SavedObstacleComboBox();
		frame = this;
		
		JPanel mainPane = (JPanel) this.getContentPane();
		mainPane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 0;
		JLabel title = new JLabel("Add an obstacle");
		title.setFont(title.getFont().deriveFont(24.0f));
		mainPane.add(title);
		c.gridy++;
		mainPane.add(new JLabel("Select an obstacle:"), c);
		c.gridy++;
		c.fill = c.HORIZONTAL;
		mainPane.add(savedObstacleComboBox, c);
		c.gridy++;
		mainPane.add(new AddButton(), c);
		c.gridy++;
		mainPane.add(new NewObstacleButton(), c);
		
		

//		pane = new Container();
//		this.setContentPane(pane);
//		pane.setLayout(new GridBagLayout());
//		gbc = new GridBagConstraints();
//		gbc.gridy = 0;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		pane.add(savedObstacleComboBox, gbc);
//
//		nameField = addField("Name:");
//		xSizeField = addField("X Size:");
//		ySizeField = addField("Y Size:");
//		zSizeField = addField("Z Size:");
//		xLocField = addField("X Location:");
//		yLocField = addField("Y Location");
//		angleField = addField("Angle:");
//
//		gbc.gridx = 0;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.gridy++;
//		pane.add(new AddButton(), gbc);
//		gbc.gridy++;
//		pane.add(new SaveButton(), gbc);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JTextField addField(JPanel pane, String fieldLabel) {
		//initialise text field and make it editable:
		JTextField textField;
		textField = new JTextField();
		textField.setEditable(true);

		//add to pane:
		c.gridx = 0;
		c.gridy++;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.gridwidth = 1;
		pane.add(new JLabel(fieldLabel), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		pane.add(textField, c);
		return textField;
	}

	private class AddButton extends JButton implements ActionListener{

		public AddButton() {
			super("Use this obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			
			controller.addObstacle((Obstacle) savedObstacleComboBox.getSelectedItem());
			frame.dispose();
			
//			//bad code inc
//			float xSize = 0, ySize = 0, zSize = 0, xLoc = 0, yLoc = 0, angle = 0;
//
//			//put this in a try to catch an exception due to poor format:
//			try {
//				//parse all the values:
//				xSize = Float.parseFloat(xSizeField.getText());
//				ySize = Float.parseFloat(ySizeField.getText());
//				zSize = Float.parseFloat(zSizeField.getText());
//				xLoc = Float.parseFloat(xLocField.getText());
//				yLoc = Float.parseFloat(yLocField.getText());
//				angle = Float.parseFloat(angleField.getText());
//				
//				//make and add the obstacle:
//				controller.addObstacle(new Obstacle(xSize, ySize, zSize, xLoc, yLoc, angle, nameField.getText()));
//				frame.dispose();
//			} catch(NumberFormatException ex) {
//				//ex.printStackTrace();
//				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
//			}
		}		
	}
	
	private class NewObstacleButton extends JButton implements ActionListener{

		public NewObstacleButton() {
			super("Create new obstacle");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			new NewObstacleFrame();
		}		
	}
	
	private class SaveButton extends JButton implements ActionListener{
		JFrame frame;
		public SaveButton(JFrame frame) {
			super("Save");
			this.frame = frame;
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			//bad code inc
			float xSize = 0, ySize = 0, zSize = 0, xLoc = 0, yLoc = 0, angle = 0;

			//put this in a try to catch an exception due to poor format:
			try {
				//parse all the values:
				xSize = Float.parseFloat(xSizeField.getText());
				ySize = Float.parseFloat(ySizeField.getText());
				zSize = Float.parseFloat(zSizeField.getText());
				xLoc = Float.parseFloat(xLocField.getText());
				yLoc = Float.parseFloat(yLocField.getText());
				angle = Float.parseFloat(angleField.getText());
				
				savedObstacles.add(new Obstacle(xSize, ySize, zSize, xLoc, yLoc, angle, nameField.getText()));
				savedObstacleComboBox.update();
				frame.dispose();
				
			} catch(NumberFormatException ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}
	
	//ObstacleComboBox wasn't abstract enough, relied on controller
	//Could make abstract by passing arraylist in
	private class SavedObstacleComboBox extends JComboBox<Obstacle> implements ActionListener{
		
		public SavedObstacleComboBox() {
			super();
			this.update();
			this.addActionListener(this);
		}
		
		public void update() {
			this.removeAllItems();
			for(Obstacle o : savedObstacles) {
				this.addItem(o);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
//			System.out.println("new Obstacle selected");
//			Obstacle selected = (Obstacle) this.getSelectedItem();
//			if(selected != null){
//				nameField.setText(String.valueOf(selected.getName()));
//				xSizeField.setText(String.valueOf(selected.getxSize()));
//				ySizeField.setText(String.valueOf(selected.getySize()));
//				zSizeField.setText(String.valueOf(selected.getzSize()));
//				xLocField.setText(String.valueOf(selected.getxLocation()));
//				yLocField.setText(String.valueOf(selected.getyLocation()));
//				angleField.setText(String.valueOf(selected.getAngle()));
//			}
		}

	}
	
	private class NewObstacleFrame extends JFrame{
		public NewObstacleFrame(){
			super("New obstacle");
			init();
		}
	
		public void init(){
			JPanel mainPane = (JPanel) this.getContentPane();
			mainPane.setLayout(new GridBagLayout());
			c.gridx = 0;
			c.gridy = 0;
			nameField = addField(mainPane, "Name:");
			xSizeField = addField(mainPane, "Length(m):");
			ySizeField = addField(mainPane, "Width(m):");
			zSizeField = addField(mainPane, "Height(m):");
			xLocField = addField(mainPane, "X Location:");
			yLocField = addField(mainPane, "Y Location");
			angleField = addField(mainPane, "Angle:");
			c.gridy++;
			c.gridwidth = 2;
			c.gridx = 0;
			mainPane.add(new SaveButton(this), c);
			
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}

}
