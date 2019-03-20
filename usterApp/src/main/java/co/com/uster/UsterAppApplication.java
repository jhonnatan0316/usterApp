package co.com.uster;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.com.uster.entities.Driver;
import co.com.uster.entities.Trip;
import co.com.uster.entities.Vehicle;
import co.com.uster.repositories.IDriverRepository;
import co.com.uster.repositories.ITripRepository;
import co.com.uster.repositories.IVehicleRepository;

@SpringBootApplication
public class UsterAppApplication implements CommandLineRunner {

	@Autowired
	private IVehicleRepository vehicleRep;
	@Autowired
	private IDriverRepository driverRep;
	@Autowired
	private ITripRepository triRep;


	public static void main(String[] args) {
		SpringApplication.run(UsterAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Se crean vehiculos iniciales en la base de datos H2
		vehicleRep.save(new Vehicle("Mazda", "2012", "ass123", "A"));
		vehicleRep.save(new Vehicle("Renault", "", "asf113", "B"));
		vehicleRep.save(new Vehicle("Chevrolet", "1980", "asa133", "C"));
		vehicleRep.save(new Vehicle("Kia", "2018", "tyu765", "C"));
		vehicleRep.save(new Vehicle("Mazda", "2016", "bhu098", "B"));
		vehicleRep.save(new Vehicle("subaru", "2008", "ujn869", "A"));

		// Se crean conductores iniciales en la base de datos H2
		driverRep.save(new Driver("Andres", "Marin", "A"));
		driverRep.save(new Driver("Andrea", "Fernandez", "B"));
		driverRep.save(new Driver("Saul", "Diaz", "B"));
		driverRep.save(new Driver("Betsy", "Grajales", "A"));
		driverRep.save(new Driver("Ian", "Orozco", "C"));
		driverRep.save(new Driver("Raul", "Toro", "C"));

		// Se crean Viajes iniciales en la base de datos H2
		Vehicle vehicleMaz = new Vehicle();
		vehicleMaz.setIdVehicle(1L);
		vehicleMaz.setBrand("Mazda");
		vehicleMaz.setModel("2102");
		vehicleMaz.setLicenseRequired("A");
		
		Vehicle vehicleRen = new Vehicle();
		vehicleRen.setIdVehicle(2L);
		vehicleRen.setBrand("Renault");
		vehicleRen.setModel("2015");
		vehicleRen.setLicenseRequired("B");
		
		Vehicle vehicleChe = new Vehicle();
		vehicleChe.setIdVehicle(3L);
		vehicleChe.setBrand("Chevrolet");
		vehicleChe.setModel("1980");
		vehicleChe.setLicenseRequired("C");
		
		Driver driverAndres= new Driver();
		driverAndres.setIdDriver(7L);
		driverAndres.setName("Andres");
		driverAndres.setSurname("Marin");
		driverAndres.setLicense("A");
		
		Driver driverAndrea= new Driver();
		driverAndrea.setIdDriver(8L);
		driverAndrea.setName("Andrea");
		driverAndrea.setSurname("Fernandez");
		driverAndrea.setLicense("B");
		
		Driver driverSaul = new Driver();
		driverSaul.setIdDriver(9L);
		driverSaul.setName("Saul");
		driverSaul.setSurname("Diaz");
		driverSaul.setLicense("B");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date tripDate = null;
		tripDate = format.parse("10/12/2018");
		triRep.save(new Trip(driverAndres, vehicleMaz, tripDate));
		tripDate = format.parse("16/03/2019");
		triRep.save(new Trip(driverAndrea, vehicleRen, tripDate));
		tripDate = format.parse("17/08/2019");
		triRep.save(new Trip(driverSaul, vehicleChe, tripDate));

	}

}
