package car.storage;

import car.functionality.VehicleJSONParser;
import car.functionality.VehicleXMLParser;
import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import java.io.File;
import java.util.List;

public class StateManager {

    //lets panels change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;
    private static final File storage = new File("src/main/java/car/storage/programState.json");

    public static void load() { StateManager.dealerGroup = VehicleJSONParser.readAll(storage); }

    public static void save() {
        VehicleJSONParser.writeAll(storage, StateManager.dealerGroup);
    }

}
