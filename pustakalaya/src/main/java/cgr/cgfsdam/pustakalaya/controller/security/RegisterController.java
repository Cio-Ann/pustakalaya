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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador de la vista correspondiente a la pantalla de registro.
 *
 * @author CGR-Casa
 */
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
	private ComboBox<TipoDocumento> cbTipoDocumento;

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

	private ObservableList<TipoDocumento> tiposDocumento = FXCollections.observableArrayList();

	/**
	 * Método para evaluar si la contraseña y la confirmación son iguales. Se
	 * ejecuta cada vez ue se escribe en el campo de confirmación de contraseña.
	 * 
	 * @param event
	 *            KeyEvent evento que origino la ejecución.
	 */
	@FXML
	void handleCheckPass(KeyEvent event) {
		String pass = pPassword.getText();
		String pass2 = pConfirmPassword.getText() + event.getCharacter();
		if (!pass2.equals(pass)) {
			lblPswError.setText(resourceBundle.getString("register.label.passwordError"));
			// lblPswError.setTextFill(Paint.valueOf("red"));
		} else {
			lblPswError.setText("");
		}
	}

	/**
	 * Método que registra al usuario en la base de datos, si todos los datos son
	 * válidos. Se desencadena al pulsar el botón registrar.
	 * 
	 * @param event
	 *            ActionEvent evento que desencadena la acción.
	 */
	@FXML
	void handleRegister(ActionEvent event) {
		log.info("se pulsó el botón registrarse");

		String validationErrors = validateFields();

		if (validationErrors.isEmpty()) {
			lblValidationErrors.setText("");
			saveUser();

			sendAlert(AlertType.INFORMATION, resourceBundle.getString("register.save.title"),
					resourceBundle.getString("register.save.header"), resourceBundle.getString("register.save.text"));

			stageManager.switchScene(FxmlView.LOGIN);
		} else {
			lblValidationErrors.setText(validationErrors);
		}
	}

	/**
	 * Método que finaliza la aplicación. Se desencadena al pulsar el botón salir.
	 * 
	 * @param event
	 *            ActionEvent evento que desencadena la acción.
	 */
	@FXML
	void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		if (showConfirmation(resourceBundle.getString("app.exit.title"), resourceBundle.getString("app.exit.header"),
				resourceBundle.getString("app.exit.text"))) {
			Platform.exit();
		}
	}

	/**
	 * Método que devuelve a la pantalla de login. Se desencadena al pulsar el botón
	 * volver.
	 * 
	 * @param event
	 *            ActionEvent evento que desencadena la acción.
	 */
	@FXML
	void handleBack(ActionEvent event) {
		log.info("se pulsó el botón volver");
		stageManager.switchScene(FxmlView.LOGIN);
	}

	/**
	 * Carga inicial de los valores de las etiquetas y campos de la vista.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("register.label.title"));
		lblUserData.setText(resources.getString("register.label.userdata"));
		lblUserAddress.setText(resources.getString("register.label.userAddress"));

		lblNombre.setText(resources.getString("register.label.nombre"));
		lblApellido1.setText(resources.getString("register.label.apellido1"));
		lblApellido2.setText(resources.getString("register.label.apellido2"));
		lblTipoDocumento.setText(resources.getString("register.label.tipoDocumento"));

		initializeComboTipoDocumento(resources);

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

	/**
	 * Método privado para inicializar correctamente el combo del tipo de documento.
	 * 
	 * @param resources
	 */
	private void initializeComboTipoDocumento(ResourceBundle resources) {
		// establece el texto cuando no hay selección
		cbTipoDocumento.setPromptText(resources.getString("register.comboBox.tipoDocumento"));

		// establece la conversión entre el tipo TipoDocumento y el String mostrado en
		// el combo desplegable
		cbTipoDocumento.setCellFactory(new Callback<ListView<TipoDocumento>, ListCell<TipoDocumento>>() {
			@Override
			public ListCell<TipoDocumento> call(ListView<TipoDocumento> param) {

				ListCell<TipoDocumento> cell = new ListCell<TipoDocumento>() {
					@Override
					protected void updateItem(TipoDocumento item, boolean empty) {
						super.updateItem(item, empty);

						if (item != null) {
							setText(item.getNombre());
						} else {
							setText(null);
						}
					}
				};

				return cell;
			}
		});

		// establece la conversión entre el Tipo documento y el String mostrado en el
		// combo seleccionado.
		cbTipoDocumento.setConverter(new StringConverter<TipoDocumento>() {
			@Override
			public String toString(TipoDocumento td) {
				if (td == null) {
					return "";
				} else {
					return td.getNombre();
				}
			}

			@Override
			public TipoDocumento fromString(String nombre) {
				return tipoDocumentoService.findByNombre(nombre);
			}
		});

		// carga los datos de base de datos
		loadTipoDocumento();
	}

	/**
	 * Método para cargar los valores del combo de TipoDocumento.
	 */
	private void loadTipoDocumento() {
		tiposDocumento.clear();
		tiposDocumento.addAll(tipoDocumentoService.findAll());

		cbTipoDocumento.setItems(tiposDocumento);
	}

	/**
	 * Método que envia un popup de alerta al usuario.
	 * 
	 * @param tipo
	 *            AlertType tipo de alerta a mostrar.
	 * @param title
	 *            String titulo del popup de alerta.
	 * @param header
	 *            String encabezado del popup de alerta.
	 * @param contextText
	 *            String mensaje a mostrar en la alerta.
	 */
	private void sendAlert(AlertType tipo, String title, String header, String contextText) {
		Alert alert = new Alert(tipo);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contextText);

		alert.showAndWait();
	}

	/**
	 * Método que envia un mensaje de confirmación al usuario y recoge su respuesta.
	 * 
	 * @param Title
	 *            String título del popup.
	 * @param header
	 *            String encabezado del popup.
	 * @param contextText
	 *            String texto del mensaje a mostrar en el popup.
	 * @return boolean <code>true</code> si el usuario acepta, o <code>false</code>
	 *         en caso contrario.
	 */
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
	 * Valida que todos los campos obligatorios sean correctos.
	 * 
	 * @return boolean <code>true</code> si todos los campos obligatorios estan
	 *         correctamente completados, <code>false</code> en caso contrario.
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

	/**
	 * Valida el campo de nombre
	 * @return String mensaje de error si no se ha rellenado.
	 */
	private String validateNombre() {
		String nombre = tNombre.getText().trim();

		if (nombre.isEmpty()) {
			return resourceBundle.getString("user.validation.nombreError");
		} else {
			return "";
		}
	}

	/**
	 * Valida el campo de primer apellido
	 * @return String mensaje de error si no se ha rellenado.
	 */
	private String validateApellido1() {
		String apellido = tApellido1.getText().trim();

		if (apellido.isEmpty()) {
			return resourceBundle.getString("user.validation.apellido1Error");
		} else {
			return "";
		}
	}

	/**
	 * Valida el tipo de documento.
	 * @return String mensaje de error si no se ha seleccionado.
	 */
	private String validateTipoDocumento() {
		if (cbTipoDocumento.getValue() == null || cbTipoDocumento.getValue().getNombre().isEmpty()) {
			return resourceBundle.getString("user.validation.tipoDocumentoError");
		} else {
			return "";
		}
	}

	/**
	 * Valida el documento.
	 * @return String mensaje de error si no se ha rellenado.
	 */
	private String validateDocumento() {
		String doc = tDocumento.getText().trim();

		if (doc.isEmpty()) {
			return resourceBundle.getString("user.validation.documentoError");
		} else {
			return "";
		}
	}

	/**
	 * Valida en username.
	 * @return String mensaje de error si no se ha rellenado o ya existe en el sistema.
	 */
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

	/**
	 * Valida la contraseña.
	 * @return String mensaje de error si no se ha rellenado o no coinciden los campos de contraseña y confirmación.
	 */
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

	/**
	 * Valida el campo email.
	 * @return String mensaje de error si no se ha rellenado o ya está registrado en el sistema.
	 */
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

	/**
	 * Almacena el usuario en base de datos	
	 */
	private void saveUser() {
		Usuario user = new Usuario();

		// user data
		user.setNombre(tNombre.getText().trim());
		user.setApellido1(tApellido1.getText().trim());
		if (!tApellido2.getText().trim().isEmpty()) {
			user.setApellido2(tApellido2.getText().trim());
		}

		user.setTipoDocumento(cbTipoDocumento.getValue());

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
