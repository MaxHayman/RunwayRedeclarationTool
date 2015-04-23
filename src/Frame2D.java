import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	
	public Frame2D (MainFrame mainFrame, Display.View view) {
		super("Runway Redeclaration Tool");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		if(view == Display.View.TOP_VIEW) {
			display = new Display2DTop(mainFrame);
		    this.setLocation(dim.width/2-this.getSize().width/2, 0);
		}
		else if (view == Display.View.SIDE_VIEW) {
			display = new Display2DSide(mainFrame);
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		}
			

		//view:
		this.setContentPane(display);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(720, 450);
		this.addComponentListener(this);
		
		EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "updateDisplay");
		EventManager.getEventManager().addEventNotify(EventManager.EventName.EXIT, this, "dispose");

		this.setJMenuBar(new DisplayMenuBar(this));
	}
	
	public void updateDisplay() {
		display.repaint();
	}

	public void componentResized(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
}
