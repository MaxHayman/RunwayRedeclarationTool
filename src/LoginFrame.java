import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Max
 */
public class LoginFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
    	super("Runway Redeclration Tool");
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height);
        
        this.setResizable(false);
        
        File workingDirectory = new File(System.getProperty("user.dir") + "/airports");

		if (!workingDirectory.exists()) {
			workingDirectory.mkdir();
		}
		
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".xml")) {
					return true;
				} else {
					return false;
				}
			}
		};
        
		
		DefaultListModel<File> model = new DefaultListModel<File>();
        for(File f : workingDirectory.listFiles(filter)) {
        	model.addElement(f);
        }
        airportList.setModel(model);
        airportList.setCellRenderer(new FileListCellRenderer());
    }
    
    public class FileListCellRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            String[] names = label.getText().split("\\\\");
            String name = names[names.length-1].replace(".xml", "");
            
           label.setText(name.substring(0, 1).toUpperCase() + name.substring(1));

           return label;

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	bannerPanel = new ImagePanel("header.png");
        airportListScrollPane = new javax.swing.JScrollPane();
        airportList = new javax.swing.JList<File>();
        loadButton = new javax.swing.JButton();
        loadOtherButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        airportsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bannerPanel.setBackground(new java.awt.Color(255, 255, 255));
        bannerPanel.setPreferredSize(new java.awt.Dimension(480, 120));

        javax.swing.GroupLayout bannerPanelLayout = new javax.swing.GroupLayout(bannerPanel);
        bannerPanel.setLayout(bannerPanelLayout);
        bannerPanelLayout.setHorizontalGroup(
            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        bannerPanelLayout.setVerticalGroup(
            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        airportList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        airportListScrollPane.setViewportView(airportList);

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        loadOtherButton.setText("Load Other...");
        loadOtherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadOtherButtonActionPerformed(evt);
            }
        });

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        airportsLabel.setText("Airports");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(airportsLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bannerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(airportListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(69, 69, 69)
                                            .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(loadOtherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bannerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(airportsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(airportListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(newButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadOtherButton)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	File f = airportList.getSelectedValue();
    	
    	if(f == null) {
    		JOptionPane.showMessageDialog(null, "Please select a saved file.");
    	} else {
    		Airport airport = XMLHandler.loadXML(f);
    		if(airport == null)
    			return;
    		MainFrame mainFrame = new MainFrame(airport);
    		mainFrame.setVisible(true);
    		new Frame2D(mainFrame, Display2DTop.View.TOP_VIEW);
        	new Frame2D(mainFrame, Display2DTop.View.SIDE_VIEW);
    		this.dispose();
    	}
    }                                          

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        new MainFrame(new Airport()).setVisible(true);
        this.dispose();
    }                                         

    private void loadOtherButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
		Airport airport = XMLHandler.loadXML(null);
		if(airport == null)
			return;
		MainFrame mainFrame = new MainFrame(airport);
    	mainFrame.setVisible(true);
    	new Frame2D(mainFrame, Display2DTop.View.TOP_VIEW);
    	new Frame2D(mainFrame, Display2DTop.View.SIDE_VIEW);
    	this.dispose();
    }                                               

    // Variables declaration - do not modify                     
    private javax.swing.JList<File> airportList;
    private javax.swing.JScrollPane airportListScrollPane;
    private javax.swing.JLabel airportsLabel;
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton loadOtherButton;
    private javax.swing.JButton newButton;
    // End of variables declaration                   
}
