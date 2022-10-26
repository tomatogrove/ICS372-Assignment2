package car.ui;

import car.functionality.VehicleJSONParser;
import car.inventory.Dealership;
import car.storage.StateManager;

import java.awt.event.*;
import javax.swing.*;

public class DealershipSearch extends JPanel implements ActionListener {

	private JLabel invalidSearch;
	private JTextField searchTerm;

	DealershipSearch() {

		setLayout(null);

		JButton back = new JButton("Back");
		back.setBounds(20,10,85,21);

		JLabel dealerOfInterest = new JLabel("Dealer: ");
		dealerOfInterest.setBounds(75,190,100,50);

		searchTerm = new JTextField("enter dealer id here...");
		searchTerm.setBounds(150, 200, 240,30);

		JButton search = new JButton("search");
		search.setBounds(400, 200,80,30);

		invalidSearch = new JLabel("Dealer does not exist...");
		invalidSearch.setBounds(250,300,300,100);

		back.addActionListener(this);
		search.addActionListener(this);

		add(back);
		add(dealerOfInterest);
		add(searchTerm);
		add(search);
		add(invalidSearch);
		invalidSearch.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "search":
				if (searchTerm != null) {
					Dealership dealer = StateManager.dealerGroup.getDealerByID(searchTerm.getText());
					if (dealer != null) {
						NavigationManager.changePanel(new SpecificDealerShip(dealer));
					} else {
						invalidSearch.setVisible(true);
					}
				}
				break;
			case "Back":
				NavigationManager.changePanel(new WelcomePanel());
		}
	}



	public static class SpecificDealerShip extends JPanel implements ActionListener {

		private Dealership dealer;

		private JTextField enterName;
		private JTextField transferInventory;

		public SpecificDealerShip(Dealership dealer) {

			this.dealer = dealer;

			setLayout(null);

			JButton back = new JButton("Back");

			JLabel id = new JLabel("Dealer: ID here");
			JLabel name = new JLabel("Name: ");
			JLabel renting = new JLabel("Renting: ");
			JLabel acquisition = new JLabel("Vehicle Acquisition: ");
			JLabel transfer = new JLabel("Transfer Inventory to: ");
			JLabel export = new JLabel("Export: ");
			enterName = new JTextField("Dealer Name here");
			transferInventory = new JTextField("Receiving Dealer ID here");

			JButton changeName = new JButton("save");
			JButton disableRent = new JButton("disable renting");
			JButton enableRent = new JButton("enable renting");
			JButton disableAcquisition = new JButton("disable acquisition");
			JButton enableAcquisition = new JButton("enable acquisition");
			JButton transferAllVehicles = new JButton("transfer");
			JButton jsonExport = new JButton("JSON");
			JButton specificVehicleScreen = new JButton("Specific Vehicle");

			back.addActionListener(this);
			changeName.addActionListener(this);
			disableRent.addActionListener(this);
			enableRent.addActionListener(this);
			disableAcquisition.addActionListener(this);
			enableAcquisition.addActionListener(this);
			transferAllVehicles.addActionListener(this);
			jsonExport.addActionListener(this);
			specificVehicleScreen.addActionListener(this);

			add(back);

			add(id);

			add(name);
			add(enterName);
			add(changeName);

			add(renting);
			add(enableRent);
			add(disableRent);

			add(acquisition);
			add(enableAcquisition);
			add(disableAcquisition);

			add(transfer);
			add(transferInventory);
			add(transferAllVehicles);

			add(export);
			add(jsonExport);

			add(specificVehicleScreen);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "Back":
					NavigationManager.changePanel(new DealershipSearch());
					break;
				case "save":
					dealer.setName(enterName.getText());
					break;
				case "disable renting":
					dealer.setRenting(false);
					break;
				case "enable renting":
					dealer.setRenting(true);
					break;
				case "disable acquisition":
					dealer.disableDealerVehicleAcquisition();
					break;
				case "enable acquisition":
					dealer.enableDealerVehicleAcquisition();
					break;
				case "transfer":
					boolean didSucceed = StateManager.dealerGroup.transferInventory(dealer.getDealerID(), transferInventory.getText());
					break;
				case "JSON":
					VehicleJSONParser.write(dealer);
					break;
				case "Specific Vehicle":
					NavigationManager.changePanel(new EditSpecificVehicle(dealer));
			}
		}
	}
}
