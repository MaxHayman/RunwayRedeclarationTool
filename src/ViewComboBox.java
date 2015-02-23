import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class ViewComboBox extends JComboBox<String> implements ActionListener{
	
	private static final String[] viewStrings = { "2D Top-down", "2D Side-on", "3D" };
	
	public ViewComboBox() {
		super(viewStrings);
		this.addActionListener(this);
	}

}
