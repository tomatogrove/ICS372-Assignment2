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
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

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
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addLabel.setBounds(254, 124, 54, 21);
		add(addLabel);

		JLabel xmlLabel = new JLabel("XML");
		xmlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xmlLabel.setBounds(20, 175, 45, 13);
		add(xmlLabel);

		JLabel xmlFilePath = new JLabel("XML File Path");
		xmlFilePath.setBackground(new Color(192, 192, 192));
		xmlFilePath.setHorizontalAlignment(SwingConstants.CENTER);
		xmlFilePath.setBounds(54, 175, 234, 13);
		add(xmlFilePath);

		JLabel jsonLabel = new JLabel("JSON:");
		jsonLabel.setBounds(294, 175, 45, 13);
		add(jsonLabel);

		JButton xmlBtn = new JButton("Select XML File");
		xmlBtn.addActionListener(e -> {
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
			file.setFileFilter(filter);
			int response = file.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				filepath = new File(file.getSelectedFile().getAbsolutePath());
				xmlFilePath.setText(String.valueOf(filepath));
				dealers = VehicleXMLParser.read(filepath);
				StateManager.dealerGroup.addIncomingDealers(dealers);
			}
		});
		xmlBtn.setBounds(86, 198, 111, 21);
		add(xmlBtn);

		JLabel jsonPathLabel = new JLabel("JSON File Path");
		jsonPathLabel.setBackground(new Color(255, 255, 255));
		jsonPathLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jsonPathLabel.setBounds(339, 175, 259, 13);
		add(jsonPathLabel);

		JButton jsonButton = new JButton("Select JSON File");
		jsonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("json files (*.json)", "json");
				file.setFileFilter(filter);
				int response = file.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					filepath = new File(file.getSelectedFile().getAbsolutePath());
					jsonPathLabel.setText(String.valueOf(filepath));
					vehicles = VehicleJSONParser.read(filepath);
					StateManager.dealerGroup.addIncomingVehicles(vehicles);
				}
			}
		});

		jsonButton.setBounds(437, 198, 113, 21);
		add(jsonButton);

		JLabel errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setBounds(178, 315, 274, 13);
		add(errorLabel);

		JButton parseButton = new JButton("View All Vehicles");
		parseButton.addActionListener(e -> NavigationManager.changePanel(new ViewAllVehicleGui()));
		parseButton.setBounds(203, 363, 216, 21);
		add(parseButton);

	}
}
