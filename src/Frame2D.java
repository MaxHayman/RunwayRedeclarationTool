import javax.swing.JFrame;


public class Frame2D extends JFrame {

	public Frame2D () {
		Display2D d = new Display2D();
		this.setContentPane(d);
		this.addKeyListener(d);
		this.show();
		this.setSize(720, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
