# ICS372-Assignment2

## Design Files
Design files should be located within the *AdditionalFiles* folder

## Starting the Program
The main function of the program is located in the StateManager class

## Testing
All tests are located in the *src/test/java/car* directory with the resources folder containing testing files

## Importing Information
### JSON Files will generate a default value for
* Vehicle "unit" which is dollars
* Dealer "isRenting" which defaults to false
* Dealer "vehicleAcquisition" which defaults to true

### XML Files will generate a default value for
* Vehicle "vehicleAcquisitionDate" which is the date the file was submitted
* Dealer "isRenting" which defaults to false
* Dealer "vehicleAcquisition" which defaults to true

## Exporting Information
Exported JSON files may have a different format than entered and as such will not be re-imported properly. 

Additional fields include:
* Dealer name
* Dealer renting status
* Dealer vehicleAcquisitionStatus
* Vehicle rented status
* Vehicle currency unit

These exported files are located in the working directory. 

