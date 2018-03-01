package cgr.cgfsdam.pustakalaya.model.loans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Clase que representa la relación reserva.
 * 
 * @author CGR-Casa
 */
@Entity
public class Reserva {

	/**
	 * Identificador de la reserva.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_reserva")
	private Long idReserva;

	/**
	 * Usuario que realiza la reserva.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/**
	 * Ejemplar reservado.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_recurso")
	private Recurso recurso;

	/**
	 * Fecha de inicio de la reserva.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaReserva;

	/**
	 * Estado actual de la reserva. Puede tomar los valores de
	 * {@link EstadoReservaEnum}.
	 * 
	 * Puede tomar los siguientes valores {@link EstadoReservaEnum#UNKNOWN}
	 * {@link EstadoReservaEnum#WAITING} {@link EstadoReservaEnum#CONSUMED}
	 * {@link EstadoReservaEnum#USER_CANCELLED}
	 * {@link EstadoReservaEnum#ADMIN_CANCELLED}
	 */
	@Enumerated(EnumType.ORDINAL)
	private EstadoReservaEnum estadoReserva;

	/**
	 * @return the idReserva
	 */
	public Long getIdReserva() {
		return idReserva;
	}

	/**
	 * @param idReserva
	 *            the idReserva to set
	 */
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
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
	 * @return the ejemplar
	 */
	public Recurso getRecurso() {
		return recurso;
	}

	/**
	 * @param ejemplar
	 *            the ejemplar to set
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	/**
	 * @return the fechaReserva
	 */
	public Date getFechaReserva() {
		return fechaReserva;
	}

	/**
	 * @param fechaReserva
	 *            the fechaReserva to set
	 */
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * @return the estadoReserva
	 */
	public EstadoReservaEnum getEstadoReserva() {
		return estadoReserva;
	}

	/**
	 * @param estadoReserva
	 *            the estadoReserva to set
	 */
	public void setEstadoReserva(EstadoReservaEnum estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	/**
	 * Constructor por defecto.
	 */
	public Reserva() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param usuario
	 * @param ejemplar
	 * @param fechaReserva
	 * @param estadoReserva
	 */
	public Reserva(Usuario usuario, Recurso recurso, Date fechaReserva, EstadoReservaEnum estadoReserva) {
		this.usuario = usuario;
		this.recurso = recurso;
		this.fechaReserva = fechaReserva;
		this.estadoReserva = estadoReserva;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", usuario=" + usuario + ", recurso=" + recurso + ", fechaReserva="
				+ fechaReserva + ", estadoReserva=" + estadoReserva + "]";
	}

}
