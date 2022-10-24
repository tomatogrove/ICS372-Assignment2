package car.functionality;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePanel extends JPanel implements ActionListener{

	WelcomePanel(){
		setLayout(null);
		JButton vehicleButton = new JButton("All Vehicles");
		vehicleButton.addActionListener(this);
		vehicleButton.setBounds(80, 300, 100, 50);

		JButton dealerButton = new JButton("Dealers");
		dealerButton.addActionListener(this);
		dealerButton.setBounds(320, 300, 100, 50);

		JLabel title = new JLabel("Vehicle Inventory Program");
		title.setFont(new Font("Areil", Font.BOLD, 24));
		title.setBounds(100,100, 500, 100);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand().equals("All Vehicles")) {
	            MainFrame.changePanel(new VehicleImportFile());
	        }
		 else if(e.getActionCommand().equals("Dealers")) {
			 MainFrame.changePanel(new Test());
		 }
	}
}
