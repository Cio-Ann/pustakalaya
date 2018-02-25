package cgr.cgfsdam.pustakalaya.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public abstract class BaseController implements Initializable {

	//establece el logeer apropiado para todas las subclases
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Usuario usuario;
	
	@Autowired
	private ApplicationContext appContext;

	@Autowired
	ResourceBundle resourceBundle;


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		log.info("Se establece el usuario en la clase " + this.getClass().getSimpleName());
		this.usuario = usuario;
	}

	/**
	 * Method to get a child pane from path to its fxml file.
	 * 
	 * @param path
	 *            String path to child fxml file.
	 * @return Pane
	 * @throws IOException
	 *             if file is not found or access is not allowed.
	 */
	public Pane getChildPane(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(appContext::getBean); //Spring now FXML Controller Factory
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(path));
        return loader.load();
	}

	/**
	 * Method to get a child controller form its path
	 * 
	 * @param path
	 * @param clazz
	 * @return
	 * @throws IOException 
	 */
	public <T extends BaseController> BaseController getChildController(String path,
			Class<? extends BaseController> clazz) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(appContext::getBean); 
		loader.setLocation(clazz.getResource(path));
//		loader.load();
		T controller = loader.getController();
		
		return controller;
	}

	/**
	 * Method to show a confirmation dialog.
	 * 
	 * @param title String title of dialog window
	 * @param header String header of dialog window
	 * @param contextText String message shown in dialog window
	 * @return boolean <code>true</code> if user accept dialog, <code>false</code> otherwise
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
	 * Method to show an alert dialog box.
	 * 
	 * @param tipo AlertType type of alert to show.
	 * @param title String title of dialog window
	 * @param header String header of dialog window
	 * @param contextText String message shown in dialog window
	 */
	protected void sendAlert(AlertType tipo, String title, String header, String contextText) {
		Alert alert = new Alert(tipo);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		alert.showAndWait();
	}

}
