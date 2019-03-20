package co.com.uster.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.uster.entities.Trip;
import co.com.uster.entities.Vehicle;
import co.com.uster.exceptions.UsterException;
import co.com.uster.repositories.ITripRepository;
import co.com.uster.repositories.IVehicleRepository;

/**
 * Clase encargada de comunicar el negocio (Vehiculo) con la capa de 
 * datos y gestionar logica de negocio en caso de ser necesario
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@Component
public class VehicleBusiness {

	
	@Autowired
	private IVehicleRepository vehicleRep;
	
	@Autowired
	private ITripRepository tripRep;

	/**
	 * Metodo encargado de obtner el listado vevehiculos
	 * 
	 * @return List<Vehicle>
	 */
	public List<Vehicle> getVehicles() {
		return (List<Vehicle>) vehicleRep.findAll();
	}
	
	/**
	 * Metodo encargado de buscar un vehiculo por idVehiculo
	 * @param idVehicle
	 * @return List<Vehicle>
	 */
	public Optional<Vehicle> getVehicle(Long idVehicle) {
		return vehicleRep.findById(idVehicle);
	}

	/**
	 * Metodo encargado de eliminar un vehiculo 
	 * @param idVeiculo
	 * @return boolean
	 * @throws UsterException 
	 */
	public boolean deleteVehicle(Long idVehicle) throws UsterException {
		List<Trip> trips = tripRep.getTripsByehicle(idVehicle);
		if (trips != null && !trips.isEmpty()) {
			throw new UsterException("No puede eliminar el vehículo, ya que tiene viajes asignados");
		}
		vehicleRep.deleteById(idVehicle);
		return true;
	}

	/**
	 * Metodo encargado de actualizar información de un vehiculo
	 * @param vehicle
	 * @return Vehicle
	 */
	public Vehicle updateVehicle(Vehicle vehicle) {
		return vehicleRep.save(vehicle);
	}

	/**
	 * Metodo encargado de crear vehiculos
	 * @param vehicle
	 * @return Vehicle
	 */
	public Vehicle createVehicle(Vehicle vehicle) {
		return vehicleRep.save(vehicle);
	}
	
}
