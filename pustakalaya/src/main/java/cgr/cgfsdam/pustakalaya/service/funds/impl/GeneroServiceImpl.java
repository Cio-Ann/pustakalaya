package cgr.cgfsdam.pustakalaya.service.funds.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.repository.funds.GeneroRepository;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;

/**
 * Implementación del servicio de la entidad Genero.
 *
 * @author CGR-Casa
 */
@Service("generoService")
public class GeneroServiceImpl implements GeneroService {
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Override
	public List<Genero> findByNombreIgnoreCase(String nombre) {
		return generoRepository.findByNombreIgnoreCase(nombre);
	}

	@Override
	public List<Genero> findByNombreAndByDescripcionAllIgnoreCase(String nombre, String descripcion) {
		return generoRepository.findByNombreAndByDescripcionAllIgnoreCase(nombre, descripcion);
	}

	@Override
	public void save(Genero genero) {
		generoRepository.save(genero);
	}

	@Override
	public List<Genero> findAll() {
		return generoRepository.findAll();
	}

	@Override
	public Genero findById(Long idGenero) {
		return generoRepository.findOne(idGenero);
	}

}
