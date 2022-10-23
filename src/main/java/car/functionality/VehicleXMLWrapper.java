package car.functionality;

import car.inventory.Dealership;
import java.util.List;

public class VehicleXMLWrapper {
    private List<Dealership> dealers;

    public VehicleXMLWrapper() { }

    public List<Dealership> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealership> dealers) {
        this.dealers = dealers;
    }
}
