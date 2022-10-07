package car.functionality;

import car.inventory.Dealership;
import car.inventory.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleXMLParser {

    private static final XmlMapper mapper = new XmlMapper();

    public static List<Vehicle> read(String filePath) {
        File file = new File(filePath);
        List<Dealership> dealers = new ArrayList<>();
        try {
            dealers = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getVehicles(dealers);
    }

    public static void write(Dealership dealer) {
        String filePath = "./dealer" + dealer.getDealerID() + "Inventory.XML";
        File file = new File(filePath);
        try {
            file.createNewFile();

            VehicleWrapper wrapper = new VehicleWrapper();
            wrapper.addDealer(dealer);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAll(List<Dealership> dealers, File file) {
        try {
            VehicleWrapper wrapper = new VehicleWrapper();
            wrapper.setDealers(dealers);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Vehicle> getVehicles(List<Dealership> dealers) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Dealership dealer : dealers) {
            vehicles.addAll(dealer.getVehicleInventory().values());
        }
        return vehicles;
    }
}
