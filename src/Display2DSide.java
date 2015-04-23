import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display2DSide extends Display {

	private int cameraAngle = 0;
	private float cameraZoom = 0.5f;
	public BufferedImage image = null;

	public Display2DSide(MainFrame mainFrame) {
		super(mainFrame);

		this.setVisible(true);
		this.addMouseWheelListener(this);
	}

	public void init() {

	}

	int startx = 100;
	
	int width = 1000;
	
	public void drawCacl(Graphics2D g2, Color color, int side, int depth, String type, int value, int displaced, int displacedopp) {
		int length = (value * width) / mainFrame.runway.TODA ;
		displaced = (displaced * width) / mainFrame.runway.TODA ;
		displacedopp = (displacedopp * width) / mainFrame.runway.TODA ;
		int start = side == 0 ? startx + displaced : startx + width - length + displacedopp;
		g2.setColor(color);
		g2.fillRect(start, (int)(getHeight()/2)+5, 3, depth);
		g2.fillRect(start, (int)(getHeight()/2)+5+depth, length, 3);
		g2.fillRect(start+length-3, (int)(getHeight()/2)+5, 3, depth);
		g2.drawString(type + ": " + value, start + (int)(length/2), (getHeight()/2)+5+depth-10);

	}
	
	public void drawLandingSlope(Graphics2D g2, Obstacle o, int start) {
		int size = ((mainFrame.runway.TODA - mainFrame.runway.obstacles.get(o) - mainFrame.runway.nLDA)* width) / mainFrame.runway.TODA ;

		AffineTransform orig = g2.getTransform();
		AffineTransform at = new AffineTransform();
		at.translate((startx+start), (int)((this.getHeight()/2)-o.height));
		at.rotate(Math.PI / 45/cameraZoom);
		
		g2.setTransform(at);
		
		g2.fillRect(0, 0, (int)(size), (int)(1));
		g2.setTransform(orig);
		
	}
	
	public void drawTakeoffSlope(Graphics2D g2, Obstacle o, int start) {
		int size = ((mainFrame.runway.obstacles.get(o) - mainFrame.runway.nTODA)* width) / mainFrame.runway.TODA ;

		AffineTransform orig = g2.getTransform();
		AffineTransform at = new AffineTransform();
		at.translate((startx+start), (int)((this.getHeight()/2)));
		at.rotate(-Math.PI / 35/cameraZoom);
		
		g2.setTransform(at);
		
		g2.fillRect(0, 0, (int)(size), (int)(1));
		g2.setTransform(orig);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		width = (int) (1000*cameraZoom);

		redrawImage();
		Graphics2D g2 = (Graphics2D) g ;

		//Grass
		g2.setColor(skyColor);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight()/2);
		g2.setColor(groundColor);
		g2.fillRect(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		
		//Tarmac
		g2.setColor(runwayColor);
		g2.fillRect((int)(startx), this.getHeight()/2, (int)(width), (int)(5));

		if(mainFrame.runway == null)
			return;
		
		g2.setColor(obstacleColor);
		Obstacle o = mainFrame.runway.pair.runways[0].getObstacle();
		if(o != null) {
			int start = (mainFrame.runway.obstacles.get(o) * width) / mainFrame.runway.TODA ;
			//float start = ((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.LDA);
			g2.fillRect((int)(startx+start), (int)((this.getHeight()/2)-o.height), (int)(5), (int)(o.height));
			if(mainFrame.runway.calcType == 1)
				drawLandingSlope(g2, o, start);
			else 
				drawTakeoffSlope(g2, o, (mainFrame.runway.nTODA * width) / mainFrame.runway.TODA);
		}
		
		int calc = mainFrame.runway.calcType;
		drawCacl(g2, ldaColor, calc, 100, "LDA", mainFrame.runway.nLDA, mainFrame.runway.displacedThreshold, 0);
		drawCacl(g2, asdaColor, calc, 75, "ASDA", mainFrame.runway.nASDA, 0, mainFrame.runway.nASDA-mainFrame.runway.nTORA);
		drawCacl(g2, todaColor, calc, 50, "TODA", mainFrame.runway.nTODA, 0, mainFrame.runway.nTODA-mainFrame.runway.nTORA);
		drawCacl(g2, toraColor, calc, 25, "TORA", mainFrame.runway.nTORA, 0, 0);
		

		g2.setColor(lineColor);

		Runway one = mainFrame.runway;
		
		Runway two = mainFrame.runway.pair.runways[0] == mainFrame.runway ? mainFrame.runway.pair.runways[1] : mainFrame.runway.pair.runways[0];
		g2.drawString(one.orientation, startx+30, this.getHeight()/2-50);
		g2.drawString(one.designation, startx+32, this.getHeight()/2-35);
	

		g2.drawString(two.orientation, startx+width-30, this.getHeight()/2-50);
		g2.drawString(two.designation, startx+width-26, this.getHeight()/2-35);
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
				//starty += 4/ cameraZoom;
				changed = true;
			}
			if (pressed.contains(KeyEvent.VK_DOWN)) {
				//starty -= 4/ cameraZoom;
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
		
		startx += notches > 0 ? 4 : -4;

		repaint();
	}

}
