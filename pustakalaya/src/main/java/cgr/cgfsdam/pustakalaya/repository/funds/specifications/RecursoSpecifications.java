package cgr.cgfsdam.pustakalaya.repository.funds.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;

public class RecursoSpecifications {

	public static Specification<Recurso> findByFormData(String titulo, String isbn, Autor autor, Genero genero,
			Idioma idioma, Date desde, Date hasta) {
		return new Specification<Recurso>() {
			public Predicate toPredicate(Root<Recurso> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				List<Predicate> predicates = new ArrayList<>();

				// titulo
				if (!MyUtils.isEmptyString(titulo)) {
					predicates.add(builder.like(builder.lower(root.get("titulo")), ("%" + titulo + "%").toLowerCase()));
				}
				// isbn
				if (!MyUtils.isEmptyString(isbn)) {
					predicates.add(builder.equal(root.get("isbn"), isbn));
				}

				// autor
				if (autor != null) {
					predicates.add(builder.isMember(autor, root.get("autores")));
				}

				// genero
				if (genero != null) {
					predicates.add(builder.isMember(genero, root.get("generos")));
				}
				// idioma
				if (idioma != null) {
					predicates.add(builder.equal(root.get("idioma"), idioma));
				}

				// fecha desde
				if (desde != null) {
					predicates.add(builder.greaterThanOrEqualTo(root.<Date>get("fechaPublicacion"), desde));
				}
				// fecha hasta
				if (hasta != null) {
					predicates.add(builder.lessThanOrEqualTo(root.<Date>get("fechaPublicacion"), hasta));
				}

				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
}
