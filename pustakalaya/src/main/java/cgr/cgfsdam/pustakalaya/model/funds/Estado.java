package cgr.cgfsdam.pustakalaya.model.funds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que representa el atributo compuesto Estado de la entidad Ejemplar.
 *
 * @author CGR-Casa
 */
@Entity
public class Estado {

	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_estado")
	private Long idEstado;
	
	/**
	 * Nombre.
	 */
	@Column(unique=true)
	private String nombre;

	/**
	 * Descripción.
	 */
	private String descripcion;

	/**
	 * @return the idEstado
	 */
	public Long getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
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

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Constructor por defecto.
	 */
	public Estado() { }

	/**
	 * Constructor por parámetros.
	 * 
	 * @param nombre
	 * @param descripcion
	 */
	public Estado(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
