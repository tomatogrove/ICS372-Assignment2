package car.storage;

import car.functionality.VehicleXMLParser;
import car.inventory.DealerGroup;
import car.inventory.Dealership;

import java.io.File;
import java.util.List;

public class StateManager {

    private static final String storage = "../storage/programState.XML";

    public static DealerGroup load() {
        DealerGroup dealerGroup = new DealerGroup();
        List<Dealership> dealers = VehicleXMLParser.read(storage);

        dealerGroup.addIncomingDealers(dealers);

        return dealerGroup;
    }

    public static void save(List<Dealership> dealers) {
        File storageFile = new File(storage);
        VehicleXMLParser.writeAll(storageFile, dealers);
    }

}
