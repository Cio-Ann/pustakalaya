package cgr.cgfsdam.pustakalaya.service.loans.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.loans.ReservaRepository;
import cgr.cgfsdam.pustakalaya.service.loans.ReservaService;

/**
 * Implementación del servicio de la entidad Reserva
 *
 * @author CGR-Casa
 */
@Service("reservaService")
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	ReservaRepository reservaRepository;
	
	@Override
	public Reserva find(Long idReserva) {
		
		return reservaRepository.findOne(idReserva);
	}

	@Override
	public List<Reserva> findAll() {

		return reservaRepository.findAll();
	}

	@Override
	public List<Reserva> findByRecurso(Recurso recurso) {

		return reservaRepository.findByRecurso(recurso);
	}

	@Override
	public List<Reserva> findByUsuario(Usuario usuario) {

		return reservaRepository.findByUsuario(usuario);
	}

	@Override
	public Reserva findByRecursoAndUsuarioAndEstadoReserva(Recurso recurso, Usuario usuario,
			EstadoReservaEnum estadoReserva) {

		return reservaRepository.findByRecursoAndUsuarioAndEstadoReserva(recurso, usuario, estadoReserva);
	}

	@Override
	public void save(Reserva reserva) {

		reservaRepository.save(reserva);
	}

	@Override
	public void delete(Reserva reserva) {

		reservaRepository.delete(reserva);
	}

}
