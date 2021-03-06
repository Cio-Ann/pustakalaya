package cgr.cgfsdam.pustakalaya.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad que representa un tipo de documento.
 * Se almacena en la tabla tipo_documento
 *
 * @author CGR-Casa
 */
@Entity
public class TipoDocumento {

	/**
	 * Identificador del tipo de documento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_documento")
	private long idTipoDocumento;

	/**
	 * Nombre del tipo de documento.
	 */
	@Column(nullable = false)
	private String nombre;

	/**
	 * Descripción del tipo de documento.
	 */
	private String descripcion;

	/**
	 * @return the idTipoDocumento
	 */
	public long getIdTipoDocumento() {

		return idTipoDocumento;
	}

	/**
	 * @param idTipoDocumento
	 *            the idTipoDocumento to set
	 */
	public void setIdTipoDocumento(long idTipoDocumento) {

		this.idTipoDocumento = idTipoDocumento;
	}

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
	 * @return the descripcion
	 */
	public String getDescripcion() {

		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;
	}

	/**
	 * Constructor por defecto.
	 */
	public TipoDocumento() {

	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param nombre
	 * @param descripcion
	 */
	public TipoDocumento(String nombre, String descripcion) {

		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {

		return "TipoDocumento [idTipoDocumento=" + idTipoDocumento + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
