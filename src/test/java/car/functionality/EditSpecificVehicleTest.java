package car.functionality;

import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;



class EditSpecificVehicleTest {

    EditSpecificVehicle editSpecificVehicle = new EditSpecificVehicle();
    ActionEvent mockEvent = new ActionEvent(editSpecificVehicle,0,"Change Vehicle Rental Status");

    @Test
    void actionPerformed() {
        editSpecificVehicle.actionPerformed(mockEvent);
    }
}