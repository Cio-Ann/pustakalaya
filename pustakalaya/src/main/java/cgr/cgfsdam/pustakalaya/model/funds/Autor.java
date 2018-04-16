package cgr.cgfsdam.pustakalaya.model.funds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que represente a cada uno de los elementos del atributo múltiple
 * Autores de la entidad Recurso.
 * 
 * @author CGR-Casa
 */
@Entity
public class Autor {

	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_autor")
	private Long idAutor;

	/**
	 * Nombre del autor.
	 */
	private String nombre;

	/**
	 * Apellidos del autor.
	 */
	private String apellidos;

	/**
	 * @return the idAutor
	 */
	public Long getIdAutor() {

		return idAutor;
	}

	/**
	 * @param idAutor
	 *            the idAutor to set
	 */
	public void setIdAutor(Long idAutor) {

		this.idAutor = idAutor;
	}

	// /**
	// * @return the recursos
	// */
	// public Set<Recurso> getRecursos() {
	// return recursos;
	// }
	//
	// /**
	// * @param recursos
	// * the recursos to set
	// */
	// public void setRecursos(Set<Recurso> recursos) {
	// this.recursos = recursos;
	// }

	/**
	 * @return the nombre
	 */
	public String getNombre() {

		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {

		return apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(String apellidos) {

		this.apellidos = apellidos;
	}

	/**
	 * Constructor por defecto.
	 */
	public Autor() {

	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param recursos
	 * @param nombre
	 * @param apellidos
	 */
	public Autor(
			// Set<Recurso> recursos,
			String nombre, String apellidos) {

		// this.recursos = recursos;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {

		String ret = "Autor [idAutor=" + idAutor;
		// if (recursos != null) {
		// + ", recursos=";
		// ret += recursos;
		// } else {
		// ret += " ... ";
		// }
		ret += ", nombre=" + nombre + ", apellidos=" + apellidos + "]";

		return ret;
	}

}
