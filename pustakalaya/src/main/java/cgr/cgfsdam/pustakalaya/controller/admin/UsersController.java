package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.users.Direccion;
import cgr.cgfsdam.pustakalaya.model.users.Role;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.users.RoleService;
import cgr.cgfsdam.pustakalaya.service.users.TipoDocumentoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador de la vista de edición de usuarios del administrador.
 *
 * @author CGR-Casa
 */
@Controller
public class UsersController extends BaseController {

	// servicios y recursos
	@Autowired
	private ResourceBundle resourceBundle;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Usuario de trabajo, que se mostrará en el panel, y que se maipulará.
	 */
	private Usuario usuarioActual;

	/* Elementos generales de la vista */

	@FXML
	private Label lblSearch;

	@FXML
	private TextField txtSearchNombre;

	@FXML
	private TextField txtSearchApellido1;

	@FXML
	private TextField txtSearchApellido2;

	@FXML
	private ListView<Usuario> UsersList;

	@FXML
	private Button btnClearSearch;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	@FXML
	private Tab tabData;

	@FXML
	private Tab tabAddress;

	@FXML
	private Tab tabStatus;

	/* elementos de la pestaña de datos de usuario */
	@FXML
	private Label lblUserData;

	@FXML
	private Label lblNombre;

	@FXML
	private TextField txtNombre;

	@FXML
	private Label lblApellido1;

	@FXML
	private TextField txtApellido1;

	@FXML
	private Label lblApellido2;

	@FXML
	private TextField txtApellido2;

	@FXML
	private Label lblTipoDocumento;

	@FXML
	private ComboBox<TipoDocumento> cmbTipoDocumento;

	@FXML
	private Label lblDocumento;

	@FXML
	private TextField txtDocumento;

	@FXML
	private TextField txtUserName;

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblPassword;

	@FXML
	private PasswordField pPassword;

	@FXML
	private Label lblConfirmPassword;

	@FXML
	private PasswordField pConfirmPassword;

	@FXML
	private Label lblPwdError;

	@FXML
	private Label lblUserDataErrors;

	/* elementos de la pestaña direccion */

	@FXML
	private Label lblUserAddress;

	@FXML
	private Label lblTipoVia;

	@FXML
	private TextField txtTipoVia;

	@FXML
	private TextField txtNombreVia;

	@FXML
	private Label lblNombreVia;

	@FXML
	private Label lblNumero;

	@FXML
	private TextField txtNumero;

	@FXML
	private Label lblPortal;

	@FXML
	private TextField txtPortal;

	@FXML
	private Label lblEscalera;

	@FXML
	private TextField txtEscalera;

	@FXML
	private Label lblPlanta;

	@FXML
	private TextField txtPlanta;

	@FXML
	private Label lblPuerta;

	@FXML
	private TextField txtPuerta;

	@FXML
	private Label lblCP;

	@FXML
	private TextField txtCP;

	@FXML
	private Label lblMunicipio;

	@FXML
	private TextField txtMunicipio;

	@FXML
	private Label lblProvincia;

	@FXML
	private TextField txtProvincia;

	@FXML
	private Label lblTelefono;

	@FXML
	private TextField txtTelefono;

	@FXML
	private Label lblEmail;

	@FXML
	private TextField txtEmail;

	@FXML
	private Label lblErrorAddress;

	/* elementos de la pestaña estaus */

	@FXML
	private Label lblStatus;

	@FXML
	private Label lblActiveUser;

	@FXML
	private CheckBox chkActive;

	@FXML
	private Label lblRole;

	@FXML
	private ComboBox<Role> cmbRol;

	@FXML
	private Label lblDateAlta;

	@FXML
	private DatePicker dpFechaAlta;

	@FXML
	private Label lblDateVigor;

	@FXML
	private DatePicker dpFechaVigor;

	@FXML
	private Label lblErrorStatus;

	/* Elementos para gestión de listados */
	private ObservableList<Usuario>		  usuarios;
	private FilteredList<Usuario>		  filteredUsuarios;
	private ObservableList<TipoDocumento> tiposDocumento = FXCollections.observableArrayList();
	private ObservableList<Role>		  roles			 = FXCollections.observableArrayList();

	@FXML
	void handleClearSearch(ActionEvent event) {

		clearView();
	}

	@FXML
	void handleEdit(ActionEvent event) {

		usuarioActual = UsersList.getSelectionModel().getSelectedItem();
		if (usuarioActual != null) {
			readUsuario();
			btnSave.setDisable(false);
		} else {
			this.sendAlert(AlertType.ERROR, resourceBundle.getString("admin.users.empty.user.title"),
					resourceBundle.getString("admin.users.empty.user.header"),
					resourceBundle.getString("admin.users.empty.user.msg"));
		}
	}

	@FXML
	void handleNew(ActionEvent event) {

		usuarioActual = new Usuario();
		usuarioActual.setFechaAlta(new Date());
		readUsuario();
		btnSave.setDisable(false);
	}

	@FXML
	void handleSave(ActionEvent event) {

		readForm();
		if (isValidUsuario()) {
			log.info("Se guardaría el usuario ->\n" + usuarioActual.toString());

			usuarioService.saveRawUsuario(usuarioActual);

			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.users.save.success.title"),
					resourceBundle.getString("admin.users.save.success.header"),
					resourceBundle.getString("admin.users.save.success.msg"));

			clearView();
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.users.save.error.title"),
					resourceBundle.getString("admin.users.save.error.header"),
					resourceBundle.getString("admin.users.save.error.msg"));
		}
	}

	@FXML
	void handleCheckPassword(KeyEvent event) {

		String pass = pPassword.getText();
		String pass2 = pConfirmPassword.getText() + event.getCharacter();
		if (!pass2.equals(pass)) {
			lblPwdError.setText(resourceBundle.getString("register.label.passwordError"));
			// lblPswError.setTextFill(Paint.valueOf("red"));
		} else {
			lblPwdError.setText("");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// inicialización de etiquetas y textos

		// bloque de filtrado de usuarios
		lblSearch.setText(resources.getString("admin.users.lblSeach"));
		txtSearchNombre.setPromptText(resources.getString("admin.users.txtNameSearch"));
		txtSearchApellido1.setPromptText(resources.getString("admin.users.txtAp1Search"));
		txtSearchApellido2.setPromptText(resources.getString("admin.users.txtAp2Search"));

		// botones generales
		btnSave.setDisable(true);
		btnSave.setText(resources.getString("admin.users.btnSave"));

		btnClearSearch.setText(resources.getString("admin.users.btnClear"));
		btnEdit.setText(resources.getString("admin.users.btnEdit"));
		btnNew.setText(resources.getString("admin.users.btnNew"));

		// pestañas
		tabData.setText(resources.getString("admin.users.tab.data"));
		tabAddress.setText(resources.getString("admin.users.tab.address"));
		tabStatus.setText(resources.getString("admin.users.tab.status"));

		// pestaña datos de usuario
		lblUserData.setText(resources.getString("register.label.userdata"));
		lblNombre.setText(resources.getString("register.label.nombre"));
		lblApellido1.setText(resources.getString("register.label.apellido1"));
		lblApellido2.setText(resources.getString("register.label.apellido2"));
		lblTipoDocumento.setText(resources.getString("register.label.tipoDocumento"));

		initializeComboTipoDocumento(resources);

		lblDocumento.setText(resources.getString("register.label.documento"));
		lblUserName.setText(resources.getString("register.label.userName"));
		lblPassword.setText(resources.getString("register.label.password"));
		lblConfirmPassword.setText(resources.getString("register.label.confirmPassword"));

		lblPwdError.setText("");
		lblUserDataErrors.setText("");

		// pestaña dirección de usuario
		lblUserAddress.setText(resources.getString("register.label.userAddress"));
		lblTipoVia.setText(resources.getString("register.label.tipoVia"));
		lblNombreVia.setText(resources.getString("register.label.via"));
		lblNumero.setText(resources.getString("register.label.numero"));
		lblPortal.setText(resources.getString("register.label.portal"));
		lblEscalera.setText(resources.getString("register.label.escalera"));
		lblPlanta.setText(resources.getString("register.label.planta"));
		lblPuerta.setText(resources.getString("register.label.puerta"));
		lblCP.setText(resources.getString("register.label.cp"));
		lblMunicipio.setText(resources.getString("register.label.localidad"));
		lblProvincia.setText(resources.getString("register.label.provincia"));
		lblTelefono.setText(resources.getString("register.label.telf"));
		lblEmail.setText(resources.getString("register.label.email"));
		lblErrorAddress.setText("");

		// pestaña de datos de configuración
		lblStatus.setText(resources.getString("register.label.status"));
		lblActiveUser.setText(resources.getString("register.label.active.user"));
		lblRole.setText(resources.getString("register.label.role"));
		initializeComboRole(resources);
		lblDateAlta.setText(resources.getString("register.label.date.alta"));
		dpFechaAlta.setDisable(true);
		lblDateVigor.setText(resources.getString("register.label.date.vigor"));
		lblErrorStatus.setText("");

		initializeUsuarioList();

	}

	/**
	 * Método que inicializa el combo de roles para que se muestren y lean
	 * correctamente.
	 * 
	 * @param resources
	 */
	private void initializeComboRole(ResourceBundle resources) {

		cmbRol.setPromptText(resources.getString("register.combo.role.placeholder"));

		cmbRol.setCellFactory(new Callback<ListView<Role>, ListCell<Role>>() {
			@Override
			public ListCell<Role> call(ListView<Role> param) {

				ListCell<Role> cell = new ListCell<Role>() {
					@Override
					protected void updateItem(Role item, boolean empty) {

						super.updateItem(item, empty);

						if (item != null) {
							setText(item.getRole());
						} else {
							setText(null);
						}
					}
				};

				return cell;
			}
		});

		cmbRol.setConverter(new StringConverter<Role>() {
			@Override
			public String toString(Role role) {

				if (role == null) {
					return "";
				} else {
					return role.getRole();
				}
			}

			@Override
			public Role fromString(String nombre) {

				return roleService.findByRole(nombre);
			}
		});

		loadRoles();
	}

	/**
	 * Método privado que carga los roles de base de datos en el combo.
	 */
	private void loadRoles() {

		roles.clear();
		roles.addAll(roleService.findAll());
		cmbRol.setItems(roles);
	}

	/**
	 * Método privado para inicializar correctamente el combo del tipo de documento.
	 * 
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
	 * Método privado para cargar los tipos de documento de la base de datos en el
	 * combo.
	 */
	private void loadTipoDocumento() {

		tiposDocumento.clear();
		tiposDocumento.addAll(tipoDocumentoService.findAll());
		cmbTipoDocumento.setItems(tiposDocumento);
	}

	/**
	 * Método que inicializa la lista de usuarios y la vincula con los filtros de búsqueda.
	 */
	private void initializeUsuarioList() {

		// inicia el observable list
		usuarios = FXCollections.observableArrayList();
		usuarios.clear();
		usuarios.addAll(usuarioService.findAll());

		// inicia el filteredList
		filteredUsuarios = new FilteredList<>(usuarios, u -> true);

		txtSearchNombre.textProperty().addListener(obs -> {
			filteredUsuarios.setPredicate(u -> testFilteredUser(u));
		});

		txtSearchApellido1.textProperty().addListener(obs -> {
			filteredUsuarios.setPredicate(u -> testFilteredUser(u));
		});

		txtSearchApellido2.textProperty().addListener(obs -> {
			filteredUsuarios.setPredicate(u -> testFilteredUser(u));
		});

		UsersList.setItems(filteredUsuarios);

		UsersList.setCellFactory(new Callback<ListView<Usuario>, ListCell<Usuario>>() {

			@Override
			public ListCell<Usuario> call(ListView<Usuario> param) {

				ListCell<Usuario> cell = new ListCell<Usuario>() {
					protected void updateItem(Usuario item, boolean empty) {

						super.updateItem(item, empty);

						if (item != null) {
							setText(item.getNombre() + " " + item.getApellido1()
									+ ( MyUtils.isEmptyString(item.getApellido2()) ? "" : " " + item.getApellido2()));
						} else {
							setText(null);
						}
					}
				};

				return cell;
			}
		});
	}

	/**
	 * Metodo privado para filtar los usuarios de la lista teniendo en cuenta los
	 * tres campos que se usan en la comparación.
	 * 
	 * @param usuario
	 *            Usuario a comprobar si cumple con el filtro o no
	 * @return boolean true si el usuario coincide con los criterios de búsqueda o
	 *         false si no lo hace
	 */
	private boolean testFilteredUser(Usuario usuario) {

		String entradaNombre = txtSearchNombre.getText().toLowerCase();
		String entradaAp1 = txtSearchApellido1.getText().toLowerCase();
		String entradaAp2 = txtSearchApellido2.getText().toLowerCase();

		log.debug(
				"Entradas: entradaNombre=" + entradaNombre + " entradaAp1=" + entradaAp1 + " entradaAp2=" + entradaAp2);

		boolean matchNombre = entradaNombre == null || entradaNombre.length() < 3
				|| usuario.getNombre().toLowerCase().contains(entradaNombre);
		boolean matchAp1 = entradaAp1 == null || entradaAp1.length() < 3
				|| usuario.getApellido1().toLowerCase().contains(entradaAp1);
		boolean matchAp2 = entradaAp2 == null || entradaAp2.length() < 3
				|| usuario.getApellido2().toLowerCase().contains(entradaAp2);

		log.debug("Matches: matchNombre=" + matchNombre + " matchAp1=" + matchAp1 + " matchAp2=" + matchAp2);

		return matchNombre && matchAp1 && matchAp2;
	}

	/**
	 * Metodo que lee los datos del formulario y los traslada al usuario en memoria.
	 */
	private void readForm() {

		if (usuarioActual != null) {
			// datos de usuario
			usuarioActual.setNombre(txtNombre.getText());
			usuarioActual.setApellido1(txtApellido1.getText());
			usuarioActual.setApellido2(txtApellido2.getText());
			usuarioActual.setTipoDocumento(cmbTipoDocumento.getSelectionModel().getSelectedItem());
			usuarioActual.setDocumento(txtDocumento.getText());
			usuarioActual.setUsername(txtUserName.getText());

			String p1 = pPassword.getText();
			String p2 = pConfirmPassword.getText();
			if (!p1.isEmpty() && !p2.isEmpty() && p1.equals(p2)) {
				usuarioActual.setPassword(bCryptPasswordEncoder.encode(p1));
			}

			// datos de dirección
			Direccion direccionForm = null;
			if (usuarioActual.getDireccion() != null) {
				direccionForm = usuarioActual.getDireccion();
			} else {
				direccionForm = new Direccion();
			}
			direccionForm.setTipoVia(txtTipoVia.getText());
			direccionForm.setVia(txtNombreVia.getText());
			direccionForm.setNumero(txtNumero.getText());
			direccionForm.setPortal(txtPortal.getText());
			direccionForm.setEscalera(txtEscalera.getText());
			direccionForm.setPlanta(txtPlanta.getText());
			direccionForm.setPuerta(txtPuerta.getText());
			direccionForm.setCp(txtCP.getText());
			direccionForm.setMunicipio(txtMunicipio.getText());
			direccionForm.setProvincia(txtProvincia.getText());

			usuarioActual.setDireccion(direccionForm);

			usuarioActual.setTelefono(txtTelefono.getText());
			usuarioActual.setEmail(txtEmail.getText());

			// datos de estado
			usuarioActual.setActive(chkActive.isSelected() ? 1 : 0);
			if (cmbRol.getSelectionModel().getSelectedItem() != null) {
				usuarioActual.setRoles(new HashSet<Role>(Arrays.asList(cmbRol.getSelectionModel().getSelectedItem())));
			} else {
				usuarioActual.setRoles(null);
			}
			if (dpFechaAlta.getValue() != null) {
				usuarioActual.setFechaAlta(MyUtils.fromLocalToDate(dpFechaAlta.getValue()));
			}
			if (dpFechaVigor.getValue() != null) {
				usuarioActual.setFechaVigor(MyUtils.fromLocalToDate(dpFechaVigor.getValue()));
			}
		}
	}

	/**
	 * Metodo que lee los datos del usuario en memoria y los traslada al formulario.
	 */
	private void readUsuario() {

		if (usuarioActual != null) {
			// user data
			txtNombre.setText(usuarioActual.getNombre());
			txtApellido1.setText(usuarioActual.getApellido1());
			txtApellido2.setText(isEmptyString(usuarioActual.getApellido2()) ? "" : usuarioActual.getApellido2());
			cmbTipoDocumento.getSelectionModel().select(usuarioActual.getTipoDocumento());
			txtDocumento.setText(usuarioActual.getDocumento());
			txtUserName.setText(usuarioActual.getUsername());

			lblPwdError.setText("");
			lblUserDataErrors.setText("");

			// user addres
			// datos de dirección
			Direccion direccionUser = null;
			if (usuarioActual.getDireccion() != null) {
				direccionUser = usuarioActual.getDireccion();
			} else {
				direccionUser = new Direccion();
			}

			txtTipoVia.setText(direccionUser.getTipoVia());
			txtNombreVia.setText(direccionUser.getVia());
			txtNumero.setText(direccionUser.getNumero());
			txtPortal.setText(direccionUser.getPortal());
			txtEscalera.setText(direccionUser.getEscalera());
			txtPlanta.setText(direccionUser.getPlanta());
			txtPuerta.setText(direccionUser.getPuerta());
			txtCP.setText(direccionUser.getCp());
			txtMunicipio.setText(direccionUser.getMunicipio());
			txtProvincia.setText(direccionUser.getProvincia());
			txtTelefono.setText(usuarioActual.getTelefono());
			txtEmail.setText(usuarioActual.getEmail());

			lblErrorAddress.setText("");

			// user status
			chkActive.setSelected(usuarioActual.getActive() == 1);
			Role userRole = null;
			if (usuarioActual.getRoles() != null) {
				userRole = usuarioActual.getRoles().stream().findFirst().orElse(null);
				if (userRole != null) {
					cmbRol.getSelectionModel().select(userRole);
				}
			} else {
				cmbRol.getSelectionModel().clearSelection();
				cmbRol.setValue(null);
			}

			if (usuarioActual.getFechaAlta() != null) {
				dpFechaAlta.setValue(MyUtils.fromDateToLocal(usuarioActual.getFechaAlta()));
			} else {
				dpFechaAlta.getEditor().clear();
				dpFechaAlta.setValue(null);
			}

			if (usuarioActual.getFechaVigor() != null) {
				dpFechaVigor.setValue(MyUtils.fromDateToLocal(usuarioActual.getFechaVigor()));
			} else {
				dpFechaVigor.getEditor().clear();
				dpFechaVigor.setValue(null);
			}

		} else {
			// user data
			txtNombre.clear();
			txtApellido1.clear();
			txtApellido2.clear();
			cmbTipoDocumento.getSelectionModel().clearSelection();
			cmbTipoDocumento.setValue(null);
			txtDocumento.clear();
			txtUserName.clear();
			pPassword.clear();
			pConfirmPassword.clear();
			lblPwdError.setText("");
			lblUserDataErrors.setText("");

			// user addres

			txtTipoVia.clear();
			txtNombreVia.clear();
			txtNumero.clear();
			txtPortal.clear();
			txtEscalera.clear();
			txtPlanta.clear();
			txtPuerta.clear();
			txtCP.clear();
			txtMunicipio.clear();
			txtProvincia.clear();
			txtTelefono.clear();
			txtEmail.clear();
			lblErrorAddress.setText("");

			// user status
			chkActive.setSelected(false);
			cmbRol.getSelectionModel().clearSelection();
			cmbRol.setValue(null);
			dpFechaAlta.getEditor().clear();
			dpFechaAlta.setValue(null);
			dpFechaVigor.getEditor().clear();
			dpFechaVigor.setValue(null);
			lblErrorStatus.setText("");

		}
	}

	/**
	 * Método que valida si los datos que tiene el usuario son suficientes para
	 * 
	 * @return
	 */
	private boolean isValidUsuario() {

		String errorDataMsg = "";
		String errorAddressMsg = "";
		String errorStatusMsg = "";

		boolean isValido = true;

		// check datos de usuario
		if (isEmptyString(usuarioActual.getNombre())) {
			errorDataMsg += resourceBundle.getString("user.validation.nombreError");
			isValido = false;
		}
		if (isEmptyString(usuarioActual.getApellido1())) {
			errorDataMsg += resourceBundle.getString("user.validation.apellido1Error");
			isValido = false;
		}
		if (usuarioActual.getTipoDocumento() == null) {
			errorDataMsg += resourceBundle.getString("user.validation.tipoDocumentoError");
			isValido = false;
		}
		if (isEmptyString(usuarioActual.getDocumento())) {
			errorDataMsg += resourceBundle.getString("user.validation.documentoError");
			isValido = false;
		}
		if (isEmptyString(usuarioActual.getUsername())) {
			errorDataMsg += resourceBundle.getString("user.validation.usernameError");
			isValido = false;
		} else {
			Usuario u = usuarioService.findByUsername(usuarioActual.getUsername());
			if (u != null && u.getId() != usuarioActual.getId()) {
				errorDataMsg += resourceBundle.getString("user.validation.usernameExists");
				isValido = false;
			}
		}

		if (isEmptyString(usuarioActual.getPassword())) {
			errorDataMsg += resourceBundle.getString("user.validation.passwordError");
			isValido = false;
		}

		lblUserDataErrors.setText(errorDataMsg);

		// check datos de address
		if (isEmptyString(usuarioActual.getEmail())) {
			errorAddressMsg += resourceBundle.getString("user.validation.emailError");
			isValido = false;
		} else {
			Usuario u = usuarioService.findByEmail(usuarioActual.getEmail());
			if (u != null && u.getId() != usuarioActual.getId()) {
				errorAddressMsg += resourceBundle.getString("user.validation.emailAlreadyExists");
				isValido = false;
			}
		}

		lblErrorAddress.setText(errorAddressMsg);

		// check status
		if (usuarioActual.getRoles() == null) {
			errorStatusMsg += resourceBundle.getString("user.validation.roleError");
			isValido = false;
		} else {
			Role currentRole = usuarioActual.getRoles().stream().findFirst().orElse(null);
			if (currentRole == null) {
				errorStatusMsg += resourceBundle.getString("user.validation.roleError");
				isValido = false;
			}
		}

		if (usuarioActual.getFechaAlta() == null) {
			errorStatusMsg += resourceBundle.getString("user.validation.fechaAltaError");
			isValido = false;
		}

		if (usuarioActual.getFechaVigor() == null) {
			errorStatusMsg += resourceBundle.getString("user.validation.fechaVigorError");
			isValido = false;
		}

		lblErrorStatus.setText(errorStatusMsg);

		return isValido;
	}

	/**
	 * Util para comprobar si un String es nulo o vacio.
	 * 
	 * @param value
	 *            String a evaluar.
	 * @return boolean <code>true</code> si la cadena esta vacio, o
	 *         <code>false</code> en caso contrario.
	 */
	private boolean isEmptyString(String value) {

		return value == null || value.isEmpty();
	}

	/**
	 * Método que limpia todos los campos del formulario, así como el usuario en memoria y los campos de filtrado.
	 */
	private void clearView() {

		txtSearchNombre.setText("");
		txtSearchApellido1.setText("");
		txtSearchApellido2.setText("");
		usuarioActual = null;

		initializeUsuarioList();
		// UsersList.getSelectionModel().clearSelection();
		readUsuario();
		btnSave.setDisable(true);
	}

}
