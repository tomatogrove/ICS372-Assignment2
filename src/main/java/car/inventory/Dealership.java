package car.inventory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

public class Dealership {
    private String dealerID;
    private boolean vehicleAcquisition;
    private List<Vehicle> vehicleInventory;

    private String name;
    private boolean isRenting;

    //for Jackson
    public Dealership(){
        vehicleAcquisition = true;
        isRenting = false;
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

    // for Jackson
    public void setVehicleInventory(List<Vehicle> vehicleInventory) { this.vehicleInventory = vehicleInventory; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDealerID() {
        return dealerID;
    }

    public void setDealerID(String dealerID) { this.dealerID = dealerID; }

    public boolean isVehicleAcquisition() {
        return vehicleAcquisition;
    }

    public List<Vehicle> getVehicleInventory() { return vehicleInventory; }

    //methods
    public boolean addIncomingVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleType().equalsIgnoreCase("SUV") || vehicle.getVehicleType().equalsIgnoreCase("Sedan") || vehicle.getVehicleType().equalsIgnoreCase("Pickup") || vehicle.getVehicleType().equalsIgnoreCase("Sports Car")) {
            if (vehicleAcquisition) {
                if (vehicleInventory.contains(vehicle)) {
                    Vehicle removeVehicle = vehicleInventory.stream().filter((Vehicle v) -> v.getVehicleID().equals(vehicle.getVehicleID())).collect(Collectors.toList()).get(0);
                    vehicleInventory.remove(removeVehicle);
                }
                vehicleInventory.add(vehicle);
                return true;
            }
        }
        return false;
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
