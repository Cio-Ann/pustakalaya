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

import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Clase que representa la relación prestamo.
 *
 * @author CGR-Casa
 */
@Entity
public class Prestamo {

	/**
	 * Id del préstamo
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_prestamo")
	private Long idPrestamo;

	/**
	 * Fecha de inicio del préstamo.
	 */
	@Temporal(TemporalType.DATE)
	private Date fechaPrestamo;

	/**
	 * Fecha límite de devolución del préstamo.
	 */
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	/**
	 * Fecha real de devolución del préstamo.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrega;

	/**
	 * Usuario al que se le ha prestado el recurso.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/**
	 * Ejemplar prestado.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_ejemplar")
	private Ejemplar ejemplar;

	/**
	 * Reserva que originó el prestamo (solo si el libro se prestó tras reserva).
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_reserva")
	private Reserva reserva;

	/**
	 * @return the idPrestamo
	 */
	public Long getIdPrestamo() {
		return idPrestamo;
	}

	/**
	 * @param idPrestamo
	 *            the idPrestamo to set
	 */
	public void setIdPrestamo(Long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	/**
	 * @return the fechaPrestamo
	 */
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	/**
	 * @param fechaPrestamo
	 *            the fechaPrestamo to set
	 */
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	/**
	 * @param ejemplar
	 *            the ejemplar to set
	 */
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	/**
	 * @return the reserva
	 */
	public Reserva getReserva() {
		return reserva;
	}

	/**
	 * @param reserva
	 *            the reserva to set
	 */
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	/**
	 * Constructor por defecto.
	 */
	public Prestamo() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param fechaPrestamo
	 * @param fechaVencimiento
	 * @param fechaEntrega
	 * @param usuario
	 * @param ejemplar
	 * @param reserva
	 */
	public Prestamo(Date fechaPrestamo, Date fechaVencimiento, Date fechaEntrega, Usuario usuario, Ejemplar ejemplar,
			Reserva reserva) {
		this.fechaPrestamo = fechaPrestamo;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaEntrega = fechaEntrega;
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", fechaPrestamo=" + fechaPrestamo + ", fechaVencimiento="
				+ fechaVencimiento + ", fechaEntrega=" + fechaEntrega + ", usuario=" + usuario + ", ejemplar="
				+ ejemplar + ", reserva=" + reserva + "]";
	}

}
