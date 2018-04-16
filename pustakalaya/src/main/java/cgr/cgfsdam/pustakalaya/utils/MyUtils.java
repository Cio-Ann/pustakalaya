package cgr.cgfsdam.pustakalaya.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

	/**
	 * Devuelve el Date equivalente al LocalDate recibido.
	 * 
	 * @param localDate LocalDate fecha original
	 * @return Date fecha transformada
	 */
	public static Date fromLocalToDate(LocalDate localDate) {

		if (null != localDate)
			return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		else
			return null;
	}

	/**
	 * Devuelve el LocalDate equivalente al Date recibido.
	 * 
	 * @param date Date fecha origen
	 * @return LocalDate fecha transformada
	 */
	public static LocalDate fromDateToLocal(Date date) {

		if (null != date)
			return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
		else
			return null;
	}

	/**
	 * Devuelve un String a partir de la fecha dada en formato dd/MM/yyyy.
	 * 
	 * @param fecha Date fecha de la que obtener el texto
	 * @return String fecha formateada
	 */
	public static String dateToShort(Date fecha) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		return format.format(fecha);
	}

}
