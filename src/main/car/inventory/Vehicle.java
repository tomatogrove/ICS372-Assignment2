package car.inventory;

import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {
	@JsonProperty("vehicle_id")
	private String vehicleID;
	@JsonProperty("dealership_id")
	private String dealershipID;
	@JsonProperty("vehicle_type")
	private String vehicleType;
	@JsonProperty("vehicle_model")
	private String vehicleModel;
	@JsonProperty("vehicle_manufacturer")
	private String vehicleManufacturer;
	@JsonProperty("acquisition_date")
	private Date acquisitionDate;
	private Double price;
	private String unit;
	private boolean rentStatus = false;

	// make Jackson happy
	public Vehicle() {
	}

	public Vehicle(String vehicleID, String dealershipID, String vehicleType, String vehicleModel,
				   String vehicleManufacturer, Double price, Date acquisitionDate) {
		this.vehicleID = vehicleID;
		this.dealershipID = dealershipID;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.vehicleManufacturer = vehicleManufacturer;
		this.price = price;
		this.acquisitionDate = acquisitionDate;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getDealershipID() {
		return dealershipID;
	}

	public void setDealershipID(String dealershipID) {
		this.dealershipID = dealershipID;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public boolean getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(boolean status) {
		rentStatus = status;
	}

	@Override
	public String toString() {

		String summary = "Vehicle Information\n" + "---------------------------------------------------\n";
		String heading = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", "Vehicle ID", "Dealership ID",
				"Vehicle Type", "Vehicle Model", "Vehicle Manufacturer", "Price", "Acquisition Date", "Rent Status");

		String secondLine = "-----------------------------------------------------------------------------------------\n";

		String info = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", vehicleID, dealershipID, vehicleType,
				vehicleModel, vehicleManufacturer, price, acquisitionDate, rentStatus);

		return summary + heading + secondLine + info;
	}

}