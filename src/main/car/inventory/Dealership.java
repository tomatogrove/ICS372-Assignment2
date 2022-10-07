package car.inventory;

import java.util.Map;
import java.util.HashMap;

public class Dealership {
    private String dealerID;
    private boolean vehicleAcquisition;
    private Map<String, Vehicle> vehicleInventory;

    
    //getters
    public String getDealerID() {
        return dealerID;
    }

    public boolean isVehicleAcquisition() {
        return vehicleAcquisition;
    }

    public Map<String, Vehicle> getVehicleInventory() {
        return vehicleInventory;
    }

    public Dealership(String newDealer) {
        dealerID = newDealer;
        vehicleAcquisition = true;
        vehicleInventory = new HashMap<>();
    }
    //constructors
    public Dealership(String dealerID, boolean vehicleAcquisition, HashMap<String, Vehicle> vehicleInventory) {
        this.dealerID = dealerID;
        this.vehicleAcquisition = vehicleAcquisition;
        this.vehicleInventory = vehicleInventory;
    }
    //methods
    public void addIncomingVehicle(String stockNumber, Vehicle car) {
        if(car.getVehicleType().equalsIgnoreCase("SUV") || car.getVehicleType().equalsIgnoreCase("Sedan") || car.getVehicleType().equalsIgnoreCase("Pickup") || car.getVehicleType().equalsIgnoreCase("Sports Car")) {
            if(vehicleAcquisition == true && this.vehicleInventory.containsKey(stockNumber) == false){
                this.vehicleInventory.put(stockNumber, car);
            } else if(vehicleAcquisition == false){
                System.out.println("Dealer " + this.getDealerID() + " is not allowed to add additional vehicles to their inventory\n");
            } else if(this.vehicleInventory.containsKey(stockNumber) == true) {
                System.out.println("Vehicle " + car.getVehicleID() + " is already in this dealer's inventory\n");
            }
        } else {
            System.out.println("Not a valid vehicle type.\n");
        }
    }

    public void enableDealerVehicleAcquisition() {
        this.vehicleAcquisition = true;
    }

    public void disableDealerVehicleAcquisition() {
        this.vehicleAcquisition = false;
    }

    public String inventory() {
        String inventory = "";
        for( Map.Entry<String, Vehicle> car : this.vehicleInventory.entrySet()) {
            inventory += car.getValue().toString();
        }
        return inventory;
    }
}
