package car.functionality;

import car.inventory.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class VehicleWrapper {
    private List<Vehicle> car_inventory;

    public VehicleWrapper() {
    }

    public List<Vehicle> getCar_inventory() {
        return car_inventory;
    }

    public void setCar_inventory(ArrayList<Vehicle> car_inventory) {
        this.car_inventory = car_inventory;
    }
}
