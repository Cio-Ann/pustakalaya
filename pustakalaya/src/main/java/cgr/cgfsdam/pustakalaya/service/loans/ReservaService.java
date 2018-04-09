package cgr.cgfsdam.pustakalaya.service.loans;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Interfaz del servicio de la entidad Reserva.
 *
 * @author CGR-Casa
 */
public interface ReservaService {

	/**
	 * Recupera una entidad a partir de su id
	 * 
	 * @param idReserva Long id de la reserva
	 * @return Reserva entidad buscada
	 */
	Reserva find(Long idReserva);

	/**
	 * Lista todas las entidades.
	 * 
	 * @return List<Reserva> todas las reservas
	 */
	List<Reserva> findAll();

	/**
	 * Lista todas las entidades relacionada con un recurso.
	 * 
	 * @param recurso Recurso entidad por la que buscar.
	 * @return List<Reserva> todas las reservas relacionadas.
	 */
	List<Reserva> findByRecurso(Recurso recurso);

	/**
	 * Lista  todas las entidades relacionada con un usuario.
	 * 
	 * @param usuario Usuario entidad por la que buscar.
	 * @return List<Reserva> todas las reservas relacionadas.
	 */
	List<Reserva> findByUsuario(Usuario usuario);

	/**
	 * Busca una reserva por recurso, usuario y estado.
	 * 
	 * @param recurso Recurso entidad recurso de busqueda
	 * @param usuario Usuario entidad autor de búsqueda
	 * @param estadoReserva EstadoReservaEnum valor de estado de busqueda
	 * @return Reserva entidad coincidente. 
	 */
	Reserva findByRecursoAndUsuarioAndEstadoReserva(Recurso recurso, Usuario usuario, EstadoReservaEnum estadoReserva);

	/**
	 * Guarda una reserva.
	 * 
	 * @param reserva
	 */
	void save(Reserva reserva);

	/**
	 * Elimina una reserva.
	 * 
	 * @param reserva
	 */
	void delete(Reserva reserva);

}
