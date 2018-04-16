package cgr.cgfsdam.pustakalaya.service.users;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;

/**
 * Interfaz del servicio de TipoDocumento.
 *
 * @author CGR-Casa
 */
public interface TipoDocumentoService {

	/**
	 * Encuentra un TipoDocumento por su nombre
	 * 
	 * @param nombre String nombre que buscar.
	 * @return TipoDocumento objeto.
	 */
	public TipoDocumento findByNombre(String nombre);

	/**
	 * Devuelve un listado con todos los TipoDocumento del repositorio.
	 * 
	 * @return List<TipoDocumento> todos los TipoDocumento
	 */
	public List<TipoDocumento> findAll();

	/**
	 * Devuelve una lista con los nombres de todos los TipoDocumento.
	 * 
	 * @return List<String> todos los nombres.
	 */
	public List<String> findAllNombres();
}
