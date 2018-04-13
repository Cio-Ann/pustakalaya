package cgr.cgfsdam.pustakalaya.repository.loans.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;

public class ReservaSpecifications {

	public static Specification<Reserva> findByFormData(EstadoReservaEnum estadoReserva, String idUsuario, String titulo, String isbn) {

		return new Specification<Reserva>() {
			public Predicate toPredicate(Root<Reserva> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<>();

				if (estadoReserva != null) {
					predicates.add(builder.equal(root.get("estadoReserva"), estadoReserva));
				}

				// documento (busqueda exacta)
				if (!MyUtils.isEmptyString(titulo)) {
					predicates.add(builder.equal((root.get("usuario.documento")), idUsuario.toUpperCase()));
				}

				// titulo (contiene)
				if (!MyUtils.isEmptyString(titulo)) {
					predicates.add(builder.like(builder.lower(root.get("titulo")), ("%" + titulo + "%").toLowerCase()));
				}
				
				// isbn (busqueda exacta)
				if (!MyUtils.isEmptyString(isbn)) {
					predicates.add(builder.equal((root.get("recurso.isbn")), isbn.toUpperCase()));
				}


				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
