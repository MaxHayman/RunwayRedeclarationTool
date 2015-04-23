import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 *
 * @author Max
 */
public class MainFrame extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */
	Airport airport;
	Runway runway;
	int calculationsType = 0;
	public DefaultComboBoxModel<Obstacle> obstacleTemplateModel = new DefaultComboBoxModel<Obstacle>();
	public List<String> log = new ArrayList<String>();

	public MainFrame(Airport airport) {
		super("Main");
		initComponents();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/8-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setResizable(false);
		ButtonGroup group = new ButtonGroup();
		group.add(takeOffAwayMenuItem);
		group.add(takeOffTowardsMenuItem);

		this.airport = airport;

		if(airport.runways.size() > 0) {
			this.runway = airport.runways.get(0).runways[0];
			setRunway(runway);
		}
		setAirport(airport);

		obstacleTemplateModel.addElement(new Obstacle("Plane", 20));
		obstacleTemplateModel.addElement(new Obstacle ("Box", 1));

		EventManager.getEventManager().addEventNotify(EventManager.EventName.UPDATE, this, "updateObstacles");
		EventManager.getEventManager().addEventNotify(EventManager.EventName.LOG, this, "addLog");
		
		String[] names = airport.fileName.split("\\\\");
        String name = names[names.length-1].replace(".xml", "");
		String logLine = "Successfully " + (airport.fileName.equals("") ? "created " : ("loaded " + name)) + " airport.";
		EventManager.getEventManager().notify(EventManager.EventName.LOG, logLine);
	}

	public void addLog(String line) {
		log.add(line);
	}

	public void setRunway(Runway runway) {
		this.runway = runway;

		calculationsType = this.runway.calcType = this.runway.bestOption();

		if(this.runway.calcType == 0) {
			takeOffAwayMenuItem.setSelected(true);
			takeOffTowardsMenuItem.setSelected(false);
			calcualtionsTypeLabel.setText("Take Off Away, Landing Over");
		} else if (this.runway.calcType == 1){
			takeOffAwayMenuItem.setSelected(false);
			takeOffTowardsMenuItem.setSelected(true);
			calcualtionsTypeLabel.setText("Take Off Towards, Landing Towards");
		}

		updateObstacles();
	}

	public void updateObstacles() {
		DefaultListModel<Obstacle> model = new DefaultListModel<Obstacle>();
		for(Obstacle o : runway.obstacles.keySet()){
			model.addElement(o);
		}    
		obstaclesList.setModel(model);     
		obstaclesList.setSelectedIndex(0);

		if(calculationsType == 1 ) {
			runway.calculate(0, 300);
		} else {
			runway.calculate(1, 300);
		}
		calculationsTable.getModel().setValueAt(runway.TORA, 0, 1);
		calculationsTable.getModel().setValueAt(runway.TODA, 1, 1);
		calculationsTable.getModel().setValueAt(runway.ASDA, 2, 1);
		calculationsTable.getModel().setValueAt(runway.LDA, 3, 1);   
		calculationsTable.getModel().setValueAt(runway.nTORA, 0, 2);
		calculationsTable.getModel().setValueAt(runway.nTODA, 1, 2);
		calculationsTable.getModel().setValueAt(runway.nASDA, 2, 2);
		calculationsTable.getModel().setValueAt(runway.nLDA, 3, 2); 
	}

	public void setAirport(Airport airport) {
		jComboBox1.removeAllItems();
		for(RunwayPair runwayPair : airport.runways) {
			jComboBox1.addItem(runwayPair.runways[0]);
			jComboBox1.addItem(runwayPair.runways[1]);
		}
	}

	private void initComponents() {

		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		obstaclesScrollPane = new javax.swing.JScrollPane();
		obstaclesList = new javax.swing.JList<Obstacle>();
		obstaclesLabel = new javax.swing.JLabel();
		addObstacleButton = new javax.swing.JButton();
		removeObstacleButton = new javax.swing.JButton();
		modifyObstacleButton = new javax.swing.JButton();
		blastProtectionAllowanceTextField = new javax.swing.JTextField();
		blastProtectionAllowanceLabel = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox<Runway>();
		calculationsScrollPane = new javax.swing.JScrollPane();
		calculationsTable = new javax.swing.JTable();
		calcualtionsTypeLabel = new javax.swing.JLabel();
		titleLabel = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		exitMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		editRunwayMenuItem = new javax.swing.JMenuItem();
		addRunwayMenuItem = new javax.swing.JMenuItem();
		removeRunwayMenuItem = new javax.swing.JMenuItem();
		viewMenu = new javax.swing.JMenu();
		topDownMenuItem = new javax.swing.JMenuItem();
		sideMenuItem = new javax.swing.JMenuItem();
		calculationsMenuItem = new javax.swing.JMenuItem();
		calculationsMenu = new javax.swing.JMenu();
		takeOffAwayMenuItem = new javax.swing.JRadioButtonMenuItem();
		takeOffTowardsMenuItem = new javax.swing.JRadioButtonMenuItem();

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null}
				},
				new String [] {
						"Title 1", "Title 2", "Title 3", "Title 4"
				}
				));
		jScrollPane2.setViewportView(jTable1);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		/*obstaclesList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "09R", "27L", "<<SEPERATOR>>", "09L", "27R" };
			public int getSize() { return strings.length; }
			public Object getElementAt(int i) { return strings[i]; }
		});*/
		obstaclesScrollPane.setViewportView(obstaclesList);

		obstaclesLabel.setText("Obstacles");

		addObstacleButton.setText("Add");
		addObstacleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addObstacleButtonActionPerformed(evt);
			}
		});

		removeObstacleButton.setText("Remove");
		removeObstacleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeObstacleButtonActionPerformed(evt);
			}
		});

		modifyObstacleButton.setText("Modify");
		modifyObstacleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyObstacleButtonActionPerformed(evt);
			}
		});

		blastProtectionAllowanceTextField.setText("300");

		blastProtectionAllowanceLabel.setText("Blast Protection Allowance");

		jLabel4.setText("Heathrow");

		//jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09R", "27L", "<<SEPERATOR>>", "09L", "27R" }));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});

		jComboBox1.setRenderer(new ComboSeparatorsRenderer(jComboBox1.getRenderer()){});    

		calculationsTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
						{"TORA", null, null},
						{"TODA", null, null},
						{"ASDA", null, null},
						{"LDA", null, null}
				},
				new String [] {
						"Property", "Original Value", "New Value"
				}
				));
		calculationsTable.setEnabled(false);
		calculationsTable.getTableHeader().setReorderingAllowed(false);
		calculationsScrollPane.setViewportView(calculationsTable);

		calcualtionsTypeLabel.setText("Take Off Away, Landing Over");

		titleLabel.setText("Runway Redeclaration");

		fileMenu.setText("File");

		saveMenuItem.setText("Save");
		saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save As...");
		saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveAsMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(saveAsMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");

		addRunwayMenuItem.setText("Add Runway");
		addRunwayMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addRunwayMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(addRunwayMenuItem);

		editRunwayMenuItem.setText("Edit Runway");
		editRunwayMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editRunwayMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(editRunwayMenuItem);

		removeRunwayMenuItem.setText("Remove Runway");
		removeRunwayMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeRunwayMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(removeRunwayMenuItem);

		menuBar.add(editMenu);

		viewMenu.setText("View");

		topDownMenuItem.setText("2D Top-down");
		topDownMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				topDownMenuItemActionPerformed(evt);
			}
		});
		viewMenu.add(topDownMenuItem);

		sideMenuItem.setText("2D Side");
		sideMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sideMenuItemActionPerformed(evt);
			}
		});
		viewMenu.add(sideMenuItem);

		calculationsMenuItem.setText("Calculations Text");
		calculationsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				calculationsMenuItemActionPerformed(evt);
			}
		});
		viewMenu.add(calculationsMenuItem);

		menuBar.add(viewMenu);

		calculationsMenu.setText("Calculations");

		takeOffAwayMenuItem.setSelected(true);
		takeOffAwayMenuItem.setText("Take Off Away, Landing Over");
		takeOffAwayMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeCalculationsItemActionPerformed(evt);
			}
		});
		calculationsMenu.add(takeOffAwayMenuItem);

		takeOffTowardsMenuItem.setSelected(true);
		takeOffTowardsMenuItem.setText("Take Off Towards, Landing Towards");
		takeOffTowardsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeCalculationsItemActionPerformed(evt);
			}
		});
		calculationsMenu.add(takeOffTowardsMenuItem);

		menuBar.add(calculationsMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(obstaclesScrollPane)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(titleLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(obstaclesLabel)
														.addGroup(layout.createSequentialGroup()
																.addComponent(addObstacleButton)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(removeObstacleButton)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(modifyObstacleButton))
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																		.addComponent(blastProtectionAllowanceTextField, javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(blastProtectionAllowanceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																		.addComponent(calcualtionsTypeLabel)
																		.addComponent(calculationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(0, 0, Short.MAX_VALUE)))
																		.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(titleLabel))
								.addGap(44, 44, 44)
								.addComponent(calcualtionsTypeLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(calculationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
								.addComponent(blastProtectionAllowanceLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(blastProtectionAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(obstaclesLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(obstaclesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(addObstacleButton)
										.addComponent(removeObstacleButton)
										.addComponent(modifyObstacleButton))
										.addGap(31, 31, 31))
				);

		pack();
	}// </editor-fold>                        

	private void addObstacleButtonActionPerformed(java.awt.event.ActionEvent evt) {   
		if(runway == null) {
			JOptionPane.showMessageDialog(null, "You must select a runway to be able to add obstacles to it.");
			return;
		}

		AddObstacleFrame frame = new AddObstacleFrame(this, null);
		frame.obstacleTemplateComboBox.setModel(obstacleTemplateModel);
		frame.setVisible(true);
	}  

	private void removeObstacleButtonActionPerformed(java.awt.event.ActionEvent evt) {   
		if(runway == null) {
			JOptionPane.showMessageDialog(null, "You must select a runway to be able to remove obstacles from it.");
			return;
		}

		Obstacle o = obstaclesList.getSelectedValue();

		if(o == null) {
			JOptionPane.showMessageDialog(null, "Please select an obstace.");
		} else {
			runway.pair.removeObstacle(obstaclesList.getSelectedValue());
			updateObstacles();
			EventManager.getEventManager().notify(EventManager.EventName.UPDATE);
		}
	} 

	private void modifyObstacleButtonActionPerformed(java.awt.event.ActionEvent evt) {       
		if(runway == null) {
			JOptionPane.showMessageDialog(null, "You must select a runway to be able to modify obstacles to it.");
			return;
		}

		Obstacle o = obstaclesList.getSelectedValue();

		if(o == null) {
			JOptionPane.showMessageDialog(null, "Please select an obstace.");
		} else {
			new AddObstacleFrame(this, o).setVisible(true);
		}
	} 

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) { 
		if(jComboBox1.getSelectedItem() != null) {
			setRunway((Runway)jComboBox1.getSelectedItem());
			EventManager.getEventManager().notify(EventManager.EventName.UPDATE);
		}
	}           

	private void addRunwayMenuItemActionPerformed(java.awt.event.ActionEvent evt) {       
		new AddRunwayFrame(this, null).setVisible(true);
	}    

	private void editRunwayMenuItemActionPerformed(java.awt.event.ActionEvent evt) {        
		if(runway == null) {
			JOptionPane.showMessageDialog(null, "You must select a runway to be able to edit it.");
		} else {
			new AddRunwayFrame(this, this.runway.pair).setVisible(true);
		}
	}                                                  

	private void removeRunwayMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                    
		if(runway == null) {
			JOptionPane.showMessageDialog(null, "You must select a runway to be able to remove it.");
		} else {
			airport.runways.remove(runway.pair);
			this.setAirport(airport);
			this.runway = null;
			EventManager.getEventManager().notify(EventManager.EventName.UPDATE);
		}
	}                                                   

	private void topDownMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
		new Frame2D(this, Display2DTop.View.TOP_VIEW);
	}

	private void sideMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
		new Frame2D(this, Display2DTop.View.SIDE_VIEW);
	}

	private void calculationsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		new CalculationsFrame(this).setVisible(true);
		new LogFrame(log).setVisible(true);
	}

	private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
		XMLHandler.saveXML(airport, false);
	} 

	private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
		XMLHandler.saveXML(airport, true);
	} 

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
		EventManager.getEventManager().notify(EventManager.EventName.EXIT);
		this.dispose();
		new LoginFrame().setVisible(true);
	}                                            

	private void changeCalculationsItemActionPerformed(java.awt.event.ActionEvent evt) { 
		if(takeOffAwayMenuItem.isSelected()) {
			calculationsType = 0;
			calcualtionsTypeLabel.setText("Take Off Away, Landing Over");
		} else if (takeOffTowardsMenuItem.isSelected()){
			calculationsType = 1;
			calcualtionsTypeLabel.setText("Take Off Towards, Landing Towards");
		}
		updateObstacles();
	}                                                   

	// Variables declaration - do not modify                     
	private javax.swing.JButton addObstacleButton;
	private javax.swing.JLabel blastProtectionAllowanceLabel;
	private javax.swing.JTextField blastProtectionAllowanceTextField;
	private javax.swing.JLabel calcualtionsTypeLabel;
	private javax.swing.JMenu calculationsMenu;
	private javax.swing.JScrollPane calculationsScrollPane;
	private javax.swing.JTable calculationsTable;
	private javax.swing.JMenuItem addRunwayMenuItem;
	private javax.swing.JMenuItem removeRunwayMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem editRunwayMenuItem;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JComboBox<Runway> jComboBox1;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JButton modifyObstacleButton;
	private javax.swing.JLabel obstaclesLabel;
	private javax.swing.JList<Obstacle> obstaclesList;
	private javax.swing.JScrollPane obstaclesScrollPane;
	private javax.swing.JButton removeObstacleButton;
	private javax.swing.JMenuItem sideMenuItem;
	private javax.swing.JRadioButtonMenuItem takeOffAwayMenuItem;
	private javax.swing.JRadioButtonMenuItem takeOffTowardsMenuItem;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JMenuItem topDownMenuItem;
	private javax.swing.JMenuItem calculationsMenuItem;
	private javax.swing.JMenu viewMenu;
	// End of variables declaration       

	abstract class ComboSeparatorsRenderer implements ListCellRenderer{
		private ListCellRenderer delegate;
		private JPanel separatorPanel = new JPanel(new BorderLayout());
		private JSeparator separator = new JSeparator();

		public ComboSeparatorsRenderer(ListCellRenderer delegate){
			this.delegate = delegate;
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
			Component comp = delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if(index > 1 && index % 2 == 0){ // index==1 if renderer is used to paint current value in combo
				separatorPanel.removeAll();
				separatorPanel.add(separator, BorderLayout.NORTH);
				separatorPanel.add(comp, BorderLayout.SOUTH);
				return separatorPanel;
			}else
				return comp;
		}
	}
}
