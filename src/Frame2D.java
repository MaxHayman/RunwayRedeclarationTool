import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	
	public Frame2D (World map, Controller controller) {
		super("Runway Redeclaration Tool");
		
		int width = 720;
		int height = 450;
		
		display = new Display2D(map);
		
		//view:
		this.setContentPane(display);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
	}
	
	public void updateDisplay() {
		System.out.println("updating display");
		display.redrawImage();
	}

	public void componentResized(ComponentEvent e) {
		
		//Dimension d = display.getSize();
		//display.setDisplaySize((int)d.getWidth(), (int)d.getHeight());
		System.out.println(display.getWidth() + " " + display.getHeight());
		display.redrawImage();
	}

	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
}
