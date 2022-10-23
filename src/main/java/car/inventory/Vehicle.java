package car.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Date;
import java.util.Objects;

public class Vehicle {
	@JacksonXmlProperty(isAttribute = true, localName = "id")
	@JsonProperty("vehicle_id")
	private String vehicleID;

	@JsonProperty("dealership_id")
	private String dealershipID;

	@JacksonXmlProperty(isAttribute = true, localName = "type")
	@JsonProperty("vehicle_type")
	private String vehicleType;

	@JacksonXmlProperty(localName = "Model")
	@JsonProperty("vehicle_model")
	private String vehicleModel;

	@JacksonXmlProperty(localName = "Make")
	@JsonProperty("vehicle_manufacturer")
	private String vehicleManufacturer;

	/*
		the price value may need to be changed to Java's Currency. Or it could be an Enum or two values.
	 */

	private String unit;

	private Double price;

	@JacksonXmlProperty(localName = "Price")
	private Price priceWrapper;

	@JsonProperty("acquisition_date")
	private Date acquisitionDate;

	/*
		class will also need a "rented" boolean that needs a setter/getter
	 */
	private boolean rented;

	//make Jackson happy
	public Vehicle() {}

	public Vehicle(String vehicleID, String dealershipID, String vehicleType, String vehicleModel, String vehicleManufacturer,
		   Double price, String unit, Date acquisitionDate) {
		this.vehicleID = vehicleID;
		this.dealershipID = dealershipID;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.vehicleManufacturer = vehicleManufacturer;
		this.price = price;
		this.unit = unit;
		this.acquisitionDate = acquisitionDate;
	}

	public Vehicle(String vehicleID, String dealershipID, String vehicleType, String vehicleModel, String vehicleManufacturer,
			Double price, Date acquisitionDate) {
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
	public String getUnit() { return unit; }

	public void setUnit(String unit) { this.unit = unit; }

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Price getPriceWrapper() {
		return priceWrapper;
	}

	public void setPriceWrapper(Price priceWrapper) {
		this.price = Double.valueOf(priceWrapper.getPrice());
		this.unit = priceWrapper.getUnit();
		this.priceWrapper = priceWrapper;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public boolean isRented() { return rented; }

	public void setRented(boolean rented) { this.rented = rented; }

	@Override
	public String toString() {

		String summary = "Vehicle Information\n" + "---------------------------------------------------\n";
		String heading = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", "Vehicle ID", "Dealership ID",
				"Vehicle Type", "Vehicle Model", "Vehicle Manufacturer", "Price", "Acquisition Date");

		String secondLine = "-----------------------------------------------------------------------------------------\n";

		String info = String.format("%-16s%-11s%-5s%-12s%-12s%-12s%-12s\n", vehicleID, dealershipID, vehicleType,
				vehicleModel, vehicleManufacturer, price, acquisitionDate);

		return summary + heading + secondLine + info;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vehicle vehicle = (Vehicle) o;
		return vehicleID.equals(vehicle.vehicleID);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Price {
		@JacksonXmlProperty(isAttribute = true)
		String unit;

		@JacksonXmlText
		String price;

		public Price() {
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
	}
}
