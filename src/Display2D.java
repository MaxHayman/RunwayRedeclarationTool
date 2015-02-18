import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;


public class Display2D extends JPanel implements KeyListener {
	
	private BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
	private Vector camera = new Vector(100, 100, 1000);
	private World map = new World();
	private int height = 400;
	private int width = 400;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//image 
		//map.draw();
		Graphics2D g2 = (Graphics2D) g ;
		g2.drawImage(image, 0,0, null);
		redrawImage();
		
	}

	
	 
	public void redrawImage() {
		int startx = (int) (camera.x - Math.floor(map.size / 2));
		int starty = (int) (camera.y - Math.floor(map.size / 2));
		int endx = (int) (camera.x + Math.floor(map.size / 2));
		int endy = (int) (camera.y + Math.floor(map.size / 2));
		int[][] drawnMap = map.map;
		int theColor = 0;

			
			for (int i=0; i < width; ++i){
				for (int j=0; j < height; ++j){

					
					
					if(i == 350 && j == 350)
					{
						System.out.println(startx + "\t" + (startx + i));
					
					}
					
					if((startx + i) < 0 || (starty + j) < 0 || (endx - map.size - (map.size - i)) >= 0 || (endy - map.size - (map.size - j)) >= 0)
							theColor = Color.BLACK.getRGB();
					else
						/*if(i + camera.x < 0 || i + camera.x >= map.size ||  j + camera.y >= map.size || j + camera.y <0)
							theColor = Color.BLACK.getRGB();
						else*/
							theColor = drawnMap[(int) (i - (Math.floor(map.size / 2) - camera.x))][(int) (j - (Math.floor(map.size / 2) - camera.y))];
					
					image.setRGB(i, j, theColor);
				}
			}
	}
	
	private final Set<Integer> pressed = new HashSet<Integer>();
	public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) {
    	pressed.add(e.getKeyCode());
        if (pressed.size() > 0) {
            if (pressed.contains(KeyEvent.VK_UP)) {
            	//System.out.println("UP");
            	camera.y -= 1;
            }
            if (pressed.contains(KeyEvent.VK_DOWN)) {
            	//System.out.println("DOWN");
            	camera.y += 1;
            }
            if (pressed.contains(KeyEvent.VK_RIGHT)) {
            	//System.out.println("RIGHT");
            	camera.x += 1;
            }
            if (pressed.contains(KeyEvent.VK_LEFT)) {
            	//System.out.println("LEFT");
            	camera.x -= 1;
            }
        }
    	
    	
        int key = e.getKeyCode();
 
        repaint();
    }
}
