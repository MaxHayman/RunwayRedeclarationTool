import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	
	public Frame2D (World map, Controller controller) {
		super("Runway Redeclaration Tool");
		
		display = new Display2D(map);
		
		//view:
		this.setContentPane(display);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(720, 450);
		this.addComponentListener(this);
		
		Controller.eventManager.addEventNotify(EventManager.EventName.UPDATE_DISPLAY, this, "updateDisplay");
	}
	
	public void updateDisplay() {
		System.out.println("updating display");
		display.repaint();
	}

	public void componentResized(ComponentEvent e) {
		System.out.println("Resized: " + display.getWidth() + " " + display.getHeight());
	}

	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
}
