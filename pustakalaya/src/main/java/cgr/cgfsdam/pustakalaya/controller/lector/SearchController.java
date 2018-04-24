package cgr.cgfsdam.pustakalaya.controller.lector;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.utility.FormObjects;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador de la vista de búsqueda de recursos.
 *
 * @author CGR-Casa
 */
@Controller
public class SearchController extends BaseController {

	/**
	 * Servicio de la entidad autor.
	 */
	@Autowired
	private AutorService autorService;

	/**
	 * Servicio de la entidad genero.
	 */
	@Autowired
	private GeneroService generoService;

	/**
	 * Servicio de la entidad idioma.
	 */
	@Autowired
	private IdiomaService idiomaService;

	/**
	 * Servicio de la entidad recurso.
	 */
	@Autowired
	private RecursoService recursoService;

	/**
	 * Servicio de la entidad Usuario.
	 */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Clase para extraer los textos localizados.
	 */
	@Autowired
	private ResourceBundle resourceBundle;

	@FXML
	private Label lblViewTitle;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtTitle;

	@FXML
	private Label lblISBN;

	@FXML
	private TextField txtIsbn;

	@FXML
	private Label lblAutor;

	@FXML
	private ComboBox<Autor> cbAutor;

	@FXML
	private Label lblGenero;

	@FXML
	private ComboBox<Genero> cbGenero;

	@FXML
	private Label lblIdioma;

	@FXML
	private ComboBox<Idioma> cbIdioma;

	@FXML
	private Label lblDesde;

	@FXML
	private DatePicker dpDesde;

	@FXML
	private Label lblHasta;

	@FXML
	private DatePicker dpHasta;

	@FXML
	private Label lblResults;

	@FXML
	private TableView<Recurso> tvResults;

	@FXML
	private TableColumn<Recurso, Long> colId;

	@FXML
	private TableColumn<Recurso, String> colTitle;

	@FXML
	private TableColumn<Recurso, Date> colAnno;

	@FXML
	private TableColumn<Recurso, Idioma> colLang;

	@FXML
	private TableColumn<Recurso, Boolean> colAction;

	@FXML
	private Button btnClean;

	@FXML
	private Button btnSearch;

	// observables de los combos
	/**
	 * Listado que contiene los autores a mostrar en el combo.
	 */
	private ObservableList<Autor>	autores;
	/**
	 * Listado que contiene los generos a mostrar en el combo.
	 */
	private ObservableList<Genero>	generos;
	/**
	 * Listado que contiene los idiomas a mostrar en el combo.
	 */
	private ObservableList<Idioma>	idiomas;
	/**
	 * Listado con los recursos resultantes de la búsqueda
	 */
	private ObservableList<Recurso>	recursos = FXCollections.observableArrayList();

	@FXML
	void handleClean(ActionEvent event) {

		clearForm();
	}

	@FXML
	void handleSearch(ActionEvent event) {

		loadResources();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblViewTitle.setText(resources.getString("lector.searcView.form.title"));
		lblTitle.setText(resources.getString("lector.searcView.title.label"));
		lblISBN.setText(resources.getString("lector.searcView.isbn.label"));
		lblAutor.setText(resources.getString("lector.searcView.autor.label"));
		lblGenero.setText(resources.getString("lector.searcView.genero.label"));
		lblIdioma.setText(resources.getString("lector.searcView.idioma.label"));
		lblDesde.setText(resources.getString("lector.searcView.desde.label"));
		lblHasta.setText(resources.getString("lector.searcView.hasta.label"));
		lblResults.setText(resources.getString("lector.searcView.resultados.label"));

		btnSearch.setText(resources.getString("lector.searcView.search.button"));
		btnClean.setText(resources.getString("lector.searcView.clear.button"));

		// inicializar combos
		initializeAutor();
		initializeGenero();
		initializeIdioma();

		// inicializar tabla
		initializeTable(resources);

	}

	/**
	 * Inicializa el combo de autores
	 * 
	 * @param resources
	 */
	private void initializeAutor() {

		autores = FXCollections.observableArrayList();

		cbAutor.setPromptText(resourceBundle.getString("lector.searcView.autores.combo.promt"));

		// establece la conversión entre el Autor y el string mostrado
		cbAutor.setCellFactory(new Callback<ListView<Autor>, ListCell<Autor>>() {

			@Override
			public ListCell<Autor> call(ListView<Autor> param) {

				ListCell<Autor> cell = new ListCell<Autor>() {

					@Override
					protected void updateItem(Autor item, boolean empty) {

						super.updateItem(item, empty);

						if (item != null) {
							String fullName = item.getNombre();
							if (!MyUtils.isEmptyString(item.getApellidos())) {
								fullName += ", " + item.getApellidos();
							}
							setText(fullName);
						} else {
							setText(null);
						}
					}
				};

				return cell;
			}
		});

		cbAutor.setConverter(new StringConverter<Autor>() {

			@Override
			public String toString(Autor autor) {

				String fullName = autor.getNombre();
				if (!MyUtils.isEmptyString(autor.getApellidos())) {
					fullName += ", " + autor.getApellidos();
				}
				return fullName;
			}

			@Override
			public Autor fromString(String fullName) {

				String[] nameElements = fullName.split(", ");

				String nombre = null;
				String apellidos = null;
				Autor ret = null;

				if (nameElements.length == 2) {
					nombre = nameElements[0];
					apellidos = nameElements[1];
					ret = autorService.findByNombreAndApellidosAllIgnoreCase(nombre, apellidos).stream().findFirst()
							.orElse(null);
				} else if (nameElements.length == 1) {
					nombre = nameElements[0];
					ret = autorService.findByNombreAllIgnoreCase(nombre).stream().findFirst().orElse(null);
				}

				return ret;
			}

		});

		loadAutores();
	}

	/**
	 * Carga los autores en el combo de autores
	 */
	private void loadAutores() {

		autores.clear();
		autores.addAll(autorService.findAll());
		cbAutor.setItems(autores);
	}

	/**
	 * Inicializa el combo de genero
	 */
	private void initializeGenero() {

		generos = FXCollections.observableArrayList();

		cbGenero.setPromptText(resourceBundle.getString("lector.searcView.genero.combo.promt"));

		// establece la conversión entre el Autor y el string mostrado
		cbGenero.setCellFactory(new Callback<ListView<Genero>, ListCell<Genero>>() {

			@Override
			public ListCell<Genero> call(ListView<Genero> param) {

				ListCell<Genero> cell = new ListCell<Genero>() {

					@Override
					protected void updateItem(Genero item, boolean empty) {

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

		cbGenero.setConverter(new StringConverter<Genero>() {

			@Override
			public String toString(Genero genero) {

				return genero.getNombre();
			}

			@Override
			public Genero fromString(String genero) {

				return generoService.findByNombreIgnoreCase(genero).stream().findFirst().orElse(null);
			}

		});

		loadGeneros();
	}

	/**
	 * Carga los recursos de base de datos en el combo de recursos
	 */
	private void loadGeneros() {

		generos.clear();
		generos.addAll(generoService.findAll());
		cbGenero.setItems(generos);
	}

	/**
	 * Inicializa el combo de idiomas.
	 */
	private void initializeIdioma() {

		idiomas = FXCollections.observableArrayList();

		cbIdioma.setPromptText(resourceBundle.getString("lector.searcView.idioma.combo.promt"));

		// establece la conversión entre el Autor y el string mostrado
		cbIdioma.setCellFactory(new Callback<ListView<Idioma>, ListCell<Idioma>>() {

			@Override
			public ListCell<Idioma> call(ListView<Idioma> param) {

				ListCell<Idioma> cell = new ListCell<Idioma>() {

					@Override
					protected void updateItem(Idioma item, boolean empty) {

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

		cbIdioma.setConverter(new StringConverter<Idioma>() {

			@Override
			public String toString(Idioma idioma) {

				return idioma.getNombre();
			}

			@Override
			public Idioma fromString(String idioma) {

				return idiomaService.findByNombreIgnoreCase(idioma);
			}

		});

		loadIdiomas();
	}

	/**
	 * Recarga los idiomas en el combo de idomas y elimina la selección actual.
	 */
	private void loadIdiomas() {

		idiomas.clear();
		idiomas.addAll(idiomaService.findAll());
		cbIdioma.setItems(idiomas);
	}

	/**
	 * Inicia los textos y conversiones de cada columna de la tabla
	 * 
	 * @param resources
	 */
	private void initializeTable(ResourceBundle resources) {

		// recupera el usuario si el padre no se lo inyectó
		loadUsuarioFromSecurity();

		tvResults.setPlaceholder(new Label(""));

		colId.setText(resources.getString("lector.searcView.resultados.table.id"));
		colId.setStyle("-fx-alignment: CENTER;");
		colTitle.setText(resources.getString("lector.searcView.resultados.table.titulo"));
		colAnno.setText(resources.getString("lector.searcView.resultados.table.year"));
		colAnno.setStyle("-fx-alignment: CENTER;");
		colLang.setText(resources.getString("lector.searcView.resultados.table.language"));
		colAction.setText("");

		colId.setCellValueFactory(new PropertyValueFactory<>("idRecurso"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colLang.setCellValueFactory(new PropertyValueFactory<>("idioma"));
		colLang.setCellFactory(column -> {
			return new TableCell<Recurso, Idioma>() {

				@Override
				protected void updateItem(Idioma item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNombre());
					}
				}
			};
		});
		colAnno.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
		colAnno.setCellFactory(column -> {
			return new TableCell<Recurso, Date>() {

				@Override
				protected void updateItem(Date item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(String.valueOf(MyUtils.getYear(item)));
					}

				}
			};
		});

		colAction.setCellFactory(column -> {
			return new TableCell<Recurso, Boolean>() {

				final Button btnReservar = new Button();

				@Override
				protected void updateItem(Boolean item, boolean empty) {

					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						Recurso currentRecurso = getTableView().getItems().get(getIndex());

						btnReservar.setDisable(false);
						btnReservar
								.setText(resourceBundle.getString("lector.searcView.resultados.table.action.detalles"));

						btnReservar.getStyleClass().add("btnBlue");
						btnReservar.getStyleClass().add("btn");
						btnReservar.setStyle("-fx-min-width:0");
						btnReservar.setOnAction(e -> {
							showResourceDetails(currentRecurso);
						});

						setGraphic(btnReservar);
						setAlignment(Pos.CENTER);
						setText(null);

					}

				}
			};
		});

		tvResults.getColumns().clear();
		tvResults.getColumns().addAll(colId, colTitle, colAnno, colLang, colAction);
	}

	/**
	 * Carga el usuario del contexto de seguridad de Spring.
	 */
	private void loadUsuarioFromSecurity() {

		if (getUsuario() == null) {
			log.info("No existe usuario en el controller");
			String securityUser = SecurityContextHolder.getContext().getAuthentication().getName();
			log.info("El usuario autenticado es: " + securityUser);

			setUsuario(usuarioService.findByUsername(securityUser));
		}
	}

	/**
	 * recarga los recursos según la información del formulario.
	 */
	private void loadResources() {

		recursos.clear();

		if (!isEmptyForm()) {
			// solo recupera los recursos de busqueda si hay algún dato para buscar
			recursos.addAll(recursoService.findByFormData(txtTitle.getText(), txtIsbn.getText(),
					cbAutor.getSelectionModel().getSelectedItem(), cbGenero.getSelectionModel().getSelectedItem(),
					cbIdioma.getSelectionModel().getSelectedItem(), MyUtils.fromLocalToDate(dpDesde.getValue()),
					MyUtils.fromLocalToDate(dpHasta.getValue())));
		}

		tvResults.setItems(recursos);
		tvResults.refresh();
	}

	/**
	 * Comprueba si hay criterios de busqueda en el formulario.
	 * 
	 * @return boolean true si todos los campos del formulario están vacios, o false en caso contrario
	 */
	private boolean isEmptyForm() {

		if (!MyUtils.isEmptyString(txtTitle.getText())) {
			return false;
		}
		if (!MyUtils.isEmptyString(txtIsbn.getText())) {
			return false;
		}
		if (cbAutor.getSelectionModel().getSelectedItem() != null) {
			return false;
		}
		if (cbGenero.getSelectionModel().getSelectedItem() != null) {
			return false;
		}
		if (cbIdioma.getSelectionModel().getSelectedItem() != null) {
			return false;
		}
		if (dpDesde.getValue() != null) {
			return false;
		}
		if (dpHasta.getValue() != null) {
			return false;
		}
		return true;
	}

	/**
	 * Limpia todos los valores del formulario. No actualiza el listado de buscados.
	 */
	private void clearForm() {

		txtTitle.clear();
		txtIsbn.clear();
		cbAutor.getSelectionModel().clearSelection();
		cbGenero.getSelectionModel().clearSelection();
		cbIdioma.getSelectionModel().clearSelection();
		dpDesde.setValue(null);
		dpDesde.getEditor().clear();
		dpHasta.setValue(null);
		dpHasta.getEditor().clear();

		recursos.clear();
		tvResults.setItems(recursos);
		tvResults.refresh();

	}

	/**
	 * Muestra la ventana de detalles del recurso.
	 * 
	 * @param recurso
	 */
	private void showResourceDetails(Recurso recurso) {

		try {

			FormObjects formData = getFormOjects(FxmlView.L_RECURSO_DETALLES.getFxmlFile());
			((ResourceDetail) formData.getController()).setRecurso(recurso);

			Stage form = new Stage();
			form.setTitle(FxmlView.L_RECURSO_DETALLES.getTitle());
			// form.setTitle(resourceBundle.getString("lector.search.detalles.title"));
			form.setScene(new Scene(formData.getParent()));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			log.info("Volvemos del fomrulario de detalles");
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

}
