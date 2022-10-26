package car.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import car.functionality.VehicleJSONParser;
import car.functionality.VehicleXMLParser;
import car.inventory.Dealership;
import car.inventory.Vehicle;
import car.storage.StateManager;

import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.util.List;


public class VehicleImportFile extends JPanel {

	File filepath;
	List<Dealership> dealers;
	List<Vehicle> vehicles;

	/**
	 * Create the frame.
	 */
	public VehicleImportFile() {

		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		JButton backBtn = new JButton("Back");
		backBtn.setBounds(20, 10, 85, 21);
		add(backBtn);
		backBtn.addActionListener(e -> {
			NavigationManager.changePanel(new WelcomePanel());
		});

		JLabel vehiclesLabel = new JLabel("Vehicles");
		vehiclesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehiclesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vehiclesLabel.setBounds(243, 23, 124, 52);
		add(vehiclesLabel);

		JLabel addLabel = new JLabel("Add:");
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addLabel.setBounds(275, 130, 60, 30);
		add(addLabel);


		JButton xmlBtn = new JButton("Select XML File");
		xmlBtn.addActionListener(e -> {
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
			file.setFileFilter(filter);
			int response = file.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				filepath = new File(file.getSelectedFile().getAbsolutePath());
				dealers = VehicleXMLParser.read(filepath);
				List<String> disabledDealers= StateManager.dealerGroup.addIncomingDealers(dealers);
				if (disabledDealers.size() > 0) {
					String concatDealers = String.join(", ", disabledDealers);
					JOptionPane.showMessageDialog(this,"Dealer Vehicle Acquisition disabled for the following Dealer(s): "
							+ concatDealers + "\nPlease enable Acquisition to add vehicles.");
				} else {
					JOptionPane.showMessageDialog(this,"All Dealers and Vehicles added!");
				}
			}
		});
		xmlBtn.setBounds(90, 200, 150, 40);
		add(xmlBtn);

		JButton jsonButton = new JButton("Select JSON File");
		jsonButton.addActionListener(e -> {
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("json files (*.json)", "json");
			file.setFileFilter(filter);
			int response = file.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				filepath = new File(file.getSelectedFile().getAbsolutePath());
				vehicles = VehicleJSONParser.read(filepath);
				List<String> disabledDealers= StateManager.dealerGroup.addIncomingVehicles(vehicles);
				if (disabledDealers.size() > 0) {
					String concatDealers = String.join(", ", disabledDealers);
					JOptionPane.showMessageDialog(this,"Dealer Vehicle Acquisition disabled for the following Dealer(s): "
							+ concatDealers + "\nPlease enable Acquisition to add vehicles.");
				} else {
					JOptionPane.showMessageDialog(this,"All Vehicles added!");
				}

			}
		});

		jsonButton.setBounds(360, 200, 150, 40);
		add(jsonButton);

		JButton parseButton = new JButton("View All Vehicles");
		parseButton.addActionListener(e -> NavigationManager.changePanel(new ViewAllVehicleGui()));
		parseButton.setBounds(203, 363, 216, 40);
		add(parseButton);

	}
}
