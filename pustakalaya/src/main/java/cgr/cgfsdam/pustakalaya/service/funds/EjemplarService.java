package cgr.cgfsdam.pustakalaya.service.funds;

import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;

/**
 * Interfaz del servicio de la entidad Ejemplar.
 *
 * @author CGR-Casa
 */
public interface EjemplarService {

	/**
	 * guarda un ejemplar en base de datos.
	 * 
	 * @param ejemplar Ejemplar entidad a persistir
	 */
	void save(Ejemplar ejemplar);

	/**
	 * Busca un ejemplar en base de datos a partir de su código
	 * 
	 * @param codigo String código por el que buscar el ejemplar
	 * @return Ejemplar entidad coincidente con el código
	 */
	Ejemplar findByCodigo(String codigo);

}
