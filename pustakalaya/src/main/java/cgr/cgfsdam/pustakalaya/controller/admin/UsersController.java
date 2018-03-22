package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.users.TipoDocumentoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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

	//servicios y recursos
	@Autowired
	ResourceBundle resourceBundle;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Usuario de trabajo, que se mostrará en el panel, y que se maipulará.
	 */
	private Usuario usuario;

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




    /* Elementos para gestión de listados */
	private ObservableList<Usuario> usuarios;
	private FilteredList<Usuario> filteredUsuarios;
	private ObservableList<TipoDocumento> tiposDocumento = FXCollections.observableArrayList();

	@FXML
	void handleClearSearch(ActionEvent event) {
		txtSearchNombre.setText("");
		txtSearchApellido1.setText("");
		txtSearchApellido2.setText("");
		usuario = null;
		UsersList.getSelectionModel().clearSelection();
		readUsuario();
	}

	@FXML
	void handleEdit(ActionEvent event) {
		usuario = UsersList.getSelectionModel().getSelectedItem();
		if (usuario != null) {
			readUsuario();
		} else {
			this.sendAlert(
					AlertType.ERROR, 
					resourceBundle.getString("admin.users.empty.user.title"), 
					resourceBundle.getString("admin.users.empty.user.header"), 
					resourceBundle.getString("admin.users.empty.user.msg")
			);
		}
	}

	@FXML
	void handleNew(ActionEvent event) {
		usuario = new Usuario();
		readUsuario();
	}

	@FXML
	void handleSave(ActionEvent event) {
		readForm();
		if(isValidUsuario()) {
			usuarioService.saveRawUsuario(usuario);
		} else {
			this.sendAlert(
					AlertType.ERROR, 
					resourceBundle.getString("admin.users.save.error.title"), 
					resourceBundle.getString("admin.users.save.error.header"), 
					resourceBundle.getString("admin.users.save.error.msg")
			);
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
		//inicialización de etiquetas y textos
		
		//bloque de filtrado de usuarios
		lblSearch.setText(resources.getString("admin.users.lblSeach"));
		txtSearchNombre.setPromptText(resources.getString("admin.users.txtNameSearch"));
		txtSearchApellido1.setPromptText(resources.getString("admin.users.txtAp1Search"));
		txtSearchApellido2.setPromptText(resources.getString("admin.users.txtAp2Search"));
		
		//botones generales
		btnSave.setDisable(false);
		btnSave.setText(resources.getString("admin.users.btnSave"));
		
		btnClearSearch.setText(resources.getString("admin.users.btnClear"));
		btnEdit.setText(resources.getString("admin.users.btnEdit"));
		btnNew.setText(resources.getString("admin.users.btnNew"));
		
		//pestañas
		tabData.setText(resources.getString("admin.users.tab.data"));
		tabAddress.setText(resources.getString("admin.users.tab.address"));
		tabStatus.setText(resources.getString("admin.users.tab.status"));
		
		//pestaña datos de usuario
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

		//pestaña dirección de usuario
		
		//pestaña de datos de configuración
		
		
		
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
									+ (item.getApellido2().isEmpty() ? "" : " " + item.getApellido2()));
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
	 * Metodo privado para filtar los usuarios de la lista teniendo en cuenta los tres campos que se usan en la 
	 * comparación.
	 * 
	 * @param usuario Usuario a comprobar si cumple con el filtro o no
	 * @return boolean true si el usuario coincide con los criterios de búsqueda o false si no lo hace
	 */
	private boolean testFilteredUser(Usuario usuario) {
		String entradaNombre = txtSearchNombre.getText().toLowerCase();
		String entradaAp1 = txtSearchApellido1.getText().toLowerCase();
		String entradaAp2 = txtSearchApellido2.getText().toLowerCase();
		
		log.debug("Entradas: entradaNombre=" + entradaNombre + " entradaAp1=" + entradaAp1 + " entradaAp2=" + entradaAp2);

		boolean matchNombre = entradaNombre == null || entradaNombre.length() < 3
				|| usuario.getNombre().toLowerCase().contains(entradaNombre);
		boolean matchAp1 = entradaAp1 == null || entradaAp1.length() < 3
				|| usuario.getApellido1().toLowerCase().contains(entradaAp1);
		boolean matchAp2  = entradaAp2 == null || entradaAp2.length() < 3
				|| usuario.getApellido2().toLowerCase().contains(entradaAp2);

		log.debug("Matches: matchNombre=" + matchNombre + " matchAp1=" + matchAp1 + " matchAp2=" + matchAp2);

		return matchNombre && matchAp1 && matchAp2;
	}

	/**
	 * Metodo que lee los datos del formulario y los traslada al usuario en memoria.
	 */
	private void readForm() {
		if (usuario != null) {
			//datos de usuario
			usuario.setNombre(txtNombre.getText());
			usuario.setApellido1(txtApellido1.getText());
			usuario.setApellido2(txtApellido2.getText());
			usuario.setTipoDocumento(cmbTipoDocumento.getSelectionModel().getSelectedItem());
			usuario.setDocumento(txtDocumento.getText());
			usuario.setUsername(txtUserName.getText());
			
			String p1 = pPassword.getText();
			String p2 = pConfirmPassword.getText();
			if (!p1.isEmpty() && !p2.isEmpty() && p1.equals(p2)) {
				usuario.setPassword(bCryptPasswordEncoder.encode(p1));
			}
			
		}
		
		
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo que lee los datos del usuario en memoria y los traslada al formulario.
	 */
	private void readUsuario() {
		if (usuario != null) {
			//user data
			txtNombre.setText(usuario.getNombre());
			txtApellido1.setText(usuario.getApellido1());
			txtApellido2.setText(usuario.getApellido2().isEmpty()? "" : usuario.getApellido2());
			cmbTipoDocumento.getSelectionModel().select(usuario.getTipoDocumento());
			txtDocumento.setText(usuario.getDocumento());
			txtUserName.setText(usuario.getUsername());
			
			lblUserDataErrors.setText("");
			
			//user addres
			
			//user status
		} else {
			//user data
			txtNombre.clear();
			txtApellido1.clear();
			txtApellido2.clear();
			cmbTipoDocumento.getSelectionModel().clearSelection();
			cmbTipoDocumento.setValue(null);
			txtDocumento.clear();
			txtUserName.clear();
			pPassword.clear();
			pConfirmPassword.clear();
			//user addres
			
			//user status
			
			
		}
	}

	/**
	 * Método que valida si los datos que tiene el usuario son suficientes para 
	 * @return
	 */
	private boolean isValidUsuario() {
		String errorMsg = "";
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return false;
	}

}
