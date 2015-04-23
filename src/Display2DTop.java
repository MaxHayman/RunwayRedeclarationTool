import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class Display2DTop extends Display {

	private int cameraAngle = 0;
	private float cameraZoom = 0.34f;
	public BufferedImage image = null;

	public Display2DTop(MainFrame mainFrame) {
		super(mainFrame);

	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/4-this.getSize().height/2);
	        
		this.setVisible(true);
		this.addMouseWheelListener(this);

		EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "repaint");

	}

	public void init() {

	}

	int startx = 150;
	int starty = 250;
	
	public void drawCacl(Graphics2D g2, Color color, int side, int depth, String type, int value, int displaced, int displacedopp) {
		int length = (int)(cameraZoom*((value * width) / mainFrame.runway.TODA));

		displaced = (int)(((cameraZoom*(displaced * width) / mainFrame.runway.TODA))) ;
		displacedopp = (int)(((cameraZoom*(displacedopp * width) / mainFrame.runway.TODA))) ;
		int scaleStart = (int)(cameraZoom*startx);
		int scaleWidth = (int)(cameraZoom*width);
		int start = (side == 0 ? (scaleStart + displaced) : (scaleStart + scaleWidth - length + displacedopp));
		int starto = (int)(cameraZoom *(starty));
		int scaleDepth = (int)(cameraZoom *(depth));
		g2.setColor(color);
		g2.fillRect(start, starto-scaleDepth, 3, scaleDepth);
		g2.fillRect(start, starto-scaleDepth, length, 3);
		g2.fillRect(start+length-3, starto-scaleDepth, 3, scaleDepth);
		g2.drawString(type + ": " + value, start + (int)(length/2), starto+5-scaleDepth-10);
	}
	//drawCacl(g2, Color.yellow, calc, 100, "LDA", mainFrame.runway.nLDA, mainFrame.runway.displacedThreshold);
	int width = 3000;
	int height = 350;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		


		redrawImage();
		Graphics2D g2 = (Graphics2D) g ;

		//Grass
		g2.setColor(Color.GREEN.darker());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		//Tarmac
		g2.setColor(Color.BLACK);
		g2.fillRect((int)(cameraZoom*startx), (int)(cameraZoom*starty), (int)(cameraZoom*width), (int)(cameraZoom*height));

		if(mainFrame.runway == null)
			return;
		
		Obstacle o = mainFrame.runway.getObstacle();
		float displacedThresholdRight = ((float)mainFrame.runway.displacedThreshold * width / (float)mainFrame.runway.TORA);
		int calc = mainFrame.runway.calcType;
		
		drawCacl(g2, Color.yellow, calc, 400, "LDA", mainFrame.runway.nLDA, mainFrame.runway.displacedThreshold, 0);
		drawCacl(g2, Color.blue, calc, 300, "ASDA", mainFrame.runway.nASDA, 0, mainFrame.runway.nASDA-mainFrame.runway.nTORA);
		drawCacl(g2, Color.red, calc, 200, "TODA", mainFrame.runway.nTODA, 0, mainFrame.runway.nTODA-mainFrame.runway.nTORA);
		drawCacl(g2, Color.orange, calc, 100, "TORA", mainFrame.runway.nTORA, 0, 0);

		g2.setColor(Color.red);

		if(o != null) {
			float start = ((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.TODA);
			g2.fillRect((int)(cameraZoom*(startx+(width*start))), (int)(cameraZoom*(starty+height/2-20)), (int)(cameraZoom*(40)), (int)(cameraZoom*(40)));
		}

		g2.setColor(Color.white);
		for(int i = 1; i < height-10; i++) {
			if(i % 15 == 0) {
				g2.fillRect((int)(cameraZoom*(startx+15+displacedThresholdRight)), (int)(cameraZoom*(starty+i)), (int)(cameraZoom*150), (int)(cameraZoom*4));
				g2.fillRect((int)(cameraZoom*(startx+width-150-15)), (int)(cameraZoom*(starty+i)), (int)(cameraZoom*150), (int)(cameraZoom*4));
			}
		}

		for(int i = (int)(displacedThresholdRight+250); i < width-250; i+=150) {
			g2.fillRect((int)(cameraZoom*(startx+i)), (int)(cameraZoom*(starty+height/2-2)), (int)(cameraZoom*100), (int)(cameraZoom*4));

		}
		AffineTransform orig = g2.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(Math.PI / 2);
		g2.setTransform(at);
		g2.setColor(Color.WHITE);

		Runway one = mainFrame.runway;
		Runway two = mainFrame.runway.pair.runways[0] == mainFrame.runway ? mainFrame.runway.pair.runways[1] : mainFrame.runway.pair.runways[0];
		
		g2.drawString(one.orientation, (cameraZoom*(starty+height/2-10)), -(cameraZoom*(startx+200)));
		g2.drawString(one.designation, (cameraZoom*(starty+height/2-5)), -(cameraZoom*(startx+170)));
		AffineTransform at2 = new AffineTransform();
		at2.rotate(- Math.PI / 2);
		g2.setTransform(at2);
		g2.drawString(two.orientation, -(cameraZoom*(starty+height/2+10)), +(cameraZoom*(startx+width-220)));
		g2.drawString(two.designation, -(cameraZoom*(starty+height/2+5)), +(cameraZoom*(startx+width-190)));
		g2.setTransform(orig);
		//g2.drawImage(image, 0,0, null);
	}

	public void redrawImage() {
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
				starty += 4/ cameraZoom;
				changed = true;
			}
			if (pressed.contains(KeyEvent.VK_DOWN)) {
				starty -= 4/ cameraZoom;
				changed = true;
			}
			if (pressed.contains(KeyEvent.VK_RIGHT)) {
				startx -= 4/ cameraZoom;
				changed = true;
			}
			if (pressed.contains(KeyEvent.VK_LEFT)) {
				startx += 4/ cameraZoom;
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
		cameraZoom += (notches > 0 ? -0.01 : 0.01);

		if(cameraZoom < 0.25000024f)
			cameraZoom = 0.25000024f;

		if(cameraZoom > 3)
			cameraZoom = 3;

		repaint();
	}

}
