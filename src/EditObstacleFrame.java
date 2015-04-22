import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

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
	JTextField nameField, heightField, distanceFromThresholdField;
	GridBagConstraints c;
	JFrame frame;
	Map<Obstacle, Integer> savedObstacles;
	SavedObstacleComboBox savedObstacleComboBox;

	public EditObstacleFrame(Controller controller, Obstacle obstacle, Map<Obstacle, Integer> savedObstacles) {
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
		heightField = addField("Width(m):");
		distanceFromThresholdField = addField("Length(m):");

		nameField.setText(obstacle.name);
		heightField.setText(String.valueOf(obstacle.height));
		distanceFromThresholdField.setText(String.valueOf(savedObstacles.get(obstacle)));

		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridy++;
		pane.add(new EditButton(), c);
		c.gridy++;
		pane.add(new SaveButton(), c);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JTextField addField(String fieldLabel) {
		//initialise text field and make it editable:
		JTextField textField;
		textField = new JTextField(20);
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
			int height = 0, distanceFromThreshold = 0;

			//put this in a try to catch an exception due to poor format:
			try {
				//parse all the values:
				height = Integer.parseInt(heightField.getText());
				distanceFromThreshold = Integer.parseInt(distanceFromThresholdField.getText());
				
				//edit the obstacle:
				controller.printToNotification("Edited obstacle " + obstacle.toString());
				obstacle.height = height;
				savedObstacles.put(obstacle, distanceFromThreshold);

				controller.updateObstacles();
				controller.printToNotification("Obstacle changed to " + obstacle.toString());
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
			int height = 0, distanceFromThreshold = 0;

			//put this in a try to catch an exception due to poor format:
			try {
				//parse all the values:
				height = Integer.parseInt(heightField.getText());
				distanceFromThreshold = Integer.parseInt(distanceFromThresholdField.getText());

				controller.printToNotification("Editing custom obstacle " + savedObstacleComboBox.getSelectedItem().toString());
				savedObstacles.remove(savedObstacleComboBox.getSelectedItem());
				Obstacle newObstacle = new Obstacle(nameField.getText(), height);
				savedObstacles.put(newObstacle, distanceFromThreshold);
				controller.printToNotification("Custom obstacle changed to " + newObstacle.toString());
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
			for(Obstacle o : savedObstacles.keySet()) {
				this.addItem(o);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("new Obstacle selected");
			Obstacle selected = (Obstacle) this.getSelectedItem();
			if(selected != null){
				nameField.setText(String.valueOf(selected.name));
				heightField.setText(String.valueOf(selected.height));
				distanceFromThresholdField.setText(String.valueOf(savedObstacles.get(selected)));
			}
		}
	}
}
