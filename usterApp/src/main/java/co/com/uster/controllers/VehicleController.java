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

import co.com.uster.business.VehicleBusiness;
import co.com.uster.entities.Vehicle;
import co.com.uster.exceptions.ErrorToDeleteVehicle;
import co.com.uster.exceptions.UsterException;

/**
 * Clase encargada de los servicios Rest gestion vehiculos que seran expuestos
 * 
 * @author Jhonnatan Orozco Duque
 *
 */
@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class VehicleController {

	@Autowired
	private VehicleBusiness vehicleBuss;

	/**
	 * Metodo encargado de exponer el serivicio para obtener el listado de vehiculos
	 * 
	 * @return List<Vehicle>
	 */
	@GetMapping("/getvehicles")
	public List<Vehicle> getVehicles() {
		return (List<Vehicle>) vehicleBuss.getVehicles();
	}

	/**
	 * Metodo encargado de exponer el servicio para buscar un vehiculo por
	 * idVehiculo
	 * 
	 * @param idVehicle
	 * @return List<Vehicle>
	 */
	@GetMapping("/getvehicle/{idVehicle}")
	public Optional<Vehicle> getVehicle(@PathVariable Long idVehicle) {
		return vehicleBuss.getVehicle(idVehicle);
	}

	/**
	 * Metodo encargado de exponer el servicio para eliminar un vehiculo
	 * 
	 * @param idVeiculo
	 * @return boolean
	 * @throws UsterException 
	 */
	@DeleteMapping("/delvehicle/{idVehicle}")
	public boolean deleteVehicle(@PathVariable Long idVehicle) throws UsterException {
		try {
			vehicleBuss.deleteVehicle(idVehicle);
		} catch (UsterException e) {
			throw new ErrorToDeleteVehicle(e.getMessage());
		}
		return true;
	}

	/**
	 * Metodo encargado de exponer el servicio para actualizar información de un
	 * vehiculo
	 * 
	 * @param vehicle
	 * @return Vehicle
	 */
	@PutMapping("/upvehicle")
	public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
		return vehicleBuss.updateVehicle(vehicle);
	}

	/**
	 * Metodo encargado de exponer el servicio para crear vehiculos
	 * 
	 * @param vehicle
	 * @return Vehicle
	 */
	@PostMapping("/crvehicle")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
		return vehicleBuss.createVehicle(vehicle);
	}
}
