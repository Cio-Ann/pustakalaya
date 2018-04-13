package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.repository.funds.AutorRepository;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;

/**
 * Controlador para el formulario de creación / edición de autores.
 *
 * @author CGR-Casa
 */
@Controller
public class AutorController extends BaseController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private ResourceBundle resourceBundle;

	private Autor autor;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblNombre;

	@FXML
	private TextField txtNombre;

	@FXML
	private Label lblApellidos;

	@FXML
	private TextField txtApellidos;

	@FXML
	private Label lblError;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnDelete;

	/**
	 * Método que se ejecuta al pulsar el botón borrar. Cierra la ventana sin
	 * guardar información.
	 * 
	 * @param event
	 *            evento que inicia la ejecución.
	 */
	@FXML
	void handleDelete(ActionEvent event) {
		log.info("se pulsó el botón borrar");

		if (isAutorPurgeable()) {
			if (showConfirmation(resourceBundle.getString("admin.autor.delete.confirm.title"),
					resourceBundle.getString("admin.autor.delete.confirm.header"),
					resourceBundle.getString("admin.autor.delete.confirm.error.msg"))) {
				autorService.delete(autor);
				closeDialog(event);
			}
		}
	}

	/**
	 * Método que se ejecuta al pulsar el botón salir. Cierra la ventana sin guardar
	 * información.
	 * 
	 * @param event
	 *            evento que inicia la ejecución.
	 */
	@FXML
	void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		closeDialog(event);
	}

	/**
	 * Método que se ejecuta al pulsar el botón guardar. Valida los datos del
	 * formulario, y si son correctos guarda el autor en base de datos y cierra la
	 * ventana.
	 * 
	 * @param event
	 *            evento que inicia la ejecución.
	 */
	@FXML
	void handleSave(ActionEvent event) {
		log.info("se pulsó el botón guardar");
		if (validateAutor()) {
			saveAutor();
			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.autor.save.success.title"),
					resourceBundle.getString("admin.autor.save.success.header"),
					resourceBundle.getString("admin.autor.save.success.msg"));
			closeDialog(event);
		}

	}

	/**
	 * Inicializa los valores del formulario.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("admin.autor.title"));
		lblNombre.setText(resources.getString("admin.autor.label.nombre"));
		lblApellidos.setText(resources.getString("admin.autor.label.apellidos"));
		lblError.setText("");
		btnSave.setText(resources.getString("admin.autor.button.save"));
		btnExit.setText(resources.getString("admin.autor.button.exit"));
		btnDelete.setText(resources.getString("admin.autor.button.delete"));
	}

	/**
	 * setter del autor.
	 * 
	 * @param autor
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
		sendEntityToForm();
	}

	/**
	 * Metodo para cerrar la ventana.
	 * 
	 * @param event
	 *            ActionEvent evento que inicia el cierre de la ventana.
	 */
	private void closeDialog(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
	 * Método que valida que haya información suficiente en el formulario y que el
	 * autor no exista ya.
	 * 
	 * @return boolean true si los datos del formulario son validos o false en caso
	 *         contrario
	 */
	private boolean validateAutor() {
		boolean ret = true;
		lblError.setText("");

		if (MyUtils.isEmptyString(txtNombre.getText())) {
			ret = false;
			lblError.setText(resourceBundle.getString("admin.autor.save.error.empty"));
		} else if (autor == null || autor.getIdAutor() == null) {
			List<Autor> temp = null;
			if (MyUtils.isEmptyString(txtApellidos.getText())) {
				temp = autorService.findByNombreAllIgnoreCase(txtNombre.getText());
			} else {
				temp = autorService.findByNombreAndApellidosAllIgnoreCase(txtNombre.getText(), txtApellidos.getText());
			}

			if (temp != null && temp.size() > 0) {
				// si existe algún autor con los mismos datos que el formulario y distinto id,
				// ya existe y no es válido
				if (temp.stream().anyMatch(a -> !a.getIdAutor().equals(autor.getIdAutor()))) {
					ret = false;
					lblError.setText(resourceBundle.getString("admin.autor.save.error.alreadyExists"));
				}

			}
		}

		return ret;
	}

	/**
	 * Metodo que guarda el autor si es valido.
	 */
	private void saveAutor() {
		if (autor == null) {
			autor = new Autor();
		}
		autor.setNombre(txtNombre.getText());
		autor.setApellidos(txtApellidos.getText());

		autorService.save(autor);
	}

	/**
	 * Traslada los datos de la entidad al formulario.
	 */
	private void sendEntityToForm() {
		if (autor == null) {
			autor = new Autor();
		}
		txtNombre.setText(autor.getNombre());
		txtApellidos.setText(autor.getApellidos());
		
		if (isAutorPurgeable()) {
			btnDelete.setDisable(false);
		} else {
			btnDelete.setDisable(true);
		}

	}
	
	/**
	 * Indica si el autor actual es susceptible de ser eliminado - existe en base de
	 * datos - no tiene ningún recurso relacionado
	 * 
	 * @return boolean true si el autor puede ser borrado, o false en caso contrario
	 */
	private boolean isAutorPurgeable() {
		boolean ret = true;

		if (autor == null || autor.getIdAutor() == null) {
			ret = false;
		} else {
			ret = autorService.countResourcesByAutor(autor) == 0;
		}

		return ret;
	}

}
