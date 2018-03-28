package cgr.cgfsdam.pustakalaya.service.funds.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.repository.funds.EjemplarRepository;
import cgr.cgfsdam.pustakalaya.service.funds.EjemplarService;

/**
 * Implementación del servicio de la entidad Ejemplar.
 *
 * @author CGR-Casa
 */
@Service("ejemplarService")
public class EjemplarServiceImpl implements EjemplarService {

	@Autowired
	EjemplarRepository ejemplarRepository;
	
	@Override
	public void save(Ejemplar ejemplar) {
		ejemplarRepository.save(ejemplar);
	}

	@Override
	public Ejemplar findByCodigo(String codigo) {
		return ejemplarRepository.findByCodigo(codigo);
	}

}
