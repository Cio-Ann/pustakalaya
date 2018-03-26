package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa la entidad Recurso.
 *
 * @author CGR-Casa
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Recurso {

	/**
	 * Identificador del recurso.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_recurso")
	private Long idRecurso;

	/**
	 * Título.
	 */
	@Column(nullable = false)
	private String titulo;

	/**
	 * Genero al que pertenece el recurso.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_genero")
	private Genero genero;

	/**
	 * Fecha de publicación.
	 */
	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;

	/**
	 * Idioma principal del recurso.
	 */
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_idioma")
	private Idioma idioma;

	/**
	 * Conjunto de autores participantes en la creación del recurso.
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "recursos")
	private Set<Autor> autores;

	/**
	 * Número de páginas.
	 */
	private int numPaginas;

	/**
	 * ISBN Número internacional normalizado de libro.
	 */
	private String ISBN;

	/**
	 * @return the idRecurso
	 */
	public Long getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso
	 *            the idRecurso to set
	 */
	public void setIdRecurso(Long idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion
	 *            the fechaPublicacion to set
	 */
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the idioma
	 */
	public Idioma getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the autores
	 */
	public Set<Autor> getAutores() {
		return autores;
	}

	/**
	 * @param autores
	 *            the autores to set
	 */
	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	/**
	 * Adds autor to autores set.
	 * 
	 * @param autor
	 */
	public void addAutores(Autor autor) {
		this.autores.add(autor);
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN
	 *            the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the numPaginas
	 */
	public int getNumPaginas() {
		return numPaginas;
	}

	/**
	 * @param numPaginas
	 *            the numPaginas to set
	 */
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	/**
	 * Constructor por defecto.
	 */
	public Recurso() {
	}

	/**
	 * Constructor por parámetros.
	 * 
	 * @param titulo
	 * @param genero
	 * @param fechaPublicacion
	 * @param idioma
	 * @param autores
	 * @param numPaginas
	 * @param iSBN
	 */
	public Recurso(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			int numPaginas, String iSBN) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.fechaPublicacion = fechaPublicacion;
		this.idioma = idioma;
		this.autores = autores;
		this.numPaginas = numPaginas;
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Recurso [idRecurso=" + idRecurso + ", titulo=" + titulo + ", genero=" + genero + ", fechaPublicacion="
				+ fechaPublicacion + ", idioma=" + idioma + ", autores=" + autores + ", numPaginas=" + numPaginas
				+ ", ISBN=" + ISBN + "]";
	}
}
