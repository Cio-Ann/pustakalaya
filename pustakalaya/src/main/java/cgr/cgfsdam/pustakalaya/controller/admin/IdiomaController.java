package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
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
 * Controlador para el formulario de creación / edición de idiomas. 
 *
 * @author CGR-Casa
 */
@Controller
public class IdiomaController extends BaseController {
	
	private Idioma idioma;
	
	@Autowired
	private IdiomaService idiomaService;

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
    		saveIdioma();
    		sendAlert(
    				AlertType.INFORMATION, 
    				resourceBundle.getString("admin.idioma.save.success.title"), 
    				resourceBundle.getString("admin.idioma.save.success.header"), 
    				resourceBundle.getString("admin.idioma.save.success.msg"));
    		closeDialog(event);
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("admin.idioma.title"));
		lblNombre.setText(resources.getString("admin.idioma.label.nombre"));
		lblDescripcion.setText(resources.getString("admin.idioma.label."));
		lblError.setText("");
		btnSave.setText(resources.getString("admin.idioma.button.save"));
		btnExit.setText(resources.getString("admin.idioma.button.exit"));
		
		idioma = new Idioma();
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
	 * Setter del objeto Idioma del formulario.
	 * 
	 * @param genero Genero objeto a guardar
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
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
			lblError.setText(resourceBundle.getString("admin.idioma.save.error.empty"));
		} else if (idioma == null || idioma.getIdIdioma() == null) {
			Idioma temp = null;
			
			if (StringUtils.isEmpty(txtDescripcion.getText())) {
				temp = idiomaService.findByNombreIgnoreCase(txtNombre.getText().trim());
			}
			
			if (temp != null && temp.getIdIdioma() != idioma.getIdIdioma()) {
				ret = false;
				lblError.setText(resourceBundle.getString("admin.idioma.save.error.alreadyExists"));
			}
		}
		
		
		return ret;		
	}
	
	/**
	 * Guarda el idioma del formulario en el formulario
	 */
	private void saveIdioma() {
		idioma.setNombre(txtNombre.getText().trim());
		idioma.setDescripcion(txtDescripcion.getText().trim());
		
		idiomaService.save(idioma);
	}

}

