package co.com.uster.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase encargada de manejar el error en la eliminación de conductores
 * @author Jhonnatan Orozco Duque
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ErrorToDeleteDriver extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorToDeleteDriver(String message) {

		super(message);
	}
}
