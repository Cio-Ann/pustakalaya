package cgr.cgfsdam.pustakalaya.repository.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;

@Repository("tipoDocumentoRepository")
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	TipoDocumento findByNombre(String nombre);
	
}
