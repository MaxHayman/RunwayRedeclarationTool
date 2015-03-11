import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DisplayMenuBar extends JMenuBar {

	/*
	 * panel - is the FractalPanel the Menu is controlling.
	 */
	private Frame2D frame;
	
	/*
	 * Constructor for FractalMenuBar which takes
	 * a FractalPanel which it will be controlling
	 * in its parameters.
	 */
	public DisplayMenuBar(Frame2D frame)
	{
		this.frame = frame;
		
		/*
		 * Variables used in the creation of the menu bar.
		 */
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("Options");
		this.add(menu);
		
		JMenu saveMenuItem = new JMenu("Save as...");
		menu.add(saveMenuItem);
		
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
	
	/*
	 * ColourListener will listen for when a user clicks one
	 * of the save file formats.
	 */
	public class SaveListener implements ActionListener
	{
		/*
		 * format - stores the file format a particular button
		 *          represents. 
		 */
		private String format;
		
		/*
		 * Constructor for SaveListener which takes the file format
		 * in its parameters.
		 */
		public SaveListener(String format)
		{
			this.format = format;
		}

		/*
		 * Inherited from ActionListener. It will trigger when a user
		 * clicks a file format to save and will tell the fractal to save
		 * itself to the particular file format selected.
		 */
		public void actionPerformed(ActionEvent e) {
			frame.display.saveFractal(format);
		}
	}
	
}
