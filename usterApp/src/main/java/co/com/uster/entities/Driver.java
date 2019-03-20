package co.com.uster.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


/**
 * Entidad que representa la tabla DRIVERS
 * 
 * @author Jhonnatan Orozco Duque
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
public class Driver implements Serializable{

	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idDriver;
	private String name;
	private String surname;
	@Size(max = 1)
	private String license;

	public Driver() {

	}

	public Driver(String name, String surname, @Size(max = 1) String license) {

		this.name = name;
		this.surname = surname;
		this.license = license;
	}

	public Long getIdDriver() {
		return idDriver;
	}

	public void setIdDriver(Long idDriver) {
		this.idDriver = idDriver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	@Override
	public String toString() {
		return "Driver [idDriver=" + idDriver + ", name=" + name + ", surname=" + surname + ", license=" + license
				+ "]";
	}

}
