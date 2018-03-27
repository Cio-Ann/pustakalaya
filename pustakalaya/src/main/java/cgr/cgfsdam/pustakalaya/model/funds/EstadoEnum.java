package cgr.cgfsdam.pustakalaya.model.funds;

/**
 * Enumeración con los posibles estados de conservación de un ejemplar.
 *
 * @author CGR-Casa
 */
public enum EstadoEnum {
	/**
	 * UNKNOWN: estado desconocido o sin especificar.
	 */
	UNKNOWN("Sin especificar"), 
	/**
	 * DESCATALOGADO: descatalogado por ilegible.
	 */
	DESCATALOGADO("Descatalogado"), 
	/**
	 * EN_RESTAURACION: En restauración, no se puede prestar mientras se este restaurando.
	 */
	EN_RESTAURACION("En restauración"), 
	/**
	 * MALO: Mal estado de conservación. Candidato a restauración.
	 */
	MALO("Mal estado"), 
	/**
	 * NORMAL: Estado normal.
	 */
	NORMAL("Normal"), 
	/**
	 * BUENO: Buen estado de conservación. Se aplica a libros nuevos o seminuevos.
	 */
	BUENO("Buen estado");
	
	/**
	 * Campo que contiene la descripción del estado.
	 */
	private final String descripcion;

	/**
	 * Constructor
	 * 
	 * @param descripcion String texto descriptivo del enumerado.
	 */
	private EstadoEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Método para recuperar la descripción del enumerado.
	 * 
	 * @return String descripción del enumerado.
	 */
	public String getNombre() {
		return this.descripcion;
	}
}
