package cgr.cgfsdam.pustakalaya.repository.funds;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.funds.Genero;

@Repository("generoRepository")
public interface GeneroRepository extends JpaRepository<Genero, Long> {

	/**
	 * Busca un genero por su nombre exacto.
	 * 
	 * @param nombre String nombre del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreIgnoreCase(String nombre);

	/**
	 * Busca un genero por su nombre y descripción
	 * 
	 * @param nombre String nombre del genero a buscar
	 * @param descripcion String descripción del genero a buscar
	 * @return List<Genero> generos coincidentes
	 */
	List<Genero> findByNombreAndByDescripcionAllIgnoreCase(String nombre, String descripcion);

}
