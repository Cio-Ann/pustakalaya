package cgr.cgfsdam.pustakalaya.model.loans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que representa los tipos de sanción que se pueden aplicar
 * 
 * @author CGR-Casa
 */
@Entity
public class TipoSancion {

	/**
	 * Identificador de la sancion.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_sancion")
	private Long idTipoSancion;

	/**
	 * Literal con el que se conoce la sanción
	 */
	private String denominacion;

	/**
	 * Descripción de los motivos de la sanción y en qué consiste.
	 */
	@Column(length = 512, columnDefinition = "TEXT")
	private String descripcion;

	/**
	 * Duración de la sanción en días.
	 * 
	 * -1 indica que la duración de la sanción es indefinida. Requerirá de
	 * intervención del administrador para levantar la sanción.
	 */
	private int duracion;

	/**
	 * @return the idTipoSancion
	 */
	public Long getIdTipoSancion() {
		return idTipoSancion;
	}

	/**
	 * @param idTipoSancion
	 *            the idTipoSancion to set
	 */
	public void setIdTipoSancion(Long idTipoSancion) {
		this.idTipoSancion = idTipoSancion;
	}

	/**
	 * @return the denominacion
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * @param denominacion
	 *            the denominacion to set
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
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
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Constructor por defecto
	 */
	public TipoSancion() {
	}

	/**
	 * Constructor por parámetros
	 * 
	 * @param denominacion
	 *            denominación de la sanción
	 * @param descripcion
	 *            descripción de la sanción
	 * @param duracion
	 *            duración en dias de la sanción
	 */
	public TipoSancion(String denominacion, String descripcion, int duracion) {
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "TipoSancion [idTipoSancion=" + idTipoSancion + ", denominacion=" + denominacion + ", descripcion="
				+ descripcion + ", duracion=" + duracion + "]";
	}

}
