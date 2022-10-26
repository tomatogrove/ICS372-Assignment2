package car.ui;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;
import car.storage.StateManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditSpecificVehicle extends JPanel implements ActionListener {

    Logger logger = Logger.getLogger(EditSpecificVehicle.class.getName());
    JPanel panelSouth;
    JPanel panel;
    JLabel label;
    JButton button;
    JButton backButton;
    JTextArea textArea;
    DealerGroup dealerGroup = new DealerGroup();
    Dealership dealer = new Dealership("12513");
    JComboBox<String> jComboBox = new JComboBox<String>();
    String vehicleRentalInfoText;
    JTextArea vehicleRentalInfo = new JTextArea(2,20);
    JPanel vehicleRentalInfoPanel = new JPanel();
    JPanel centerPanel = new JPanel();

    EditSpecificVehicle(Dealership dealer){

        //build list for JComboBox
        List<String> vehicleIds = new ArrayList<>();

        logger.log(Level.INFO,"Building vehicle ID list for dealership " + dealer.getDealerID());
        for (Vehicle vehicle : dealer.getVehicleInventory()) {
            vehicleIds.add(vehicle.getVehicleID());
        }
        logger.log(Level.INFO,"Finished building vehicle ID list");

        jComboBox.setModel(new DefaultComboBoxModel<String>(vehicleIds.toArray(new String[0])));
        jComboBox.addActionListener(new VehicleIDComboBoxListener(jComboBox.getSelectedItem().toString(), dealer));

        //Bottom panel container for buttons
        panelSouth = new JPanel();
        LayoutManager panelSouthLM = new BorderLayout();
        button = new JButton("Change Vehicle Rental Status");
        button.addActionListener(this);
        panelSouth.setLayout(panelSouthLM);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        panelSouth.add(button,BorderLayout.EAST);
        panelSouth.add(backButton,BorderLayout.WEST);

        //The center panel contains the main display information
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextArea instructionMsg = new JTextArea(3,20);
        String text = "Select a vehicle ID from the drop-down list to edit the vehicle's rental information.";
        instructionMsg.setEditable(false);
        instructionMsg.setText(text);
        Font font = new Font("Verdana", Font.BOLD, 20);
        instructionMsg.setFont(font);
        instructionMsg.setLineWrap(true);
        instructionMsg.setWrapStyleWord(true);
        centerPanel.add(instructionMsg);
        centerPanel.add(jComboBox);
        vehicleRentalInfo.setVisible(false);
        centerPanel.add(vehicleRentalInfo);
        centerPanel.setBorder(new EmptyBorder(100,0,100,0));

        //The north panel contains a label that identifies the purpose of the panel
        JPanel northPanel = new JPanel();
        JLabel northLabel = new JLabel("Edit Vehicle Rental Information");
        northPanel.add(northLabel);
        panel = new JPanel();
        LayoutManager panelLayout = new BorderLayout();
        panel.setLayout(panelLayout);
        panel.add(northPanel,BorderLayout.NORTH);
        panel.add(centerPanel,BorderLayout.CENTER);
        panel.add(panelSouth,BorderLayout.SOUTH);
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back")){
            NavigationManager.changePanel(new DealershipSearch());
        } else {
            String selection = (String)jComboBox.getSelectedItem();

            Vehicle vehicle = dealer.getVehicleById(selection);
            if (vehicle != null) {
                if (vehicle.isRented()) {
                    vehicle.setRented(false);
                    logger.info("The rental status has been changed to: false");
                } else {
                    vehicle.setRented(true);
                    logger.info("The rental status has been changed to: true");
                }
                StateManager.dealerGroup.addIncomingVehicles(List.of(vehicle));
                JOptionPane.showMessageDialog(panel,"The rental status for this vehicle has been updated.");
            }
        }
    }
}
