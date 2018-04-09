package cgr.cgfsdam.pustakalaya.service.loans;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

public interface PrestamoService {

	/**
	 * Recupera una entidad a partir de su id
	 * 
	 * @param idPrestamo Long id del prestamo
	 * @return Prestamo entidad buscada
	 */
	Prestamo find(Long idPrestamo);

	/**
	 * Lista todos los prestamos de base de datos.
	 * 
	 * @return List<Prestamo> todos los prestamos
	 */
	List<Prestamo> findAll();

	/**
	 * Lista todos los prestamos de un usuario.
	 * 
	 * @param usuario Usuario entidad por la que buscar
	 * @return List<Prestamo> todos los prestamos del usuario
	 */
	List<Prestamo> findByUsuario(Usuario usuario);

	/**
	 * Guarda un prestamo.
	 * 
	 * @param prestamo
	 */
	void save(Prestamo prestamo);

	/**
	 * Elimina el prestamo.
	 * 
	 * @param prestamo
	 */
	void delete(Prestamo prestamo);
}
