package co.com.uster.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


/**
 * Entidad que representa la tabla VEHICLES
 * 
 * @author Jhonnatan Orozco Duque
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
public class Vehicle implements Serializable{

	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idVehicle;
	private String brand;
	private String model;
	private String plate;
	@Size(max = 1)
	private String licenseRequired;

	public Vehicle() {

	}

	public Vehicle(String brand, String model, String plate, String licenseRequired) {
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.licenseRequired = licenseRequired;
	}

	public Long getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Long idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getLicenseRequired() {
		return licenseRequired;
	}

	public void setLicenseRequired(String licenseRequired) {
		this.licenseRequired = licenseRequired;
	}

	@Override
	public String toString() {
		return "Vehicle [idVehicle=" + idVehicle + ", brand=" + brand + ", model=" + model + ", plate=" + plate
				+ ", licenseRequired=" + licenseRequired + "]";
	}

	

}
