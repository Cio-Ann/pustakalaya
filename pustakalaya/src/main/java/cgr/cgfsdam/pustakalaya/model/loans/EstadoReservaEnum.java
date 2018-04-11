package cgr.cgfsdam.pustakalaya.model.loans;

/**
 * Enumerado que representa los estados en los que puede estar una reserva.
 *
 * @author CGR-Casa
 */
public enum EstadoReservaEnum {
	/**
	 * UNKNOWN: estado desconocido o sin especificar.
	 */
	UNKNOWN("Sin especificar"), 
	/**
	 * WAITING: reserva en espera de disponibilidad del recurso.
	 */
	WAITING("Disponible"),
	/**
	 * CONSUMED: se ha consumido la reserva, restando el recurso al usuario.
	 */
	CONSUMED("Consumida"),
	/**
	 * USER_CANCELLED: el usuario canceló la reserva.
	 */
	USER_CANCELLED("Cancelada/Usuario"),
	/**
	 * ADMIN_CANCELLED: el administrador cancelo la reserva.
	 */
	ADMIN_CANCELLED("Cancelada/Admin");
	
	/**
	 * Campo que contiene la descripción del estado.
	 */
	private final String nombre;

	/**
	 * Constructor.
	 * 
	 * @param nombre
	 */
	private EstadoReservaEnum(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Método para recuperar en nombre.
	 * 
	 * @return String nombre del enumerado.
	 */
	public String getNombre() {
		return this.nombre;
	}
}