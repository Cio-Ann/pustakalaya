package cgr.cgfsdam.pustakalaya.service.funds.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.repository.funds.AutorRepository;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;

/**
 * Implementación del servicio de la entidad Autor.
 *
 * @author CGR-Casa
 */
@Service("autorService")
public class AutorServiceImpl implements AutorService {

	@Autowired
	AutorRepository autorRepository;

	@Override
	public List<Autor> findByNombreAllIgnoreCase(String nombre) {

		return autorRepository.findByNombreAllIgnoreCase(nombre);
	}

	@Override
	public List<Autor> findByNombreContainingAllIgnoreCase(String nombre) {

		return autorRepository.findByNombreContainingAllIgnoreCase(nombre);
	}

	@Override
	public List<Autor> findByApellidosAllIgnoreCase(String apellidos) {

		return autorRepository.findByApellidosAllIgnoreCase(apellidos);
	}

	@Override
	public List<Autor> findByApellidosContainingAllIgnoreCase(String apellidos) {

		return autorRepository.findByApellidosContainingAllIgnoreCase(apellidos);
	}

	@Override
	public List<Autor> findByNombreAndApellidosAllIgnoreCase(String nombre, String apellidos) {

		return autorRepository.findByNombreAndApellidosAllIgnoreCase(nombre, apellidos);

	}

	@Override
	public List<Autor> findByNombreContainingAndApellidosContainingAllIgnoreCase(String nombre, String apellidos) {

		return autorRepository.findByNombreContainingAndApellidosContainingAllIgnoreCase(nombre, apellidos);
	}

	@Override
	public Autor save(Autor autor) {

		return autorRepository.save(autor);

	}

	@Override
	public List<Autor> findAll() {

		return autorRepository.findAll();
	}

	@Override
	public Autor findById(Long idAutor) {

		return autorRepository.findOne(idAutor);
	}

	@Override
	public void delete(Autor autor) {

		autorRepository.delete(autor);
	}

	@Override
	public Long countResourcesByAutor(Autor autor) {

		return autorRepository.countResourcesByAutor(autor);
	}

}
