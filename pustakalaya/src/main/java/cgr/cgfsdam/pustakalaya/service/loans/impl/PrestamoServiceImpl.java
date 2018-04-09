package cgr.cgfsdam.pustakalaya.service.loans.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.loans.PrestamoRepository;
import cgr.cgfsdam.pustakalaya.service.loans.PrestamoService;

/**
 * Implementación del servicio de la entidad Prestamo
 *
 * @author CGR-Casa
 */
@Service("prestamoService")
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Override
	public Prestamo find(Long idPrestamo) {
		return prestamoRepository.findOne(idPrestamo);
	}

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public List<Prestamo> findByUsuario(Usuario usuario) {
		return prestamoRepository.findByUsuario(usuario);
	}

	@Override
	public void save(Prestamo prestamo) {

		prestamoRepository.save(prestamo);
	}

	@Override
	public void delete(Prestamo prestamo) {

		prestamoRepository.delete(prestamo);
	}

}
