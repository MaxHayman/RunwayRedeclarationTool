import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class RunwayComboBox extends JComboBox<Runway> implements ActionListener{
	
	Controller controller;
	
	public RunwayComboBox(Controller controller) {
		super();
		this.controller = controller;
		this.update();
		this.addActionListener(this);
	}
	
	public void update() {
		this.removeAllItems();
		for(Runway r : controller.getRunwayList()) {
			this.addItem(r);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("new Runway selected");
		controller.setCurrentRunway((Runway)this.getSelectedItem());
		controller.updateObstacles();
		controller.updateLabels();
	}

}
