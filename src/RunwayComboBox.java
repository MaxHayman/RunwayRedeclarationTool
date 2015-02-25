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
		
		Controller.eventManager.addEventNotify(EventManager.EventName.UPDATE_DISPLAY, this, "update");
	}
	
	public void update() {
		//empty the list:
		this.removeAllItems();
		
		//iterate through the list from the controller to repopulate the list:
		for(Runway r : controller.getRunwayList()) {
			this.addItem(r);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("new Runway selected");
		controller.setCurrentRunway((Runway)this.getSelectedItem());
	}

}
