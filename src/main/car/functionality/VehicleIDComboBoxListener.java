package car.functionality;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;
import car.functionality.EditSpecificVehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;



public class VehicleIDComboBoxListener implements ActionListener{
    String vehicleID = null;
    Dealership dealership;
    DealerGroup dealerGroup;

    VehicleIDComboBoxListener(String vehicleID, DealerGroup dealerGroup){
        this.vehicleID = vehicleID;
        this.dealerGroup = dealerGroup;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String vehicleInfo;
        for(Dealership dealer : dealerGroup.getDealers()){
            if(dealer.getVehicleInventory().containsKey(vehicleID)){
                if(dealer.getVehicleInventory().get(vehicleID).getIsRented()){
                    JOptionPane.showMessageDialog(new JPanel(),"The vehicle is currently rented. If you would like to change the vehicle rental status to not rented, close this message window and click the button in the bottom right.");
                } else {
                    JOptionPane.showMessageDialog(new JPanel(), "The vehicle is not currently rented. If you would like to change the vehicle rental status to rented, close this message window and click the button in the bottom right.");
                }

            }
        }
    }

}
