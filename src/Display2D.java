import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;


public class Display2D extends Display {
	
	private BufferedImage image = null;
	private Vector camera = new Vector(300, 300, 1000);
	private int cameraAngle = 0;
	private int cameraZoom = 100;

	public Display2D(World map) {
		super(map);
		this.addMouseWheelListener(this);
	}

	public void init() {
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		redrawImage();
		Graphics2D g2 = (Graphics2D) g ;
		g2.drawImage(image, 0,0, null);
	}
	 
	public void redrawImage() {
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		int startx = (int) (camera.x - Math.floor(this.getWidth() / 2));
		int starty = (int) (camera.y - Math.floor(this.getHeight() / 2));
		
		int theColor, tempi, tempj, newi, newj = 0;
		System.out.println("Camera X: " + camera.x + "\t Y: " + camera.y + "\t O: " + cameraAngle);
			
			for (int i = 0; i < this.getWidth(); i++){
				for (int j =0 ; j < this.getHeight(); j++){
					
					tempi = i*cameraZoom/100;
					tempj = j*cameraZoom/100;
					newi = (int) ((startx + tempi) * Math.cos(Math.toRadians((cameraAngle))) + (starty + tempj) * Math.sin(Math.toRadians((cameraAngle))));
					newj = (int) (-(startx + tempi) * Math.sin(Math.toRadians((cameraAngle))) + (starty + tempj) * Math.cos(Math.toRadians((cameraAngle))));

					if(newi < 0 || (newj) < 0 || (newi) >= this.getWorld().size || (newj) >= this.getWorld().size)
						theColor = Color.BLACK.getRGB();
					else
						theColor = this.getWorld().map[newi][newj];
					
					image.setRGB(i, j, theColor);
				}
			}

	}
	
	private final Set<Integer> pressed = new HashSet<Integer>();
	public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
        repaint();
    }
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) {
    	pressed.add(e.getKeyCode());
    	boolean changed = false;
        if (pressed.size() > 0) {
            if (pressed.contains(KeyEvent.VK_UP)) {
            	camera.y -= 4;
            	changed = true;
            }
            if (pressed.contains(KeyEvent.VK_DOWN)) {
            	camera.y += 4;
            	changed = true;
            }
            if (pressed.contains(KeyEvent.VK_RIGHT)) {
            	camera.x += 4;
            	changed = true;
            }
            if (pressed.contains(KeyEvent.VK_LEFT)) {
            	camera.x -= 4;
            	changed = true;
            }
            if (pressed.contains(KeyEvent.VK_Z)) {
            	cameraAngle =  (cameraAngle + 2);
            	changed = true;
            
            }
            if (pressed.contains(KeyEvent.VK_X)) {
            	cameraAngle = (cameraAngle - 2);
            	changed = true;
            }

        }
    	
        if(changed)
        	repaint();
    }

	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
	    cameraZoom -= notches;
	    repaint();
	}
}
