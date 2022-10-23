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

    //getters

    public void setDealerID(String dealerID) {
        this.dealerID = dealerID;
    }

    public boolean isVehicleAcquisition() {
        return vehicleAcquisition;
    }

    public List<Vehicle> getVehicleInventory() {
        return vehicleInventory;
    }

    // for Jackson
    public void setVehicleInventory(List<Vehicle> vehicleInventory) {
        this.vehicleInventory = vehicleInventory;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //for Jackson
    public Dealership(){
        vehicleAcquisition = true;
    }

    public Dealership(String dealerID, String name) {
        this.dealerID = dealerID;
        this.name = name;
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
    }

    public Dealership(String newDealer) {
        dealerID = newDealer;
        vehicleAcquisition = true;
        vehicleInventory = new ArrayList<>();
    }
    //constructors
    public Dealership(String dealerID, boolean vehicleAcquisition, List<Vehicle> vehicleInventory) {
        this.dealerID = dealerID;
        this.vehicleAcquisition = vehicleAcquisition;
        this.vehicleInventory = vehicleInventory;
    }

    public String getDealerID() {
        return dealerID;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String newName) {
        this.dealerName = newName;
    }

    public boolean getIsRenting() {
        return rentStatus;
    }

    public boolean getVehicleAcquisition() {
        return vehicleAcquisition;
    }
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
