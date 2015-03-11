import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public abstract class Display extends JPanel implements KeyListener, MouseWheelListener{
	
	//protected int width, height;
	public World world;
	public BufferedImage image = null;
	
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
	
	public void saveFractal(String format)
	{
		System.out.print("Saving...");
		
		  File theDir = new File("saves");

		  /*
		   * This will check if 'saves' exists and if
		   * it dosen't, create it.
		   */
		  if (!theDir.exists()) {
		    boolean result = theDir.mkdir();  

		     if(result) {    
		       System.out.print("folder created...");  
		     }
		  }

		try {
		    /*
		     * filename stores the destination file name.
		     */
			String filename = "saves/" + System.currentTimeMillis() + "."+ format;
		    File outputfile = new File(filename);
		    ImageIO.write(image, format, outputfile);
		    System.out.println("Complete! (" + filename + ")");
		} catch (Exception e) {
		    System.out.println("Failed:\n" + e );
		}
	}

}
