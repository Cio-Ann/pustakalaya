package cgr.cgfsdam.pustakalaya.controller.lector;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista de búsqueda de recursos.
 *
 * @author CGR-Casa
 */
@Controller
public class SearchController extends BaseController {

	@FXML
	private Label lblTitleSearch;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtAutor;

	@FXML
	private Label lblAutor;

	@FXML
	private Label lblGenero;

	@FXML
	private ComboBox<Genero> cmbGenero;

	@FXML
	private Label lblFechaPub;

	@FXML
	private DatePicker dpFechaPub;

	@FXML
	private ToggleButton tbFPAntes;

	@FXML
	private ToggleButton tbFPDespues;

	@FXML
	private ToggleButton tbRecursosLibro;

	@FXML
	private ToggleButton tbRecursosMultimedia;

	@FXML
	private Label lblISBN;

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblNumPag;

	@FXML
	private TextField txtNumPag;

	@FXML
	private Label lblISAN;

	@FXML
	private TextField txtISAN;

	@FXML
	private Label lblDuracion;

	@FXML
	private TextField txtDuracion;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnClearForm;

    @FXML
    private TableView<Recurso> tvRecursos;

    @FXML
    private TableColumn<Recurso, String> tcTitulo;

    @FXML
    private TableColumn<Recurso, Set<Autor>> tcAutor;

    @FXML
    private TableColumn<Recurso, Date> tcAnnoPub;

    @FXML
    private TableColumn<Recurso, String> tcDisponible;

    @FXML
    private TableColumn<Recurso, String> tcActions;

	/**
	 * Flag que indica si los recursos se buscan antes de la fecha indicada(true) o
	 * después(false)
	 */
	private boolean findPrevious;

	@FXML
	void handleSwitchFP(ActionEvent event) {
		if (tbFPAntes.equals(event.getSource())) {
			if (tbFPAntes.isSelected()) {
				tbFPDespues.setSelected(false);
				findPrevious = true;
			}
		} else if (tbFPDespues.equals(event.getSource())) {
			if (tbFPDespues.isSelected()) {
				tbFPAntes.setSelected(false);
				findPrevious = false;
			}

		}
	}

	@FXML
	void handleSwitchRecurso(ActionEvent event) {
		if (tbRecursosLibro.equals(event.getSource())) {
			if (tbRecursosLibro.isSelected()) {
				tbRecursosMultimedia.setSelected(false);
				hideMultimediaResource();
				showBookResource();
			} else if (!tbRecursosLibro.isSelected()) {
				hideBookResource();
			}
		} else if (tbRecursosMultimedia.equals(event.getSource())) {
			if (tbRecursosMultimedia.isSelected()) {
				tbRecursosLibro.setSelected(false);
				hideBookResource();
				showMultimediaResource();
			} else if (!tbRecursosMultimedia.isSelected()) {
				hideMultimediaResource();
			}
		}
	}

	private void showMultimediaResource() {
		lblISAN.setVisible(true);
		txtISAN.setVisible(true);
		lblDuracion.setVisible(true);
		txtDuracion.setVisible(true);
	}

	private void showBookResource() {
		lblISBN.setVisible(true);
		txtISBN.setVisible(true);
		lblNumPag.setVisible(true);
		txtNumPag.setVisible(true);
	}

	private void hideBookResource() {
		lblISBN.setVisible(false);
		txtISBN.clear();
		txtISBN.setVisible(false);
		lblNumPag.setVisible(false);
		txtNumPag.clear();
		txtNumPag.setVisible(false);
	}

	private void hideMultimediaResource() {
		lblISAN.setVisible(false);
		txtISAN.clear();
		txtISAN.setVisible(false);
		lblDuracion.setVisible(false);
		txtDuracion.clear();
		txtDuracion.setVisible(false);
	}

	@FXML
	void handleClearForm(ActionEvent event) {
		log.info("se el boton limpiar");
	}

	@FXML
	void handleSearch(ActionEvent event) {
		log.info("se pulsa el boton buscar");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitleSearch.setText(resources.getString("lector.search.label.titleView"));
		lblTitulo.setText(resources.getString("lector.search.label.title"));
//		private TextField txtTitulo;
		lblAutor.setText(resources.getString("lector.search.label.autor"));
//		private TextField txtAutor;
		lblGenero.setText(resources.getString("lector.search.label.genero"));
		cmbGenero.setPromptText(resources.getString("lector.search.combo.genero"));
//		private ComboBox<Genero> cmbGenero;
		lblFechaPub.setText(resources.getString("lector.search.label.fechaPub"));
//		private DatePicker dpFechaPub;
		tbFPAntes.setText(resources.getString("lector.search.toggle.antes"));
		tbFPDespues.setText(resources.getString("lector.search.toggle.despues"));
		tbRecursosLibro.setText(resources.getString("lector.search.toggle.libros"));
		tbRecursosMultimedia.setText(resources.getString("lector.search.toggle.multimedia"));
		lblISBN.setText(resources.getString("lector.search.label.ISBN"));
//		private TextField txtISBN;
		lblNumPag.setText(resources.getString("lector.search.label.numPag"));
//		private TextField txtNumPag;
		lblISAN.setText(resources.getString("lector.search.label.ISAN"));
//		private TextField txtISAN;
		lblDuracion.setText(resources.getString("lector.search.label.duracion"));
//		private TextField txtDuracion;
		btnSearch.setText(resources.getString("lector.search.button.search"));
		btnClearForm.setText(resources.getString("lector.search.button.clear"));
//	    private TableView<Recurso> tvRecursos;
//	    tvRecursos.setVisible(false);
	    tcTitulo.setText(resources.getString("lector.search.column.title"));
	    tcAutor.setText(resources.getString("lector.search.column.author"));
	    tcAnnoPub.setText(resources.getString("lector.search.columno.year"));
	    tcDisponible.setText(resources.getString("lector.search.column.available"));
	    tcActions.setText(resources.getString("lector.search.column.actions"));
		
		//configuración de la tabla
	    tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
	    
	    tcAutor.setCellFactory(column -> {
	    	return new TableCell<Recurso, Set<Autor>>() {
	    		@Override
	    		protected void updateItem(Set<Autor> item, boolean empty) {
	    			super.updateItem(item, empty);
	    			
	    			if (item == null || empty) {
	    				setText(null);
	    			} else {
	    				item.forEach(autor -> {
	    					String temp = autor.getNombre() + " " + autor.getApellidos();
	    					if (!StringUtils.isEmpty(getText())) {
	    						temp = getText() + "; " + temp;
	    					}
	    					setText(temp);
	    				});
	    			}
	    			
	    		}
	    	};
	    });

	    tcAnnoPub.setCellFactory(column ->{
	    	return new TableCell<Recurso, Date>() {
	    		@Override
	    		protected void updateItem(Date item, boolean empty) {
	    			super.updateItem(item, empty);
	    			
	    			if (item == null || empty) {
	    				setText(null);
	    			} else {
	    				Calendar cal = Calendar.getInstance();
	    				cal.setTime(item);
	    				setText(String.valueOf(cal.get(Calendar.YEAR)));
	    			}
	    		}
	    	};
	    });
	    
	    
	    tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
	    tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
	    
	    
		
		
		hideBookResource();
		hideMultimediaResource();
	}

	
}
