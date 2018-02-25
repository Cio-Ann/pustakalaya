package cgr.cgfsdam.pustakalaya.controller.lector;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.config.SpringFXMLLoader;
import cgr.cgfsdam.pustakalaya.config.StageManager;
import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador de la pantalla principal del usuario lector.
 *
 * @author CGR-Casa
 */
@Controller
public class MainController extends BaseController {
//
//	Logger log = LoggerFactory.getLogger(RegisterController.class);
//	
	
	@Autowired
	SpringFXMLLoader fxmlLoader;

	@Autowired
	ResourceBundle resourceBundle;

	@Autowired
	UsuarioService usuarioService;

	// private Usuario usuario;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnProfile;

	@FXML
	private Button btnStatus;

	@FXML
	private Button btnChangeAccount;

	@FXML
	private AnchorPane mainPanel;

	@FXML
	private Button btnRecord;

	@FXML
	private Button btnExit;

	@FXML
	private Label username;

	@FXML
	void handleProfile(ActionEvent event) {
//		sendAlert(AlertType.INFORMATION, "Boton profile", "Boton profile", "Boton profile");

		clearAllSelectedBtn();
		setSelectedBtn(btnProfile);

		AnchorPane newLoadedPane;
		try {
			newLoadedPane = (AnchorPane) getChildPane(FxmlView.L_PROFILE.getFxmlFile());
			
//			FXMLLoader loader = new FXMLLoader();
//	        loader.setLocation(ProfileController.class.getResource(FxmlView.L_PROFILE.getFxmlFile()));
//	        newLoadedPane = (AnchorPane) loader.load();
//	        
//	        ProfileController childController = loader.getController();
//	        childController.setUsuario(this.getUsuario());
			
			
//			ProfileController childController = (ProfileController) getChildController(FxmlView.L_PROFILE.getFxmlFile(),
//					ProfileController.class);
//			childController.setUsuario(this.getUsuario());

			mainPanel.getChildren().clear();
			mainPanel.getChildren().add(newLoadedPane);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void handleStatus(ActionEvent event) {
		sendAlert(AlertType.INFORMATION, "Boton status", "Boton status", "Boton status");

		clearAllSelectedBtn();

		btnStatus.getStyleClass().add("menu-button-on");

	}

	@FXML
	void handleRecord(ActionEvent event) {
		sendAlert(AlertType.INFORMATION, "Boton record", "Boton record", "Boton record");

		clearAllSelectedBtn();

		btnRecord.getStyleClass().add("menu-button-on");
	}

	@FXML
	void handleSearch(ActionEvent event) {
		sendAlert(AlertType.INFORMATION, "Boton search", "Boton search", "Boton search");

		clearAllSelectedBtn();

		btnSearch.getStyleClass().add("menu-button-on");
	}

	@FXML
	void handleChangeAccount(ActionEvent event) {
		log.info("se pulsó el botón Cambiar de cuenta");
		// vacia el contexto de seguridad
		SecurityContextHolder.clearContext();
		// redirige a la pantalla de login
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	void handleExit(ActionEvent event) {
		log.info("se puls� el bot�n salir");
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
			btnStatus.setText(resources.getString("lector.btn.status"));
			btnRecord.setText(resources.getString("lector.btn.record"));
			btnSearch.setText(resources.getString("lector.btn.search"));
			btnChangeAccount.setText(resources.getString("lector.btn.change.account"));
			btnExit.setText(resources.getString("lector.btn.exit"));
		} else {
			log.info("El usuario no est� autenticado");
			stageManager.switchScene(FxmlView.LOGIN);
		}
	}

	private void clearAllSelectedBtn() {
		btnProfile.getStyleClass().remove("menu-button-on");
		btnProfile.getStyleClass().add("menu-button-off");

		btnStatus.getStyleClass().remove("menu-button-on");
		btnStatus.getStyleClass().add("menu-button-off");

		btnRecord.getStyleClass().remove("menu-button-on");
		btnRecord.getStyleClass().add("menu-button-off");

		btnSearch.getStyleClass().remove("menu-button-on");
		btnSearch.getStyleClass().add("menu-button-off");

		btnChangeAccount.getStyleClass().remove("menu-button-on");
		btnChangeAccount.getStyleClass().add("menu-button-off");

		btnExit.getStyleClass().remove("menu-button-on");
		btnExit.getStyleClass().add("menu-button-off");
	}

	private void setSelectedBtn(Button button) {
		button.getStyleClass().add("menu-button-on");		
	}

}
