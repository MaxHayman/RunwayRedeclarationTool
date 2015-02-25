import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class ObstacleComboBox extends JComboBox<Obstacle> implements ActionListener{

	Controller controller;

	public ObstacleComboBox(Controller controller) {
		super();
		this.controller = controller;
		this.update();
		this.addActionListener(this);
	}

	public void update() {
		this.removeAllItems();
		if(controller.viewingRunway()){
			for(Obstacle o : controller.getObstacleList()) {
				//System.out.println("Adding obstacle " + o.toString());
				this.addItem(o);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("new Obstacle selected");
		controller.setCurrentObstacle((Obstacle)this.getSelectedItem());
	}

}
