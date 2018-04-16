package cgr.cgfsdam.pustakalaya.view;

import java.util.ResourceBundle;

/**
 * Enumeración con todas las vistas disponibles de la aplicación.
 * 
 * <li>{@link #LOGIN}</li>
 * <li>{@link #REGISTER}</li>
 * <li>{@link #L_MAIN}</li>
 * <li>{@link #L_MAIN_VIEW}</li>
 * <li>{@link #L_PROFILE}</li>
 * <li>{@link #L_SEARCH}</li>
 * <li>{@link #L_RECURSO_DETALLES}</li>
 * <li>{@link #A_MAIN}</li>
 * <li>{@link #A_MAINVIEW}</li>
 * <li>{@link #A_USERS}</li>
 * <li>{@link #A_RESOURCES}</li>
 * <li>{@link #A_RECURSO_FORM}</li>
 * <li>{@link #A_AUTOR_FORM}</li>
 * <li>{@link #A_GENERO_FORM}</li>
 * <li>{@link #A_IDIOMA_FORM}</li>
 *
 * @author CGR-Casa
 */
public enum FxmlView {

	/* Vistas de seguridad */

	/**
	 * Pantalla de ingreso.
	 */
	LOGIN("login.title.login", "/fxml/Login.fxml"),
	/**
	 * Pantalla de registro.
	 */
	REGISTER("login.title.register", "/fxml/Register.fxml"),

	/* Vistas de lector */

	/**
	 * Pantalla principal del lector.
	 */
	L_MAIN("lector.main.title", "/fxml/lector/Main.fxml"),
	/**
	 * Vista hija inicial del lector.
	 */
	L_MAIN_VIEW("lector.main.title", "/fxml/lector/MainView.fxml"),
	/**
	 * Vista hija para cargar en el panel principal en al vista profile.
	 */
	L_PROFILE("lector.profile.title", "/fxml/lector/ProfileView.fxml"),
	/**
	 * Vista de busqueda de recursos del lector.
	 */
	L_SEARCH("lector.search.title", "/fxml/lector/SearchView.fxml"),
	/**
	 * Vista del formulario de detalle de los recursos
	 */
	L_RECURSO_DETALLES("lector.search.detalles.title", "/fxml/lector/DetailForm.fxml"),

	/* Vistas de Administrador */

	/**
	 * Vista principal del administrador.
	 */
	A_MAIN("admin.main.title", "/fxml/admin/Main.fxml"),
	/**
	 * Vista principal del administrador.
	 */
	A_MAIN_VIEW("admin.main.title", "/fxml/admin/MainView.fxml"),
	/**
	 * Vista de gestión de usuarios del administrador.
	 * Se puede crear, editar, o eliminar usuarios.
	 */
	A_USERS("admin.users.title", "/fxml/admin/UsersView.fxml"),
	/**
	 * Vista de gestión de recursos de la biblioteca.
	 * Se puede crear, editar o dar de baja recursos.
	 */
	A_RESOURCES("admin.resources.title", "/fxml/admin/ResourcesView.fxml"),
	/**
	 * Vista del formulario de creación / edición de recursos.
	 */
	A_RECURSO_FORM("admin.resoures.recurso.form.title", "/fxml/admin/RecursoForm.fxml"),
	/**
	 * Vista del formulario de creación / edición de autores.
	 */
	A_AUTOR_FORM("admin.resoures.autor.form.title", "/fxml/admin/AutorForm.fxml"),
	/**
	 * Vista del formulario de creación / edición de generos.
	 */
	A_GENERO_FORM("admin.resoures.genero.form.title", "/fxml/admin/GeneroForm.fxml"),
	/**
	 * Vista del formulario de creación / edición de idiomas.
	 */
	A_IDIOMA_FORM("admin.resoures.idioma.form.title", "/fxml/admin/IdiomaForm.fxml");

	/**
	 * Nombre de la propiedad que contiene el literal del título en el fichero de propieades.
	 */
	private final String titleKey;
	/**
	 * Ruta del fichero fxml que contiene la vista referida.
	 */
	private final String fxmlFile;

	/**
	 * Constructor privado de los enumerados.
	 * 
	 * @param titleKey property key con el título de la vista.
	 * @param fxmlFile path del fichero de la vista.
	 */
	private FxmlView(final String titleKey, final String fxmlFile) {

		this.titleKey = titleKey;
		this.fxmlFile = fxmlFile;
	}

	/**
	 * Método para recuperar el título de la vista.
	 * 
	 * @return String título de la vista.
	 */
	public String getTitle() {

		return ResourceBundle.getBundle("texts/Bundle").getString(titleKey);
	}

	/**
	 * Método para recuperar la ruta del archivo de la vista.
	 * 
	 * @return String ruta del archivo.
	 */
	public String getFxmlFile() {

		return this.fxmlFile;
	}

}
