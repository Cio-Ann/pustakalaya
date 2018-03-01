package cgr.cgfsdam.pustakalaya.model.loans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Clase que representa la entidad sanción.
 *
 * @author CGR-Casa
 */
@Entity
public class Sancion {

	/**
	 * Identificador de la sancion.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_sancion")
	private Long idSancion;

	/**
	 * Usuario sancionado.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/**
	 * Prestamo que originó la sanción.
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_prestamo")
	private Prestamo prestamoOrigen;

	/**
	 * Tipo de sanción.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo_sancion")
	private TipoSancion tipoSancion;

	/**
	 * Fecha en la que se inicia la sanción.
	 */
	@Temporal(TemporalType.DATE)
	private Date inicioSancion;
	/**
	 * Fecha en la que se levanta la sanción.
	 * 
	 * Si la fecha fin de sanción es 0 o nula, se considera sanción indefinida (pej,
	 * hasta que devuelva un recurso)
	 */
	@Temporal(TemporalType.DATE)
	private Date finSancion;

	/**
	 * @return the idSancion
	 */
	public Long getIdSancion() {
		return idSancion;
	}

	/**
	 * @param idSancion
	 *            the idSancion to set
	 */
	public void setIdSancion(Long idSancion) {
		this.idSancion = idSancion;
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
	 * @return the prestamoOrigen
	 */
	public Prestamo getPrestamoOrigen() {
		return prestamoOrigen;
	}

	/**
	 * @param prestamoOrigen
	 *            the prestamoOrigen to set
	 */
	public void setPrestamoOrigen(Prestamo prestamoOrigen) {
		this.prestamoOrigen = prestamoOrigen;
	}

	/**
	 * @return the tipoSancion
	 */
	public TipoSancion getTipoSancion() {
		return tipoSancion;
	}

	/**
	 * @param tipoSancion
	 *            the tipoSancion to set
	 */
	public void setTipoSancion(TipoSancion tipoSancion) {
		this.tipoSancion = tipoSancion;
	}

	/**
	 * @return the inicioSancion
	 */
	public Date getInicioSancion() {
		return inicioSancion;
	}

	/**
	 * @param inicioSancion
	 *            the inicioSancion to set
	 */
	public void setInicioSancion(Date inicioSancion) {
		this.inicioSancion = inicioSancion;
	}

	/**
	 * @return the finSancion
	 */
	public Date getFinSancion() {
		return finSancion;
	}

	/**
	 * @param finSancion
	 *            the finSancion to set
	 */
	public void setFinSancion(Date finSancion) {
		this.finSancion = finSancion;
	}

	/**
	 * Constructor por defecto.
	 */
	public Sancion() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param usuario
	 * @param prestamoOrigen
	 * @param tipoSancion
	 * @param inicioSancion
	 * @param finSancion
	 */
	public Sancion(Usuario usuario, Prestamo prestamoOrigen, TipoSancion tipoSancion, Date inicioSancion,
			Date finSancion) {
		this.usuario = usuario;
		this.prestamoOrigen = prestamoOrigen;
		this.tipoSancion = tipoSancion;
		this.inicioSancion = inicioSancion;
		this.finSancion = finSancion;
	}

	@Override
	public String toString() {
		return "Sancion [idSancion=" + idSancion + ", usuario=" + usuario + ", prestamoOrigen=" + prestamoOrigen
				+ ", tipoSancion=" + tipoSancion + ", inicioSancion=" + inicioSancion + ", finSancion=" + finSancion
				+ "]";
	}

}
