package car.ui;

import car.functionality.VehicleJSONParser;
import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

public class DealershipUI {

	//Dealership object to test with called placeholder to bypass null pointer exception
	private static final Dealership placeholder = new Dealership("test");
	private static Dealership searchedDealer = placeholder;
	//DOUBLE CHECK THE LAYOUT OF THE PANELS. May need adjustment
	private static void makeDealerGUI(DealerGroup dealers) {
	   
		JFrame mainPanel = new JFrame();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setSize(500,700);
		
		//navigation panel
		JPanel navigation = new JPanel();
		
		
	    //dealer information screen
		JPanel dealerDetails = new JPanel();
		dealerDetails.setLayout(new GridLayout(3,1,0,10));
		JPanel Options = new JPanel();
		Options.setLayout(new GridLayout(6,3,10,10));
		JLabel ID = new JLabel("Dealer: ID here");
		JLabel Name = new JLabel("Name: ");
		JLabel Renting = new JLabel("Renting: ");
		JLabel Acquisition = new JLabel("Vehicle Acquisition: ");
		JLabel Transfer = new JLabel("Transfer Inventory to: ");
		JLabel Export = new JLabel("Export: ");
		JTextField enterName = new JTextField("Dealer Name here");
		JTextField transferInventory = new JTextField("Receiving Dealer ID here");
		//change name of dealer
		JButton changeName = new JButton("save");
		changeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDealer.setName(enterName.getText());
			}
		});
		//adjust rent boolean value of dealer
		JButton disableRent = new JButton("disable");
		disableRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDealer.setRenting(false);
				//printing to console for testing
				System.out.println(searchedDealer.getDealerID() + "'s renting is now " + searchedDealer.isRenting());
			}
		});
		JButton enableRent = new JButton("enable");
		enableRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDealer.setRenting(true);
				//printing to console for testing
				System.out.println(searchedDealer.getDealerID() + "'s renting is now " + searchedDealer.isRenting());
			}
		});
		//adjust vehicle acquisition boolean value of dealer
		JButton disableAcquisition = new JButton("disable");
		disableAcquisition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDealer.disableDealerVehicleAcquisition();
				//printing to console for testing
				System.out.println(searchedDealer.getDealerID() + "'s acquisition is now " + searchedDealer.isVehicleAcquisition());
			}
		});
		JButton enableAcquisition = new JButton("enable");
		enableAcquisition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedDealer.enableDealerVehicleAcquisition();
				//printing to console for testing
				System.out.println(searchedDealer.getDealerID() + "'s acquisition is now " + searchedDealer.isVehicleAcquisition());
			}
		});
		//checks inventory for rented cars first before transferring entire inventory
		JButton transferAllVehicles = new JButton("transfer");
		transferAllVehicles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Vehicle vehicle : searchedDealer.getVehicleInventory()) {
					if(searchedDealer.isVehicleRented(vehicle)) {
						//transfer did not occur because a vehicle in the dealer's inventory is rented
						return;
					}
				}
				dealers.transferInventory(searchedDealer.getDealerID(), transferInventory.getText());
				//Verifying method works through console output. transferInventory method needs adjustment to change each vehicle object's dealershipID to the receiving dealer's
				Dealership receivingDealer = dealers.getDealerByID(transferInventory.getText());
				System.out.println(receivingDealer.inventory());
			}
		});
		
		
		JButton JSONExport = new JButton("JSON");
		JSONExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleJSONParser.write(searchedDealer);
			}
		});
		
		
		//invisible elements acting as padding to make the specific vehicle button not look massive. A lazy way of addressing the problem
		JLabel filler1 = new JLabel("");
		filler1.setVisible(false);
		
		JLabel filler2 = new JLabel("");
		filler2.setVisible(false);
		
		JLabel filler3 = new JLabel("");
		filler3.setVisible(false);
		
		//button that will transition to Mike's panel
		JButton specificVehicleScreen = new JButton("Specific Vehicle");
		specificVehicleScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//This is a transition point to Mike's panel called "panel"
				dealerDetails.setVisible(false);
				//mainPanel.remove(dealerDetails); used for testing
				//EditSpecificVehicle(); constructing Mike's panels
				//mainPanel.add(panel); used for testing
				//panel.setVisible(true); used for testing
			}
		});
		
		dealerDetails.add(ID);
		
		Options.add(Name);
		Options.add(enterName);
		Options.add(changeName);
		
		Options.add(Renting);
		Options.add(enableRent);
		Options.add(disableRent);
		
		Options.add(Acquisition);
		Options.add(enableAcquisition);
		Options.add(disableAcquisition);
		
		Options.add(Transfer);
		Options.add(transferInventory);
		Options.add(transferAllVehicles);
		
		Options.add(Export);
		Options.add(JSONExport);
		Options.add(filler1);
		
		Options.add(specificVehicleScreen);
		Options.add(filler2);
		Options.add(filler3);
		
		Options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dealer Details"));
		
		
		dealerDetails.add(Options);

		dealerDetails.setVisible(false);


		//dealer search screen
		JPanel dealerSearch = new JPanel();
	    JLabel dealerOfInterest = new JLabel("Dealer: ");
	    JTextField searchTerm = new JTextField("enter dealer name here...");
	    JButton search = new JButton("search");
	    dealerSearch.add(navigation);
	    dealerSearch.add(dealerOfInterest);
	    dealerSearch.add(searchTerm);
	    dealerSearch.add(search);
	    dealerSearch.setSize(500, 100);
	    dealerSearch.setLayout(new GridLayout(1,4));
	    dealerSearch.setVisible(true);
	    search.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//search method to find Dealer. Will return null if dealer is not found
	    		if(dealers.getDealerByID(searchTerm.getText()) == null) {
	    			//show an error message that dealer does not exist
	    			searchTerm.setText("Dealer does not exist...");
	    		} else {
	    		searchedDealer = dealers.getDealerByID(searchTerm.getText());
	    		//transition point between searching for dealer screen and dealer info screen. Both converted into Jpanel
	    		mainPanel.remove(dealerSearch);
	    		dealerSearch.setVisible(false);
	    		mainPanel.add(dealerDetails);
	    		ID.setText(searchedDealer.getDealerID());
	    		enterName.setText(searchedDealer.getName());
	    		dealerDetails.setVisible(true);
	    		filler2.setVisible(false);
	    		}
	    	}
	    });
	    
	    //navigation buttons added to the main frame handling the panels.
	    //Needs a bit more work on both buttons to reach the other parts of the GUI outside of my 2 panels
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dealerDetails.isVisible()) {
				dealerDetails.setVisible(false);
				mainPanel.remove(dealerDetails);
				mainPanel.add(dealerSearch);
				dealerSearch.setVisible(true);
				} else if(dealerSearch.isVisible()){
					//show panel preceding dealer search screen. The welcome panel that is commented out pending Iab's decision on naming the welcome panel
					dealerSearch.setVisible(false);
					//mainPanel.setVisible(false);
					//searchTerm.setText("Enter Dealer ID...");
					//mainPanel.remove(dealerSearch); May want to replace "mainPanel" with the name of the JFrame and remove the "mainPanel" JPanel instead of dealerSearch
					//mainPanel.add(welcomePanel);
					//welcomePanel.setVisible(true);
				}
			}
		});
		JButton forward = new JButton("Forward");
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!dealerDetails.isVisible()) {
				dealerSearch.setVisible(false);
	    		mainPanel.remove(dealerSearch);
	    		mainPanel.add(dealerDetails);
	    		dealerDetails.setVisible(true);
				} else {
					if(searchedDealer.equals(placeholder) || searchedDealer.equals(null)) {
						//show a message to search for a dealer first
						filler2.setText("Search for a dealer first!");
						filler2.setVisible(true);
					} else {
						//go forward to specific vehicle screen ONLY if a specific dealer has been searched for
						//This is the transition point to Mike's panel called "panel"
					//dealerDetails.setVisible(false);
					//mainPanel.setVisible(false);
					//mainPanel.remove(dealerDetails); May want to replace "mainPanel" with the name of the JFrame and remove the "mainPanel" JPanel instead of dealerDetails
					//mainPanel.add(panel); used for testing
					//panel.setVisible(true); used for testing
					}
				}
			}
		});
		
		navigation.add(back);
		navigation.add(forward);

	    mainPanel.add(navigation);
	    mainPanel.add(dealerSearch);
	    mainPanel.setVisible(true);
	    
	}
}