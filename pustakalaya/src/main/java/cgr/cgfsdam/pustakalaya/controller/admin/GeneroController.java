package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para el formulario de creación / edición de géneros literarios. 
 *
 * @author CGR-Casa
 */
@Controller
public class GeneroController extends BaseController {
	
	private Genero genero;
	
	@Autowired
	private GeneroService generoService;

	@Autowired
	private ResourceBundle resourceBundle;
	
	@FXML
	private Label lblTitle;

    @FXML
    private Label lblNombre;

    @FXML
    private  TextField txtNombre;

    @FXML
    private Label lblDescripcion;
    
    @FXML
    private  TextField txtDescripcion;
    
    @FXML
    private Button btnSave;

    @FXML
    private Label lblError;

    @FXML
    private Button btnExit;

    @FXML
    void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		closeDialog(event);
    }

    @FXML
    void handleSave(ActionEvent event) {
		log.info("se pulsó el botón guardar");
    	if (validateGenero()) {
    		saveGenero();
    		sendAlert(
    				AlertType.INFORMATION, 
    				resourceBundle.getString("admin.genero.save.success.title"), 
    				resourceBundle.getString("admin.genero.save.success.header"), 
    				resourceBundle.getString("admin.genero.save.success.msg"));
    		closeDialog(event);
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("admin.genero.title"));
		lblNombre.setText(resources.getString("admin.genero.label.nombre"));
		lblDescripcion.setText(resources.getString("admin.genero.label.apellidos"));
		lblError.setText("");
		btnSave.setText(resources.getString("admin.genero.button.save"));
		btnExit.setText(resources.getString("admin.genero.button.exit"));
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
	
	/**
	 * Setter del objeto genero del formulario.
	 * 
	 * @param genero Genero objeto a guardar
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
	 * Valida si los datos del formulario son correctos.
	 * @return
	 */
	private boolean validateGenero() {
		boolean ret = true;
		lblError.setText("");
		
		if(StringUtils.isEmpty(txtNombre.getText())) {
			ret = false;
			lblError.setText(resourceBundle.getString("admin.genero.save.error.empty"));
		} else if (genero == null || genero.getIdGenero() == null) {
			List<Genero> temp = null;
			
			if (StringUtils.isEmpty(txtDescripcion.getText())) {
				temp = generoService.findByNombreIgnoreCase(txtNombre.getText().trim());
			} else {
				temp = generoService.findByNombreAndByDescripcionAllIgnoreCase(
						txtNombre.getText().trim(), 
						txtDescripcion.getText().trim());
			}
			
			if (temp != null && temp.size() > 0) {
				ret = false;
				lblError.setText(resourceBundle.getString("admin.genero.save.error.alreadyExists"));
			}
		}
		
		
		return ret;		
	}
	
	/**
	 * Guarda el género del formulario en el formulario
	 */
	private void saveGenero() {
		if (genero == null) {
			genero = new Genero();
		}
		genero.setNombre(txtNombre.getText().trim());
		genero.setDescripcion(txtDescripcion.getText().trim());
		
		generoService.save(genero);
	}

}

