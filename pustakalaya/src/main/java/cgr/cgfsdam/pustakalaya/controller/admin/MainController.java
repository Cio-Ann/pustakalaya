package cgr.cgfsdam.pustakalaya.controller.admin;

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
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador de la pantalla principal de administrador.
 *
 * @author CGR-Casa
 */
@Controller("AdminMainController")
public class MainController  extends BaseController{

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
    private Button btnUsers;

    @FXML
    private Button btnFunds;

    @FXML
    private Button btnBookings;

    @FXML
    private Button btnLoans;

    @FXML
    private Button btnNotications;

    @FXML
    private Button btnChangeAccount;

    @FXML
    private Button btnExit;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private Label username;

    @FXML
    void handleUsers(ActionEvent event) {

    }

    @FXML
    void handleFunds(ActionEvent event) {

    }

    @FXML
    void handleBookings(ActionEvent event) {

    }

    @FXML
    void handleLoans(ActionEvent event) {

    }

    @FXML
    void handleNotifications(ActionEvent event) {

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
			btnUsers.setText(resources.getString("admin.btn.users"));
			btnFunds.setText(resources.getString("admin.btn.funds"));
			btnBookings.setText(resources.getString("admin.btn.bookings"));
			btnLoans.setText(resources.getString("admin.btn.loans"));
			btnNotications.setText(resources.getString("admin.btn.notifications"));
			btnChangeAccount.setText(resources.getString("admin.btn.change.account"));
			btnExit.setText(resources.getString("admin.btn.exit"));

			loadChildPanel(FxmlView.A_MAIN_VIEW.getFxmlFile());

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
		btnUsers.getStyleClass().remove("menu-button-on");
		btnUsers.getStyleClass().add("menu-button-off");

		btnFunds.getStyleClass().remove("menu-button-on");
		btnFunds.getStyleClass().add("menu-button-off");

		btnBookings.getStyleClass().remove("menu-button-on");
		btnBookings.getStyleClass().add("menu-button-off");

		btnLoans.getStyleClass().remove("menu-button-on");
		btnLoans.getStyleClass().add("menu-button-off");

		btnNotications.getStyleClass().remove("menu-button-on");
		btnNotications.getStyleClass().add("menu-button-off");

		btnChangeAccount.getStyleClass().remove("menu-button-on");
		btnChangeAccount.getStyleClass().add("menu-button-off");

		btnExit.getStyleClass().remove("menu-button-on");
		btnExit.getStyleClass().add("menu-button-off");
	}

	/**
	 * Establece la clase de botón seleccionado en el botón pulsado.
	 * 
	 * @param button Button to apply new style;
	 */
	private void setSelectedBtn(Button button) {
		button.getStyleClass().add("menu-button-on");
	}
}
