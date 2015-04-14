import javax.swing.event.ChangeEvent;


/**
 *
 * @author Max
 */
public class AddObstacleFrame extends javax.swing.JFrame {

	MainFrame mainFrame;
	Obstacle o = null;
	
    public AddObstacleFrame(MainFrame mainFrame, Obstacle o) {
    	super(o == null ? "Add Obstacle" : "Edit Obstacle");

        initComponents();
        this.mainFrame = mainFrame;
        
        distanceFromThresholdLabel.setText("Distance from " + mainFrame.runway.pair.runways[0].toString() + " threshold");
        
        distanceFromOtherThresholdLabel.setText("Distance from " + mainFrame.runway.pair.runways[1].toString() + " threshold");

        if(o != null) {
        	this.o = o;
        	obstacleNameTextField.setText(o.name);
        	distanceFromThresholdSpinner.setValue(mainFrame.runway.pair.runways[0].obsticles.get(o));
        	distanceFromOtherThresholdSpinner.setValue(mainFrame.runway.pair.runways[1].obsticles.get(o));
        	obstacleHeightTextField.setText("" + o.height);
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

        obstacleNameLabel = new javax.swing.JLabel();
        obstacleNameTextField = new javax.swing.JTextField();
        obstacleHeightLabel = new javax.swing.JLabel();
        obstacleHeightTextField = new javax.swing.JTextField();
        distanceFromThresholdSpinner = new javax.swing.JSpinner();
        distanceFromThresholdLabel = new javax.swing.JLabel();
        distanceFromOtherThresholdSpinner = new javax.swing.JSpinner();
        distanceFromOtherThresholdLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        obstacleTemplateComboBox = new javax.swing.JComboBox();
        obstacleTempaltesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        obstacleNameLabel.setText(" Name");

        obstacleHeightLabel.setText("Height");

        distanceFromThresholdSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				distanceFromThresholdSpinnerStateChanged(e);
			}
        });

        distanceFromThresholdLabel.setText("Distance from 09L threshold");

        distanceFromOtherThresholdSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				distanceFromOtherThresholdSpinnerStateChanged(e);
			}
        });

        distanceFromOtherThresholdLabel.setText("Distance from 27R threshold");

        saveButton.setText("Add");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        obstacleTemplateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        obstacleTempaltesLabel.setText("Obstacle Templates");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(obstacleTempaltesLabel)
                    .addComponent(distanceFromOtherThresholdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(saveButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(obstacleTemplateComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(obstacleNameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(obstacleNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(obstacleHeightLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(obstacleHeightTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(distanceFromThresholdLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(distanceFromThresholdSpinner, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(distanceFromOtherThresholdSpinner))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(obstacleTempaltesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obstacleTemplateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(obstacleNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obstacleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(obstacleHeightLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obstacleHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(distanceFromThresholdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanceFromThresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(distanceFromOtherThresholdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanceFromOtherThresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>                        

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {       
    	
    	if(o == null)
    		o = new Obstacle();
    	
    	o.height = Integer.parseInt(obstacleHeightTextField.getText());
    	o.name = obstacleNameTextField.getText();
    	mainFrame.runway.pair.runways[0].obsticles.put(o, (Integer) distanceFromThresholdSpinner.getValue());
    	mainFrame.runway.pair.runways[1].obsticles.put(o, (Integer) distanceFromOtherThresholdSpinner.getValue());
    	mainFrame.updateObstacles();
        this.dispose();
    }                                         

    private void distanceFromThresholdSpinnerStateChanged (ChangeEvent evt) {                                                      
        if((int)distanceFromOtherThresholdSpinner.getValue() == 0) {
        	int dis = Math.min(mainFrame.runway.pair.runways[0].LDA, mainFrame.runway.pair.runways[1].LDA);
        	distanceFromOtherThresholdSpinner.setValue(dis - (int)distanceFromThresholdSpinner.getValue() + 1);
        }
    }                                                     

    private void distanceFromOtherThresholdSpinnerStateChanged(ChangeEvent evt) {                                                           
        if((int)distanceFromThresholdSpinner.getValue() == 0) {
        	int dis = Math.min(mainFrame.runway.pair.runways[0].LDA, mainFrame.runway.pair.runways[1].LDA);
        	distanceFromThresholdSpinner.setValue(dis - (int)distanceFromOtherThresholdSpinner.getValue() + 1);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel distanceFromOtherThresholdLabel;
    private javax.swing.JSpinner distanceFromOtherThresholdSpinner;
    private javax.swing.JLabel distanceFromThresholdLabel;
    private javax.swing.JSpinner distanceFromThresholdSpinner;
    private javax.swing.JLabel obstacleHeightLabel;
    private javax.swing.JTextField obstacleHeightTextField;
    private javax.swing.JLabel obstacleNameLabel;
    private javax.swing.JTextField obstacleNameTextField;
    private javax.swing.JLabel obstacleTempaltesLabel;
    private javax.swing.JComboBox obstacleTemplateComboBox;
    // End of variables declaration                   
}
