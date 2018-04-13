package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
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
	private TextField txtNombre;

	@FXML
	private Label lblDescripcion;

	@FXML
	private TextField txtDescripcion;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnDelete;

	@FXML
	private Label lblError;

	/**
	 * Método que se ejecuta al pulsar el botón borrar. Elimina el genero de base de
	 * datos y cierra el diálogo de creación / edicion.
	 * 
	 * @param event
	 *            evento que inicia la ejecución.
	 */
	@FXML
	void handleDelete(ActionEvent event) {
		if (isGeneroPurgeable()) {
			if (showConfirmation(resourceBundle.getString("admin.genero.delete.confirm.title"),
					resourceBundle.getString("admin.genero.delete.confirm.header"),
					resourceBundle.getString("admin.genero.delete.confirm.error.msg"))) {
				generoService.delete(genero);
				closeDialog(event);
			}
		}
	}

	@FXML
	void handleExit(ActionEvent event) {
		closeDialog(event);
	}

	@FXML
	void handleSave(ActionEvent event) {
		if (validateGenero()) {
			saveGenero();
			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.genero.save.success.title"),
					resourceBundle.getString("admin.genero.save.success.header"),
					resourceBundle.getString("admin.genero.save.success.msg"));
			closeDialog(event);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("admin.genero.title"));
		lblNombre.setText(resources.getString("admin.genero.label.nombre"));
		lblDescripcion.setText(resources.getString("admin.genero.label.descripcion"));
		lblError.setText("");
		btnSave.setText(resources.getString("admin.genero.button.save"));
		btnExit.setText(resources.getString("admin.genero.button.exit"));
		btnDelete.setText(resources.getString("admin.genero.button.delete"));
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
		sendEntityToForm();
	}

	/**
	 * Valida si los datos del formulario son correctos.
	 * 
	 * @return
	 */
	private boolean validateGenero() {
		boolean ret = true;
		lblError.setText("");

		if (MyUtils.isEmptyString(txtNombre.getText())) {
			ret = false;
			lblError.setText(resourceBundle.getString("admin.genero.save.error.empty"));
		} else if (genero == null || genero.getIdGenero() == null) {
			List<Genero> temp = null;

			if (MyUtils.isEmptyString(txtDescripcion.getText())) {
				temp = generoService.findByNombreIgnoreCase(txtNombre.getText());
			} else {
				temp = generoService.findByNombreAndDescripcionAllIgnoreCase(txtNombre.getText(),
						txtDescripcion.getText());
			}

			if (temp != null && temp.size() > 0) {
				ret = false;
				lblError.setText(resourceBundle.getString("admin.genero.save.error.alreadyExists"));
			}
		}

		return ret;
	}

	/**
	 * Indica si el genero puede ser borrado de base de datos.
	 * 
	 * @return boolean true si el genero se puede borrar sin problemas, o false si está vinculado a algún recurso
	 */
	private boolean isGeneroPurgeable() {
		boolean ret = true;
		if(genero == null || genero.getIdGenero() == null) {
			ret = false;
		} else {
			ret = generoService.countResourcesByGenero(genero) == 0;
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
		genero.setNombre(txtNombre.getText());
		genero.setDescripcion(txtDescripcion.getText());

		generoService.save(genero);
	}

	/**
	 * Traslada los datos de la entidad al formulario.
	 */
	private void sendEntityToForm() {
		if (genero == null) {
			genero = new Genero();
		}
		txtNombre.setText(genero.getNombre());
		txtDescripcion.setText(genero.getDescripcion());
		
		if (isGeneroPurgeable()) {
			btnDelete.setDisable(false);
		} else {
			btnDelete.setDisable(true);
		}
	}
}
