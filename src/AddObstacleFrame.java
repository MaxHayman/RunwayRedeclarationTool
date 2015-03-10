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
import javax.swing.JTextField;


public class AddObstacleFrame extends JFrame{

	Controller controller;
	Container pane;
	JTextField nameField, xSizeField, ySizeField, zSizeField, xLocField, yLocField, angleField;
	GridBagConstraints gbc;
	JFrame frame;
	ArrayList<Obstacle> savedObstacles;
	SavedObstacleComboBox savedObstacleComboBox;

	public AddObstacleFrame(Controller controller, ArrayList<Obstacle> savedObstacles) {
		super("Add Obstacle");
		this.controller = controller;
		this.savedObstacles = savedObstacles;
		savedObstacleComboBox = new SavedObstacleComboBox();
		frame = this;

		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(savedObstacleComboBox, gbc);

		nameField = addField("Name:");
		xSizeField = addField("X Size:");
		ySizeField = addField("Y Size:");
		zSizeField = addField("Z Size:");
		xLocField = addField("X Location:");
		yLocField = addField("Y Location");
		angleField = addField("Angle:");

		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy++;
		pane.add(new AddButton(), gbc);
		gbc.gridy++;
		pane.add(new SaveButton(), gbc);

		this.pack();
		this.setVisible(true);
	}

	private JTextField addField(String fieldLabel) {
		//initialise text field and make it editable:
		JTextField textField;
		textField = new JTextField();
		textField.setEditable(true);

		//add to pane:
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		pane.add(new JLabel(fieldLabel), gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		pane.add(textField, gbc);
		return textField;
	}

	private class AddButton extends JButton implements ActionListener{

		public AddButton() {
			super("Add Obstacle");
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
				
				//make and add the obstacle:
				controller.addObstacle(new Obstacle(xSize, ySize, zSize, xLoc, yLoc, angle, nameField.getText()));
				frame.dispose();
			} catch(NumberFormatException ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}
	
	private class SaveButton extends JButton implements ActionListener{

		public SaveButton() {
			super("Save Obstacle");
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
			System.out.println("new Obstacle selected");
			Obstacle selected = (Obstacle) this.getSelectedItem();
			if(selected != null){
				nameField.setText(String.valueOf(selected.getName()));
				xSizeField.setText(String.valueOf(selected.getxSize()));
				ySizeField.setText(String.valueOf(selected.getySize()));
				zSizeField.setText(String.valueOf(selected.getzSize()));
				xLocField.setText(String.valueOf(selected.getxLocation()));
				yLocField.setText(String.valueOf(selected.getyLocation()));
				angleField.setText(String.valueOf(selected.getAngle()));
			}
		}

	}

}
