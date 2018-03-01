package cgr.cgfsdam.pustakalaya.model.funds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase que representa cada uno de los elementos del atributo múltiple
 * etiquetas de la entidad Recurso.
 *
 * @author CGR-Casa
 */
@Entity
public class Etiqueta {

	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_etiqueta")
	private Long idEtiqueta;

	/**
	 * Valor.
	 */
	private String valor;

	/**
	 * @return the idEtiqueta
	 */
	public Long getIdEtiqueta() {
		return idEtiqueta;
	}

	/**
	 * @param idEtiqueta
	 *            the idEtiqueta to set
	 */
	public void setIdEtiqueta(Long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Constructor por defecto.
	 */
	public Etiqueta() {
		super();
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param valor
	 */
	public Etiqueta(String valor) {
		super();
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Etiqueta [idEtiqueta=" + idEtiqueta + ", valor=" + valor + "]";
	}

}
