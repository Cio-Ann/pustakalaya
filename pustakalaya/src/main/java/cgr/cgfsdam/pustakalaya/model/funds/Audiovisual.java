package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa la especialización Audiovisual de la entidad Recurso.
 * 
 * @author CGR-Casa
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_recurso")
public class Audiovisual extends Recurso {

	/**
	 * Duración de la obra.
	 */
	@Temporal(TemporalType.TIME)
	private Date duracion;

	/**
	 * ISAN Número internacional normalizado de obras audiovisuales.
	 */
	private String ISAN;

	/**
	 * @return the duracion
	 */
	public Date getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(Date duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the iSAN
	 */
	public String getISAN() {
		return ISAN;
	}

	/**
	 * @param iSAN the iSAN to set
	 */
	public void setISAN(String iSAN) {
		ISAN = iSAN;
	}

	/**
	 * Constructor por defecto.
	 */
	public Audiovisual() { }

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
	 * @param ISAN
	 */
	public Audiovisual(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			Set<Etiqueta> etiquetas, Set<Comentario> comentarios, Date duracion, String ISAN) {
		super(titulo, genero, fechaPublicacion, idioma, autores, etiquetas, comentarios);
		this.duracion = duracion;
		this.ISAN = ISAN;
	}

	@Override
	public String toString() {
		return "Audiovisual [" + super.toString() + ", duracion=" + duracion + ", ISAN=" + ISAN + "]";
	}

}
