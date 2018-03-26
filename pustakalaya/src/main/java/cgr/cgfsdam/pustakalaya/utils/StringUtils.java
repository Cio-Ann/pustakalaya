package cgr.cgfsdam.pustakalaya.utils;

/**
 * Clase con metodos estático para operaciones comunes con cadenas.
 *
 * @author CGR-Casa
 */
public class StringUtils {
	
	/**
	 * Método que indica si una String es nula o vacia.
	 * 
	 * @param text texto a evaluar
	 * @return boolean true si la cadena está vacia.
	 */
	public static boolean isEmpty(String text) {
		return text == null || text.isEmpty();
	}
}
