package cgr.cgfsdam.pustakalaya.controller.lector;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.config.SpringFXMLLoader;
import cgr.cgfsdam.pustakalaya.config.StageManager;
import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.utility.FormObjects;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador de la pantalla principal del usuario lector.
 *
 * @author CGR-Casa
 */
@Controller
public class MainController extends BaseController {

	@Autowired
	SpringFXMLLoader fxmlLoader;

	@Autowired
	ResourceBundle resourceBundle;

	@Autowired
	UsuarioService usuarioService;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private Label username;

	@FXML
	private Button btnProfile;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnChangeAccount;

	@FXML
	private Button btnExit;

	@FXML
	private AnchorPane mainPanel;

	@FXML
	void handleProfile(ActionEvent event) {

		clearAllSelectedBtn();
		setSelectedBtn(btnProfile);

		try {

			FormObjects formData = getFormOjects(FxmlView.L_PROFILE.getFxmlFile());
			((ProfileController) formData.getController()).setUsuario(this.getUsuario());

			mainPanel.getChildren().clear();
			mainPanel.getChildren().add(formData.getParent());
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	@FXML
	void handleSearch(ActionEvent event) {

		clearAllSelectedBtn();
		setSelectedBtn(btnSearch);

		try {

			FormObjects formData = getFormOjects(FxmlView.L_SEARCH.getFxmlFile());
			((SearchController) formData.getController()).setUsuario(this.getUsuario());

			mainPanel.getChildren().clear();
			mainPanel.getChildren().add(formData.getParent());
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	@FXML
	void handleChangeAccount(ActionEvent event) {

		log.info("se pulsó el botón Cambiar de cuenta");
		// vacia el contexto de seguridad
		SecurityContextHolder.clearContext();
		// this.setUsuario(null);
		// redirige a la pantalla de login
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void handleExit(ActionEvent event) {

		log.info("se pulsó el botón salir");
		if (showConfirmation(resourceBundle.getString("app.exit.title"), resourceBundle.getString("app.exit.header"),
				resourceBundle.getString("app.exit.text"))) {
			Platform.exit();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			String securityUser = SecurityContextHolder.getContext().getAuthentication().getName();
			log.info("El usuario autenticado es: " + securityUser);

			setUsuario(usuarioService.findByUsername(securityUser));

			username.setText(getUsuario().getUsername());
			btnProfile.setText(resources.getString("lector.btn.profile"));
			btnSearch.setText(resources.getString("lector.btn.search"));
			btnChangeAccount.setText(resources.getString("lector.btn.change.account"));
			btnExit.setText(resources.getString("lector.btn.exit"));

			loadChildPanel(FxmlView.L_MAIN_VIEW.getFxmlFile());

		} else {
			log.info("El usuario no está autenticado");
			stageManager.switchScene(FxmlView.LOGIN);
		}
	}

	/**
	 * Carga un AnchorPane a partir de un fichero fxml en el campo mainPanel.
	 * 
	 * @param path
	 *            String ruta al archivo fxml a cargar.
	 */
	private void loadChildPanel(String path) {

		AnchorPane newLoadedPane;
		try {
			newLoadedPane = (AnchorPane) getChildPane(path);

			mainPanel.getChildren().clear();
			mainPanel.getChildren().add(newLoadedPane);

		} catch (IOException e) {
			log.error("Error al cargar el fichero " + path, e, e.getCause());
		}
	}

	/**
	 * Elimina la clase de boton seleccionado de los botones de navegación.
	 */
	private void clearAllSelectedBtn() {

		btnProfile.getStyleClass().remove("menu-button-on");
		btnProfile.getStyleClass().add("menu-button-off");
		btnProfile.setDisable(false);

		btnSearch.getStyleClass().remove("menu-button-on");
		btnSearch.getStyleClass().add("menu-button-off");
		btnSearch.setDisable(false);

		btnChangeAccount.getStyleClass().remove("menu-button-on");
		btnChangeAccount.getStyleClass().add("menu-button-off");
		btnChangeAccount.setDisable(false);

		btnExit.getStyleClass().remove("menu-button-on");
		btnExit.getStyleClass().add("menu-button-off");
		btnExit.setDisable(false);
	}

	/**
	 * Establece la clase de botón seleccionado en el botón pulsado.
	 * 
	 * @param button Button to apply new style;
	 */
	private void setSelectedBtn(Button button) {

		button.getStyleClass().add("menu-button-on");
		button.setDisable(true);
	}

}
