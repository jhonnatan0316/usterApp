package co.com.uster.controllers;

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

import co.com.uster.business.DriverBusiness;
import co.com.uster.entities.Driver;
import co.com.uster.exceptions.ErrorToDeleteDriver;
import co.com.uster.exceptions.UsterException;

/**
 * Clase encargada de los servicios Rest gestion conductores que seran expuestos
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class DriverController {

	@Autowired
	private DriverBusiness driverBuss;

	/**
	 * Metodo encargado de exponer el serivicio para obtener el listado de
	 * conductores
	 * 
	 * @return List<Vehicle>
	 */
	@GetMapping("/getdrivers")
	public List<Driver> getDrivers() {
		return (List<Driver>) driverBuss.getDrivers();
	}

	/**
	 * Metodo encargado de exponer el servicio para buscar un conductor por idDriver
	 * 
	 * @param idDriver
	 * @return List<Driver>
	 */
	@GetMapping("/getdriver/{idDriver}")
	public Optional<Driver> getDriver(@PathVariable Long idDriver) {
		return driverBuss.getDriver(idDriver);
	}

	/**
	 * Metodo encargado de exponer el servicio para eliminar un conductor
	 * 
	 * @param idDriver
	 * @return boolean
	 * @throws UsterException 
	 */
	@DeleteMapping("/deldriver/{idDriver}")
	public boolean deleteDriver(@PathVariable Long idDriver) throws UsterException {
		try {
			driverBuss.deleteDriver(idDriver);
		} catch (UsterException e) {
			throw new ErrorToDeleteDriver(e.getMessage());
		}
		return true;
	}

	/**
	 * Metodo encargado de exponer el servicio para actualizar información de un
	 * conductor
	 * 
	 * @param driver
	 * @return Driver
	 */
	@PutMapping("/updriver")
	public Driver updateDriver(@RequestBody Driver driver) {
		return driverBuss.updateDriver(driver);
	}

	/**
	 * Metodo encargado de exponer el servicio para crear conductores
	 * 
	 * @param driver
	 * @return Driver
	 */
	@PostMapping("/crdriver")
	public Driver createDriver(@RequestBody Driver driver) {
		return driverBuss.createDriver(driver);
	}

}
