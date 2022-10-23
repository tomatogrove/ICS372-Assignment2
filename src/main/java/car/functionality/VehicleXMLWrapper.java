package car.functionality;

import car.inventory.Dealership;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Dealers")
public class VehicleXMLWrapper {

    @JacksonXmlProperty(localName = "Dealer")
    private List<Dealership> dealer;

    public VehicleXMLWrapper() { }

    public List<Dealership> getDealer() {
        return dealer;
    }

    public void setDealer(List<Dealership> dealers) {
        this.dealer = dealers;
    }
}
