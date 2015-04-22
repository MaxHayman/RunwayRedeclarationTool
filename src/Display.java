import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


public abstract class Display extends JPanel implements KeyListener, MouseWheelListener {

	public enum View {
		TOP_VIEW,
		SIDE_VIEW,
	}
	
	MainFrame mainFrame;
	
	public Display(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
