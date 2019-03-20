package co.com.uster.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.uster.entities.Trip;

/**
 * Interface encargada de manipular la gestion de la data de los viajes 
 * @author Jhonnatan Orozco Duque
 *
 */
public interface ITripRepository extends JpaRepository<Trip, Long> {
	@Query("Select t FROM Trip t WHERE t.tripDate = :tripDate")
	public List<Trip> getTrips(Date tripDate);
	
	@Query("Select t FROM Trip t WHERE t.idDriver.idDriver = :idDriver")
	public List<Trip> getTripsByDriver(Long idDriver);
	
	@Query("Select t FROM Trip t WHERE t.idVehicle.idVehicle = :idVehicle")
	public List<Trip> getTripsByehicle(Long idVehicle);

}
