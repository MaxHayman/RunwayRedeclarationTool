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
		
		orientationField = addField("Orientation:");
		designationField = addField("Designation:");
		lengthField = addField("Length:");
		widthField = addField("Width:");
		
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(new AddButton(), gbc);
		
		this.setSize(300, 150);
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
				if(designationField.getText().length() > 1) {
					designation = designationField.getText().charAt(0);
				}
				length = Float.parseFloat(lengthField.getText());
				width = Float.parseFloat(widthField.getText());
				//add the runway to the controller:
				controller.addRunway(new Runway(orientation, designation, length, width));
			} catch(NumberFormatException ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}

}
