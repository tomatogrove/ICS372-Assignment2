package car.functionality;

import car.inventory.Dealership;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleXMLParser {

    private static final XmlMapper mapper = createMapper();

    public static List<Dealership> read(File file) {
        List<Dealership> dealers = new ArrayList<>();
        Date now = new Date(System.currentTimeMillis());
        try {
            dealers = mapper.readValue(file, VehicleXMLWrapper.class).getDealer();

            // sets default values for dealerId and vehicleAcquisitionDate
            for (Dealership dealer : dealers) {
                dealer.setAllVehicleDealerIDs(dealer.getDealerID());
                dealer.setAllVehicleAcquisitionDates(now);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dealers;
    }

    public static void writeAll(File file, List<Dealership> dealers) {
        try {
            VehicleXMLWrapper wrapper = new VehicleXMLWrapper();
            wrapper.setDealer(dealers);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // from https://github.com/FasterXML/jackson-dataformat-xml/issues/219#issuecomment-286003056
    private static XmlMapper createMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        return new XmlMapper(module);
    }
}
