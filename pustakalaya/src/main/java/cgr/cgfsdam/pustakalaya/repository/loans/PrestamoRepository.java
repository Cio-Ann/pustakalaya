package cgr.cgfsdam.pustakalaya.repository.loans;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

@Repository("prestamoRepository")
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

	/**
	 * Devuelve todos los prestamos relacionados con el usuario dado.
	 * 
	 * @param usuario Usuario  entidad por la que buscar
	 * @return List<Prestamo> entidades coincidentes
	 */
	List<Prestamo> findByUsuario(Usuario usuario);

}
