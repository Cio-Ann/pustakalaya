package cgr.cgfsdam.pustakalaya.controller.admin;

import java.net.URL;
import java.util.Date;
import java.util.EnumSet;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.loans.EstadoReservaEnum;
import cgr.cgfsdam.pustakalaya.model.loans.Prestamo;
import cgr.cgfsdam.pustakalaya.model.loans.Reserva;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.service.funds.EjemplarService;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;
import cgr.cgfsdam.pustakalaya.service.loans.ReservaService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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
	ReservaService reservaService;

	@Autowired
	RecursoService recursoService;

	@Autowired
	EjemplarService ejemplarService;

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
			sendAlert(AlertType.WARNING, resourceBundle.getString("admin.bookingView.loan.empty.title"),
					resourceBundle.getString("admin.bookingView.loan.empty.header"),
					resourceBundle.getString("admin.bookingView.loan.empty.msg"));
		} else if (temp.getEstadoReserva() != EstadoReservaEnum.WAITING) {
			sendAlert(AlertType.WARNING, resourceBundle.getString("admin.bookingView.loan.notBookable.title"),
					resourceBundle.getString("admin.bookingView.loan.notBookable.header"),
					resourceBundle.getString("admin.bookingView.loan.notBookable.msg"));
		} else {
			long ejemplaresDisponibles = recursoService.countEjemplaresNoPrestados(temp.getRecurso());

			if (ejemplaresDisponibles < 1) {
				// si no hay ejemplares disponibles
				sendAlert(AlertType.WARNING, resourceBundle.getString("admin.bookingView.loan.sinEjemplares.title"),
						resourceBundle.getString("admin.bookingView.loan.sinEjemplares.header"),
						resourceBundle.getString("admin.bookingView.loan.sinEjemplares.msg"));
			} else {
				long reservasPrevias = reservaService.countByRecursoAndEstadoReservaAndFechaReservaBefore(
						temp.getRecurso(), temp.getEstadoReserva(), temp.getFechaReserva());

				// si hay mas reservas del libro previas a la que está formalizando, pide confirmación al administrador
				if (ejemplaresDisponibles - reservasPrevias < 1) {
					if (showConfirmation(resourceBundle.getString("admin.bookingView.loan.sinEjemplares.title"),
							resourceBundle.getString("admin.bookingView.loan.sinEjemplares.header"),
							resourceBundle.getString("admin.bookingView.loan.sinEjemplares.msg"))) {
						materializeBooking(temp);
					}
				} else {
					materializeBooking(temp);
				}

			}

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
		lblViewTitle.setText(resourceBundle.getString("admin.bookingView.title"));
		lblEstado.setText(resourceBundle.getString("admin.bookingView.form.estado.label"));
		lblIdUsuario.setText(resourceBundle.getString("admin.bookingView.form.idUsuario.label"));
		lblTitulo.setText(resourceBundle.getString("admin.bookingView.form.titlulo.label"));
		lblIsbn.setText(resourceBundle.getString("admin.bookingView.form.isbn.label"));
		lblResultados.setText(resourceBundle.getString("admin.bookingView.resultados"));
		// botones
		btnAbort.setText(resourceBundle.getString("admin.bookingView.form.abortar.button"));
		btnLoan.setText(resourceBundle.getString("admin.bookingView.form.prestar.button"));
		btnClean.setText(resourceBundle.getString("admin.bookingView.form.limpiar.button"));
		btnSearch.setText(resourceBundle.getString("admin.bookingView.form.buscar.button"));

		initializeComboEstado();

		initializeTabla();

		searchBookings();
	}

	/**
	 * Inicializa el combo de estados
	 */
	private void initializeComboEstado() {

		// establece el prompt para seleccionar
		cbEstado.setPromptText(resourceBundle.getString("admin.bookingView.form.estado.prompt"));

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
	 * Limpia todos los valores del formulario.
	 */
	private void cleanForm() {

		cbEstado.getSelectionModel().clearSelection();
		txtIdUsuario.clear();
		txtTitulo.clear();
		txtIsbn.clear();

		reservas.clear();
		tvReservas.setItems(reservas);
		tvReservas.refresh();
	}

	/**
	 * recarga los recursos según la información del formulario.
	 */
	private void searchBookings() {

		reservas.clear();

		if (!isEmptyForm()) {
			reservas.addAll(reservaService.findByForm(cbEstado.getSelectionModel().getSelectedItem(),
					txtIdUsuario.getText(), txtTitulo.getText(), txtIsbn.getText()));

		}

		tvReservas.setItems(reservas);
		tvReservas.refresh();
	}

	/**
	 * Comprueba si el formulario está vacio.
	 * 
	 * @return boolean <code>true</code> si el formulario está vacio, <code>false</code> en caso contrario
	 */
	private boolean isEmptyForm() {

		if (cbEstado.getSelectionModel().getSelectedItem() == null || MyUtils.isEmptyString(txtIdUsuario.getText())
				|| MyUtils.isEmptyString(txtTitulo.getText()) || MyUtils.isEmptyString(txtIsbn.getText())) {
			return false;
		}

		return true;
	}

	/**
	 * Materializa la reserva creando el prestamo asociado.
	 * 
	 * @param reserva Reserva reserva a formalizar.
	 */
	private void materializeBooking(Reserva reserva) {

		Prestamo prestamo = new Prestamo();
		Date now = new Date();

		Ejemplar ejemplar = ejemplarService.findFirstFree(reserva.getRecurso().getIdRecurso(), now);

		reserva.setEstadoReserva(EstadoReservaEnum.CONSUMED);

		prestamo.setEjemplar(ejemplar);
		prestamo.setUsuario(reserva.getUsuario());
		prestamo.setReserva(reserva);

		prestamo.setFechaPrestamo(now);
		prestamo.setFechaVencimiento(MyUtils.getFechaVencimiento(now));

	}

}
