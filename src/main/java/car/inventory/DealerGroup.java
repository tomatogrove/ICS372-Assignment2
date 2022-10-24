package car.inventory;

import java.util.ArrayList;
import java.util.List;

public class DealerGroup {

	private List<Dealership> dealers;
	
	public DealerGroup() {
		this.dealers = new ArrayList<>();
	}

	public List<Dealership> getDealers() {
		return dealers;
	}
	
	public Dealership getDealerByID(String dealerID) {
		Dealership getDealer = null;
		for(int i = 0; i < dealers.size(); i++) {
			if(dealerID.equalsIgnoreCase(dealers.get(i).getDealerID()) ) {
				getDealer = dealers.get(i);
			}
		}
		return getDealer;
	}
	
	public String displayDealerVehicles() {
		for(Dealership dealer : dealers) {
			for(Vehicle vehicle : dealer.getVehicleInventory()) {
				System.out.println(vehicle);
			}
		}
		return null;
	}

	public void addIncomingVehicles(List<Vehicle> vehicles) {
		for (Vehicle vehicle : vehicles ) {
			Dealership dealer = getDealerByID(vehicle.getDealershipID());
			if (dealer == null) {
				dealer = new Dealership(vehicle.getDealershipID());
				dealers.add(dealer);
			}
			dealer.addIncomingVehicle(vehicle);
		}
	}

	public void transferInventory(String d1, String d2) {
		Dealership dealer1 = getDealerByID(d1);
		Dealership dealer2 = getDealerByID(d2);
		for (Vehicle vehicle : dealer1.getVehicleInventory()) {
			dealer2.addIncomingVehicle(vehicle);
		}
		dealer1.clearInventory();
	}

}
