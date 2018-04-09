package cgr.cgfsdam.pustakalaya.service.funds.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.repository.funds.IdiomaRepository;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;

/**
 * Implementación del servicio de la entidad Idioma.
 *
 * @author CGR-Casa
 */
@Service("idiomaService")
public class IdiomaServiceImpl implements IdiomaService {

	@Autowired
	IdiomaRepository idiomaRepository;
	
	@Override
	public Idioma findByNombreIgnoreCase(String nombre) {
		return idiomaRepository.findByNombreIgnoreCase(nombre);
	}

	@Override
	public void save(Idioma idioma) {
		idiomaRepository.save(idioma);
	}

	@Override
	public List<Idioma> findAll() {
		return idiomaRepository.findAll();
	}

	@Override
	public void delete(Idioma idioma) {
		idiomaRepository.delete(idioma);
	}

	@Override
	public Long countResourcesByIdioma(Idioma idioma) {
		return idiomaRepository.countResourcesByIdioma(idioma);
	}

}
