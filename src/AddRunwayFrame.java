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


public class AddRunwayFrame extends JFrame{
	
	Controller controller;
	Container pane;
	JTextField orientationField, designationField, lengthField, widthField;
	GridBagConstraints gbc;
	JFrame frame;
	
	public AddRunwayFrame(Controller controller) {
		super("Add Runway");
		this.controller = controller;
		frame = this;
		
		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		addField("Orientation:", orientationField);
		addField("Designation:", designationField);
		addField("Length:", lengthField);
		addField("Width:", widthField);
		
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(new AddButton());
		
		this.pack();
		this.setVisible(true);
	}
	
	private void addField(String fieldLabel, JTextField textField) {
		//initialise text field and make it editable:
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
	}
	
	private class AddButton extends JButton implements ActionListener{
		
		public AddButton() {
			super("Add Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			//bad code inc
			int orientation = 0;
			Character designation = null;
			float length = 0, width = 0;
			
			//put this in a try to catch an exception due to poor format:
			try {
				orientation = Integer.parseInt(orientationField.getText());
				designation = null;
				//check if there is an entry in the designation field before setting the value
				if(designationField.getText() != "") {
					designation = designationField.getText().charAt(0);
				}
				length = Float.parseFloat(lengthField.getText());
				width = Float.parseFloat(widthField.getText());
				//add the runway to the controller:
				controller.addRunway(new Runway(orientation, designation, length, width));
			} catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}

}
