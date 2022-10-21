package car.functionality;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import java.io.File;
import java.util.List;

public class StateManager {

    private static final String storage = "../storage/programState.XML";

    public static DealerGroup load() {
        DealerGroup dealerGroup = new DealerGroup();
        List<Vehicle> vehicles = VehicleXMLParser.read(storage);

        dealerGroup.addIncomingVehicles(vehicles);

        return dealerGroup;
    }

    public static void save(List<Dealership> dealers) {
        File storageFile = new File(storage);
        VehicleXMLParser.writeAll(storageFile, dealers);
    }

}
