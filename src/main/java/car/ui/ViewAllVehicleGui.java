package car.ui;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import car.storage.StateManager;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ViewAllVehicleGui extends JPanel {

	public ViewAllVehicleGui() {

		setBackground(new Color(255, 255, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> NavigationManager.changePanel(new VehicleImportFile()));
		backButton.setBounds(25, 10, 85, 21);
		add(backButton);

		JLabel informationLabel = new JLabel("Information");
		informationLabel.setBounds(275, 30, 150, 25);
		add(informationLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 525, 350);
		add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		textArea.setText(StateManager.dealerGroup.displayDealerVehicles());

	}
}
