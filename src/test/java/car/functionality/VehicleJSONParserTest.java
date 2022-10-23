package car.functionality;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleJSONParserTest {

    private static final File READ_TO = new File("./src/test/resources/dealer12513Inventory.json");
    private static final File MOCK_STATE = new File("./src/test/resources/TestStateStorage.json");
    private static final DealerGroup TEST_DEALER_GROUP = createTestDealership();

    @Test
    void read() {
    }

    @Test
    void write() {
    }

    @Test
    void readAll() {
        DealerGroup readDealerGroup = VehicleJSONParser.readAll(MOCK_STATE);

        List<Dealership> readDealers = readDealerGroup.getDealers();
        List<Dealership> testDealers = TEST_DEALER_GROUP.getDealers();
        for (int i = 0; i < testDealers.size(); i++) {

            assertEquals(readDealers.get(i).getDealerID(), testDealers.get(i).getDealerID());
            assertEquals(readDealers.get(i).getName(), testDealers.get(i).getName());

            List<Vehicle> readVehicles = readDealers.get(i).getVehicleInventory();
            List<Vehicle> testVehicles = testDealers.get(i).getVehicleInventory();
            for (int j = 0; j < testVehicles.size(); j++) {
                assertEquals(readVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
                assertEquals(readVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
                assertEquals(readVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
                assertEquals(readVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
                assertEquals(readVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
            }
        }
    }

    @Test
    void writeAll() {
        VehicleJSONParser.writeAll(MOCK_STATE, TEST_DEALER_GROUP);

        readAll();
    }

    private static DealerGroup createTestDealership() {
        DealerGroup dealerGroup = new DealerGroup();
        Date now = new Date(System.currentTimeMillis());

        Dealership dealer1 = new Dealership("1", "Wacky Dealer One");

        dealer1.addIncomingVehicle(new Vehicle("1", "1", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer1.addIncomingVehicle(new Vehicle("2", "1", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("3", "1", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer1.addIncomingVehicle(new Vehicle("4", "1", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));


        Dealership dealer2 = new Dealership("1", "Wacky Dealer One");

        dealer2.addIncomingVehicle(new Vehicle("1", "1", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer2.addIncomingVehicle(new Vehicle("2", "1", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("3", "1", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer2.addIncomingVehicle(new Vehicle("4", "1", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        dealerGroup.getDealers().addAll(List.of(dealer1, dealer2));

        return dealerGroup;
    }
}