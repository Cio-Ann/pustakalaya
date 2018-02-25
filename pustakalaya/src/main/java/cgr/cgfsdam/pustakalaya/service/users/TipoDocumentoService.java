package cgr.cgfsdam.pustakalaya.service.users;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;

public interface TipoDocumentoService {

	/**
	 * Finds a TipoDocumento by its name.
	 * 
	 * @param nombre String name to search.
	 * @return TipoDocumento object.
	 */
	public TipoDocumento findByNombre(String nombre);
	
	/**
	 * Returns a list with all TipoDocumento stored on DB.
	 * 
	 * @return List<TipoDocumento> all TipoDocumento
	 */
	public List<TipoDocumento> findAll();
	
	/**
	 * Returns a List<String> with all TipoDocumento names.
	 * 
	 * @return List<String> all names.
	 */
	public List<String> findAllNombres();
}
