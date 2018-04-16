package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
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

	@FXML
	void handleDelete(ActionEvent event) {

		if (isIdiomaPurgeable()) {
			if (showConfirmation(resourceBundle.getString("admin.idioma.delete.confirm.title"),
					resourceBundle.getString("admin.idioma.delete.confirm.header"),
					resourceBundle.getString("admin.idioma.delete.confirm.error.msg"))) {
				idiomaService.delete(idioma);
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
			saveIdioma();
			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.idioma.save.success.title"),
					resourceBundle.getString("admin.idioma.save.success.header"),
					resourceBundle.getString("admin.idioma.save.success.msg"));
			closeDialog(event);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblTitle.setText(resources.getString("admin.idioma.title"));
		lblNombre.setText(resources.getString("admin.idioma.label.nombre"));
		lblDescripcion.setText(resources.getString("admin.idioma.label.descripcion"));
		lblError.setText("");
		btnSave.setText(resources.getString("admin.idioma.button.save"));
		btnExit.setText(resources.getString("admin.idioma.button.exit"));
		btnDelete.setText(resources.getString("admin.idioma.button.delete"));

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
			lblError.setText(resourceBundle.getString("admin.idioma.save.error.empty"));
		} else {
			// if (idioma == null || idioma.getIdIdioma() == null) {
			Idioma temp = null;

			temp = idiomaService.findByNombreIgnoreCase(txtNombre.getText());

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

		idioma.setNombre(txtNombre.getText());
		idioma.setDescripcion(txtDescripcion.getText());

		idiomaService.save(idioma);
	}

	/**
	 * Verifica si el elemento actual se puede o no eliminar.
	 * 
	 * @return boolean true si el elemento se puede borar, o false si está relacionado con algún recurso
	 */
	private boolean isIdiomaPurgeable() {

		boolean ret = true;
		if (idioma == null || idioma.getIdIdioma() == null) {
			ret = false;
		} else {
			ret = idiomaService.countResourcesByIdioma(idioma) == 0;
		}
		return ret;
	}

	/**
	 * Traslada los datos de la entidad al formulario.
	 */
	private void sendEntityToForm() {

		if (idioma == null) {
			idioma = new Idioma();
		}
		txtNombre.setText(idioma.getNombre());
		txtDescripcion.setText(idioma.getDescripcion());

		if (isIdiomaPurgeable()) {
			btnDelete.setDisable(false);
		} else {
			btnDelete.setDisable(true);
		}
	}
}
