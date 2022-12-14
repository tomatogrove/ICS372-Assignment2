package car.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomePanel extends JPanel implements ActionListener {

    WelcomePanel(){

        setLayout(null);

        JButton vehicleButton = new JButton("All Vehicles");
        vehicleButton.addActionListener(this);
        vehicleButton.setBounds(80, 300, 150, 50);

        JButton dealerButton = new JButton("Dealers");
        dealerButton.addActionListener(this);
        dealerButton.setBounds(400, 300, 100, 50);

        JLabel title = new JLabel("Vehicle Inventory Program");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(150,100, 500, 100);

        add(title);
        add(vehicleButton);
        add(dealerButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("All Vehicles")) {
            NavigationManager.changePanel(new VehicleImportFile());
        }
        else if (e.getActionCommand().equals("Dealers")) {
            NavigationManager.changePanel(new DealershipSearch());
        }
    }
}
