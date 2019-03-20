package co.com.uster.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.uster.entities.Driver;

/**
 * Interface encargada de manipular la gestion de la data de los conductores 
 * @author Jhonnatan Orozco Duque
 *
 */
public interface IDriverRepository extends JpaRepository<Driver, Long> {

	@Query("Select d FROM Driver d WHERE d.license = :license")
	public List<Driver> getDriversByLicense(String license);

}
