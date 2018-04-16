package cgr.cgfsdam.pustakalaya.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.model.utility.FormObjects;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

/**
 * Clase abstracta para los controladores de vistas una vez autenticado el usuario.
 * 
 * Establece algúnos campos y métodos comunes a todos los controladores de este tipo.
 *
 * @author CGR-Casa
 */
public abstract class BaseController implements Initializable {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private Usuario usuario;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	ResourceBundle resourceBundle;

	/**
	 * getter del campo usuario.
	 * 
	 * @return Usuario usuario almacenado en el controlador.
	 */
	public Usuario getUsuario() {

		return usuario;
	}

	/**
	 * setter del campo usuario.
	 * 
	 * @param usuario Usuario a almacenar en el controlador.
	 */
	public void setUsuario(Usuario usuario) {

		log.info("Se establece el usuario en la clase " + this.getClass().getSimpleName());
		this.usuario = usuario;
	}

	/**
	 * Metodo para obtener un Pane a partir del fichero fxml que lo define.
	 * 
	 * @param path String ruta al fichero fxml.
	 * @return Pane objeto cargado a partir del fichero.
	 * @throws IOException si no se encuentra o no se tiene acceso al fichero indicado.
	 */
	public Pane getChildPane(String path) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(appContext::getBean); // Spring now FXML Controller Factory
		loader.setResources(resourceBundle);
		loader.setLocation(getClass().getResource(path));
		return loader.load();
	}

	public FormObjects getFormOjects(String path) throws IOException {

		FormObjects ret = new FormObjects();

		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(appContext::getBean); // Spring now FXML Controller Factory
		loader.setResources(resourceBundle);
		loader.setLocation(getClass().getResource(path));

		ret.setParent(loader.load());
		ret.setController(loader.getController());
		return ret;
	}

	/**
	 * Método para mostrar un popup de confirmación y recoger la respuesta del usuario.
	 * 
	 * @param title String título del popup.
	 * @param header String cabecera del popup.
	 * @param contextText String mensaje a mostrar.
	 * @return boolean <code>true</code> si el usuario acepta, <code>false</code> en caso contrario.
	 */
	protected boolean showConfirmation(String title, String header, String contextText) {

		boolean ret = false;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			ret = true;
		}
		return ret;
	}

	/**
	 * Método para mostrar un mensaje de alerta al usuario.
	 * 
	 * @param tipo AlertType tipo de alerta a mostrar.
	 * @param title String título del popup.
	 * @param header String cabecera del popup.
	 * @param contextText String mensaje a mostrar.
	 */
	protected void sendAlert(AlertType tipo, String title, String header, String contextText) {

		Alert alert = new Alert(tipo);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		alert.showAndWait();
	}

}
