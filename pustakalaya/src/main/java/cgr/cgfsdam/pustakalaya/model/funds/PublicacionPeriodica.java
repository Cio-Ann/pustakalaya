package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Clase que representa la especialización de recurso Publicación periodica.
 * 
 * @author CGR-Casa
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_recurso")
public class PublicacionPeriodica extends Recurso {

	/**
	 * Número de publicación.
	 */
	private String numero;

	/**
	 * Volumen de publicación.
	 */
	private String volumen;

	/**
	 * Número de páginas.
	 */
	private String numPaginas;

	/**
	 * ISSN número internacional normalizado de publicaciones seriadas.
	 */
	private String ISSN;

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
	 * @return the volumen
	 */
	public String getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen
	 *            the volumen to set
	 */
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return the numPaginas
	 */
	public String getNumPaginas() {
		return numPaginas;
	}

	/**
	 * @param numPaginas
	 *            the numPaginas to set
	 */
	public void setNumPaginas(String numPaginas) {
		this.numPaginas = numPaginas;
	}

	/**
	 * @return the iSSN
	 */
	public String getISSN() {
		return ISSN;
	}

	/**
	 * @param iSSN
	 *            the iSSN to set
	 */
	public void setISSN(String ISSN) {
		this.ISSN = ISSN;
	}

	/**
	 * Constructor por defecto.
	 */
	public PublicacionPeriodica() {
	}

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
	 * @param numero
	 * @param volumen
	 * @param numPaginas
	 * @param ISSN
	 */
	public PublicacionPeriodica(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			Set<Etiqueta> etiquetas, Set<Comentario> comentarios, String numero, String volumen, String numPaginas,
			String ISSN) {
		super(titulo, genero, fechaPublicacion, idioma, autores, etiquetas, comentarios);
		this.numero = numero;
		this.volumen = volumen;
		this.numPaginas = numPaginas;
		this.ISSN = ISSN;
	}

	@Override
	public String toString() {
		return "PublicacionPeriodica [" + super.toString() + ", numero=" + numero + ", volumen=" + volumen
				+ ", numPaginas=" + numPaginas + ", ISSN=" + ISSN + "]";
	}

}
