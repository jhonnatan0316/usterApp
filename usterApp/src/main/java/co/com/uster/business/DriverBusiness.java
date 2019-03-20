package co.com.uster.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.uster.entities.Driver;
import co.com.uster.entities.Trip;
import co.com.uster.exceptions.UsterException;
import co.com.uster.repositories.IDriverRepository;
import co.com.uster.repositories.ITripRepository;

/**
 * Clase encargada de comunicar el negocio (Conductor) con la capa de datos y
 * gestionar logica de negocio en caso deser necesario
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@Component
public class DriverBusiness {

	@Autowired
	private IDriverRepository driverRep;

	@Autowired
	private ITripRepository tripRep;

	/**
	 * Metodo encargado de obtener el listado conductores
	 * 
	 * @return List<Driver>
	 */
	public List<Driver> getDrivers() {
		return (List<Driver>) driverRep.findAll();
	}

	/**
	 * Metodo encargado de buscar un conductor por idDriver
	 * 
	 * @param idDriver
	 * @return List<Driver>
	 */
	public Optional<Driver> getDriver(Long idDriver) {
		return driverRep.findById(idDriver);
	}

	/**
	 * Metodo encargado de eliminar un conductor
	 * 
	 * @param idDriver
	 * @return boolean
	 */
	public boolean deleteDriver(Long idDriver) throws UsterException {
		List<Trip> trips = tripRep.getTripsByDriver(idDriver);
		if (trips != null && !trips.isEmpty()) {
			throw new UsterException("No puede eliminar el conductor, ya que tiene viajes asignados");
		}
		driverRep.deleteById(idDriver);
		return true;
	}

	/**
	 * Metodo encargado de actualizar información de un conductor
	 * 
	 * @param Driver
	 * @return Driver
	 */
	public Driver updateDriver(Driver driver) {
		return driverRep.save(driver);
	}

	/**
	 * Metodo encargado de crear conductores
	 * 
	 * @param Driver
	 * @return Driver
	 */
	public Driver createDriver(Driver driver) {
		return driverRep.save(driver);
	}

	/**
	 * Metodo encargado de obtener el listado conductores por tipo delicencia
	 * 
	 * @return List<Driver>
	 */
	public List<Driver> getDriversByLicense(String license) {
		return driverRep.getDriversByLicense(license);
	}
}
