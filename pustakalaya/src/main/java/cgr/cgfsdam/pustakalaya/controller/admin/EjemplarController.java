package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.funds.EstadoEnum;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.users.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controlador del formulario de creación / edicion de ejemplares.
 * 
 * Un ejemplar solo se puede crear a partir de un Recurso.
 *
 * @author CGR-Casa
 */
@Controller
public class EjemplarController extends BaseController {

	@Autowired
	private ResourceBundle resourceBundle;
	
	private Recurso recurso;
	private Ejemplar ejemplar;
	
	@Autowired
	private EjemplarService ejemplarService;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lglRecurso;
	
	@FXML
	private TextField txtRecurso;
	
	@FXML
	private Label lblCodigo;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Label lblEstado;
	
	@FXML
	private ComboBox<EstadoEnum> cbEstado;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Button btnSave;
	

	private ObservableList<EstadoEnum> estados = FXCollections.observableArrayList();

	/**
	 * Método que se ejecuta al pulsar el botón salir. Cierra la ventana sin guardar información.
	 * 
	 * @param event evento que inicia la ejecución.
	 */
	@FXML
	void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		closeDialog(event);
	}

	/**
	 * Método que se ejecuta al pulsar el botón guardar.
	 * Valida los datos del formulario, y si son correctos guarda el ejemplar en base de datos y cierra la ventana.
	 * 
	 * @param event evento que inicia la ejecución.
	 */
	@FXML
    void handleSave(ActionEvent event) {
		log.info("se pulsó el botón guardar");
    	if (validateEjemplar()) {
    		saveEjemplar();
    		sendAlert(
    				AlertType.INFORMATION, 
    				resourceBundle.getString("admin.autor.save.success.title"), 
    				resourceBundle.getString("admin.autor.save.success.header"), 
    				resourceBundle.getString("admin.autor.save.success.msg"));
    		closeDialog(event);
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private void loadEstados() {
		estados.clear();
		estados.addAll(Collections.list(EstadoEnum));
	}
	
	
	/**
	 * Metodo para cerrar la ventana.
	 * 
	 * @param event ActionEvent evento que inicia el cierre de la ventana.
	 */
	private void closeDialog(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
}
