import javax.swing.*;


public class CalculationsFrame extends JFrame{
	
	JPanel pane = new JPanel();
	JTextArea text = new JTextArea();
	MainFrame mainFrame;

	public CalculationsFrame(MainFrame mainFrame) {
		super("Calculations");
		this.mainFrame = mainFrame;
		this.setContentPane(pane);
		
		text.setEditable(false);
		pane.add(text);
		this.updateText();
		EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "updateText");
		
		this.setSize(400, 400);
		this.setVisible(true);
	}
	
	private void updateText() {
		text.setText(mainFrame.runway.getCalculationsString());
	}
	
}
