import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	Container pane;
	RunwayComboBox runwayComboBox;
	
	public Frame2D (World map, Controller controller) {
		super("Runway Redeclaration Tool");
		
		int width = 720;
		int height = 450;
		
		//sort out the container and its layout:
		pane = new Container();
		this.setContentPane(pane);
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		runwayComboBox = new RunwayComboBox(controller);
		
		//first line:
		c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0;
		c.weightx = 1;
		pane.add(new ViewComboBox(), c);
		c.gridx = 1;
		pane.add(runwayComboBox, c);
		this.updateRunways();
		c.gridx = 2;
		pane.add(new JButton("Add Runway"), c);
		c.gridx = 3;
		pane.add(new JButton("Add Obstacle"), c);
		
		//view:
		c.gridx = 0; c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty = 1;
		display = new Display2D(map);
		
		pane.add(display, c);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
	}
	
	public void updateRunways() {
		runwayComboBox.update();
	}
	
	public void updateDisplay() {
		System.out.println("updating display");
		display.redrawImage();
	}

	public void componentResized(ComponentEvent e) {
		
		//Dimension d = display.getSize();
		//display.setDisplaySize((int)d.getWidth(), (int)d.getHeight());
		System.out.println(display.getWidth() + " " + display.getHeight());
		display.redrawImage();
	}

	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
}
