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

	public static List<Vehicle> read(File file) {
		List<Vehicle> vehicleList = new ArrayList<>();
		try {
			vehicleList = mapper.readValue(file, VehicleJSONWrapper.class).getCar_inventory();

			// sets a default unit of dollars
			for (Vehicle vehicle : vehicleList) {
				vehicle.setUnit("dollars");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}


	public static void write(Dealership dealer) {
		String filePath = "./dealer" + dealer.getDealerID() + "Inventory.json";
		File file = new File(filePath);
		try {
			file.createNewFile();

			VehicleJSONWrapper wrapper = new VehicleJSONWrapper();
			wrapper.setCar_inventory(new ArrayList<>(dealer.getVehicleInventory()));

			mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}