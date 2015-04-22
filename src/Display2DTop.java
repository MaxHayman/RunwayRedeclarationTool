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
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int width = 3000;
		int height = 350;


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
		
		//LDA
		Obstacle o = mainFrame.runway.getObstacle();
		
		float displacedThresholdRight = ((float)mainFrame.runway.TORA - (float)mainFrame.runway.LDA) *width / (float)mainFrame.runway.TORA;
		float ldaPercRight = (float)mainFrame.runway.nLDA / (float)mainFrame.runway.TODA;
		float ldaStartRight = 0;
		if(mainFrame.runway.calcType == 0) {
			System.out.println("lol");
			ldaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? displacedThresholdRight : 0;
		} else {
			System.out.println("lol1");
			ldaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? displacedThresholdRight : (width*((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.LDA));

		}
		g2.setColor(Color.yellow);
		g2.fillRect((int)(cameraZoom*(ldaStartRight+startx+10)), (int)(cameraZoom*(starty-200)), (int)(cameraZoom*(width-2)*ldaPercRight), (int)(cameraZoom*5));
		g2.fillRect((int)(cameraZoom*(ldaStartRight+startx+10)), (int)(cameraZoom*(starty-200)), (int)(cameraZoom*5), (int)(cameraZoom*200));
		g2.fillRect((int)(cameraZoom*(ldaStartRight+startx+(width-10)*ldaPercRight)), (int)(cameraZoom*(starty-200)), (int)(cameraZoom*5), (int)(cameraZoom*200));
		g2.drawString("LDA: " + mainFrame.runway.nLDA, (int)(cameraZoom*(ldaStartRight+startx+(width/2)*ldaPercRight)), (cameraZoom*(starty-210)));

		//ASDA
		float asdaPercRight = (float)mainFrame.runway.nASDA / (float)mainFrame.runway.ASDA;
		float asdaStartRight = 0;
		if(mainFrame.runway.calcType == 0) {
			asdaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : 0;
		} else {
			asdaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : (width*((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.ASDA));

		}
		g2.setColor(Color.blue);
		g2.fillRect((int)(cameraZoom*(asdaStartRight+startx+10)), (int)(cameraZoom*(starty-150)), (int)(cameraZoom*(width-20)*asdaPercRight), (int)(cameraZoom*5));
		g2.fillRect((int)(cameraZoom*(asdaStartRight+startx+10)), (int)(cameraZoom*(starty-150)), (int)(cameraZoom*5), (int)(cameraZoom*150));
		g2.fillRect((int)(cameraZoom*(asdaStartRight+startx+(width-10)*asdaPercRight)), (int)(cameraZoom*(starty-150)), (int)(cameraZoom*5), (int)(cameraZoom*150));
		g2.drawString("ASDA: " + mainFrame.runway.nASDA, (int)(cameraZoom*(asdaStartRight+startx+(width/2)*asdaPercRight)), (cameraZoom*(starty-160)));

		//TODA
		float todaPercRight = (float)mainFrame.runway.nTODA / (float)mainFrame.runway.TODA;
		float todaStartRight = 0;
		if(mainFrame.runway.calcType == 0) {
			todaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : 0;
		} else {
			todaStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : (width*((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.TODA));
		}
		g2.setColor(Color.red);
		g2.fillRect((int)(cameraZoom*(todaStartRight+startx+10)), (int)(cameraZoom*(starty-100)), (int)(cameraZoom*(width-20)*todaPercRight), (int)(cameraZoom*5));
		g2.fillRect((int)(cameraZoom*(todaStartRight+startx+10)), (int)(cameraZoom*(starty-100)), (int)(cameraZoom*5), (int)(cameraZoom*100));
		g2.fillRect((int)(cameraZoom*(todaStartRight+startx+(width-10)*todaPercRight)), (int)(cameraZoom*(starty-100)), (int)(cameraZoom*5), (int)(cameraZoom*100));
		g2.drawString("TODA: " + mainFrame.runway.nTODA, (int)(cameraZoom*(todaStartRight+startx+(width/2)*todaPercRight)), (cameraZoom*(starty-110)));

		//TORA
		float toraPercRight = (float)mainFrame.runway.nTORA / (float)mainFrame.runway.TORA;
		float toraStartRight = 0;
		if(mainFrame.runway.calcType == 0) {
			toraStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : 0;
		} else {
			toraStartRight = (o == null || mainFrame.runway.obstacles.get(o) == null) ? 0 : (width*((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.TORA));
		}
		g2.setColor(Color.orange);
		g2.fillRect((int)(cameraZoom*(toraStartRight+startx+10)), (int)(cameraZoom*(starty-50)), (int)(cameraZoom*(width-20)*toraPercRight), (int)(cameraZoom*5));
		g2.fillRect((int)(cameraZoom*(toraStartRight+startx+10)), (int)(cameraZoom*(starty-50)), (int)(cameraZoom*5), (int)(cameraZoom*50));
		g2.fillRect((int)(cameraZoom*(toraStartRight+startx+(width-10)*toraPercRight)), (int)(cameraZoom*(starty-50)), (int)(cameraZoom*5), (int)(cameraZoom*50));
		g2.drawString("TORA: " + mainFrame.runway.nTORA, (int)(cameraZoom*(toraStartRight+startx+(width/2)*toraPercRight)), (cameraZoom*(starty-60)));

		g2.setColor(Color.red);
		//Obstacle o = mainFrame.runway.pair.runways[1].getObstacle();
		if(o != null) {
			float start = ((float)mainFrame.runway.obstacles.get(o) / (float)mainFrame.runway.LDA);
			g2.fillRect((int)(cameraZoom*(startx+(width*start-25))), (int)(cameraZoom*(starty+height/2-10)), (int)(cameraZoom*(20)), (int)(cameraZoom*(20)));
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
