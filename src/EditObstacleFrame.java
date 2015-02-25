import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class EditObstacleFrame extends JFrame{

	Controller controller;
	Obstacle obstacle;
	Container pane;
	JTextField xSizeField, ySizeField, zSizeField, xLocField, yLocField, angleField;
	GridBagConstraints gbc;
	JFrame frame;

	public EditObstacleFrame(Controller controller, Obstacle obstacle) {
		super("Edit Obstacle");
		this.controller = controller;
		this.obstacle = obstacle;
		frame = this;

		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridy = 0;

		xSizeField = addField("X Size:");
		ySizeField = addField("Y Size:");
		zSizeField = addField("Z Size:");
		xLocField = addField("X Location:");
		yLocField = addField("Y Location");
		angleField = addField("Angle:");

		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(new EditButton(), gbc);

		this.setSize(300, 200);
		this.setVisible(true);
	}

	private JTextField addField(String fieldLabel) {
		//initialise text field and make it editable:
		JTextField textField;
		textField = new JTextField();
		textField.setEditable(true);

		//add to pane:
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		pane.add(new JLabel(fieldLabel), gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridx = 1;
		pane.add(textField, gbc);
		gbc.gridy++;
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

}
