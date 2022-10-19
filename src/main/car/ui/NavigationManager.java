package car.ui;

import car.functionality.StateManager;
import car.inventory.Dealership;

import javax.swing.*;
import java.util.List;

/*
    Need to include a window listener somewhere in here
 */
public class NavigationManager {

    public static void backButton(JFrame frame) {
        //navigate to previous screen and close the current one
    }

    public static void exitProgram(List<Dealership> dealers) {
        StateManager.save(dealers);
        //exit program
    }
}
