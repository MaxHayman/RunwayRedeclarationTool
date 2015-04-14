import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
			//frame.display.saveFractal(format);
		}
	}
	
}
