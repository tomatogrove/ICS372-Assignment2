package car.functionality;

import car.inventory.DealerGroup;
import car.inventory.Dealership;
import car.inventory.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[]args) {
        DealerGroup dealerGroup = new DealerGroup();
        boolean keepRunning = true;
        String command;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Car Inventory Program. \n"
                + "When entering functions with parameters, follow this format: function parameter");
        while (keepRunning) {
            System.out.println(
                    "Choose one of the following commands:\n" +
                    "addIncomingVehicles filePath\n" +
                    "exportDealerVehicles dealerShipID\n" +
                    "displayDealerVehicles\n" +
                    "enableDealerVehicleAcquisition dealerShipID\n" +
                    "disableDealerVehicleAcquisition dealerShipID\n" +
                    "quit"
            );

            command = scanner.nextLine();

            keepRunning = checkCommand(command, dealerGroup);
            System.out.println("------------------------------------------------\n");
        }
    }

    private static boolean checkCommand(String command, DealerGroup dealerGroup) {
        String[] commandAndParameter = command.split(" ", 2);
        Dealership dealer;

        switch (commandAndParameter[0]) {
            case "addIncomingVehicles":
                List<Vehicle> vehicleList = VehicleJSONParser.read(commandAndParameter[1]);
                dealerGroup.addIncomingVehicles(vehicleList);
                break;
            case "exportDealerVehicles":
                dealer = getDealer(dealerGroup, commandAndParameter);
                if (dealer != null) {
                    VehicleJSONParser.write(dealer);
                    System.out.println("Dealer " + dealer.getDealerID() + " vehicle's exported successfully!");
                }
                break;
            case "displayDealerVehicles":
                dealerGroup.displayDealerVehicles();
                break;
            case "enableDealerVehicleAcquisition":
                dealer = getDealer(dealerGroup, commandAndParameter);
                if(dealer != null) {
                    dealer.enableDealerVehicleAcquisition();
                }
                break;
            case "disableDealerVehicleAcquisition":
                dealer = getDealer(dealerGroup, commandAndParameter);
                if (dealer != null) {
                    dealer.disableDealerVehicleAcquisition();
                }
                break;
            case "quit":
                return false;
            default:
                System.out.println("Command invalid. Check Spelling.");
                break;
        }
        return true;
    }


    private static Dealership getDealer(DealerGroup dealerGroup, String[] commandAndParameter) {
        if (commandAndParameter.length > 1) {
            Dealership dealer = dealerGroup.getDealerByID(commandAndParameter[1]);
            if (dealer != null) {
                return dealer;
            } else {
                System.out.println("Dealer " + commandAndParameter[1] + " does not exist. Please choose an existing dealer");
            }
        } else {
            System.out.println("Enter a dealerShipID");
        }
        return null;
    }
}