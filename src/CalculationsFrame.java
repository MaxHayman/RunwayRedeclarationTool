
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.text.DefaultCaret;


/**
 *
 * @author Max
 */
public class CalculationsFrame extends javax.swing.JFrame {

    MainFrame mainFrame;
    
	public void updateText() {
		if(mainFrame.runway != null)
			logTextArea.setText(mainFrame.runway.getCalculationsString());
		else
			logTextArea.setText("No runway selected.");
	}
	
    public CalculationsFrame(MainFrame mainFrame) {
    	super("Calculations Breakdown");
        initComponents();
        
        this.mainFrame = mainFrame;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height*7/8-this.getSize().height/2-50);
		
	    this.updateText();
	    
        logTextArea.setEditable(false);
        
        EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "updateText");
        EventManager.getEventManager().addEventNotify(EventManager.EventName.EXIT, this, "dispose");
    }
    
    public void addLog(String line) {
    	logTextArea.append(line + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        logScrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logScrollPane.setViewportView(logTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JTextArea logTextArea;
    // End of variables declaration                   
}
