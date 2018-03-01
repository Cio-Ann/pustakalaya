package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa a la especialización de recurso Musica.
 * 
 * @author CGR-Casa
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_recurso")
public class Musica extends Recurso {

	/**
	 * Duración del recurso.
	 */
	@Temporal(TemporalType.TIME)
	private Date duracion;

	/**
	 * ISMN Número internacional normalizado de música
	 */
	private String ISMN;

	/**
	 * @return the duracion
	 */
	public Date getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(Date duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the iSMN
	 */
	public String getISMN() {
		return ISMN;
	}

	/**
	 * @param iSMN
	 *            the iSMN to set
	 */
	public void setISMN(String iSMN) {
		ISMN = iSMN;
	}

	/**
	 * Constructor por defecto.
	 */
	public Musica() { }

	/**
	 * Constructor por parámetros.
	 * 
	 * @param titulo
	 * @param genero
	 * @param fechaPublicacion
	 * @param idioma
	 * @param autores
	 * @param etiquetas
	 * @param comentarios
	 * @param duracion
	 * @param ISMN
	 */
	public Musica(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			Set<Etiqueta> etiquetas, Set<Comentario> comentarios, Date duracion, String ISMN) {
		super(titulo, genero, fechaPublicacion, idioma, autores, etiquetas, comentarios);
		this.duracion = duracion;
		this.ISMN = ISMN;
	}

	@Override
	public String toString() {
		return "Musica [" + super.toString() + ", duracion=" + duracion + ", ISMN=" + ISMN + "]";
	}

}
