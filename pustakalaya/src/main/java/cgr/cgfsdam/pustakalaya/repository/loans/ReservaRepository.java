package cgr.cgfsdam.pustakalaya.repository.loans;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Interface del repositorio de la entidad Reserva.
 *
 * @author CGR-Casa 
 */
@Repository("reservaRepository")
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	/**
	 * Devuelve un listado de reservas relacionadas con el recurso.
	 * 
	 * @param recurso Recurso entidad de busqueda
	 * @return List<Reserva> listado de entidades coincidentes
	 */
	List<Reserva> findByRecurso(Recurso recurso);

	/**
	 * Devuelve un listado de reservas relacionadas con el usuario.
	 * 
	 * @param usuario Usuario entidad de busqueda
	 * @return List<Reserva> listado de entidades coincidentes
	 */
	List<Reserva> findByUsuario(Usuario usuario);

	/**
	 * Devuelve una reserva dados un recurso, un usuario y un estado.
	 * 
	 * @param recurso
	 * @param usuario
	 * @param estadoReserva
	 * @return
	 */
	Reserva findByRecursoAndUsuarioAndEstadoReserva(Recurso recurso, Usuario usuario, EstadoReservaEnum estadoReserva);

}
