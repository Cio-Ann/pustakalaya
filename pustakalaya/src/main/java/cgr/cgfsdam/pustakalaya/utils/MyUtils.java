package cgr.cgfsdam.pustakalaya.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase con metodos estático para operaciones comunes.
 *
 * @author CGR-Casa
 */
public class MyUtils {
	
	/**
	 * Método que indica si una String es nula o vacia.
	 * 
	 * @param text texto a evaluar
	 * @return boolean true si la cadena está vacia.
	 */
	public static boolean isEmptyString(String text) {
		return text == null || text.isEmpty();
	}
	
	/**
	 * Método que devuelve el año correspondiente a la fecha recibida.
	 * 
	 * @param fecha Date fecha de la que calcular el año
	 * @return int año correspondiente a la fecha.
	 */
	public static int getYear(Date fecha) {
		int ret = -1;
		if (fecha != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			
			ret = calendar.get(Calendar.YEAR);
		}

		return ret;
	}
}
