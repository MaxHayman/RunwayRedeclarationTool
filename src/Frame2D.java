import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame2D extends JFrame implements ComponentListener {
	
	Display display;
	
	public Frame2D (MainFrame mainFrame, Display.View view) {
		super("Runway Redeclaration Tool");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		if(view == Display.View.TOP_VIEW) {
			display = new Display2DTop(mainFrame);
		    this.setLocation(dim.width/2-this.getSize().width/2, 0);
		}
		else if (view == Display.View.SIDE_VIEW) {
			display = new Display2DSide(mainFrame);
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		}
			

		//view:
		this.setContentPane(display);
		this.addKeyListener(display);
		this.setVisible(true);
		this.setSize(720, 450);
		this.addComponentListener(this);
		
		EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "updateDisplay");
		EventManager.getEventManager().addEventNotify(EventManager.EventName.EXIT, this, "dispose");

		this.setJMenuBar(new DisplayMenuBar(this));
	}
	
	public void updateDisplay() {
		display.repaint();
	}

	public void componentResized(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) {	}
	
	public class DisplayMenuBar extends JMenuBar {

		private Frame2D frame;
		
		public DisplayMenuBar(Frame2D frame)
		{
			this.frame = frame;

			JMenu menu;
			JMenuItem menuItem;

			menu = new JMenu("Options");
			this.add(menu);
			
			JMenu saveMenuItem = new JMenu("Save as...");
			menu.add(saveMenuItem);
			
			//JMenu colorMenuItem = new JMenu("Change Colours");
			//colorMenuItem.addActionListener
			//menu.add(colorMenuItem);
			
			menuItem = new JMenuItem("JPEG");
			menuItem.addActionListener(new SaveListener("JPEG"));
			saveMenuItem.add(menuItem);
			
			menuItem = new JMenuItem("PNG");
			menuItem.addActionListener(new SaveListener("PNG"));
			saveMenuItem.add(menuItem);
			
			menuItem = new JMenuItem("BMP");
			menuItem.addActionListener(new SaveListener("BMP"));
			saveMenuItem.add(menuItem);
			
			menuItem = new JMenuItem("GIF");
			menuItem.addActionListener(new SaveListener("GIF"));
			saveMenuItem.add(menuItem);
		}

		public class SaveListener implements ActionListener
		{
			private String format;
			
			public SaveListener(String format)
			{
				this.format = format;
			}

			public void actionPerformed(ActionEvent e) {

				File f = null;
				{
					File workingDirectory = new File(System.getProperty("user.dir") + "/saves");
			
					if (!workingDirectory.exists()) {
						workingDirectory.mkdir();
					}
					
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(workingDirectory);
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Image File", "png", "jpg", "bmp", "gif");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showSaveDialog(null);

				    if(returnVal != JFileChooser.APPROVE_OPTION) {
				    	return;
				    }
				    
				    String name = chooser.getSelectedFile().getAbsolutePath();
				    if(!name.endsWith("." + format))
				    	name += "." + format;
				    
				    f = new File(name);
				}
				
				BufferedImage bImg = new BufferedImage(frame.display.getWidth(), frame.display.getHeight(), BufferedImage.TYPE_INT_RGB);
			    Graphics2D cg = bImg.createGraphics();
			    frame.display.paintAll(cg);
			    try {
			    	ImageIO.write(bImg, format, f);

			    } catch (IOException ex) {
			            // TODO Auto-generated catch block
			            ex.printStackTrace();
			    }
			}
		}
		
	}
}
