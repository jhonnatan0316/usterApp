package co.com.uster.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.uster.entities.Driver;
import co.com.uster.entities.Trip;
import co.com.uster.entities.Vehicle;
import co.com.uster.exceptions.UsterException;
import co.com.uster.repositories.ITripRepository;

/**
 * Clase encargada de comunicar el negocio (Viajes) con la capa de datos y
 * gestionar logica de negocio en caso deser necesario
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@Component
public class TripBusiness {

	@Autowired
	private ITripRepository tripRep;

	@Autowired
	private VehicleBusiness vehicleBus;

	@Autowired
	private DriverBusiness driverpBus;

	/**
	 * Metodo encargado de obtener el listado viajes
	 * 
	 * @return List<Trip>
	 */
	public List<Trip> getTrips() {
		return (List<Trip>) tripRep.findAll();
	}

	/**
	 * Metodo encargado de buscar un viaje por idTrip
	 * 
	 * @param idTrip
	 * @return List<Trip>
	 */
	public Optional<Trip> getTrip(Long idTrip) {
		return tripRep.findById(idTrip);
	}

	/**
	 * Metodo encargado de eliminar un viaje
	 * 
	 * @param idTrip
	 * @return boolean
	 */
	public boolean deleteTrip(Long idTrip) {
		tripRep.deleteById(idTrip);
		return true;
	}

	/**
	 * Metodo encargado de actualizar información de un viaje
	 * 
	 * @param Trip
	 * @return Trip
	 */
	public Trip updateTrip(Trip trip) {
		return tripRep.save(trip);
	}

	/**
	 * Metod encargado de crear viajes
	 * 
	 * @param Trip
	 * @return Trip
	 */
	public Trip createTrip(Trip trip) {
		return tripRep.save(trip);
	}

	/**
	 * Metodo encargado de obtener los vehiculos disponibles para un viaje segun la
	 * fecha de viaje
	 * 
	 * @param tripDate
	 * @return List<Vehicle>
	 */
	public List<Vehicle> getAvailableVehicles(String tripDate) throws UsterException {
		List<Vehicle> vehiclesAvailables = new ArrayList<>();
		try {
			Date selectedDate = convertToDate(tripDate);
			List<Vehicle> vehicles = vehicleBus.getVehicles();
			if (vehicles != null && !vehicles.isEmpty()) {
				List<Trip> trips = tripRep.getTrips(selectedDate);
				if (trips != null && !trips.isEmpty()) {
					for (Vehicle vehicle : vehicles) {
						for (Trip trip : trips) {
							if (!(vehicle.getIdVehicle().equals(trip.getIdVehicle().getIdVehicle()))) {
								vehiclesAvailables.add(vehicle);
							}
						}
					}
				} else {
					return vehicles;
				}

			}
		} catch (Exception e) {
			throw new UsterException(e.getMessage());
		}

		return vehiclesAvailables;
	}

	/**
	 * Metodo encargado de obtener los conductores disponibles para un viaje segun
	 * tipo de licencia y fecha de viaje
	 * 
	 * @param tripDate
	 * @return List<Driver>
	 */
	public List<Driver> getAvailableDrivers(String tripDate, String licenceType) throws UsterException {
		List<Driver> driversAvailables = new ArrayList<>();
		try {
			Date selectedDate = convertToDate(tripDate);
			List<Driver> drivers = driverpBus.getDriversByLicense(licenceType);
			if (drivers != null && !drivers.isEmpty()) {
				List<Trip> trips = tripRep.getTrips(selectedDate);
				if (trips != null && !trips.isEmpty()) {
					for (Driver driver : drivers) {
						for (Trip trip : trips) {
							if (!(driver.getIdDriver().equals(trip.getIdDriver().getIdDriver()))
									&& driver.getLicense().equals(licenceType)) {
								driversAvailables.add(driver);
							}
						}
					}
				} else {
					return drivers;
				}

			}
		} catch (Exception e) {
			throw new UsterException(e.getMessage());
		}

		return driversAvailables;
	}

	/**
	 * Metodo para covertir un String a Date
	 * 
	 * @param tripDate
	 * @return
	 * @throws ParseException
	 */
	private Date convertToDate(String tripDate) throws ParseException {
		Date selectedDate = new SimpleDateFormat("dd/MM/yyyy").parse(tripDate);
		return selectedDate;
	}
}
