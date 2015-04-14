import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LoginFrame extends JFrame implements KeyListener {

	public static void main(String[] args) {
		new LoginFrame().setVisible(true);
	}

	FixedSizedQueue<Character> lastPressed = new FixedSizedQueue<Character>();
	JPanel panel;
	int loginState = 0;

	public LoginFrame() {
		addKeyListener(this);
		lastPressed.limit = 11;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(250, 340);
		this.setContentPane(panel = new ImagePanel("Login.png") {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if(loginState == 0) {
					g.setColor(Color.GREEN);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
					g.drawString("• Waiting for card...", 20, 290);
				} else if (loginState == 1) {
					g.setColor(Color.ORANGE);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
					g.drawString("• Checking...", 20, 290);
				} else if(loginState == 2) {
					g.setColor(Color.RED);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
					g.drawString("• Error invalid card.", 20, 290);
				}
			}

		});
	}

	public void keyPressed(KeyEvent e) {
		lastPressed.add(e.getKeyChar());
		if (e.getKeyCode() == 10)
		{
			String chars = "";
			boolean id = true;
			int count = 0;
			for (Character c : lastPressed)
			{
				if (count < 10 && (c < 48 || c > 57))
					id = false;
				else if (count == 10 && c != 10)
					id = false;

				if(c != 10)
					chars += c;

				count++;
			}
			
			if (id) {
				loginState = 1;
				panel.repaint();
				
				//new MainFrame();
				this.setVisible(false);
			}
		}
	}

	public void keyReleased(KeyEvent e) { }

	public void keyTyped(KeyEvent e) { }

}
