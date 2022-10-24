package car.functionality;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;


public class StartPage {

	JFrame frame;
	
	public StartPage() {
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Title of program displayed in the frame
		JLabel title = new JLabel("Vehicle Inventory Program");
		title.setFont(new Font("Areil", Font.BOLD, 24));
		title.setBounds(100,100, 500, 100);
		
		// Vehicle button to go to the next frame
		JButton vehicleButton = new JButton("All Vehicles");
		vehicleButton.setBounds(80, 300, 100, 50);
		// what to do when button is pressed
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleImportFile vfi = new VehicleImportFile();
				vfi.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		// Dealer button to go to the next frame
		JButton dealerButton = new JButton("Dealers");
		dealerButton.setBounds(320, 300, 100, 50);
		
		// what to do when button is pressed
		dealerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test dealerFrame = new Test();
				
				
			}
		});
		
		frame.add(title);
		frame.add(vehicleButton);
		frame.add(dealerButton);
	}

}
