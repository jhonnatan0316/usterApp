package co.com.uster.exceptions;

/**
 * Clase encargada de manejar la excepciones controladas dela app
 * @author Jhonnatan Orozco Duque
 *
 */
public class UsterException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UsterException(String message) {
        super(message);
    }
	
}
