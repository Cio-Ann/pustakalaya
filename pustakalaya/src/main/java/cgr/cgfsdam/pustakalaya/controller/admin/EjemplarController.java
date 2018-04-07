package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.Collections;
import java.util.EnumSet;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.funds.EstadoEnum;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.users.Role;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.service.funds.EjemplarService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
	private Label lblError;
	
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
    				resourceBundle.getString("admin.ejemplar.save.success.title"), 
    				resourceBundle.getString("admin.ejemplar.save.success.header"), 
    				resourceBundle.getString("admin.ejemplar.save.success.msg"));
    		closeDialog(event);
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(resources.getString("admin.ejemplar.label.title"));
		lglRecurso.setText(resources.getString("admin.ejemplar.label.recurso"));
		lblCodigo.setText(resources.getString("admin.ejemplar.label.codigo"));
		lblEstado.setText(resources.getString("admin.ejemplar.label.estado"));

		initializeComboEstados();
		
		lblError.setText("");

		btnExit.setText(resources.getString("admin.ejemplar.button.exit"));
		btnSave.setText(resources.getString("admin.ejemplar.button.save"));
	}

	
	private void initializeComboEstados() {
		// establece el texto cuando no hay selección
		cbEstado.setPromptText(resourceBundle.getString("admin.ejemplar.estado.unselected"));

		// establece la conversión entre el tipo EstadoEnum y el String mostrado en el combo desplegable
		cbEstado.setCellFactory(new Callback<ListView<EstadoEnum>, ListCell<EstadoEnum>>() {
			@Override
			public ListCell<EstadoEnum> call(ListView<EstadoEnum> param) {
				ListCell<EstadoEnum> cell = new ListCell<EstadoEnum>() {
					@Override
					protected void updateItem(EstadoEnum item, boolean empty) {
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
				
		// establece la conversión entre el EstadoEnum y el String mostrado en el combo seleccionado.
		cbEstado.setConverter(new StringConverter<EstadoEnum>() {
			@Override
			public String toString(EstadoEnum estado) {
				if (estado == null) {
					return "";
				} else {
					return estado.getNombre();
				}
			}
			
			@Override
			public EstadoEnum fromString(String string) {
				EstadoEnum ret = null;
				for (EstadoEnum val : EstadoEnum.values()) {
					if (string.equals(val.getNombre())) {
						ret = val;
					}
				}
				return ret;
			}
		});
				
		// carga los datos de la enumeracion
		loadEstados();
	}
	
	
	/**
	 * Carga los valores posibles de estado en el combo.
	 */
	private void loadEstados() {
		estados.clear();
		
		EnumSet<EstadoEnum> elementos = EnumSet.allOf(EstadoEnum.class);
		elementos.remove(EstadoEnum.UNKNOWN);
		
		estados.addAll(elementos);
		
		cbEstado.setItems(estados);
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

	private boolean validateEjemplar() {
		boolean ret = true;
		String error = "";
		
		if(MyUtils.isEmptyString(txtCodigo.getText())) {
			ret = false;
			error += resourceBundle.getString("admin.ejemplar.error.codigo.empty");
		} else {
			Ejemplar temp = ejemplarService.findByCodigo(txtCodigo.getText());
			if (temp != null && !temp.getIdEjemplar().equals(ejemplar.getIdEjemplar())) {
				ret = false;
				error += resourceBundle.getString("admin.ejemplar.error.codigo.alreadyExists");
			}
		}
		
		if(cbEstado.getSelectionModel().getSelectedItem() == null) {
			error += resourceBundle.getString("admin.ejemplar.error.estado.empty");
			ret = false;
		}
		
		lblError.setText(error);
		return ret;
	}
	
	/**
	 * Guarda el ejemplar en base de datos.
	 */
	private void saveEjemplar() {
		ejemplar.setCodigo(txtCodigo.getText());
		ejemplar.setEstado(cbEstado.getSelectionModel().getSelectedItem());
//		ejemplar.setRecurso(recurso);
		
		ejemplarService.save(ejemplar);
		
	}

	/**
	 * @param recurso the recurso to set
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
		if (recurso != null && recurso.getIdRecurso() != null ) {
			txtRecurso.setText(String.valueOf(recurso.getIdRecurso()));
		}
	}

	/**
	 * @param ejemplar the ejemplar to set
	 */
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
		sendEntityToForm();
	}

	/**
	 * @return Ejemplar the stored ejemplar.
	 */
	public Ejemplar getEjemplar() {
		return this.ejemplar;
	}

	/**
	 * Traslada los datos de la entidad al formulario.
	 */
	private void sendEntityToForm() {
		if (ejemplar == null) {
			ejemplar = new Ejemplar();
		}
		txtCodigo.setText(ejemplar.getCodigo());
		cbEstado.getSelectionModel().select(ejemplar.getEstado());
	}


}
