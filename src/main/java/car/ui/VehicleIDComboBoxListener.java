package car.ui;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;



public class VehicleIDComboBoxListener implements ActionListener{
    String vehicleID = null;
    Dealership dealership;

    VehicleIDComboBoxListener(String vehicleID, Dealership dealership){
        this.vehicleID = vehicleID;
        this.dealership = dealership;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String vehicleInfo;
        Vehicle vehicle = dealership.getVehicleById(vehicleID);
            if (vehicle != null) {
                if (vehicle.isRented()){
                    JOptionPane.showMessageDialog(new JPanel(),"The vehicle is currently rented. If you would like to change the vehicle rental status to not rented, close this message window and click the button in the bottom right.");
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "The vehicle is not currently rented. If you would like to change the vehicle rental status to rented, close this message window and click the button in the bottom right.");
                }

            }
    }

}
