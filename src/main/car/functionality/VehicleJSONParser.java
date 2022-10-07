package car.functionality;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import car.inventory.Dealership;
import car.inventory.Vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;


public class VehicleJSONParser {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static List<Vehicle> read(String filePath) {
		List<Vehicle> vehicleList = new ArrayList<>();
		try {
			vehicleList = mapper.readValue(new File(filePath), VehicleWrapper.class).getCar_inventory();
		} catch (IOException e) {
			System.out.println("File not found or invalid format.");
		}
		return vehicleList;
	}


	public static void write(Dealership dealer) {
		String filePath = "./dealer" + dealer.getDealerID() + "Inventory.json";
		File file = new File(filePath);
		try {
			file.createNewFile();

			VehicleWrapper wrapper = new VehicleWrapper();
			wrapper.setCar_inventory(new ArrayList<>(dealer.getVehicleInventory().values()));

			mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}