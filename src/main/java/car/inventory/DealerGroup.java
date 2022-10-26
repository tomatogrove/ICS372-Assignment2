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
		StringBuilder sb = new StringBuilder();
		for(Dealership dealer : dealers) {
			for(Vehicle vehicle : dealer.getVehicleInventory()) {
				sb.append(vehicle.toString());
			}
		}
		return sb.toString();
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

	public void addIncomingDealers(List<Dealership> dealers) {
		for (Dealership dealer : dealers) {
			Dealership oldDealer = getDealerByID(dealer.getDealerID());
			if (oldDealer != null) {
				String newName = dealer.getName();
				oldDealer.setName(newName);
			} else {
				this.dealers.add(dealer);
			}
			addIncomingVehicles(dealer.getVehicleInventory());
		}
	}

	public boolean transferInventory(String d1, String d2) {
		Dealership dealer1 = getDealerByID(d1);
		Dealership dealer2 = getDealerByID(d2);
		if (dealer2 != null) {
			dealer1.setAllVehicleDealerIds(d2);
			for (Vehicle vehicle : dealer1.getVehicleInventory()) {
				dealer2.addIncomingVehicle(vehicle);
			}
			dealer1.clearInventory();
			return true;
		}
		return false;
	}

}
