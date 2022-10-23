package car.functionality;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditSpecificVehicle implements ActionListener {

    Logger logger = Logger.getLogger(EditSpecificVehicle.class.getName());
    JFrame frame;
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



    EditSpecificVehicle(){
        //This vehicle import (lines 39 - 40) can be removed.
        //These were added for local testing
        List<Vehicle> vehicleList = VehicleJSONParser.read("C:/Users/eric_/Documents/ICS 372/Project1_input.json");
        dealerGroup.addIncomingVehicles(vehicleList);

        //build list for JComboBox
        List<String> vehicleIds = new ArrayList<>();
        for(Dealership dealer : dealerGroup.getDealers()){
            logger.log(Level.INFO,"Building vehicle ID list for dealership " + dealer.getDealerID());
            for(String key : dealer.getVehicleInventory().keySet()){
                vehicleIds.add(key);
            }
            logger.log(Level.INFO,"Finished building vehicle ID list");
        }

        jComboBox.setModel(new DefaultComboBoxModel<String>(vehicleIds.toArray(new String[0])));
        jComboBox.addActionListener(new VehicleIDComboBoxListener(jComboBox.getSelectedItem().toString(),dealerGroup));

        //This frame can be removed. Only used for local testing.
        frame = new JFrame();

        //Bottom panel container for buttons
        panelSouth = new JPanel();
        LayoutManager panelSouthLM = new BorderLayout();
        button = new JButton("Change Vehicle Rental Status");
        button.addActionListener(this);
        panelSouth.setLayout(panelSouthLM);
        backButton = new JButton("Back");

        //TODO add action listener for back button
//        button.addActionListener();

        panelSouth.add(button,BorderLayout.EAST);
        panelSouth.add(backButton,BorderLayout.WEST);
        label = new JLabel();

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
        frame.add(panel);
        frame.setSize(900,600);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = (String)jComboBox.getSelectedItem();
        for(Dealership dealership : dealerGroup.getDealers()){
            if(dealership.getVehicleInventory().get(selection) != null){

                if(dealership.getVehicleInventory().get(selection).getIsRented()){
                    System.out.println("rental status: " + dealership.getVehicleInventory().get(selection).getIsRented());
                    dealership.getVehicleInventory().get(selection).setIsRented(false);
                    logger.info("The rental status has been changed to: " + dealership.getVehicleInventory().get(selection).getIsRented());

                } else {
                    dealership.getVehicleInventory().get(selection).setIsRented(true);
                    System.out.println("rental status: " + dealership.getVehicleInventory().get(selection).getIsRented());
                    logger.info("The rental status has been changed to: " + dealership.getVehicleInventory().get(selection).getIsRented());

                }
                JOptionPane.showMessageDialog(panel,"The rental status for this vehicle has been updated.");
            }
        }
    }


    public JPanel getCenterPanel(){
        return centerPanel;
    }
}
