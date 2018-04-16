package cgr.cgfsdam.pustakalaya.service.users.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.repository.users.TipoDocumentoRepository;
import cgr.cgfsdam.pustakalaya.service.users.TipoDocumentoService;

/**
 * Implementación del servicio de TipoDocumento.
 *
 * @author CGR-Casa
 */
@Service("tipoDocumentoService")
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	/**
	 * Repositorio de TipoDocumento.
	 */
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public TipoDocumento findByNombre(String nombre) {

		return tipoDocumentoRepository.findByNombre(nombre);
	}

	@Override
	public List<TipoDocumento> findAll() {

		return tipoDocumentoRepository.findAll();
	}

	@Override
	public List<String> findAllNombres() {

		List<TipoDocumento> tiposDocumento = tipoDocumentoRepository.findAll();

		List<String> allNames = tiposDocumento.stream().map(TipoDocumento::getNombre).collect(Collectors.toList());

		return allNames;
	}
}
