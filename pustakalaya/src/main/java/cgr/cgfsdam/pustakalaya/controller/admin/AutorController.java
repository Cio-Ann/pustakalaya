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
import cgr.cgfsdam.pustakalaya.utils.StringUtils;

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
	}

	/**
	 * setter del autor.
	 * 
	 * @param autor
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
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

		if (StringUtils.isEmpty(txtNombre.getText())) {
			ret = false;
			lblError.setText(resourceBundle.getString("admin.autor.save.error.empty"));
		} else if (autor == null || autor.getIdAutor() == null) {
			List<Autor> temp = null;
			if (StringUtils.isEmpty(txtApellidos.getText())) {
				temp = autorService.findByNombreAllIgnoreCase(txtNombre.getText().trim());
			} else {
				temp = autorService.findByNombreAndByApellidosAllIgnoreCase(txtNombre.getText().trim(),
						txtApellidos.getText().trim());
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
		autor.setNombre(txtNombre.getText().trim());
		autor.setApellidos(txtApellidos.getText().trim());

		autorService.save(autor);
	}

}
