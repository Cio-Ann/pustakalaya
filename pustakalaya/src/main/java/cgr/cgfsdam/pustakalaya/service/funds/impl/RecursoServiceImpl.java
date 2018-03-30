package cgr.cgfsdam.pustakalaya.service.funds.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.repository.funds.RecursoRepository;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;

/**
 * Implementación del servicio de la entidad recurso.
 *
 * @author CGR-Casa
 */
@Service("recursoService")
public class RecursoServiceImpl implements RecursoService {
	
	@Autowired
	private RecursoRepository recursoRepository;

	@Override
	public List<Recurso> findByTitulo(String titulo) {
		return recursoRepository.findByTitulo(titulo);
	}

	@Override
	public List<Recurso> findByTituloContainingIgnoreCase(String titulo) {
		return recursoRepository.findByTituloContainingIgnoreCase(titulo);
	}

	@Override
	public Recurso findByISBN(String ISBN) {
		return recursoRepository.findByISBN(ISBN);
	}

	@Override
	public List<Recurso> findByAutor(Autor autor) {
		return recursoRepository.findByAutor(autor);
	}

	@Override
	public List<Recurso> findByFormData(String titulo, String ISBN, Autor autor, Genero genero, Idioma idioma,
			Date desde, Date hasta) {
		return recursoRepository.findByFormData(titulo, ISBN, autor, genero, idioma, desde, hasta);
	}

	@Override
	public Recurso findById(Long idRecurso) {
		return recursoRepository.findOne(idRecurso)
				;
	}

	@Override
	public void save(Recurso recurso) {
		recursoRepository.save(recurso);
	}

	@Override
	public void delete(Recurso recurso) {
		recursoRepository.delete(recurso);
		
	}

}
