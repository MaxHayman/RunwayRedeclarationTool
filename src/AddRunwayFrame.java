import javax.swing.JOptionPane;


/**
 *
 * @author Max
 */
public class AddRunwayFrame extends javax.swing.JFrame {

	RunwayPair pair;
	MainFrame mainFrame;

	public AddRunwayFrame(MainFrame mainFrame, RunwayPair pair) {
		super(pair == null ? "Add Runway" : "Edit Runway");

		initComponents();
		this.setResizable(false);
		this.pair = pair;
		this.mainFrame = mainFrame;
		if(pair != null) {
			LtoraTextField.setText("" + pair.runways[0].TORA);
			LtodaTextField.setText("" + pair.runways[0].TODA);
			LldaTextField.setText("" + pair.runways[0].LDA);
			LasdaTextField.setText("" + pair.runways[0].ASDA);
			LdisplacedThresholdTextField.setText("" + pair.runways[0].displacedThreshold);
			LdesignationTextField.setText("" + pair.runways[0].designation);
			LorientationTextField.setText("" + pair.runways[0].orientation);

			RtoraTextField.setText("" + pair.runways[1].TORA);
			RtodaTextField.setText("" + pair.runways[1].TODA);
			RldaTextField.setText("" + pair.runways[1].LDA);
			RasdaTextField.setText("" + pair.runways[1].ASDA);
			RdisplacedThresholdTextField.setText("" + pair.runways[1].displacedThreshold);
			RdesignationTextField.setText("" + pair.runways[1].designation);
			RorientationTextField.setText("" + pair.runways[1].orientation);
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

		LtodaTextField = new javax.swing.JTextField();
		LtoraTextField = new javax.swing.JTextField();
		LldaTextField = new javax.swing.JTextField();
		LasdaTextField = new javax.swing.JTextField();
		RtodaTextField = new javax.swing.JTextField();
		RtoraTextField = new javax.swing.JTextField();
		RldaTextField = new javax.swing.JTextField();
		RasdaTextField = new javax.swing.JTextField();
		LtodaLabel = new javax.swing.JLabel();
		LtoraLabel = new javax.swing.JLabel();
		LasdaLabel = new javax.swing.JLabel();
		LldaLabel = new javax.swing.JLabel();
		RtodaLabel = new javax.swing.JLabel();
		RtoraLabel = new javax.swing.JLabel();
		RasdaLabel = new javax.swing.JLabel();
		RldaLabel = new javax.swing.JLabel();
		LdisplacedThresholdLabel = new javax.swing.JLabel();
		LdisplacedThresholdTextField = new javax.swing.JTextField();
		RdisplacedThresholdLabel = new javax.swing.JLabel();
		RdisplacedThresholdTextField = new javax.swing.JTextField();
		saveButton = new javax.swing.JButton();
		LdesignationLabel = new javax.swing.JLabel();
		LdesignationTextField = new javax.swing.JTextField();
		RdesignationLabel = new javax.swing.JLabel();
		RdesignationTextField = new javax.swing.JTextField();
		LorientationLabel = new javax.swing.JLabel();
		LorientationTextField = new javax.swing.JTextField();
		RorientationTextField = new javax.swing.JTextField();
		RorientationLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		LtodaLabel.setText("TODA (m)");

		LtoraLabel.setText("TORA (m)");

		LasdaLabel.setText("ASDA (m)");

		LldaLabel.setText("LDA (m)");

		RtodaLabel.setText("TODA (m)");

		RtoraLabel.setText("TORA (m)");

		RasdaLabel.setText("ASDA (m)");

		RldaLabel.setText("LDA (m)");

		LdisplacedThresholdLabel.setText("Displaced Threshold (m)");

		RdisplacedThresholdLabel.setText("Displaced Threshold (m)");

		saveButton.setText("Save");
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		LdesignationLabel.setText("Designation");

		RdesignationLabel.setText("Designation");

		LorientationLabel.setText("Orientation");

		RorientationLabel.setText("Orientation");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(24, 24, 24)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(LorientationLabel)
														.addGap(59, 59, 59)
														.addComponent(LorientationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(LdesignationLabel)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(LdesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(layout.createSequentialGroup()
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(LtoraLabel)
																						.addComponent(LtodaLabel)
																						.addComponent(LasdaLabel)
																						.addComponent(LdisplacedThresholdLabel)
																						.addComponent(LldaLabel))
																						.addGap(18, 18, 18)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(LdisplacedThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(LldaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(LasdaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(LtodaTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(LtoraTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																																.addComponent(RtoraLabel)
																																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																		.addComponent(RasdaLabel)
																																		.addComponent(RldaLabel))
																																		.addComponent(RtodaLabel))
																																		.addGap(68, 68, 68))
																																		.addComponent(RdisplacedThresholdLabel, javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(RdesignationLabel))
																																		.addComponent(RorientationLabel))
																																		.addGap(18, 18, 18)
																																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																								.addComponent(RldaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(RtodaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(RasdaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(RtoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
																																								.addComponent(RdisplacedThresholdTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
																																								.addComponent(RdesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(RorientationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
																																								.addGroup(layout.createSequentialGroup()
																																										.addGap(185, 185, 185)
																																										.addComponent(saveButton)
																																										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
																																										.addGap(27, 27, 27))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(39, 39, 39)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LorientationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(LorientationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(RorientationLabel))
										.addComponent(RorientationTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(LdesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(LdesignationLabel)
																.addComponent(RdesignationLabel))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																				.addComponent(LtodaLabel)
																				.addGap(18, 18, 18)
																				.addComponent(LtoraLabel)
																				.addGap(18, 18, 18)
																				.addComponent(LasdaLabel))
																				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																								.addComponent(LtodaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(RtodaLabel))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(LtoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(RtoraLabel))
																										.addGap(11, 11, 11)
																										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																												.addComponent(LasdaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(RasdaLabel))
																												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(LldaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(RldaLabel)
																														.addComponent(LldaLabel))
																														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(LdisplacedThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(LdisplacedThresholdLabel)
																																.addComponent(RdisplacedThresholdLabel)))))
																																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																																		.addComponent(RdesignationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(RtodaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(RtoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(RasdaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(RldaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(RdisplacedThresholdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
																																		.addGap(18, 18, 18)
																																		.addComponent(saveButton)
																																		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		pack();
	}// </editor-fold>                        

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           

		String Lorientation = LorientationTextField.getText();
		if(Lorientation.length() > 2) {
			JOptionPane.showMessageDialog(null, "Invalid value for Orientation of the left runway.");
    		return;
		}
		
		while(Lorientation.length() < 2) {
			Lorientation = "0" + Lorientation;
		}
		String Ldesignation = LdesignationTextField.getText();
		if(Ldesignation.length() != 1) {
			JOptionPane.showMessageDialog(null, "Invalid value for Designation of the left runway.");
    		return;
		}
		int Ltora = 0;
		try {
			Ltora = Integer.parseInt(LtoraTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for TORA of the left runway.");
    		return;
    	}
		int Ltoda = 0;
		try {
			Ltoda = Integer.parseInt(LtodaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for TODA of the left runway.");
    		return;
    	}
		int Lasda = 0;
		try {
			Lasda = Integer.parseInt(LasdaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for ASDA of the left runway.");
    		return;
    	}
		int Llda = 0;
		try {
			Llda = Integer.parseInt(LldaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for LDA of the left runway.");
    		return;
    	}

		int Ldisplaced = 0;
		try {
			Ldisplaced = Integer.parseInt(LdisplacedThresholdTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for displaced threshold of the left runway.");
    		return;
    	}
	

		String Rorientation = RorientationTextField.getText();
		if(Rorientation.length() > 2) {
			JOptionPane.showMessageDialog(null, "Invalid value for Orientation of the right runway.");
    		return;
		}
		while(Rorientation.length() < 2) {
			Rorientation = "0" + Rorientation;
		}
		String Rdesignation = RdesignationTextField.getText();
		if(Rdesignation.length() != 1) {
			JOptionPane.showMessageDialog(null, "Invalid value for Designation of the right runway.");
    		return;
		}
		int Rtora = 0;
		try {
			Rtora = Integer.parseInt(RtoraTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for TORA of the right runway.");
    		return;
    	}
		int Rtoda = 0;
		try {
			Rtoda = Integer.parseInt(RtodaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for TODA of the right runway.");
    		return;
    	}
		int Rasda = 0;
		try {
			Rasda = Integer.parseInt(RasdaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for ASDA of the right runway.");
    		return;
    	}
		int Rlda = 0;
		try {
			Rlda = Integer.parseInt(RldaTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for LDA of the right runway.");
    		return;
    	}

		int Rdisplaced = 0;
		try {
			Rdisplaced = Integer.parseInt(RdisplacedThresholdTextField.getText());
		} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Invalid value for displaced threshold of the right runway.");
    		return;
    	}

		String logLine = "Runways ";
		
		if(pair == null) {
			pair = new RunwayPair();
			pair.add(0, new Runway(Lorientation, Ldesignation, Ltora, Ltoda, Lasda, Llda));
			pair.add(1, new Runway(Rorientation, Rdesignation, Rtora, Rtoda, Rasda, Rlda));
			pair.runways[0].setDisplacedThreshold(Ldisplaced);
			pair.runways[1].setDisplacedThreshold(Rdisplaced);
			logLine += "added. ";
		} else {
			pair.runways[0].orientation = Lorientation;
			pair.runways[0].designation = Ldesignation;
			pair.runways[0].TODA = Ltoda;
			pair.runways[0].TORA = Ltora;
			pair.runways[0].LDA = Llda;
			pair.runways[0].ASDA = Lasda;
			pair.runways[0].displacedThreshold = Ldisplaced;

			pair.runways[1].orientation = Rorientation;
			pair.runways[1].designation = Rdesignation;
			pair.runways[1].TODA = Rtoda;
			pair.runways[1].TORA = Rtora;
			pair.runways[1].LDA = Rlda;
			pair.runways[1].ASDA = Rasda;
			pair.runways[1].displacedThreshold = Rdisplaced;
			logLine += "updated. ";
		}

		if(mainFrame.airport.runways.contains(pair))
			mainFrame.airport.runways.remove(pair);
		
		logLine += ("\n" + Lorientation + Ldesignation + " TODA: " + Ltoda + " TORA: " + Ltora + " ASDA: " + Lasda + " LDA: " + Llda + " Displaced Threshold: " + Ldisplaced + ".");
		logLine += ("\n" + Rorientation + Rdesignation + " TODA: " + Rtoda + " TORA: " + Rtora + " ASDA: " + Rasda + " LDA: " + Rlda + " Displaced Threshold: " + Rdisplaced + ".");
		EventManager.getEventManager().notify(EventManager.EventName.LOG, logLine);
		
		mainFrame.airport.runways.add(pair);
		mainFrame.setAirport(mainFrame.airport);
		this.dispose();
	}                                          

	// Variables declaration - do not modify                     
	private javax.swing.JLabel LasdaLabel;
	private javax.swing.JTextField LldaTextField;
	private javax.swing.JLabel LdesignationLabel;
	private javax.swing.JTextField LdesignationTextField;
	private javax.swing.JLabel LdisplacedThresholdLabel;
	private javax.swing.JTextField LdisplacedThresholdTextField;
	private javax.swing.JLabel LldaLabel;
	private javax.swing.JTextField LasdaTextField;
	private javax.swing.JLabel LorientationLabel;
	private javax.swing.JTextField LorientationTextField;
	private javax.swing.JLabel LtodaLabel;
	private javax.swing.JTextField LtodaTextField;
	private javax.swing.JLabel LtoraLabel;
	private javax.swing.JTextField LtoraTextField;
	private javax.swing.JLabel RasdaLabel;
	private javax.swing.JTextField RldaTextField;
	private javax.swing.JLabel RdesignationLabel;
	private javax.swing.JTextField RdesignationTextField;
	private javax.swing.JLabel RdisplacedThresholdLabel;
	private javax.swing.JTextField RdisplacedThresholdTextField;
	private javax.swing.JLabel RldaLabel;
	private javax.swing.JTextField RasdaTextField;
	private javax.swing.JTextField RorientationTextField;
	private javax.swing.JLabel RtodaLabel;
	private javax.swing.JTextField RtodaTextField;
	private javax.swing.JLabel RtoraLabel;
	private javax.swing.JTextField RtoraTextField;
	private javax.swing.JLabel RorientationLabel;
	private javax.swing.JButton saveButton;
	// End of variables declaration                   
}
