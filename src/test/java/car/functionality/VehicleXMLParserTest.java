package car.functionality;

import org.junit.jupiter.api.Test;

import car.functionality.VehicleXMLParser;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleXMLParserTest {

    private static final File REAL_FILE = new File("./src/test/resources/Dealers.xml");
    private static final File WRITE_TO = new File("./src/test/resources/writeAllTest1.xml");
    private static final Dealership DEALER = createTestDealership();

    @Test
    void readOneDealer() {
        List<Dealership> readDealer = VehicleXMLParser.read(REAL_FILE);

        assertEquals(readDealer.get(0).getDealerID(), DEALER.getDealerID());

        List<Vehicle> readVehicles = readDealer.get(0).getVehicleInventory();
        List<Vehicle> testVehicles = DEALER.getVehicleInventory();

        for (int i = 0; i < readVehicles.size(); i++) {
            assertEquals(readVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
            assertEquals(readVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
            assertEquals(readVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
            assertEquals(readVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
            assertEquals(readVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
        }
    }

    @Test
    void writeAllOneDealer() {
        List<Dealership> writeDealer = new ArrayList<>();
        writeDealer.add(DEALER);
        VehicleXMLParser.writeAll(WRITE_TO, writeDealer);

        List<Dealership> readWrittenDealer = VehicleXMLParser.read(WRITE_TO);

        assertEquals(readWrittenDealer.get(0).getDealerID(), DEALER.getDealerID());

        List<Vehicle> readWrittenVehicles = readWrittenDealer.get(0).getVehicleInventory();
        List<Vehicle> testVehicles = DEALER.getVehicleInventory();

        for (int i = 0; i < readWrittenDealer.size(); i++) {
            assertEquals(readWrittenVehicles.get(i).getVehicleID(), testVehicles.get(i).getVehicleID());
            assertEquals(readWrittenVehicles.get(i).getVehicleType(), testVehicles.get(i).getVehicleType());
            assertEquals(readWrittenVehicles.get(i).getVehicleModel(), testVehicles.get(i).getVehicleModel());
            assertEquals(readWrittenVehicles.get(i).getUnit(), testVehicles.get(i).getUnit());
            assertEquals(readWrittenVehicles.get(i).getPrice(), testVehicles.get(i).getPrice());
            assertEquals(readWrittenVehicles.get(i).isRented(), testVehicles.get(i).isRented());
        }
    }

    private static Dealership createTestDealership() {
        Dealership dealer = new Dealership("485", "Wacky Bob’s Automall");
        Date now = new Date(System.currentTimeMillis());
        dealer.addIncomingVehicle(new Vehicle("848432", "485", "suv", "Range Rover", "Land Rover", 17000.0, "pounds", now));
        dealer.addIncomingVehicle(new Vehicle("52523", "485", "pickup", "Tundra", "Toyota", 22600.0, "dollars", now));
        dealer.addIncomingVehicle(new Vehicle("151e5dde", "485", "sedan", "G70", "Genesis", 36600.0, "dollars", now));
        dealer.addIncomingVehicle(new Vehicle("ern222", "485", "sports car", "Miata", "Mazda", 22330.0, "dollars", now));

        return dealer;
    }

}