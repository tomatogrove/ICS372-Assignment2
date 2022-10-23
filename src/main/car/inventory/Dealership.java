package car.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dealership {
    private String dealerID;
    private boolean vehicleAcquisition;
    private Map<String, Vehicle> vehicleInventory;
    //Unsure if these 2 arrays are needed
    private ArrayList<Vehicle> rentedList;
    private ArrayList<Vehicle> notRentedList;
    private String dealerName;
    private boolean rentStatus;
    /*
     * need new fields for dealer's name and if they are renting vehicles or not
     * also need getters/setters for these fields
     */

    public Dealership(String newDealer) {
        dealerID = newDealer;
        vehicleAcquisition = true;
        vehicleInventory = new HashMap<>();
    }

    // constructors
    public Dealership(String dealerID, boolean vehicleAcquisition, HashMap<String, Vehicle> vehicleInventory) {
        this.dealerID = dealerID;
        this.vehicleAcquisition = vehicleAcquisition;
        this.vehicleInventory = vehicleInventory;
    }

    // methods
    public void addIncomingVehicle(String stockNumber, Vehicle car) {
        if (car.getVehicleType().equalsIgnoreCase("SUV") || car.getVehicleType().equalsIgnoreCase("Sedan")
                || car.getVehicleType().equalsIgnoreCase("Pickup")
                || car.getVehicleType().equalsIgnoreCase("Sports Car")) {
            if (vehicleAcquisition == true && this.vehicleInventory.containsKey(stockNumber) == false) {
                this.vehicleInventory.put(stockNumber, car);
            } else if (vehicleAcquisition == false) {
                System.out.println("Dealer " + this.getDealerID()
                        + " is not allowed to add additional vehicles to their inventory\n");
            } else if (this.vehicleInventory.containsKey(stockNumber) == true) {
                System.out.println("Vehicle " + car.getVehicleID() + " is already in this dealer's inventory\n");
            }
        } else {
            System.out.println("Not a valid vehicle type.\n");
        }
    }

    public void clearInventory() {
        vehicleInventory.clear();
    }

    // check if a specific car is rented or not BUT RETURN BOOLEAN
    public boolean checkIfCarIsRented(Vehicle car) {
        boolean rentedOrNot = false;
        for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
            if(car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                rentedOrNot = car.getRentStatus();
            }
        }
        return rentedOrNot;
    }

    // check if a specific car is rented or not BUT RETURN STRING YES OR NO
    public String isCarIsRented(Vehicle car) {
        String rent = "";
        for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
            if(car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                if(car.getRentStatus() == true) {
                    rent = "yes";
                }
                else {
                    rent = "no";
                }
            }
        }
        return rent;
    }

    // Getting the rented cars manually
    public String getRentedOutCars() {
        for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
            if (vehicle.getValue().getRentStatus() == true) {
                System.out.println(vehicle.getValue());
            }
        }
        return null;
    }

    // Getting the cars from the rentedList
    public String getRentedVehicles() {
        for (Vehicle v : rentedList) {
            System.out.println(v);
        }
        return null;
    }

    // Getting the cars from the notRentedList
    public String getNotRentedVehicles() {
        for (Vehicle v : notRentedList) {
            System.out.println(v);
        }
        return null;
    }

    //Set a dealer's car to rented but does not add to the RENTED LIST
    public void setVehicleToRented(Vehicle car) {
        for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
            if (car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                vehicle.getValue().setToRented();
            }
        }
    }

    //Set a dealer's car to not rented but does not add to the NOT RENTED LIST
    public void setVehicleToNotRented(Vehicle car) {
        for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
            if (car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                vehicle.getValue().setToNotRented();
            }
        }
    }

    // Add car to the rentedList and set the car to "rented"
    public void addCarToRentedList(Vehicle car) {
        try {
            for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
                if (car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                    this.rentedList.add(car);
                    vehicle.getValue().setToRented();
                }
            }
        } catch (Exception e) {
            System.out.println("Car does not exist in inventory");
        }
    }

    // Add car to the not rented list and set car to "not rented"
    public void addCarToNotRentedList(Vehicle car) {
        try {
            for (Map.Entry<String, Vehicle> vehicle : vehicleInventory.entrySet()) {
                if (car.getVehicleID().equalsIgnoreCase(vehicle.getValue().getVehicleID())) {
                    this.notRentedList.add(car);
                    vehicle.getValue().setToNotRented();
                }
            }
        } catch (Exception e) {
            System.out.println("Car does not exist in inventory");
        }
    }

    // getters and setters
    public String getDealerID() {
        return dealerID;
    }

    public boolean getVehicleAcquisition() {
        return vehicleAcquisition;
    }

    public Map<String, Vehicle> getVehicleInventory() {
        return vehicleInventory;
    }

    public ArrayList<Vehicle> getRentedList() {
        return rentedList;
    }

    public ArrayList<Vehicle> getNotRentedList() {
        return notRentedList;
    }

    public void enableDealerVehicleAcquisition() {
        this.vehicleAcquisition = true;
    }

    public void disableDealerVehicleAcquisition() {
        this.vehicleAcquisition = false;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String newName) {
        this.dealerName = newName;
    }

    public boolean getRentOrNot() {
        return rentStatus;
    }

    public void enableRenting() {
        this.rentStatus = true;
    }

    public void disableRenting() {
        this.rentStatus = false;
    }

    public String inventory() {
        String inventory = "";
        for (Map.Entry<String, Vehicle> car : this.vehicleInventory.entrySet()) {
            inventory += car.getValue().toString();
        }
        return inventory;
    }

    @Override
    public String toString() {
        String summary = "Dealer Information\n" + "---------------------------------------------------\n";
        String heading = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", "Dealer ID", "Dealer Name", "Renting cars?");

        String secondLine = "-----------------------------------------------------------------------------------------\n";

        String info = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", dealerID, dealerName, rentStatus);

        return summary + heading + secondLine + info;
    }
}