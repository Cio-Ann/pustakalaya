package cgr.cgfsdam.pustakalaya.controller.security;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.config.StageManager;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Vista-Controlador de la pantalla de login
 *
 * @author Carlos Gonzalez
 */
@Controller
public class LoginController implements Initializable {

	Logger log = LoggerFactory.getLogger(LoginController.class);

	@FXML
	private Label lblLogin;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnRegister;

	@Autowired
	AuthenticationManager autenticationManager;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblLogin.setText(resources.getString("login.label"));
		username.setPromptText(resources.getString("login.username.placeholder"));
		password.setPromptText(resources.getString("login.password.placeholder"));
		btnLogin.setText(resources.getString("login.btn.signin"));
		btnRegister.setText(resources.getString("login.btn.register"));
	}

	/**
	 * Autentica al usuario contra el sistema de autenticación de Spring.
	 * 
	 * @param event
	 *            ActionEvent evento que desenlaza la acción
	 * @throws IOException excepción si hay algún problema al cargar el fxml correspondiente a la siguiente vista.
	 */
	@FXML
	private void login(ActionEvent event) throws IOException {
		// recupera los valores introducidos
		String userName = getUsername();
		String userPassword = getPassword();

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(userName, userPassword);
			Authentication result = autenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

			if (isRoleAdmin()) {
				stageManager.switchScene(FxmlView.A_MAIN);
			} else if (isRoleLector()) {
				stageManager.switchScene(FxmlView.L_MAIN);
			}
		} catch (Exception e) {
			showNotAllowedMessage(e);
		}
	}

	/**
	 * Cambia a la pantalla de registro.
	 * 
	 * @param event ActionEvent evento que desenlaza la acción.
	 * @throws IOException excepción si hay algún problema al cargar el fxml correspondiente a la pantalla de registro.
	 */
	@FXML
	private void register(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.REGISTER);
	}

	/**
	 * Verifica si el usuario autenticado es del tipo Administrador.
	 * 
	 * @return boolean <code>true</code> si el usuario es de tipo administrador, o
	 *         <code>false</code> en caso contrario.
	 */
	private boolean isRoleAdmin() {
		return isRole("ROLE_ADMIN");
	}

	/**
	 * Verifica si el usuario es de tipo lector.
	 * 
	 * @return boolean <code>true</code> si el usuario es de tipo lector, o
	 *         <code>false</code> en caso contrario.
	 */
	private boolean isRoleLector() {
		return isRole("ROLE_LECTOR");
	}

	/**
	 * Valida si el usuario que se ha autenticado pertenece al rol pasado como
	 * parámetro.
	 * 
	 * @param role
	 *            String nombre del rol a comprobar.
	 * @return boolean <code>true</code> si el usuario cumple con el rol recibido, o
	 *         <code>false</code> en caso contrario.
	 */
	private boolean isRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(role));

		return hasUserRole;
	}

	/**
	 * Añade el mensaje de error en la vista para que el usuario sepa que el login
	 * ha fallado.
	 * 
	 * @param e
	 *            Exception excepción que ha producido el error de autenticación.
	 */
	private void showNotAllowedMessage(Exception e) {
		log.info("Intento fallido de login, user = " + getUsername() + ", password= " + getPassword());
		log.error(e.getLocalizedMessage());

		lblLogin.setText("Login Failed.");
	}

	/**
	 * Recupera la contrasña introducida por el usuario en la vista.
	 * 
	 * @return String password introducido por el usuario.
	 */
	public String getPassword() {
		return password.getText().trim();
	}

	/**
	 * Recupera el nombre de usuario de la vista.
	 * 
	 * @return String nombre de usuario.
	 */
	public String getUsername() {
		return username.getText().trim();
	}

}
