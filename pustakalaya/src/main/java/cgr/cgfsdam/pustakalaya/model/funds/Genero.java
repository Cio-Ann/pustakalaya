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
	
	/**
	 * Tipo de recurso al que pertenece el género.
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private TipoRecursoEnum tipoRecurso;

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

	/**
	 * @return the tipoRecurso
	 */
	public TipoRecursoEnum getTipoRecurso() {
		return tipoRecurso;
	}

	/**
	 * @param tipoRecurso the tipoRecurso to set
	 */
	public void setTipoRecurso(TipoRecursoEnum tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	/**
	 * Constructor por defecto.
	 */
	public Genero() {
		this.tipoRecurso = TipoRecursoEnum.DESCONOCIDO;
	}

	/**
	 * Constructor por parámetros. 
	 * 
	 * @param nombre
	 * @param tipoRecurso
	 */
	public Genero(String nombre, TipoRecursoEnum tipoRecurso) {
		this.nombre = nombre;
		this.tipoRecurso = tipoRecurso;
	}

	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nombre=" + nombre + ", tipoRecurso=" + tipoRecurso.getNombre() + "]";
	}
	
	
	
	
	
}
