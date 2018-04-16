package cgr.cgfsdam.pustakalaya.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;

/**
 * Interfaz del repositorio de tipo de documento.
 *
 * @author CGR-Casa
 */
@Repository("tipoDocumentoRepository")
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
	/**
	 * Busca un tipo de documento por su nombre.
	 * 
	 * @param nombre String por el que buscar el documento
	 * @return TipoDocumento tipo de documento que coincide con el nombre o <code>null</code> en caso contrario
	 */
	TipoDocumento findByNombre(String nombre);

}
