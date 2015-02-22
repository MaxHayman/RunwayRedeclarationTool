import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;


public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	
	public Frame2D (World map) {
		super("Runway Redeclaration Tool");
		
		int width = 720;
		int height = 450;
		
		display = new Display2D(map);
		this.setContentPane(display);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
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
