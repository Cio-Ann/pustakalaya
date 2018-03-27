package cgr.cgfsdam.pustakalaya.model.funds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que representa el atributo compuesto Género de la entidad Recurso. 
 *
 * @author CGR-Casa
 */
@Entity
public class Genero {
	
	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_genero")
	private Long idGenero;
	
	/**
	 * Nombre.
	 */
	@Column(nullable=false)
	private String nombre;
	
	private String descripcion;
	
	/**
	 * @return the idGenero
	 */
	public Long getIdGenero() {
		return idGenero;
	}

	/**
	 * @param idGenero the idGenero to set
	 */
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Constructor por defecto.
	 */
	public Genero() { }

	/**
	 * Constructor por parámetros. 
	 * 
	 * @param nombre
	 * @param tipoRecurso
	 */
	public Genero(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
}
