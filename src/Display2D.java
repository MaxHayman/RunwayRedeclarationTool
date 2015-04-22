import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Display2D extends Display {

	private Vector camera;//= new Vector(300, 2500, 1000);
	private int cameraAngle = 0;
	private int cameraZoom = 100;
	public World.View view = World.View.TOP_VIEW;

	public Display2D(World map, World.View view) {
		super(map);
		this.addMouseWheelListener(this);

		this.view = view;

		if(view == World.View.TOP_VIEW)
			camera = new Vector(300, 300, 1000);
		else if (view == World.View.SIDE_VIEW)
			camera = new Vector(300, map.sizex/2, 1000);
	}

	public void init() {

	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		redrawImage();
		Graphics2D g2 = (Graphics2D) g ;
		g2.drawImage(image, 0,0, null);

		if(world.controller.getCurrentRunway() != null) {
			g2.setColor(Color.red);
			g2.drawString("TODA: " + world.controller.getCurrentRunway().nTODA, 10, this.getHeight()-80);
			g2.drawString("TORA: " + world.controller.getCurrentRunway().nTORA, 10, this.getHeight()-60);
			g2.drawString("ASDA: " + world.controller.getCurrentRunway().nASDA, 10, this.getHeight()-40);
			g2.drawString("LDA: "  + world.controller.getCurrentRunway().nLDA, 10, this.getHeight()-20);
		}
	}

	public void redrawImage() {

		if(view == World.View.TOP_VIEW)
		{
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

					if(newi < 0 || (newj) < 0 || (newi) >= this.getWorld().sizex || (newj) >= this.getWorld().sizey)
						theColor = Color.green.darker().getRGB();
					else
						theColor = this.getWorld().mapTop[newi][newj];

					image.setRGB(i, j, theColor);
				}
			}
		}
		else if (view == World.View.SIDE_VIEW)
		{
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

					if(newi < 0 || (newj) < 0 || (newi) >= this.getWorld().sizex || (newj) >= this.getWorld().sizey)
						theColor = Color.white.getRGB();
					else
						theColor = this.getWorld().mapSide[newi][newj];

					image.setRGB(i, j, theColor);
				}
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
