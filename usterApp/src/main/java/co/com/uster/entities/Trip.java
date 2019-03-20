package co.com.uster.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entidad que representa la tabla TRIP
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@Entity
public class Trip implements Serializable{

	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idTrip;
	//Id que relaciona un viaje a un conductor
	@OneToOne(fetch = FetchType.EAGER)
	private Driver idDriver;
	//Id que relaciona un viaje  a un vehiculo
	@OneToOne(fetch = FetchType.EAGER)
	private Vehicle idVehicle;
	@Temporal(TemporalType.DATE)
	//Fecha del viaje
	private Date tripDate;
	
	public Trip() {

	}

	public Trip(Driver idDriver, Vehicle idVehicle, Date tripDate) {
		this.idDriver = idDriver;
		this.idVehicle = idVehicle;
		this.tripDate = tripDate;
	}

	
	public Long getIdTrip() {
		return idTrip;
	}

	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}

	public Driver getIdDriver() {
		return idDriver;
	}

	public void setIdDriver(Driver idDriver) {
		this.idDriver = idDriver;
	}

	public Vehicle getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Vehicle idVehicle) {
		this.idVehicle = idVehicle;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	@Override
	public String toString() {
		return "Trip [idTrip=" + idTrip + ", idDriver=" + idDriver + ", idVehicle=" + idVehicle + ", tripDate="
				+ tripDate + "]";
	}
	
}
