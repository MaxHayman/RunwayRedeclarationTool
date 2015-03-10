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


public class EditObstacleFrame extends JFrame{

	Controller controller;
	Obstacle obstacle;
	Container pane;
	JTextField nameField, xSizeField, ySizeField, zSizeField, xLocField, yLocField, angleField;
	GridBagConstraints c;
	JFrame frame;
	ArrayList<Obstacle> savedObstacles;
	SavedObstacleComboBox savedObstacleComboBox;

	public EditObstacleFrame(Controller controller, Obstacle obstacle, ArrayList<Obstacle> savedObstacles) {
		super("Edit Obstacle");
		this.controller = controller;
		this.savedObstacles = savedObstacles;
		this.obstacle = obstacle;
		savedObstacleComboBox = new SavedObstacleComboBox();
		frame = this;

		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(savedObstacleComboBox, c);

		nameField = addField("Name:");
		xSizeField = addField("Width(m):");
		ySizeField = addField("Length(m):");
		zSizeField = addField("Height(m)");
		xLocField = addField("X Location:");
		yLocField = addField("Y Location");
		angleField = addField("Angle:");
		nameField.setText(obstacle.getName());
		xSizeField.setText(String.valueOf(obstacle.getxSize()));
		ySizeField.setText(String.valueOf(obstacle.getySize()));
		zSizeField.setText(String.valueOf(obstacle.getzSize()));
		xLocField.setText(String.valueOf(obstacle.getxLocation()));
		yLocField.setText(String.valueOf(obstacle.getyLocation()));
		angleField.setText(String.valueOf(obstacle.getAngle()));

		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridy++;
		pane.add(new EditButton(), c);
		c.gridy++;
		pane.add(new SaveButton(), c);

		this.pack();
		this.setVisible(true);
	}

	private JTextField addField(String fieldLabel) {
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

	private class EditButton extends JButton implements ActionListener{

		public EditButton() {
			super("Edit Obstacle");
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
				
				//edit the obstacle:
				obstacle.setxSize(xSize);
				obstacle.setySize(ySize);
				obstacle.setzSize(zSize);
				obstacle.setxLocation(xLoc);
				obstacle.setyLocation(yLoc);
				obstacle.setAngle(angle);
				controller.updateObstacles();
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
				
				savedObstacles.remove(savedObstacleComboBox.getSelectedItem());
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
