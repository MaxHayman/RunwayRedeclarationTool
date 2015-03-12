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
	JTextField orientationField, designationField, lengthField, widthField, clearwayField, stopwayField, displacedThresholdField;
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
		
		orientationField = addField("Orientation:", "The orientation of the in tens of degrees. (e.g. 09 for 90 degrees)");
		designationField = addField("Designation:", "If there is more than 1 runway, specifiy if its the left or right runway. (e.g R for right)");
		lengthField = addField("Length:", "The length of the runway.");
		widthField = addField("Width:", "How wide the runway is.");
		clearwayField = addField("Clearway:", "The amount of clearway the runway has.");
		stopwayField = addField("Stopway:", "The amount of stopway the runway has.");
		displacedThresholdField = addField("Displaced Threshold:", "The displaced threshold the runway has.");
		
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(new AddButton(), gbc);
		
		this.pack();
		//this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JTextField addField(String fieldLabel, String tooltip) {
		//initialise text field and make it editable:
		JTextField textField;
		textField = new JTextField(20);
		textField.setEditable(true);
		
		//add to pane:
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		JLabel label = new JLabel(fieldLabel);
		if(tooltip.length() > 0)
			label.setToolTipText(tooltip);
		pane.add(label, gbc);
		
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
			float length = 0, width = 0, clearway = 0, stopway = 0, displacedThreshold = 0;
			
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
				clearway = Float.parseFloat(clearwayField.getText());
				stopway = Float.parseFloat(stopwayField.getText());
				displacedThreshold = Float.parseFloat(displacedThresholdField.getText());
				//add the runway to the controller:
				controller.addRunway(new Runway(orientation, designation, length, width, clearway, stopway, displacedThreshold, controller));
				frame.dispose();
			} catch(NumberFormatException ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}

}
