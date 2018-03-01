package cgr.cgfsdam.pustakalaya.model.funds;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase que representa la entidad Ejemplar.
 * 
 * @author CGR-Casa
 */
@Entity
public class Ejemplar {

	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ejemplar")
	private Long idEjemplar;

	/**
	 * Recurso del que es copia.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_recurso")
	private Recurso recurso;

	/**
	 * Estado del ejemplar.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	private Estado estado;

	/**
	 * @return the idEjemplar
	 */
	public Long getIdEjemplar() {
		return idEjemplar;
	}

	/**
	 * @param idEjemplar the idEjemplar to set
	 */
	public void setIdEjemplar(Long idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	/**
	 * @return the recurso
	 */
	public Recurso getRecurso() {
		return recurso;
	}

	/**
	 * @param recurso the recurso to set
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Constructor por defecto.
	 */
	public Ejemplar() { }

	/**
	 * Constructor por parámetros.
	 * 
	 * @param recurso
	 * @param estado
	 */
	public Ejemplar(Recurso recurso, Estado estado) {
		this.recurso = recurso;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Ejemplar [idEjemplar=" + idEjemplar + ", recurso=" + recurso + ", estado=" + estado + "]";
	}

}
