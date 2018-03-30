package cgr.cgfsdam.pustakalaya.controller.admin;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Ejemplar;
import cgr.cgfsdam.pustakalaya.model.funds.EstadoEnum;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.funds.EjemplarRepository;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;
import cgr.cgfsdam.pustakalaya.service.funds.EjemplarService;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;
import cgr.cgfsdam.pustakalaya.utils.StringUtils;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Controlador del formulario de la entidad Recurso.
 *
 * @author CGR-Casa
 */
@Controller
public class RecursoController extends BaseController {

	/**
	 * Constante para concatenaciones en la muestra de ejemplares.
	 */
	private final String SEPARATOR = " - ";

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

	private RecursoService recursoService;

	/**
	 * Clase para extraer los textos localizados.
	 */
	@Autowired
	private ResourceBundle resourceBundle;

	/**
	 * Entidad que representa el recurso que se está creando/editando.
	 */
	private Recurso recurso;

	/**
	 * String a usar en los mensajes de error de validación.
	 */
	private String validationMsg;

	/* Controles definidos en el fichero fxml */
	@FXML
	private Label lblFormTitle;

	@FXML
	private Label lblTittulo;

	@FXML
	private TextField txtTitulo;

	@FXML
	private Label lblISBN;

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblAutor;

	@FXML
	private ComboBox<Autor> cbAutor;

	@FXML
	private Button btnAddAutor;

	@FXML
	private Button btnEditAutor;

	@FXML
	private Button btnDeleteAutor;

	@FXML
	private ListView<Autor> lvAutor;

	@FXML
	private Label lblGenero;

	@FXML
	private ComboBox<Genero> cbGenero;

	@FXML
	private Button btnAddGenero;

	@FXML
	private Button btnEditGenero;

	@FXML
	private Button btnDeleteGenero;

	@FXML
	private ListView<Genero> lvGenero;

	@FXML
	private Label lblIdioma;

	@FXML
	private Button btnEditIdioma;

	@FXML
	private ComboBox<Idioma> cbIdioma;

	@FXML
	private Label lblPDate;

	@FXML
	private DatePicker dpPDate;

	@FXML
	private Label lblNumPages;

	@FXML
	private TextField txtNumPages;

	@FXML
	private Label lblEjemplares;

	@FXML
	private Button btnAddEjemplar;

	@FXML
	private Button btnEditEjemplar;

	@FXML
	private Button btnDeleteEjemplar;

	@FXML
	private ListView<Ejemplar> lvEjemplares;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnDelete;

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

	/**
	 * Añade el autor seleccionado del combo de autores al ListView de autores.
	 * 
	 * @param event
	 *            AcActionEvent pulsación del boton añadir autor.
	 */
	@FXML
	void handleAddAutor(ActionEvent event) {
		Autor selected = cbAutor.getSelectionModel().getSelectedItem();
		if (selected != null) {
			lvAutor.getItems().add(selected);
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.autor.add.error.title"),
					resourceBundle.getString("admin.recurso.form.autor.add.error.header"),
					resourceBundle.getString("admin.recurso.form.autor.add.error.error.msg"));
		}
	}

	/**
	 * Muestra el formulario de creación de autores y espera hasta que este se
	 * cierre para recargar los autores.
	 * 
	 * @param event
	 *            ActionEvent pulsación del boton editar autor.
	 */
	@FXML
	void handleEditAutor(ActionEvent event) {
		Autor selected = cbAutor.getSelectionModel().getSelectedItem();

		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			Parent p = fxmlLoader.load(getClass().getResource(FxmlView.A_AUTOR_FORM.getFxmlFile()).openStream());
			AutorController controller = (AutorController) fxmlLoader.getController();
			controller.setAutor(selected);

			Stage form = new Stage();
			form.setScene(new Scene(p));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			loadAutores();
			checkAutoresExists();
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un autor de los autores seleccionados para el recurso.
	 * 
	 * @param event
	 *            ActionEvent pulsación del boton eliminar autor.
	 */
	@FXML
	void handleDeleteAutor(ActionEvent event) {
		Autor selected = lvAutor.getSelectionModel().getSelectedItem();
		if (selected != null) {
			lvAutor.getItems().remove(selected);
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.autor.delete.error.title"),
					resourceBundle.getString("admin.recurso.form.autor.delete.error.header"),
					resourceBundle.getString("admin.recurso.form.autor.delete.error.msg"));
		}
	}

	/**
	 * Añade el genero seleccionado en el ListView de generos.
	 * 
	 * @param event
	 *            ActionEvent pulsación del boton añadir genero.
	 */
	@FXML
	void handleAddGenero(ActionEvent event) {
		Genero selected = cbGenero.getSelectionModel().getSelectedItem();
		if (selected != null) {
			lvGenero.getItems().add(selected);
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.genero.add.error.title"),
					resourceBundle.getString("admin.recurso.form.genero.add.error.header"),
					resourceBundle.getString("admin.recurso.form.genero.add.error.msg"));
		}
	}

	/**
	 * Muestra el formulario de creación / edición de un Genero.
	 * 
	 * @param event
	 *            ActionEvent pulsación del boton editar genero.
	 */
	@FXML
	void handleEditGenero(ActionEvent event) {
		Genero selected = cbGenero.getSelectionModel().getSelectedItem();

		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			Parent p = fxmlLoader.load(getClass().getResource(FxmlView.A_GENERO_FORM.getFxmlFile()).openStream());
			GeneroController controller = (GeneroController) fxmlLoader.getController();
			controller.setGenero(selected);

			Stage form = new Stage();
			form.setScene(new Scene(p));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			loadGeneros();
			checkGenerosExists();
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	/**
	 * Elimina el genero selecionado de la listview de generos.
	 * 
	 * @param eventActionEvent
	 *            pulsación del boton elinimar genero.
	 */
	@FXML
	void handleDeleteGenero(ActionEvent event) {
		Genero selected = lvGenero.getSelectionModel().getSelectedItem();
		if (selected != null) {
			lvGenero.getItems().remove(selected);
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.genero.delete.error.title"),
					resourceBundle.getString("admin.recurso.form.genero.delete.error.header"),
					resourceBundle.getString("admin.recurso.form.genero.delete.error.error.msg"));
		}
	}

	/**
	 * Muestra el formulario de editar idioma, editando el idioma seleccionado en el
	 * combo de idiomas. Si no hay ninguno seleccionado, creará uno nuevo.
	 * 
	 * @param event
	 *            ActionEvent pulsación del boton editar idioma.
	 */
	@FXML
	void handleEditIdioma(ActionEvent event) {
		Idioma selected = cbIdioma.getSelectionModel().getSelectedItem();

		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			Parent p = fxmlLoader.load(getClass().getResource(FxmlView.A_IDIOMA_FORM.getFxmlFile()).openStream());
			IdiomaController controller = (IdiomaController) fxmlLoader.getController();
			controller.setIdioma(selected);

			Stage form = new Stage();
			form.setScene(new Scene(p));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			loadIdiomas();
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el formulario de creación de ejemplares.
	 * 
	 * @param event
	 *            ActionEvent pulsación en el botón añadir ejemplar
	 */
	@FXML
	void handleAddEjemplar(ActionEvent event) {
		Ejemplar newEjemplar = new Ejemplar();

		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			Parent p = fxmlLoader.load(getClass().getResource(FxmlView.A_EJEMPLAR_FORM.getFxmlFile()).openStream());
			EjemplarController controller = (EjemplarController) fxmlLoader.getController();
			controller.setEjemplar(newEjemplar);
			controller.setRecurso(recurso);

			Stage form = new Stage();
			form.setScene(new Scene(p));
			form.initModality(Modality.APPLICATION_MODAL);
			form.showAndWait();

			loadEjemplares();
		} catch (IOException e) {
			log.info("No se pudo abrir el fichero fxml");
			e.printStackTrace();
		}
	}

	@FXML
	void handleEditEjemplar(ActionEvent event) {
		Ejemplar editEjemplar = lvEjemplares.getSelectionModel().getSelectedItem();

		if (editEjemplar != null) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			try {
				Parent p = fxmlLoader.load(getClass().getResource(FxmlView.A_EJEMPLAR_FORM.getFxmlFile()).openStream());
				EjemplarController controller = (EjemplarController) fxmlLoader.getController();
				controller.setEjemplar(editEjemplar);
				controller.setRecurso(recurso);

				Stage form = new Stage();
				form.setScene(new Scene(p));
				form.initModality(Modality.APPLICATION_MODAL);
				form.showAndWait();

				loadEjemplares();
			} catch (IOException e) {
				log.info("No se pudo abrir el fichero fxml");
				e.printStackTrace();
			}
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.ejemplar.editar.empty.title"),
					resourceBundle.getString("admin.recurso.form.ejemplar.editar.empty.header"),
					resourceBundle.getString("admin.recurso.form.ejemplar.editar.empty.error.msg"));
		}

	}

	@FXML
	void handleDeleteEjemplar(ActionEvent event) {
		Ejemplar deleteEjemplar = lvEjemplares.getSelectionModel().getSelectedItem();

		if (deleteEjemplar != null) {
			if (showConfirmation(resourceBundle.getString("admin.recurso.form.ejemplar.delete.confirm.title"),
					resourceBundle.getString("admin.recurso.form.ejemplar.delete.confirm.header"),
					resourceBundle.getString("admin.recurso.form.ejemplar.delete.confirm.error.msg"))) {
				ejemplarService.delete(deleteEjemplar);
			}
		} else {
			sendAlert(AlertType.ERROR, resourceBundle.getString("admin.recurso.form.ejemplar.delete.error.title"),
					resourceBundle.getString("admin.recurso.form.ejemplar.delete.error.header"),
					resourceBundle.getString("admin.recurso.form.ejemplar.delete.error.error.msg"));
		}
	}

	@FXML
	void handleExit(ActionEvent event) {
		log.info("se pulsó el botón salir");
		closeDialog(event);
	}

	@FXML
	void handleSave(ActionEvent event) {
		validationMsg = "";
		if (validateRecurso()) {
			saveRecurso();
			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.recurso.save.success.title"),
					resourceBundle.getString("admin.recurso.save.success.header"),
					resourceBundle.getString("admin.recurso.save.success.msg"));
			closeDialog(event);
		} else {
			sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.recurso.save.validation.title"),
					resourceBundle.getString("admin.recurso.save.validation.header"), validationMsg);
		}
	}

	@FXML
	void handleDelete(ActionEvent event) {
		if (recurso.getIdRecurso() != null && recurso.getIdRecurso() > 0) {
			boolean borrar = showConfirmation(resourceBundle.getString("admin.recurso.delete.confirmation.title"),
					resourceBundle.getString("admin.recurso.delete.confirmation.header"),
					resourceBundle.getString("admin.recurso.delete.confirmation.msg"));

			if (borrar) {
				recursoService.delete(recurso);
				sendAlert(AlertType.INFORMATION, resourceBundle.getString("admin.recurso.delete.success.title"),
						resourceBundle.getString("admin.recurso.delete.success.header"),
						resourceBundle.getString("admin.recurso.delete.success.msg"));
				closeDialog(event);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (recurso == null) {
			recurso = new Recurso();
		}

		// reemplazar textos
		lblFormTitle.setText(resources.getString("admin.recurso.form.title"));
		lblTittulo.setText(resources.getString("admin.recurso.form.titulo.label"));
		lblISBN.setText(resources.getString("admin.recurso.form.isbn.label"));

		lblAutor.setText(resources.getString("admin.recurso.form.autor.label"));
		btnAddAutor.setText(resources.getString("admin.recurso.form.autor.button.add"));
		btnEditAutor.setText(resources.getString("admin.recurso.form.autor.button.edit"));
		btnDeleteAutor.setText(resources.getString("admin.recurso.form.autor.button.delete"));
		initializeAutor(resources);

		lblGenero.setText(resources.getString("admin.recurso.form.genero.label"));
		btnAddGenero.setText(resources.getString("admin.recurso.form.genero.button.add"));
		btnEditGenero.setText(resources.getString("admin.recurso.form.genero.button.edit"));
		btnDeleteGenero.setText(resources.getString("admin.recurso.form.genero.button.delete"));
		initializeGenero(resources);

		lblIdioma.setText(resources.getString("admin.recurso.idioma.label"));
		btnEditIdioma.setText(resources.getString("admin.recurso.form.idioma.button.edit"));
		initializeIdioma(resources);

		lblPDate.setText(resources.getString("admin.recurso.form.publicationDate.label"));
		lblNumPages.setText(resources.getString("admin.recurso.form.numPages.label"));

		lblEjemplares.setText(resources.getString("admin.recurso.form.ejemplar.label"));
		btnAddEjemplar.setText(resources.getString("admin.recurso.form.ejemplar.button.add"));
		btnEditEjemplar.setText(resources.getString("admin.recurso.form.ejemplar.button.edit"));
		btnDeleteEjemplar.setText(resources.getString("admin.recurso.form.ejemplar.button.delete"));
		initializeEjemplares(resources);

		btnExit.setText(resources.getString("admin.recurso.form.button.exit"));
		btnSave.setText(resources.getString("admin.recurso.form.button.save"));

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
							if (!StringUtils.isEmpty(item.getApellidos())) {
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
				if (!StringUtils.isEmpty(autor.getApellidos())) {
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
					ret = autorService.findByNombreAndByApellidosAllIgnoreCase(nombre, apellidos).stream().findFirst()
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
	 * Evalua si hay algún autor en la lista que no exista en base de datos, y lo
	 * elimina del listview.
	 */
	private void checkAutoresExists() {
		lvAutor.getItems().forEach(autor -> {
			if (autorService.findById(autor.getIdAutor()) == null) {
				lvAutor.getItems().remove(autor);
			}
		});
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
	 * Evalua si hay elementos en el listview de generos que ya no existan en la
	 * capa de persistencia y los elimina.
	 */
	private void checkGenerosExists() {
		lvGenero.getItems().forEach(genero -> {
			if (generoService.findById(genero.getIdGenero()) == null) {
				lvGenero.getItems().remove(genero);
			}
		});
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

		sendRecursoToForm();
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
	 * Metodo que inicia el listview de ejemplares.
	 * 
	 * @param resources
	 */
	private void initializeEjemplares(ResourceBundle resources) {

		lvEjemplares.setCellFactory(new Callback<ListView<Ejemplar>, ListCell<Ejemplar>>() {

			@Override
			public ListCell<Ejemplar> call(ListView<Ejemplar> param) {
				ListCell<Ejemplar> cell = new ListCell<Ejemplar>() {

					protected void updateItem(Ejemplar item, boolean empty) {
						super.updateItem(item, empty);

						if (item != null) {
							StringBuffer out = new StringBuffer();

							out.append(item.getIdEjemplar()).append(SEPARATOR).append(item.getCodigo())
									.append(SEPARATOR).append(item.getEstado().getNombre());

							setText(out.toString());
						}
					};
				};

				return cell;
			}
		});

		loadEjemplares();

	}

	/**
	 * Carga los ejemplares del recurso desde base de datos. Se carga desde base de
	 * datos por lo que se pueda haber hecho en el formulario de ejemplares.
	 */
	private void loadEjemplares() {
		// activa o desactiva controles en función del recurso
		checkNewRecurso();
		// solo recarga la lista si el recurso existe
		if (recurso != null && recurso.getIdRecurso() > 0) {
			lvEjemplares.getItems().clear();
			lvEjemplares.getItems().addAll(ejemplarService.findfindByRecurso_idRecurso(recurso.getIdRecurso()));
		}
		// si el recurso es nuevo, no se puede añadir ejemplares
	}

	/**
	 * Activa o desactiva los controles de ejemplares en función de si el recurso es
	 * nuevo o no.
	 * 
	 * Si el recurso es nuevo no se puede relacionar los ejemplares generados en el
	 * formulario de ejemplares al no existir un recurso en base de datos todavia.
	 */
	private void checkNewRecurso() {
		if (recurso != null && recurso.getIdRecurso() > 0) {
			lblEjemplares.setDisable(true);
			btnAddEjemplar.setDisable(true);
			btnEditEjemplar.setDisable(true);
			btnDeleteEjemplar.setDisable(true);
			lvEjemplares.setDisable(true);

			btnDelete.setDisable(true);
		} else {
			lblEjemplares.setDisable(false);
			btnAddEjemplar.setDisable(false);
			btnEditEjemplar.setDisable(false);
			btnDeleteEjemplar.setDisable(false);
			lvEjemplares.setDisable(false);

			btnDelete.setDisable(false);
		}
	}

	/**
	 * Lee los datos del recurso y los plasma en el formulario.
	 */
	private void sendRecursoToForm() {
		txtTitulo.setText(recurso.getTitulo());
		txtISBN.setText(recurso.getISBN());

		lvAutor.getItems().clear();
		lvAutor.getItems().addAll(recurso.getAutores());

		lvGenero.getItems().clear();
		lvGenero.getItems().addAll(recurso.getGeneros());

		cbIdioma.getSelectionModel().select(recurso.getIdioma());
		dpPDate.setValue(LocalDate
				.from(Instant.ofEpochMilli(recurso.getFechaPublicacion().getTime()).atZone(ZoneId.systemDefault())));

		lvEjemplares.getItems().clear();
		lvEjemplares.getItems().addAll(recurso.getEjemplares());
	}

	/**
	 * Lee los datos del formulario y los plasma en el recurso.
	 */
	private void sendFormToRecurso() {
		recurso.setTitulo(txtTitulo.getText().trim());
		recurso.setISBN(txtISBN.getText().trim());
		recurso.setAutores(lvAutor.getItems().stream().collect(Collectors.toSet()));
		recurso.setGeneros(lvGenero.getItems().stream().collect(Collectors.toSet()));
		recurso.setIdioma(cbIdioma.getSelectionModel().getSelectedItem());
		recurso.setFechaPublicacion(Date.from(dpPDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		recurso.setEjemplares(lvEjemplares.getItems().stream().collect(Collectors.toList()));
	}

	/**
	 * Setter del campo recurso.
	 * 
	 * @param recurso
	 *            Recurso entidad a editar.
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
		sendRecursoToForm();
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
	 * Valida si los dátos del formulario son válidos para guardar el recurso
	 * 
	 * @return
	 */
	private boolean validateRecurso() {
		boolean isValid = true;
		validationMsg = "";

		// titulo obligatorio
		if (StringUtils.isEmpty(txtTitulo.getText().trim())) {
			isValid = false;
			validationMsg += resourceBundle.getString("admin.recurso.validation.title.empty");
		} else {
			List<Recurso> temp = recursoService.findByTitulo(txtTitulo.getText().trim());
			if (temp != null && temp.stream().filter(r -> !r.getIdRecurso().equals(recurso.getIdRecurso())).findAny()
					.orElse(null) != null) {
				isValid = false;
				validationMsg += resourceBundle.getString("admin.recurso.validation.title.alreadyExists");
			}
		}

		// isbn no obligatorio
		if (!StringUtils.isEmpty(txtISBN.getText().trim())) {
			Recurso temp = recursoService.findByISBN(txtISBN.getText().trim());
			if (temp.getIdRecurso().equals(recurso.getIdRecurso())) {
				isValid = false;
				validationMsg += resourceBundle.getString("admin.recurso.validation.isbn.alreadyExists");
			}
		}

		// autores obligatorio
		if (lvAutor.getItems() == null || lvAutor.getItems().isEmpty()) {
			isValid = false;
			validationMsg += resourceBundle.getString("admin.recurso.validation.autores.empty");
		}

		// generos obligatorio
		if (lvGenero.getItems() == null || lvGenero.getItems().isEmpty()) {
			isValid = false;
			validationMsg += resourceBundle.getString("admin.recurso.validation.generos.empty");
		}

		// idioma obligatorio
		if (cbIdioma.getSelectionModel().isEmpty()) {
			isValid = false;
			validationMsg += resourceBundle.getString("admin.recurso.validation.idioma.empty");
		}

		// fechapublicacion no obligatorio
		// numero de paginas no obligatorio

		return isValid;
	}

	/**
	 * Guarda el recurso en la capa del formulario
	 */
	private void saveRecurso() {
		sendFormToRecurso();
		recursoService.save(recurso);
	}

}
