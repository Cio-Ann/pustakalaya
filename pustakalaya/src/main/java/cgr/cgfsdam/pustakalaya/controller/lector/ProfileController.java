package cgr.cgfsdam.pustakalaya.controller.lector;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.users.Direccion;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.service.users.TipoDocumentoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador de la vista de perfil para usuarios con rol lector.
 *
 * @author CGR-Casa
 */
@Controller
public class ProfileController extends BaseController {

	@Autowired
	ResourceBundle resourceBundle;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

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
	private ComboBox<TipoDocumento> cmbTipoDocumento;

	@FXML
	private TextField tDocumento;

	@FXML
	private Label lblDocumento;

	@FXML
	private Label lblUserName;

	@FXML
	private TextField tUserName;

	@FXML
	private Label lblPassword;

	@FXML
	private PasswordField pPassword;

	@FXML
	private PasswordField pConfirmPassword;

	@FXML
	private Label lblConfirmPassword;

	@FXML
	private Label lblPswError;

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
	private TextField tPuerta;

	@FXML
	private Label lblPuerta;

	@FXML
	private Label lblCP;

	@FXML
	private TextField tCP;

	@FXML
	private Label lblPoblacion;

	@FXML
	private TextField tPoblacion;

	@FXML
	private Label lblProvincia;

	@FXML
	private TextField tProvincia;

	@FXML
	private Label lblTelefono;

	@FXML
	private TextField tTelefono;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField tEmail;

	@FXML
	private Label lblValidationError;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnRestore;

	private ObservableList<TipoDocumento> tiposDocumento = FXCollections.observableArrayList();

	/**
	 * Método para evaluar si la contraseña y la confirmación son iguales.
	 * Se ejecuta cada vez ue se escribe en el campo de confirmación de contraseña.
	 * 
	 * @param event KeyEvent evento que origino la ejecución.
	 */
	@FXML
	void handleCheckPassword(KeyEvent event) {
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
	 * Método para restaurar los valores del usuario.
	 * Se ejecuta al pulsar el botón restore.
	 * 
	 * @param event ActionEvent evento que provoca la ejecución.
	 */
	@FXML
	void handleRestore(ActionEvent event) {
		loadUsuarioToView();
		clearErrors();
	}

	/**
	 * Método para actualizar el usuario, tanto en memoria como en base de datos.
	 * 
	 * @param event ActionEvent evento que provoca la ejecución.
	 */
	@FXML
	void handleUpdate(ActionEvent event) {
		loadViewToUsuario();

		usuarioService.saveUsuario(getUsuario());
		
	}

	/**
	 * Carga inicial de los valores de las etiquetas y campos de la vista.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// carga etiquetas
		lblUserData.setText(resources.getString("register.label.userdata"));
		lblUserAddress.setText(resources.getString("register.label.userAddress"));

		lblNombre.setText(resources.getString("register.label.nombre"));
		lblApellido1.setText(resources.getString("register.label.apellido1"));
		lblApellido2.setText(resources.getString("register.label.apellido2"));
		lblTipoDocumento.setText(resources.getString("register.label.tipoDocumento"));

		initializeComboTipoDocumento(resources);

		lblDocumento.setText(resources.getString("register.label.documento"));
		lblUserName.setText(resources.getString("register.label.userName"));
		lblPassword.setText(resources.getString("register.label.password"));
		lblConfirmPassword.setText(resources.getString("register.label.confirmPassword"));

		clearErrors();

		lblDocumento.setText(resources.getString("register.label.documento"));
		lblDocumento.setText(resources.getString("register.label.documento"));

		lblTipoVia.setText(resources.getString("register.label.tipoVia"));
		lblVia.setText(resources.getString("register.label.via"));
		lblNumero.setText(resources.getString("register.label.numero"));
		lblPortal.setText(resources.getString("register.label.portal"));
		lblEscalera.setText(resources.getString("register.label.escalera"));
		lblPlanta.setText(resources.getString("register.label.planta"));
		lblPuerta.setText(resources.getString("register.label.puerta"));
		lblPoblacion.setText(resources.getString("register.label.localidad"));
		lblProvincia.setText(resources.getString("register.label.provincia"));
		lblCP.setText(resources.getString("register.label.cp"));
		lblTelefono.setText(resources.getString("register.label.telf"));
		lblEmail.setText(resources.getString("register.label.email"));

		btnRestore.setText(resources.getString("profile.button.restore"));
		btnUpdate.setText(resources.getString("profile.button.update"));

		// crea la lista de documentos
		loadTipoDocumento();

		// recupera el usuario si el padre no se lo inyectó
		loadUsuarioFromSecurity();

		// carga usuario
		loadUsuarioToView();
	}

	/**
	 * Método privado para inicializar correctamente el combo del tipo de documento.
	 * @param resources
	 */
	private void initializeComboTipoDocumento(ResourceBundle resources) {
		// establece el texto cuando no hay selección
		cmbTipoDocumento.setPromptText(resources.getString("register.comboBox.tipoDocumento"));

		// establece la conversión entre el tipo TipoDocumento y el String mostrado en
		// el combo desplegable
		cmbTipoDocumento.setCellFactory(new Callback<ListView<TipoDocumento>, ListCell<TipoDocumento>>() {
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
		cmbTipoDocumento.setConverter(new StringConverter<TipoDocumento>() {
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
	 * Método privado para cargar los tipos de documento de la base de datos en el combo.
	 */
	private void loadTipoDocumento() {
		tiposDocumento.clear();
		tiposDocumento.addAll(tipoDocumentoService.findAll());
		cmbTipoDocumento.setItems(tiposDocumento);
	}

	/**
	 * Carga los valores del usuario en memoria(autenticado) en los campos de la vista.
	 */
	private void loadUsuarioToView() {
		if (!isEmptyString(getUsuario().getNombre())) {
			tNombre.clear();
			tNombre.setText(getUsuario().getNombre());
		}
		if (!isEmptyString(getUsuario().getApellido1())) {
			tApellido1.clear();
			tApellido1.setText(getUsuario().getApellido1());
		}
		if (!isEmptyString(getUsuario().getApellido2())) {
			tApellido2.clear();
			tApellido2.setText(getUsuario().getApellido2());
		}
		if (!isEmptyTipoDocumento()) {
			cmbTipoDocumento.getSelectionModel().clearSelection();
			cmbTipoDocumento.getSelectionModel().select(getUsuario().getTipoDocumento());
		}
		if (!isEmptyString(getUsuario().getDocumento())) {
			tDocumento.clear();
			tDocumento.setText(getUsuario().getDocumento());
		}
		if (!isEmptyString(getUsuario().getUsername())) {
			tUserName.clear();
			tUserName.setText(getUsuario().getUsername());
		}
		if (!isEmptyString(getUsuario().getTelefono())) {
			tTelefono.clear();
			tTelefono.setText(getUsuario().getTelefono());
		}
		if (!isEmptyString(getUsuario().getEmail())) {
			tEmail.clear();
			tEmail.setText(getUsuario().getEmail());
		}

		pPassword.clear();
		pConfirmPassword.clear();

		if (getUsuario().getDireccion() != null) {
			loadDireccionToView();
		}
	}

	/**
	 * Carga los valores actuales de la vista al usuario en memoria y lo almacena en base de datos.
	 */
	private void loadViewToUsuario() {
		// recorro todos los campos
		if (!isEmptyString(tNombre.getText()) && !tNombre.getText().equals(getUsuario().getNombre())) {
			getUsuario().setNombre(tNombre.getText());
		}
		if (!isEmptyString(tApellido1.getText()) && !tApellido1.getText().equals(getUsuario().getApellido1())) {
			getUsuario().setApellido1(tApellido1.getText());
		}
		if (!isEmptyString(tApellido2.getText()) && !tApellido2.getText().equals(getUsuario().getApellido2())) {
			getUsuario().setApellido2(tApellido2.getText());
		}
		if (cmbTipoDocumento.getSelectionModel().getSelectedItem() != null
				&& !cmbTipoDocumento.getSelectionModel().getSelectedItem().equals(getUsuario().getTipoDocumento())) {
			getUsuario().setTipoDocumento(cmbTipoDocumento.getSelectionModel().getSelectedItem());
		}
		if (!isEmptyString(tDocumento.getText()) && !tDocumento.getText().equals(getUsuario().getDocumento())) {
			getUsuario().setDocumento(tDocumento.getText());
		}
		if (!isEmptyString(tUserName.getText()) && !tUserName.getText().equals(getUsuario().getUsername())) {
			getUsuario().setUsername(tUserName.getText());
		}
		if (!isEmptyString(pPassword.getText()) && !isEmptyString(pConfirmPassword.getText())
				&& pPassword.getText().equals(pConfirmPassword.getText())) {
			// si se ha escrito contraseña, debe actualizarse en el usuario
			getUsuario().setPassword(pPassword.getText());
		}
		if (!isEmptyString(tTelefono.getText()) && !tTelefono.getText().equals(getUsuario().getTelefono())) {
			getUsuario().setTelefono(tTelefono.getText());
		}
		if (!isEmptyString(tEmail.getText()) && !tEmail.getText().equals(getUsuario().getEmail())) {
			getUsuario().setEmail(tEmail.getText());
		}

		// datos de la dirección
		if (!isEmptyString(tTipoVia.getText())
				&& !tTipoVia.getText().equals(getUsuario().getDireccion().getTipoVia())) {
			getUsuario().getDireccion().setTipoVia(tTipoVia.getText());
		}
		if (!isEmptyString(tVia.getText())
				&& !tVia.getText().equals(getUsuario().getDireccion().getVia())) {
			getUsuario().getDireccion().setVia(tVia.getText());
		}
		if (!isEmptyString(tNumero.getText())
				&& !tNumero.getText().equals(getUsuario().getDireccion().getNumero())) {
			getUsuario().getDireccion().setNumero(tNumero.getText());
		}
		if (!isEmptyString(tPortal.getText())
				&& !tPortal.getText().equals(getUsuario().getDireccion().getPortal())) {
			getUsuario().getDireccion().setPortal(tPortal.getText());
		}
		if (!isEmptyString(tEscalera.getText())
				&& !tEscalera.getText().equals(getUsuario().getDireccion().getEscalera())) {
			getUsuario().getDireccion().setEscalera(tEscalera.getText());
		}
		if (!isEmptyString(tPlanta.getText())
				&& !tPlanta.getText().equals(getUsuario().getDireccion().getPlanta())) {
			getUsuario().getDireccion().setPlanta(tPlanta.getText());
		}
		if (!isEmptyString(tPuerta.getText())
				&& !tPuerta.getText().equals(getUsuario().getDireccion().getPuerta())) {
			getUsuario().getDireccion().setPuerta(tPuerta.getText());
		}
		if (!isEmptyString(tCP.getText())
				&& !tCP.getText().equals(getUsuario().getDireccion().getCp())) {
			getUsuario().getDireccion().setCp(tCP.getText());
		}
		if (!isEmptyString(tPoblacion.getText())
				&& !tPoblacion.getText().equals(getUsuario().getDireccion().getMunicipio())) {
			getUsuario().getDireccion().setMunicipio(tPoblacion.getText());
		}
		if (!isEmptyString(tProvincia.getText())
				&& !tProvincia.getText().equals(getUsuario().getDireccion().getProvincia())) {
			getUsuario().getDireccion().setProvincia(tProvincia.getText());
		}
	}

	/**
	 * Carga el usuario del contexto de seguridad de Spring.
	 */
	private void loadUsuarioFromSecurity() {
		if (getUsuario() == null) {
			log.info("No existe usuario en el controller");
			String securityUser = SecurityContextHolder.getContext().getAuthentication().getName();
			log.info("El usuario autenticado es: " + securityUser);

			setUsuario(usuarioService.findByUsername(securityUser));
		}
	}

	/**
	 * Carga la dirección almacenada en el usuario en los campos de la vista destinados a la dirección.
	 */
	private void loadDireccionToView() {
		Direccion currentAddress = getUsuario().getDireccion();

		if (!isEmptyString(currentAddress.getTipoVia())) {
			tTipoVia.clear();
			tTipoVia.setText(currentAddress.getTipoVia());
		}
		if (!isEmptyString(currentAddress.getVia())) {
			tVia.clear();
			tVia.setText(currentAddress.getVia());
		}
		if (!isEmptyString(currentAddress.getNumero())) {
			tNumero.clear();
			tNumero.setText(currentAddress.getNumero());
		}
		if (!isEmptyString(currentAddress.getPortal())) {
			tPortal.clear();
			tPortal.setText(currentAddress.getPortal());
		}
		if (!isEmptyString(currentAddress.getEscalera())) {
			tEscalera.clear();
			tEscalera.setText(currentAddress.getEscalera());
		}
		if (!isEmptyString(currentAddress.getPlanta())) {
			tPlanta.clear();
			tPlanta.setText(currentAddress.getPlanta());
		}
		if (!isEmptyString(currentAddress.getPuerta())) {
			tPuerta.clear();
			tPuerta.setText(currentAddress.getPuerta());
		}
		if (!isEmptyString(currentAddress.getCp())) {
			tCP.clear();
			tCP.setText(currentAddress.getCp());
		}
		if (!isEmptyString(currentAddress.getMunicipio())) {
			tPoblacion.clear();
			tPoblacion.setText(currentAddress.getMunicipio());
		}
		if (!isEmptyString(currentAddress.getProvincia())) {
			tProvincia.clear();
			tProvincia.setText(currentAddress.getProvincia());
		}

	}

	/**
	 * Elimina los mensajes de error de la vista.
	 */
	private void clearErrors() {
		lblPswError.setText("");
		lblValidationError.setText("");
	}

	/**
	 * Util para comprobar si un String es nulo o vacio.
	 * 
	 * @param value String a evaluar.
	 * @return boolean <code>true</code> si la cadena esta vacio, o <code>false</code> en caso contrario.
	 */
	private boolean isEmptyString(String value) {
		return value == null || value.isEmpty();
	}

	/**
	 * Util para evaluar si el tipo de documento del usuario en memoria es núlo o está vacio.
	 * @return boolean <code>true</code> si el TipoDocumento está vacio, o <code>false</code> en caso contrario.
	 */
	private boolean isEmptyTipoDocumento() {
		return getUsuario().getTipoDocumento() == null || isEmptyString(getUsuario().getTipoDocumento().getNombre());
	}

}
