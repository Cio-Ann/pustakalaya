package cgr.cgfsdam.pustakalaya.controller.security;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.config.StageManager;
import cgr.cgfsdam.pustakalaya.model.users.Direccion;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.users.TipoDocumentoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

@Controller
public class RegisterController implements Initializable {

	Logger log = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	ResourceBundle resourceBundle;

	@Autowired
	UsuarioService usuarioService;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblUserData;

	@FXML
	private Label lblUserAddress;

	@FXML
	private Label lblNombre;

	@FXML
	private TextField tNombre;

	@FXML
	private Label lblApellido1;

	@FXML
	private TextField tApellido1;

	@FXML
	private Label lblApellido2;

	@FXML
	private TextField tApellido2;

	@FXML
	private Label lblTipoDocumento;

	@FXML
	private ComboBox<String> cbTipoDocumento;

	@FXML
	private Label lblDocumento;

	@FXML
	private TextField tDocumento;

	@FXML
	private Label lblUsername;

	@FXML
	private TextField tUsername;

	@FXML
	private Label lblPassword;

	@FXML
	private PasswordField pPassword;

	@FXML
	private Label lblConfirmPassword;

	@FXML
	private PasswordField pConfirmPassword;

	@FXML
	private Label lblPswError;

	@FXML
	private Label lblValidationErrors;

	@FXML
	private Label lblTipoVia;

	@FXML
	private TextField tTipoVia;

	@FXML
	private Label lblVia;

	@FXML
	private TextField tVia;

	@FXML
	private Label lblNumero;

	@FXML
	private TextField tNumero;

	@FXML
	private Label lblPortal;

	@FXML
	private TextField tPortal;

	@FXML
	private Label lblEscalera;

	@FXML
	private TextField tEscalera;

	@FXML
	private Label lblPlanta;

	@FXML
	private TextField tPlanta;

	@FXML
	private Label lblPuerta;

	@FXML
	private TextField tPuerta;

	@FXML
	private Label lblLocalidad;

	@FXML
	private TextField tLocalidad;

	@FXML
	private Label lblProvincia;

	@FXML
	private TextField tProvincia;

	@FXML
	private Label lblCP;

	@FXML
	private TextField tCP;

	@FXML
	private Label lblTelf;

	@FXML
	private TextField tTelf;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField tEmail;

	@FXML
	private Button btnRegister;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnExit;

	private ObservableList<String> tiposDocumento = FXCollections.observableArrayList();

	/**
	 * Method called on event type from pConfirmPassword field to check if password
	 * and confirmation are equals.
	 * 
	 * @param event
	 *            KeyEvent handled.
	 */
	@FXML
	void handleCheckPass(KeyEvent event) {
		String pass = pPassword.getText();
		String pass2 = pConfirmPassword.getText() + event.getCharacter();
		if (!pass2.equals(pass)) {
			lblPswError.setText(resourceBundle.getString("register.label.passwordError"));
//			lblPswError.setTextFill(Paint.valueOf("red"));
		} else {
			lblPswError.setText("");
		}
	}

	/**
	 * Method called on register button click event to save user if is valid. Shows
	 * errors otherwise.
	 * 
	 * @param event
	 *            ActionEvent handled.
	 */
	@FXML
	void handleRegister(ActionEvent event) {
		log.info("se pulsó el botón registrarse");

		String validationErrors = validateFields();

		if (validationErrors.isEmpty()) {
			lblValidationErrors.setText("");
			saveUser();
			
			sendAlert(AlertType.INFORMATION, 
					resourceBundle.getString("register.save.title"), 
					resourceBundle.getString("register.save.header"), 
					resourceBundle.getString("register.save.text"));
			
			stageManager.switchScene(FxmlView.LOGIN);
		} else {
			lblValidationErrors.setText(validationErrors);
//			lblValidationErrors.setTextFill(Paint.valueOf("red"));
		}

		// sendAlert(AlertType.WARNING, "Debug Info", "handleRegister", "Pulsaste el
		// boton registrarse");
	}

	/**
	 * Method called when exit button is clicked to exit application on click
	 * 
	 * @param event
	 */
	@FXML
	void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		if (showConfirmation(resourceBundle.getString("app.exit.title"), resourceBundle.getString("app.exit.header"),
				resourceBundle.getString("app.exit.text"))) {
			Platform.exit();
		}
	}

	@FXML
	void handleBack(ActionEvent event) {
		log.info("se pulsó el botón volver");
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("register.label.title"));
		lblUserData.setText(resources.getString("register.label.userdata"));
		lblUserAddress.setText(resources.getString("register.label.userAddress"));

		lblNombre.setText(resources.getString("register.label.nombre"));
		lblApellido1.setText(resources.getString("register.label.apellido1"));
		lblApellido2.setText(resources.getString("register.label.apellido2"));
		lblTipoDocumento.setText(resources.getString("register.label.tipoDocumento"));
		cbTipoDocumento.setPromptText(resources.getString("register.comboBox.tipoDocumento"));
		lblDocumento.setText(resources.getString("register.label.documento"));
		lblUsername.setText(resources.getString("register.label.userName"));
		lblPassword.setText(resources.getString("register.label.password"));
		lblConfirmPassword.setText(resources.getString("register.label.confirmPassword"));

		lblPswError.setText("");
		lblValidationErrors.setText("");

		lblDocumento.setText(resources.getString("register.label.documento"));
		lblDocumento.setText(resources.getString("register.label.documento"));

		lblTipoVia.setText(resources.getString("register.label.tipoVia"));
		lblVia.setText(resources.getString("register.label.via"));
		lblNumero.setText(resources.getString("register.label.numero"));
		lblPortal.setText(resources.getString("register.label.portal"));
		lblEscalera.setText(resources.getString("register.label.escalera"));
		lblPlanta.setText(resources.getString("register.label.planta"));
		lblPuerta.setText(resources.getString("register.label.puerta"));
		lblLocalidad.setText(resources.getString("register.label.localidad"));
		lblProvincia.setText(resources.getString("register.label.provincia"));
		lblCP.setText(resources.getString("register.label.cp"));
		lblTelf.setText(resources.getString("register.label.telf"));
		lblEmail.setText(resources.getString("register.label.email"));

		btnRegister.setText(resources.getString("register.button.register"));
		btnBack.setText(resources.getString("register.button.back"));
		btnExit.setText(resources.getString("register.button.exit"));

		loadTipoDocumento();
	}

	private void loadTipoDocumento() {
		tiposDocumento.clear();
		tiposDocumento.addAll(tipoDocumentoService.findAllNombres());

		cbTipoDocumento.setItems(tiposDocumento);
	}

	private void sendAlert(AlertType tipo, String title, String header, String contextText) {
		Alert alert = new Alert(tipo);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		alert.showAndWait();
	}

	private boolean showConfirmation(String Title, String header, String contextText) {
		boolean ret = false;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			ret = true;
		}
		return ret;
	}

	/**
	 * Method to validate all mandatory fields
	 * 
	 * @return <code>true</code> if all mandatory fields are correctly filled,
	 *         <code>false</code> otherwhise.
	 */
	private String validateFields() {
		String ret = "";

		ret += validateNombre();
		ret += validateApellido1();
		ret += validateTipoDocumento();
		ret += validateDocumento();
		ret += validateUsername();
		ret += validatePassword();
		ret += validateEmail();

		// TODO Auto-generated method stub
		return ret;
	}

	private String validateNombre() {
		String nombre = tNombre.getText().trim();

		if (nombre.isEmpty()) {
			return resourceBundle.getString("user.validation.nombreError");
		} else {
			return "";
		}
	}

	private String validateApellido1() {
		String apellido = tApellido1.getText().trim();

		if (apellido.isEmpty()) {
			return resourceBundle.getString("user.validation.apellido1Error");
		} else {
			return "";
		}
	}

	private String validateTipoDocumento() {
		if (cbTipoDocumento.getValue() == null || cbTipoDocumento.getValue().trim().isEmpty()) {
			return resourceBundle.getString("user.validation.tipoDocumentoError");
		} else {
			return "";
		}
	}

	private String validateDocumento() {
		String doc = tDocumento.getText().trim();

		if (doc.isEmpty()) {
			return resourceBundle.getString("user.validation.documentoError");
		} else {
			return "";
		}
	}

	private String validateUsername() {
		String username = tUsername.getText().trim();

		if (username.isEmpty()) {
			return resourceBundle.getString("user.validation.usernameError");
		} else {
			Usuario u = usuarioService.findByUsername(username);
			if (u != null) {
				return resourceBundle.getString("user.validation.usernameExists");
			} else {
				return "";
			}
		}
	}

	private String validatePassword() {
		String p = pPassword.getText().trim();

		if (p.isEmpty()) {
			return resourceBundle.getString("user.validation.passwordError");
		}

		String cp = pConfirmPassword.getText().trim();

		if (!p.equals(cp)) {
			return resourceBundle.getString("user.validation.passwordNotMatching");
		}

		return "";
	}

	private String validateEmail() {
		String email = tEmail.getText().trim();

		if (email.isEmpty()) {
			return resourceBundle.getString("user.validation.emailError");
		}

		Usuario u = usuarioService.findByEmail(email);
		if (u != null) {
			return resourceBundle.getString("user.validation.emailAlreadyExists");
		}

		return "";
	}

	private void saveUser() {
		Usuario user = new Usuario();

		// user data
		user.setNombre(tNombre.getText().trim());
		user.setApellido1(tApellido1.getText().trim());
		if (!tApellido2.getText().trim().isEmpty()) {
			user.setApellido2(tApellido2.getText().trim());
		}
		
		
//		TipoDocumento tipoDocumento = tipoDocumentoService.findByNombre(cbTipoDocumento.getValue());
		user.setTipoDocumento(tipoDocumentoService.findByNombre(cbTipoDocumento.getValue()));
//		log.info(tipoDocumento.toString());
		
		user.setDocumento(tDocumento.getText().trim());
		user.setUsername(tUsername.getText().trim());
		user.setPassword(pPassword.getText().trim());
		user.setEmail(tEmail.getText().trim());
		if (!tTelf.getText().trim().isEmpty()) {
			tTelf.getText().trim();
		}

		// address data
		String tipoVia = tTipoVia.getText().trim().isEmpty() ? null : tTipoVia.getText().trim();
		String via = tVia.getText().trim().isEmpty() ? null : tVia.getText().trim();
		String numero = tNumero.getText().trim().isEmpty() ? null : tNumero.getText().trim();
		String portal = tPortal.getText().trim().isEmpty() ? null : tPortal.getText().trim();
		String escalera = tEscalera.getText().trim().isEmpty() ? null : tEscalera.getText().trim();
		String planta = tPlanta.getText().trim().isEmpty() ? null : tPlanta.getText().trim();
		String puerta = tPuerta.getText().trim().isEmpty() ? null : tPuerta.getText().trim();
		String localidad = tLocalidad.getText().trim().isEmpty() ? null : tLocalidad.getText().trim();
		String provincia = tProvincia.getText().trim().isEmpty() ? null : tProvincia.getText().trim();
		String cp = tCP.getText().trim().isEmpty() ? null : tCP.getText().trim();

		Direccion address = null;
		if (tipoVia != null || via != null || numero != null || portal != null || escalera != null || planta != null
				|| puerta != null || localidad != null || provincia != null || cp != null) {
			address = new Direccion(tipoVia, via, numero, portal, escalera, planta, puerta, localidad, provincia, cp);
		}

		user.setDireccion(address);
		
		usuarioService.saveUsuario(user);
	}

}
