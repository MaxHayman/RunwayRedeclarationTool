import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;


public class Frame2D extends JFrame implements ComponentListener {
	
	Display2D display2D;
	
	public Frame2D () {
		super("Runway Redeclaration Tool");
		display2D = new Display2D();
		this.setContentPane(display2D);
		this.addKeyListener(display2D);
		this.setVisible(true);
		this.setSize(720, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
	}

	public void componentResized(ComponentEvent e) {
		
		Dimension d = display2D.getSize();
		display2D.setDisplaySize((int)d.getWidth(), (int)d.getHeight());
		System.out.println(d.getWidth() + " " + d.getHeight());
	}

	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
}
