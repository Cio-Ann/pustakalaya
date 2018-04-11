package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.Date;
import java.util.EnumSet;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.loans.ReservaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador de la vista de reservas del administrador.
 *
 * @author CGR-Casa
 */
@Controller
public class BooksViewController extends BaseController {

	@Autowired
	ReservaService		   reservaService;
	/**
	 * Clase para extraer los textos localizados.
	 */
	@Autowired
	private ResourceBundle resourceBundle;

	@FXML
	private Label lblViewTitle;

	@FXML
	private Label lblEstado;

	@FXML
	private ComboBox<EstadoReservaEnum> cbEstado;

	@FXML
	private Label lblIdUsuario;

	@FXML
	private TextField txtIdUsuario;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtTitulo;

	@FXML
	private Label lblIsbn;

	@FXML
	private TextField txtIsbn;

	@FXML
	private Label lblResultados;

	@FXML
	private TableView<Reserva> tvReservas;

	@FXML
	private TableColumn<Reserva, Long> colId;

	@FXML
	private TableColumn<Reserva, EstadoReservaEnum> colEstado;

	@FXML
	private TableColumn<Reserva, String> colTitle;

	@FXML
	private TableColumn<Reserva, Usuario> colUsuario;

	@FXML
	private TableColumn<Reserva, Date> colFecha;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnClean;

	@FXML
	private Button btnLoan;

	@FXML
	private Button btnAbort;

	private ObservableList<EstadoReservaEnum> estados = FXCollections.observableArrayList();

	private ObservableList<Reserva> reservas = FXCollections.observableArrayList();

	@FXML
	void handleClean(ActionEvent event) {

		cleanForm();
	}

	@FXML
	void handleSearch(ActionEvent event) {

		searchBookings();
	}

	@FXML
	void handleLoan(ActionEvent event) {

		Reserva temp = tvReservas.getSelectionModel().getSelectedItem();

		if (temp == null) {
			// TODO: mensaje unselected
		} else if (temp.getEstadoReserva() != EstadoReservaEnum.WAITING) {
			// TODO: mensaje el estado no permite prestamo
		} else {
			// TODO: 1º ver si hay ejemplares disponibles
			// TODO: 2º ver si hay reservas de otros usuarios previas
			// TODO: 3º crear prestamo y persistirlo
			// TODO: actualizar busqueda
		}
	}

	@FXML
	void handleAbort(ActionEvent event) {

		Reserva temp = tvReservas.getSelectionModel().getSelectedItem();

		if (temp == null) {
			// TODO: mensaje unselected
		} else if (temp.getEstadoReserva() != EstadoReservaEnum.WAITING) {
			// TODO: mensaje el estado no permite prestamo
		} else {
			// TODO:cambiar estado y guardar reserva
			// TODO: actualizar busqueda
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// formulario
		lblViewTitle.setText(resourceBundle.getString("admin.loanView.title"));
		lblEstado.setText(resourceBundle.getString("admin.loanView.form.estado.label"));
		lblIdUsuario.setText(resourceBundle.getString("admin.loanView.form.idUsuario.label"));
		lblTitulo.setText(resourceBundle.getString("admin.loanView.form.titlulo.label"));
		lblIsbn.setText(resourceBundle.getString("admin.loanView.form.isbn.label"));
		lblResultados.setText(resourceBundle.getString("admin.loanView.resultados"));
		// botones
		btnAbort.setText(resourceBundle.getString("admin.loanView.form.abortar.button"));
		btnLoan.setText(resourceBundle.getString("admin.loanView.form.prestar.button"));
		btnClean.setText(resourceBundle.getString("admin.loanView.form.limpiar.button"));
		btnSearch.setText(resourceBundle.getString("admin.loanView.form.buscar.button"));

		initializeComboEstado();

		initializeTabla();

		loadReservas();
	}

	/**
	 * Inicializa el combo de estados
	 */
	private void initializeComboEstado() {

		// establece el prompt para seleccionar
		cbEstado.setPromptText(resourceBundle.getString("admin.loanView.form.estado.prompt"));

		// establece la conversión entre el tipo EstadoReservaEnum y el String mostrado en el combo desplegable
		cbEstado.setCellFactory(new Callback<ListView<EstadoReservaEnum>, ListCell<EstadoReservaEnum>>() {
			@Override
			public ListCell<EstadoReservaEnum> call(ListView<EstadoReservaEnum> param) {

				ListCell<EstadoReservaEnum> cell = new ListCell<EstadoReservaEnum>() {
					@Override
					protected void updateItem(EstadoReservaEnum item, boolean empty) {

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
		cbEstado.setConverter(new StringConverter<EstadoReservaEnum>() {
			@Override
			public String toString(EstadoReservaEnum estado) {

				if (estado == null) {
					return "";
				} else {
					return estado.getNombre();
				}
			}

			@Override
			public EstadoReservaEnum fromString(String string) {

				EstadoReservaEnum ret = null;
				for (EstadoReservaEnum val : EstadoReservaEnum.values()) {
					if (string.equals(val.getNombre())) {
						ret = val;
					}
				}
				return ret;
			}
		});

		estados.clear();
		EnumSet<EstadoReservaEnum> elementos = EnumSet.allOf(EstadoReservaEnum.class);
		elementos.remove(EstadoReservaEnum.UNKNOWN);
		estados.addAll(elementos);
		cbEstado.setItems(estados);
	}

	/**
	 * Establece los valores y transformaciones de las celdas de la tabla.
	 */
	private void initializeTabla() {

		// TODO Auto-generated method stub

	}

	/**
	 * Carga las reservas en la tabla según los criterios de búsqueda.
	 */
	private void loadReservas() {

		// TODO Auto-generated method stub

	}

	private void cleanForm() {

		// TODO Auto-generated method stub

	}

	private void searchBookings() {

		// TODO Auto-generated method stub

	}

}
