package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Clase que representa cada uno de los elementos que compone el atributo
 * múltiple Comentarios de la entidad Recurso.
 * 
 * @author CGR-Casa
 */
@Entity
public class Comentario {

	/**
	 * Identificador.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comentario")
	private Long idComentario;

	/**
	 * Usuario que ha hecho el comentario.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/**
	 * Fecha de realización del comentario.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	/**
	 * Fecha de última modificación del comentario.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	/**
	 * Flag que indica si el comentario ha sido aprobado.
	 */
	private boolean aprobado;

	/**
	 * Texto que conforma el comentario.
	 */
	@Column(length = 512, columnDefinition = "TEXT")
	private String comentario;

	/**
	 * @return the idComentario
	 */
	public Long getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario
	 *            the idComentario to set
	 */
	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the aprobado
	 */
	public boolean isAprobado() {
		return aprobado;
	}

	/**
	 * @param aprobado
	 *            the aprobado to set
	 */
	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Constructor por defecto.
	 */
	public Comentario() { }

	/**
	 * Constructor por parámetros.
	 * 
	 * @param usuario
	 * @param fechaCreacion
	 * @param fechaModificacion
	 * @param aprobado
	 * @param comentario
	 */
	public Comentario(Usuario usuario, Date fechaCreacion, Date fechaModificacion, boolean aprobado,
			String comentario) {
		this.usuario = usuario;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.aprobado = aprobado;
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		String ret = "Comentario [idComentario=" + idComentario + ", usuario=";

		if (usuario != null) {
			ret += usuario.getId();
		} else {
			ret += "";
		}

		ret += ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", aprobado="
				+ aprobado + ", comentario=" + comentario + "]";

		return ret;
	}

}
