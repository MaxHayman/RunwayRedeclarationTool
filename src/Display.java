import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public abstract class Display extends JPanel implements KeyListener, MouseWheelListener{
	
	//protected int width, height;
	public World world;
	
	public Display(World world) {
		super();
		this.world = world;
	}
	
	protected World getWorld() {
		return world;
	}
	
	/*protected int getWidth() {
		return width;
	}
	
	protected int getHeight() {
		return height;
	}*/
	
	public abstract void redrawImage();

}
