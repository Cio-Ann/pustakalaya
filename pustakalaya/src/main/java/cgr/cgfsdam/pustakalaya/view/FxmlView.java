package cgr.cgfsdam.pustakalaya.view;

import java.util.ResourceBundle;

/**
 * Enumeración con todas las vistas disponibles de la aplicación.
 * 
 * <li>{@link #LOGIN}</li>
 * <li>{@link #REGISTER}</li>
 * <li>{@link #L_PROFILE}</li>
 * <li>{@link #L_STATUS}</li>
 * <li>{@link #L_RECORD}</li>
 * <li>{@link #L_SEARCH}</li>
 * <li>{@link #L_DETAIL}</li>
 * <li>{@link #A_USERS}</li>
 * <li>{@link #A_ROLES}</li>
 * <li>{@link #A_RESOURCES}</li>
 * <li>{@link #A_BOOKINGS}</li>
 * <li>{@link #A_LOANS}</li>
 * <li>{@link #A_PENALTIES}</li>
 * <li>{@link #A_COMMENTS}</li>
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
	 * Vista principal del lector.
	 */
	L_MAIN("lector.main.title", "/fxml/lector/Main.fxml"),
	/**
	 * Vista principal del lector.
	 */
	L_MAIN_VIEW("lector.main.title", "/fxml/lector/MainView.fxml"),
	/**
	 * Vista hija para cargar en el panel principal en al vista profile.
	 */
	L_PROFILE("lector.profile.title", "/fxml/lector/ProfileView.fxml"),
	/**
	 * Vista de estado actual del lector. Muestra los prestamos, reservar o
	 * sanciones vigentes.
	 */
	L_STATUS("lector.status.title", "/fxml/lector/Status.fxml"),
	/**
	 * Vista de histórico del lector. 
	 * Muestra un listado con todos los prestamos, reservas y sanciones que ha 
	 * tenido el lector desde que se dió de alta.
	 */
	L_RECORD("lector.record.title", "/fxml/lector/Record.fxml"), 
	/**
	 * Vista de busqueda de recursos del lector.
	 */
	L_SEARCH("lector.search.title",	"/fxml/lector/Search.fxml"), 
	/**
	 * Vista de detalles de un recurso para un lector.
	 * Aqui puede puntuar o comentar un recurso siempre que lo haya retirado 
	 * alguna vez.
	 */
	L_DETAIL("lector.detail.title", "/fxml/lector/Detail.fxml"),

	/* Vistas de Administrador */
	
	/**
	 * Vista principal del administrador.
	 */
	A_MAIN("admin.main.title", "/fxml/admin/Main.fxml"),
	/**
	 * Vista de gestión de usuarios del administrador.
	 * Se puede crear, editar, o eliminar usuarios.
	 */
	A_USERS("admin.users.title", "/fxml/admin/Users.fxml"), 
	/**
	 * Vista de gestión de roles del administrador.
	 * Se puede crear, editar o modificar roles.
	 * @see ver si es necesaria o no.
	 */
	A_ROLES("admin.roles.title", "/fxml/admin/Roles.fxml"), 
	/**
	 * Vista de gestión de recursos de la biblioteca.
	 * Se puede crear, editar o dar de baja recursos.
	 */
	A_RESOURCES("admin.resources.title", "/fxml/admin/Resources.fxml"), 
	/**
	 * Vista de reservas.
	 * El administrador puede ver las reservas actuales o notificar de recursos 
	 * disponibles a los usuarios que las han reservado.
	 */
	A_BOOKINGS("admin.bookings.title", "/fxml/admin/Bookings.fxml"), 
	/**
	 * Vista de prestamos.
	 * Se puede registrar prestamos y devoluciones, además se pueden notificar
	 * retrasos a los lectores.
	 */
	A_LOANS("admin.loans.title", "/fxml/admin/Loans.fxml"), 
	/**
	 * Vista de sanciones.
	 * Se pueden crear y editar sanciones.
	 */
	A_PENALTIES("admin.penalties.title", "/fxml/admin/Penalties.fxml"), 
	/**
	 * Vista de comentarios.
	 * El administrador puede moderar los comentarios de los lectores.
	 */
	A_COMMENTS("admin.comments.title", "/fxml/admin/Comments.fxml")
	;

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
