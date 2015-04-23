import java.awt.Color;
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
	
	public Color skyColor = Color.blue.darker();
	public Color groundColor = Color.green.darker();
	public Color lineColor = Color.white;
	public Color toraColor = Color.orange;
	public Color todaColor = Color.red;
	public Color asdaColor = Color.BLUE;
	public Color ldaColor = Color.YELLOW;
	public Color obstacleColor = Color.red;
	public Color runwayColor = Color.black;
}
