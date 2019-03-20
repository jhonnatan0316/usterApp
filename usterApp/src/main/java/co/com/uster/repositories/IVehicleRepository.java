package co.com.uster.repositories;

import org.springframework.data.repository.CrudRepository;

import co.com.uster.entities.Vehicle;

/**
 * Interface encargada de manipular la gestion de la data de los vehiculos 
 * @author Jhonnatan Orozco Duque
 *
 */
public interface IVehicleRepository extends CrudRepository<Vehicle, Long> {

}

