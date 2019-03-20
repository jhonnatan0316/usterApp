package co.com.uster.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase encargada de manejar el error en la eliminación de veiculos
 * @author Jhonnatan Orozco Duque
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No puede eliminar el vehículo, ya que tiene viajes asignados")
public class ErrorToDeleteVehicle extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorToDeleteVehicle(String message) {

		super(message);
	}
}
