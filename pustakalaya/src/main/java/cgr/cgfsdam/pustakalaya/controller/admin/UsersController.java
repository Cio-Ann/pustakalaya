package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * Controlador de la vista de edición de usuarios del administrador.
 *
 * @author CGR-Casa
 */
@Controller
public class UsersController extends BaseController {

	@FXML
	private TextField txtAp1;

	@FXML
	private ListView<Usuario> UsersList;

	@FXML
	private Button btnClearSearch;

	@FXML
	private TextField txtAp2;

	@FXML
	private TextField txtNombre;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	@FXML
	private Label lblSearch;

//	@FXML
//	void checkNameField(ActionEvent event) {
//
//	}
//
//	@FXML
//	void checkAp1(ActionEvent event) {
//
//	}
//
//	@FXML
//	void checkAp2(ActionEvent event) {
//
//	}

	@FXML
	void handleClearSearch(ActionEvent event) {

	}

	@FXML
	void handleEdit(ActionEvent event) {

	}

	@FXML
	void handleNew(ActionEvent event) {

	}

	@FXML
	void handleSave(ActionEvent event) {

	}

	@Autowired
	private UsuarioService usuarioService;

	ObservableList<Usuario> usuarios;
	FilteredList<Usuario> filteredUsuarios;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//inicia el observable list
		usuarios = FXCollections.observableArrayList();
		usuarios.clear();
		usuarios.addAll(usuarioService.findAll());

		//inicia el filteredList
		filteredUsuarios = new FilteredList<>(usuarios, u -> true);
		
		txtNombre.textProperty().addListener(obs -> {
			String filtro = txtNombre.getText();
			if(filtro == null || filtro.length() < 3) {
				filteredUsuarios.setPredicate(u -> true);
			} else {
				filteredUsuarios.setPredicate(u -> u.getNombre().contains(filtro));
			}
		});

		txtAp1.textProperty().addListener(obs -> {
			String filtro = txtAp1.getText();
			if(filtro == null || filtro.length() < 3) {
				filteredUsuarios.setPredicate(u -> true);
			} else {
				filteredUsuarios.setPredicate(u -> u.getApellido1().contains(filtro));
			}
		});

		txtAp2.textProperty().addListener(obs -> {
			String filtro = txtAp2.getText();
			if(filtro == null || filtro.length() < 3) {
				filteredUsuarios.setPredicate(u -> true);
			} else {
				filteredUsuarios.setPredicate(u -> u.getApellido2().contains(filtro));
			}
		});
		

		UsersList.setItems(filteredUsuarios);
		
		UsersList.setCellFactory(new Callback<ListView<Usuario>, ListCell<Usuario>>() {
			
			@Override
			public ListCell<Usuario> call(ListView<Usuario> param) {
				ListCell<Usuario> cell = new ListCell<Usuario>() {
					protected void updateItem(Usuario item, boolean empty) {
						super.updateItem(item, empty);
						
						if (item != null ) {
							setText(item.getNombre() + " " + item.getApellido1() 
									+ (item.getApellido2().isEmpty()? "" : " " + item.getApellido2()));
						} else {
							setText(null);
						}
					}
				};
				
				return cell;
			}
		});
		
		
		

	}

}
