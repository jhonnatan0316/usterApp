package co.com.uster.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.uster.business.TripBusiness;
import co.com.uster.dto.DataRequestDTO;
import co.com.uster.entities.Driver;
import co.com.uster.entities.Trip;
import co.com.uster.entities.Vehicle;
import co.com.uster.exceptions.UsterException;

/**
 * Clase encargada de los servicios Rest gestion viajes que seran expuestos
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@RestController
@RequestMapping("/trip")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TripController {
	@Autowired
	private TripBusiness tripBuss;

	/**
	 * Metodo encargado de exponer el serivicio para obtener el listado de viajes
	 * 
	 * @return List<Trip>
	 */
	@GetMapping("/gettrips")
	public List<Trip> getDrivers() {
		return (List<Trip>) tripBuss.getTrips();
	}

	/**
	 * Metodo encargado de exponer el servicio para buscar un viaje por idTrip
	 * 
	 * @param idTrip
	 * @return List<Trip>
	 */
	@GetMapping("/gettrip/{idTrip}")
	public Optional<Trip> getTrip(@PathVariable Long idTrip) {
		return tripBuss.getTrip(idTrip);
	}

	/**
	 * Metodo encargado de exponer el servicio para eliminar un viaje
	 * 
	 * @param idTrip
	 * @return boolean
	 */
	@DeleteMapping("/deltrip/{idTrip}")
	public boolean deleteTrip(@PathVariable Long idTrip) {
		tripBuss.deleteTrip(idTrip);
		return true;
	}

	/**
	 * Metodo encargado de exponer el servicio para actualizar información de un
	 * viaje
	 * 
	 * @param trip
	 * @return Trip
	 */
	@PutMapping("/uptrip")
	public Trip updateTrip(@RequestBody Trip trip) {
		return tripBuss.updateTrip(trip);
	}

	/**
	 * Metodo encargado de exponer el servicio para crear viajes
	 * 
	 * @param trip
	 * @returnt Trip
	 */
	@PostMapping("/crtrip")
	public Trip createTrip(@RequestBody Trip trip) {
		return tripBuss.createTrip(trip);
	}

	/**
	 * Metodo encargado de exponer el servicio para buscar vehiculos disponible para
	 * un viaje segun fecha
	 * 
	 * @param tripDate
	 * @return List<Vehicle>
	 * @throws UsterException
	 */

	// @GetMapping("/getvehicles/{tripDate}")
	// @PostMapping("/getvehicles")
	@PostMapping("/getvehicles")
	public List<Vehicle> getAvailableVehicles(@RequestBody String tripDate) throws UsterException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			vehicles = tripBuss.getAvailableVehicles(tripDate);
		} catch (UsterException e) {
			throw new UsterException(e.getMessage());
		}
		return vehicles;
	}

	/**
	 * Metodo encargado de exponer el servicio para buscar conductores disponible
	 * para un viaje segun fecha
	 * 
	 * @param tripDate
	 * @return List<Driver>
	 * @throws UsterException
	 */
	@PostMapping(value = "/getdrivers", consumes = "application/json", produces = "application/json")
	public List<Driver> getAvailableDrivers(@RequestBody DataRequestDTO dataRequestDTO) throws UsterException {
		System.out.println("Ingreso al metodo getAvailableDrivers : " + dataRequestDTO.getTripDate()
				+ dataRequestDTO.getLicenceType());
		List<Driver> drivers = new ArrayList<Driver>();
		try {

			if (dataRequestDTO != null) {
				drivers = tripBuss.getAvailableDrivers(dataRequestDTO.getTripDate(), dataRequestDTO.getLicenceType());
			}

		} catch (Exception e) {
			throw new UsterException(e.getMessage());
		}
		return drivers;
	}

}
