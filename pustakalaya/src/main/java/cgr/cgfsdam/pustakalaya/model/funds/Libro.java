package cgr.cgfsdam.pustakalaya.model.funds;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Clase que representa la especialización de recurso Libro.
 *
 * @author CGR-Casa
 */
@Entity
@PrimaryKeyJoinColumn(name = "id_recurso")
public class Libro extends Recurso {

	/**
	 * Número de páginas.
	 */
	private int numPaginas;

	/**
	 * ISBN Número internacional normalizado de libro.
	 */
	private String ISBN;

	/**
	 * @return the numPaginas
	 */
	public int getNumPaginas() {
		return numPaginas;
	}

	/**
	 * @param numPaginas the numPaginas to set
	 */
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * Constructor por defecto.
	 */
	public Libro() { }

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
	 * @param numPaginas
	 * @param ISBN
	 */
	public Libro(String titulo, Genero genero, Date fechaPublicacion, Idioma idioma, Set<Autor> autores,
			Set<Etiqueta> etiquetas, Set<Comentario> comentarios, int numPaginas, String ISBN) {
		super(titulo, genero, fechaPublicacion, idioma, autores, etiquetas, comentarios);
		this.numPaginas = numPaginas;
		this.ISBN = ISBN;
	}

	@Override
	public String toString() {
		return "Libro [" + super.toString() + ", numPaginas=" + numPaginas + ", ISBN=" + ISBN + "]";
	}

}
