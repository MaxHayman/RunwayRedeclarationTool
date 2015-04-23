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
		
	}
}
