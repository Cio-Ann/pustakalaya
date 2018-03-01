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
import javax.persistence.JoinTable;
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
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "recursos")
	private Set<Autor> autores;

	/**
	 * Conjunto de etiquetas que encajan con el recurso.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recurso_etiqueta", joinColumns = @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso"), inverseJoinColumns = @JoinColumn(name = "id_etiqueta", referencedColumnName = "id_etiqueta"))
	private Set<Etiqueta> etiquetas;

	/**
	 * Conjunto de comentarios del recurso.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recurso_comentario", joinColumns = @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso"), inverseJoinColumns = @JoinColumn(name = "id_comentario", referencedColumnName = "id_comentario"))
	private Set<Comentario> comentarios;

	/**
	 * @return the idRecurso
	 */
	public Long getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso the idRecurso to set
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
	 * @param titulo the titulo to set
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
	 * @param genero the genero to set
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
	 * @param fechaPublicacion the fechaPublicacion to set
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
	 * @param idioma the idioma to set
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
	 * @param autores the autores to set
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
	 * @return the etiquetas
	 */
	public Set<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	/**
	 * @param etiquetas the etiquetas to set
	 */
	public void setEtiquetas(Set<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}
	
	/**
	 * Adds etiqueta to etiquetas set.
	 * 
	 * @param etiqueta
	 */
	public void addEtiquetas(Etiqueta etiqueta) {
		this.etiquetas.add(etiqueta);
	}

	/**
	 * @return the comentarios
	 */
	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	/**
	 * Adds a comentario in comentarios set
	 * 
	 * @param comentario
	 */
	public void addComentarios(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	/**
	 * Constructor por defecto.
	 */
	public Recurso() { }

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
	 */
	public Recurso(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			Set<Etiqueta> etiquetas, Set<Comentario> comentarios) {
		this.titulo = titulo;
		this.genero = genero;
		this.fechaPublicacion = fechaPublicacion;
		this.idioma = idioma;
		this.autores = autores;
		this.etiquetas = etiquetas;
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Recurso [idRecurso=" + idRecurso + ", titulo=" + titulo + ", genero=" + genero + ", fechaPublicacion="
				+ fechaPublicacion + ", idioma=" + idioma + ", autores=" + autores + ", etiquetas=" + etiquetas
				+ ", comentarios=" + comentarios + "]";
	}

}
