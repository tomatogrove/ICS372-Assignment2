package car.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import car.functionality.VehicleJSONParser;
import car.functionality.VehicleXMLParser;
import car.inventory.Dealership;
import car.inventory.Vehicle;
import car.storage.StateManager;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class VehicleImportFile extends JPanel {
	JFrame frameNew;
	private JPanel contentPane;
	File filepath;
	static VehicleImportFile frame;
	List<Dealership> dealers;
	List<Vehicle> vehicles;

	/**
	 * Create the frame.
	 */
	public VehicleImportFile() {
		frameNew.setBackground(new Color(192, 192, 192));
		frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNew.setBounds(100, 100, 622, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frameNew.setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton backBtn = new JButton("Back");
		backBtn.setBounds(20, 10, 85, 21);
		contentPane.add(backBtn);

		JLabel vehiclesLabel = new JLabel("Vehicles");
		vehiclesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehiclesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vehiclesLabel.setBounds(243, 23, 124, 52);
		contentPane.add(vehiclesLabel);

		JLabel addLabel = new JLabel("Add:");
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addLabel.setBounds(254, 124, 54, 21);
		contentPane.add(addLabel);

		JLabel xmlLabel = new JLabel("XML");
		xmlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xmlLabel.setBounds(20, 175, 45, 13);
		contentPane.add(xmlLabel);

		JLabel xmlFilePath = new JLabel("XML File Path");
		xmlFilePath.setBackground(new Color(192, 192, 192));
		xmlFilePath.setHorizontalAlignment(SwingConstants.CENTER);
		xmlFilePath.setBounds(54, 175, 234, 13);
		contentPane.add(xmlFilePath);

		JLabel jsonLabel = new JLabel("JSON:");
		jsonLabel.setBounds(294, 175, 45, 13);
		contentPane.add(jsonLabel);

		JButton xmlBtn = new JButton("Select File");
		xmlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				file.setFileFilter(filter);
				int response = file.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					filepath = new File(file.getSelectedFile().getAbsolutePath());
					xmlFilePath.setText(String.valueOf(filepath));
					dealers = VehicleXMLParser.read(filepath);
					for (Dealership dealer : dealers) {
						StateManager.dealerGroup.addIncomingVehicles(dealer.getVehicleInventory());
					}
				}
			}
		});
		xmlBtn.setBounds(86, 198, 111, 21);
		contentPane.add(xmlBtn);

		JLabel jsonPathLabel = new JLabel("JSON File Path");
		jsonPathLabel.setBackground(new Color(255, 255, 255));
		jsonPathLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jsonPathLabel.setBounds(339, 175, 259, 13);
		contentPane.add(jsonPathLabel);

		JButton jsonButton = new JButton("Select File");
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
		contentPane.add(jsonButton);

		JLabel errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setBounds(178, 315, 274, 13);
		contentPane.add(errorLabel);

		JButton parseButton = new JButton("View All Vehicles");
		parseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filepath == null) {
					errorLabel.setText("No File Chosen! Please choose a file!");
				} else {
					ViewAllVehicleGui frameView = new ViewAllVehicleGui(vehicles);
					frameView.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		parseButton.setBounds(203, 363, 216, 21);
		contentPane.add(parseButton);

	}
}