package cgr.cgfsdam.pustakalaya.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.utility.FormObjects;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;
import cgr.cgfsdam.pustakalaya.service.funds.EjemplarService;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;
import cgr.cgfsdam.pustakalaya.utils.MyUtils;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

@Controller
public class ResourceViewController extends BaseController {

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
	 * Servicio de la entidad ejemplar.
	 */
	@Autowired
	private EjemplarService ejemplarService;

	@Autowired
	private RecursoService recursoService;

	/**
	 * Clase para extraer los textos localizados.
	 */
	@Autowired
	private ResourceBundle resourceBundle;

	@FXML
	private Label lblFormTitle;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtTitle;

	@FXML
	private Label lblIsbn;

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
	private Label lblResultados;

	@FXML
	private Button btnRecursoEdit;

	@FXML
	private Button btnRecursoNew;

	@FXML
	private Button btnRecursoDelete;

	@FXML
	private TableView<Recurso> tableResultados;

	@FXML
	private TableColumn<Recurso, Long> tcId;

	@FXML
	private TableColumn<Recurso, String> tcTitulo;

	@FXML
	private TableColumn<Recurso, String> tcIsbn;

	@FXML
	private TableColumn<Recurso, Date> tcYear;

	@FXML
	private TableColumn<Recurso, List<Ejemplar>> tcEjemplares;
//	@FXML
//	private TableColumn<Recurso, String> tcEjemplares;


	@FXML
	private Button btnClear;

	@FXML
	private Button btnSearch;

	// observables de los combos
	/**
	 * Listado que contiene los autores a mostrar en el combo.
	 */
	private ObservableList<Autor> autores;
	/**
	 * Listado que contiene los generos a mostrar en el combo.
	 */
	private ObservableList<Genero> generos;
	/**
	 * Listado que contiene los idiomas a mostrar en el combo.
	 */
	private ObservableList<Idioma> idiomas;

	private ObservableList<Recurso> recursos = FXCollections.observableArrayList();

	@FXML
	void handleRecursoNew(ActionEvent event) {
		Recurso recurso = new Recurso();

		try {

			FormObjects formData = getFormOjects(FxmlView.A_RECURSO_FORM.getFxmlFile());
			((RecursoController) formData.getController()).setRecurso(recurso);

			Stage form = new Stage();
			form.setScene(new Scene(formData.getParent()));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			log.info("Volvemos del fomrulario de recursos");

		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	@FXML
	void handleRecursoEdit(ActionEvent event) {
		Recurso recurso = tableResultados.getSelectionModel().getSelectedItem();

		if (recurso != null) {
			try {

				FormObjects formData = getFormOjects(FxmlView.A_RECURSO_FORM.getFxmlFile());
				((RecursoController) formData.getController()).setRecurso(recurso);

				Stage form = new Stage();
				form.setScene(new Scene(formData.getParent()));
				form.initModality(Modality.APPLICATION_MODAL);
				form.showAndWait();

				log.info("Volvemos del fomrulario de recursos");

			} catch (IOException e) {
				log.info("No se pudo abrir el fichero fxml");
				e.printStackTrace();
			}
		} else {
			log.info("No hay ningún recurso seleccionado");
		}
	}

	@FXML
	void handleRecursoDelete(ActionEvent event) {

	}

	@FXML
	void handleClear(ActionEvent event) {

	}

	@FXML
	void handleSearch(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblFormTitle.setText(resources.getString("admin.resourceview.form.title"));
		lblTitle.setText(resources.getString("admin.resourceview.title.label"));
		lblIsbn.setText(resources.getString("admin.resourceview.isbn.label"));
		lblAutor.setText(resources.getString("admin.resourceview.autor.label"));
		lblGenero.setText(resources.getString("admin.resourceview.genero.label"));
		lblIdioma.setText(resources.getString("admin.resourceview.idioma.label"));
		lblDesde.setText(resources.getString("admin.resourceview.desde.label"));
		lblHasta.setText(resources.getString("admin.resourceview.hasta.label"));
		lblResultados.setText(resources.getString("admin.resourceview.resultados.label"));
		btnRecursoEdit.setText(resources.getString("admin.resourceview.resultados.edit.button"));
		btnRecursoNew.setText(resources.getString("admin.resourceview.resultados.new.button"));
		btnRecursoDelete.setText(resources.getString("admin.resourceview.resultados.delete.button"));

		btnClear.setText(resources.getString("admin.resourceview.clear.button"));
		btnSearch.setText(resources.getString("admin.resourceview.search.button"));

		// inicializar combos
		initializeAutor(resources);
		initializeGenero(resources);
		initializeIdioma(resources);

		// inicializar tabla
		initializeTable(resources);

	}

	/**
	 * Inicializa el combo de autores
	 * 
	 * @param resources
	 */
	private void initializeAutor(ResourceBundle resources) {
		autores = FXCollections.observableArrayList();

		cbAutor.setPromptText(resources.getString("admin.recurso.form.autores.combo.promt"));

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
	 * 
	 * @param resources
	 */
	private void initializeGenero(ResourceBundle resources) {
		generos = FXCollections.observableArrayList();

		cbGenero.setPromptText(resources.getString("admin.recurso.form.genero.combo.promt"));

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
	 * Carga los recursos de base de datos en el compbo de recursos
	 */
	private void loadGeneros() {
		generos.clear();
		generos.addAll(generoService.findAll());
		cbGenero.setItems(generos);
	}

	/**
	 * Inicializa el combo de idiomas.
	 * 
	 * @param resources
	 */
	private void initializeIdioma(ResourceBundle resources) {
		idiomas = FXCollections.observableArrayList();

		cbIdioma.setPromptText(resources.getString("admin.recurso.form.idioma.combo.promt"));

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
		tableResultados.setPlaceholder(new Label(""));

		tcId.setText(resources.getString("admin.resourceview.resultados.table.id"));
		tcTitulo.setText(resources.getString("admin.resourceview.resultados.table.titulo"));
		tcIsbn.setText(resources.getString("admin.resourceview.resultados.table.isbn"));
		tcYear.setText(resources.getString("admin.resourceview.resultados.table.year"));
		tcEjemplares.setText(resources.getString("admin.resourceview.resultados.table.ejemplares"));

		tcId.setCellValueFactory(new PropertyValueFactory<>("idRecurso"));
		tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tcIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		tcYear.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
		// tcYear.setCellFactory(new Callback<TableColumn<Recurso, Date>,
		// TableCell<Recurso, Date>>() {
		//
		// @Override
		// public TableCell<Recurso, Date> call(TableColumn<Recurso, Date> param) {
		// TableCell<Recurso, Date> cell = new TableCell<Recurso, Date>() {
		// @Override
		// protected void updateItem(Date item, boolean empty) {
		// super.updateItem(item, empty);
		//
		// if (item == null || empty) {
		// setText(null);
		// } else {
		// setText(String.valueOf(MyUtils.getYear(item)));
		// }
		// }
		// };
		//
		// return cell;
		// }
		// });

		tcYear.setCellFactory(column -> {
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
		tcEjemplares.setCellValueFactory(new PropertyValueFactory<>("ejemplares"));
//		tcEjemplares.setCellFactory(
//				new Callback<TableColumn<Recurso, String>, TableCell<Recurso, String>>() {
//
//					@Override
//					public TableCell<Recurso, String> call(TableColumn<Recurso, String> param) {
//						TableCell<Recurso, String> cell = new TableCell<Recurso, String>() {
//							@Override
//							protected void updateItem(String item, boolean empty) {
//								super.updateItem(item, empty);
//								
//
//								if (empty ) {
//									setText(null);
//								} else {
//									Recurso r = getTableView().getItems().get(getIndex());
//									if ( r != null && r.getEjemplares() != null) {
//										setText(String.valueOf(r.getEjemplares().size()));
//									} else {
//										setText("0");
//									}
//								}
//							}
//						};
//
//						return cell;
//					}
//				});

				tcEjemplares.setCellFactory(column -> {
					return new TableCell<Recurso, List<Ejemplar>>() {
						@Override
						protected void updateItem(List<Ejemplar> item, boolean empty) {
							super.updateItem(item, empty);

							if (item == null || empty) {
								setText(null);
							} else {
								setText(String.valueOf(item.size()));
							}

						}
					};
				});
		

		loadAllRecursos();
		
		tableResultados.getColumns().clear();
		tableResultados.getColumns().addAll(tcId, tcTitulo, tcIsbn, tcYear, tcEjemplares);
	}

	private void loadAllRecursos() {
		recursos.clear();
		recursos.addAll(recursoService.findAll());

		tableResultados.setItems(recursos);
		tableResultados.refresh();
	}
}
