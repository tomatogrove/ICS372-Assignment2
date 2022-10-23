package car.storage;

import car.functionality.VehicleXMLParser;
import car.inventory.DealerGroup;
import car.inventory.Dealership;

import java.io.File;
import java.util.List;

public class StateManager {

    //lets panels change dealerGroup with class methods like addIncomingVehicles() etc
    public static DealerGroup dealerGroup;
    private static final String storage = "../storage/programState.XML";

    public static void load() {
        List<Dealership> dealers = VehicleXMLParser.read(storage);

        StateManager.dealerGroup.addIncomingDealers(dealers);
    }

    public static void save() {
        File storageFile = new File(storage);
        VehicleXMLParser.writeAll(storageFile, StateManager.dealerGroup.getDealers());
    }

}
