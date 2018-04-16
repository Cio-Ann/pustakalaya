package cgr.cgfsdam.pustakalaya.controller.lector;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Controller
public class ResourceDetail extends BaseController {

	private Recurso recurso;

	@FXML
	private Label lblFormTitle;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField txtTitulo;

	@FXML
	private Label lblIsbn;

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblIdioma;

	@FXML
	private TextField txtIdioma;

	@FXML
	private Label lblFechaPub;

	@FXML
	private TextField txtFechaPub;

	@FXML
	private Label lblNumPaginas;

	@FXML
	private TextField txtNumPaginas;

	@FXML
	private Label lblAutores;

	@FXML
	private Label lblGeneros;

	@FXML
	private ListView<Autor> listAutores;

	@FXML
	private ListView<Genero> listGeneros;

	@FXML
	private Button btnBack;

	@FXML
	void handleBack(ActionEvent event) {

		closeDialog(event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (recurso == null) {
			recurso = new Recurso();
		}

		lblFormTitle.setText(resources.getString("lector.detail.form.title"));
		lblTitulo.setText(resources.getString("lector.detail.form.title.label"));
		lblIsbn.setText(resources.getString("lector.detail.form.isbn.label"));
		lblIdioma.setText(resources.getString("lector.detail.form.idioma.label"));
		lblFechaPub.setText(resources.getString("lector.detail.form.fechaPub.label"));
		lblNumPaginas.setText(resources.getString("lector.detail.form.numPag.label"));
		lblAutores.setText(resources.getString("lector.detail.form.autores.label"));
		lblGeneros.setText(resources.getString("lector.detail.form.generos.label"));
		btnBack.setText(resources.getString("lector.detail.form.volver.button"));

		initializeListas();

	}

	private void initializeListas() {

		listAutores.setCellFactory(column -> {
			return new ListCell<Autor>() {
				@Override
				protected void updateItem(Autor item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText("");
					} else {
						String fullName = item.getNombre();
						if (!MyUtils.isEmptyString(item.getApellidos())) {
							fullName += ", " + item.getApellidos();
						}
						setText(fullName);
					}
				}
			};
		});

		loadAutores();

		listGeneros.setCellFactory(column -> {
			return new ListCell<Genero>() {
				@Override
				protected void updateItem(Genero item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText("");
					} else {
						setText(item.getNombre());
					}
				}
			};
		});

		loadGeneros();
	}

	/**
	 * Carga los autores del recurso actual en la lista de autores.
	 */
	private void loadAutores() {

		listAutores.getItems().clear();
		if (recurso != null && recurso.getAutores() != null) {
			listAutores.getItems().addAll(recurso.getAutores());
		}
	}

	/**
	 * Carga los generos del recurso actual en la lista de generos.
	 */
	private void loadGeneros() {

		listGeneros.getItems().clear();
		if (recurso != null && recurso.getGeneros() != null) {
			listGeneros.getItems().addAll(recurso.getGeneros());
		}
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

	public void setRecurso(Recurso recurso) {

		this.recurso = recurso;

		txtTitulo.setText(recurso.getTitulo());
		txtISBN.setText(recurso.getIsbn());
		txtIdioma.setText(recurso.getIdioma().getNombre());
		txtFechaPub.setText(MyUtils.dateToShort(recurso.getFechaPublicacion()));
		txtNumPaginas.setText(String.valueOf(recurso.getNumPaginas()));
		loadAutores();
		loadGeneros();
	}

}
