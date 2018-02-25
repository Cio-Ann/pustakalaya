package cgr.cgfsdam.pustakalaya.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad que representa una dirección.
 * Se almacena en la tabla direccion de la base de datos.
 *
 * @author CGR-Casa
 */
@Entity
public class Direccion {
	/**
	 * Identificador de dirección.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_direccion")
	private Integer iddireccion;

	/**
	 * Tipo de via.
	 */
	private String tipoVia;

	/**
	 * Nombre de la via.
	 */
	private String via;

	/**
	 * Número.
	 */
	private String numero;

	/**
	 * Portal.
	 */
	private String portal;

	/**
	 * Escalera.
	 */
	private String escalera;

	/**
	 * Planta / piso.
	 */
	private String planta;

	/**
	 * Puerta.
	 */
	private String puerta;

	/**
	 * Municipio.
	 */
	private String municipio;

	/**
	 * Provincia.
	 */
	private String provincia;

	/**
	 * Código postal
	 */
	private String cp;

	/**
	 * @return the iddireccion
	 */
	public Integer getIddireccion() {
		return iddireccion;
	}

	/**
	 * @param iddireccion
	 *            the iddireccion to set
	 */
	public void setIddireccion(Integer iddireccion) {
		this.iddireccion = iddireccion;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @param tipoVia
	 *            the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @return the via
	 */
	public String getVia() {
		return via;
	}

	/**
	 * @param via
	 *            the via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the portal
	 */
	public String getPortal() {
		return portal;
	}

	/**
	 * @param portal
	 *            the portal to set
	 */
	public void setPortal(String portal) {
		this.portal = portal;
	}

	/**
	 * @return the escalera
	 */
	public String getEscalera() {
		return escalera;
	}

	/**
	 * @param escalera
	 *            the escalera to set
	 */
	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	/**
	 * @return the planta
	 */
	public String getPlanta() {
		return planta;
	}

	/**
	 * @param planta
	 *            the planta to set
	 */
	public void setPlanta(String planta) {
		this.planta = planta;
	}

	/**
	 * @return the puerta
	 */
	public String getPuerta() {
		return puerta;
	}

	/**
	 * @param puerta
	 *            the puerta to set
	 */
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * Constructor por defecto.
	 */
	public Direccion() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param tipoVia
	 * @param via
	 * @param numero
	 * @param portal
	 * @param escalera
	 * @param planta
	 * @param puerta
	 * @param municipio
	 * @param provincia
	 * @param cp
	 */
	public Direccion(String tipoVia, String via, String numero, String portal, String escalera, String planta,
			String puerta, String municipio, String provincia, String cp) {
		this.tipoVia = tipoVia;
		this.via = via;
		this.numero = numero;
		this.portal = portal;
		this.escalera = escalera;
		this.planta = planta;
		this.puerta = puerta;
		this.municipio = municipio;
		this.provincia = provincia;
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [iddireccion=" + iddireccion + ", tipoVia=" + tipoVia + ", via=" + via + ", numero=" + numero
				+ ", portal=" + portal + ", escalera=" + escalera + ", planta=" + planta + ", puerta=" + puerta
				+ ", municipio=" + municipio + ", provincia=" + provincia + ", cp=" + cp + "]";
	}

}
