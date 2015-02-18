import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;


public class Display2D extends JPanel implements KeyListener {
	
	private BufferedImage image = new BufferedImage(720, 450, BufferedImage.TYPE_INT_RGB);
	private Vector camera = new Vector(300, 200, 1000);
	private int cameraAngle = 0;
	private World map = new World();
	private int height = 450;
	private int width = 720;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//image 
		//map.draw();
		Graphics2D g2 = (Graphics2D) g ;
		g2.drawImage(image, 0,0, null);
		redrawImage();
		
	}
	 
	public void redrawImage() {
		int startx = (int) (camera.x - Math.floor(width / 2));
		int starty = (int) (camera.y - Math.floor(height / 2));

		int theColor = 0;
		System.out.println("Camera X: " + camera.x + "\t Y: " + camera.y + "\t O: " + cameraAngle);
			
			for (int i=0; i < width; i++){
				for (int j=0; j < height; j++){
					
					int newi = (int) ((startx + i)*Math.cos(Math.toRadians((cameraAngle))) + (starty + j)*Math.sin(Math.toRadians((cameraAngle))));
					int newj = (int) (-(startx + i)*Math.sin(Math.toRadians((cameraAngle))) + (starty + j)*Math.cos(Math.toRadians((cameraAngle))));

					if(newi < 0 || (newj) < 0 || (newi) >= map.size || (newj) >= map.size)
						theColor = Color.BLACK.getRGB();
					else
						theColor = map.map[newi][newj];
					
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
        if (pressed.size() > 0) {
            if (pressed.contains(KeyEvent.VK_UP)) {
            	//System.out.println("UP");
            	camera.y -= 4;
            	
            }
            if (pressed.contains(KeyEvent.VK_DOWN)) {
            	//System.out.println("DOWN");
            	camera.y += 4;
             	
            }
            if (pressed.contains(KeyEvent.VK_RIGHT)) {
            	//System.out.println("RIGHT");
            	camera.x += 4;
            }
            if (pressed.contains(KeyEvent.VK_LEFT)) {
            	//System.out.println("LEFT");
            	camera.x -= 4;
            }
            if (pressed.contains(KeyEvent.VK_Z)) {
            	//System.out.println("RIGHT");
            	cameraAngle =  (cameraAngle + 2);
            
            }
            if (pressed.contains(KeyEvent.VK_X)) {
            	//System.out.println("LEFT");
            	cameraAngle = (cameraAngle - 2);
            }

        }
    	
        repaint();
    }
}
