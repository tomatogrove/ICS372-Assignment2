package car.inventory;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String dealerID;
    private boolean vehicleAcquisition;
    private String name;
    private boolean isRenting;
    private List<Vehicle> vehicleInventory;

    public Dealership(){
        vehicleAcquisition = true;
        isRenting = false;
        name = "N/A";
    }

    public Dealership(String dealerID, String name) {
        this.dealerID = dealerID;
        this.name = name;
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
        isRenting = false;
    }

    public Dealership(String newDealer) {
        dealerID = newDealer;
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
        isRenting = false;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDealerID() {
        return dealerID;
    }
    public void setDealerID(String dealerID) { this.dealerID = dealerID; }

    public boolean isVehicleAcquisition() {
        return vehicleAcquisition;
    }

    public boolean isRenting() { return isRenting; }
    public void setRenting(boolean renting) { isRenting = renting; }

    public List<Vehicle> getVehicleInventory() { return vehicleInventory; }
    public void setVehicleInventory(List<Vehicle> vehicleInventory) { this.vehicleInventory = vehicleInventory; }

    //methods
    public void addIncomingVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleType().equalsIgnoreCase("SUV") || vehicle.getVehicleType().equalsIgnoreCase("Sedan") || vehicle.getVehicleType().equalsIgnoreCase("Pickup") || vehicle.getVehicleType().equalsIgnoreCase("Sports Car")) {
            if (vehicleAcquisition) {
                if (vehicleInventory.contains(vehicle)) {
                    Vehicle removeVehicle = getVehicleById(vehicle.getVehicleID());
                    vehicleInventory.remove(removeVehicle);
                }
                vehicleInventory.add(vehicle);
            }
        }
    }

    private Vehicle getVehicleById(String vehicleID) {
        for (Vehicle vehicle : vehicleInventory) {
            if (vehicle.getVehicleID().equals(vehicleID)) {
                return vehicle;
            }
        }
        return null;
    }

    public void enableDealerVehicleAcquisition() {
        this.vehicleAcquisition = true;
    }

    public void disableDealerVehicleAcquisition() {
        this.vehicleAcquisition = false;
    }

    public String inventory() {
        String inventory = "";
        for( Vehicle vehicle : this.vehicleInventory) {
            inventory += vehicle.toString();
        }
        return inventory;
    }

    public void clearInventory() {
        vehicleInventory.clear();
    }
}
