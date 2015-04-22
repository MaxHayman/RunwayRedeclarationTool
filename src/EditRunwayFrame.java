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


public class EditRunwayFrame extends JFrame{
	Controller controller;
	Container pane;
	JTextField orientationField, designationField, lengthField, widthField, clearwayField, stopwayField, displacedThresholdField;
	GridBagConstraints gbc;
	JFrame frame;
	Runway runway;
	
	public EditRunwayFrame(Controller controller, Runway runway) {
		super("Edit Runway");
		this.controller = controller;
		this.runway = runway;
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
		clearwayField = addField("Clearway:");
		stopwayField = addField("Stopway:");
		displacedThresholdField = addField("Displaced Threshold:");
		
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pane.add(new EditButton(), gbc);
		
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
			super("Edit Runway");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			//bad code inc
			int orientation = 0;
			Character designation = null;
			int toda = 0, tora = 0, asda = 0, lda = 0, displacedThreshold = 0;
			
			//put this in a try to catch an exception due to poor format:
			try {
				orientation = Integer.parseInt(orientationField.getText());
				designation = null;
				//check if there is an entry in the designation field before setting the value
				if(designationField.getText().length() > 1) {
					designation = designationField.getText().charAt(0);
				}
				toda = Integer.parseInt(lengthField.getText());
				tora = Integer.parseInt(widthField.getText());
				asda = Integer.parseInt(clearwayField.getText());
				lda = Integer.parseInt(stopwayField.getText());
				displacedThreshold = Integer.parseInt(displacedThresholdField.getText());
				//edit the runway:
				controller.printToNotification("Editing runway " + runway.toString());
				runway.name = "" + orientation + designation;
				runway.setTORA(toda);
				runway.setTODA(tora);
				runway.setASDA(asda);
				runway.setLDA(lda);
				runway.setDisplacedThreshold(displacedThreshold);
				controller.printToNotification("Runway changed to " + runway.toString());
				controller.updateRunways();
				frame.dispose();
			} catch(NumberFormatException ex) {
				//ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error: poorly formatted values");
			}
		}		
	}
	
}
