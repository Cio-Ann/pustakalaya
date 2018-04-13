package cgr.cgfsdam.pustakalaya;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import cgr.cgfsdam.pustakalaya.config.StageManager;
import cgr.cgfsdam.pustakalaya.model.funds.Autor;
import cgr.cgfsdam.pustakalaya.model.funds.Genero;
import cgr.cgfsdam.pustakalaya.model.funds.Idioma;
import cgr.cgfsdam.pustakalaya.model.funds.Recurso;
import cgr.cgfsdam.pustakalaya.model.users.Direccion;
import cgr.cgfsdam.pustakalaya.model.users.Role;
import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.users.RoleRepository;
import cgr.cgfsdam.pustakalaya.repository.users.TipoDocumentoRepository;
import cgr.cgfsdam.pustakalaya.service.funds.AutorService;
import cgr.cgfsdam.pustakalaya.service.funds.GeneroService;
import cgr.cgfsdam.pustakalaya.service.funds.IdiomaService;
import cgr.cgfsdam.pustakalaya.service.funds.RecursoService;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Clase inicial de la aplicación.
 *
 * @author CGR-Casa
 */
@SpringBootApplication
public class PustakalayaApplication extends Application {

	/**
	 * Contexto Spring
	 */
	protected ConfigurableApplicationContext springContext;
	/**
	 * Gestor de Stages
	 */
	protected StageManager					 stageManager;

	/**
	 * Método de inicio.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void init() throws Exception {

		springContext = springBootApplicationContext();
	}

	@Override
	public void start(Stage stage) throws Exception {

		stageManager = springContext.getBean(StageManager.class, stage);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {

		springContext.close();
	}

	/**
	 * Establece la pantalla inicial.
	 */
	protected void displayInitialScene() {

		stageManager.switchScene(FxmlView.LOGIN);
	}

	/**
	 * Método para crear un contexto de aplicación que se utilizará para mantener un
	 * gestor común de depentencias.
	 * 
	 * @return ConfigurableApplicationContext contexto de aplicación
	 */
	private ConfigurableApplicationContext springBootApplicationContext() {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(PustakalayaApplication.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return builder.run(args);
	}

	@Autowired
	RoleRepository			roleRepository;
	@Autowired
	TipoDocumentoRepository	tipoDocumentoRepository;
	@Autowired
	UsuarioService			usuarioService;
	@Autowired
	RecursoService			recursoService;
	@Autowired
	AutorService			autorService;
	@Autowired
	GeneroService			generoService;
	@Autowired
	IdiomaService			idiomaService;

	@Bean
	InitializingBean sendDatabase() {

		return () -> {
			// inserto los roles por defecto
			Role roleAdmin = new Role("ADMIN", "Perfil de administrador");
			roleRepository.save(roleAdmin);
			Role roleLector = new Role("LECTOR", "Perfil de usuario de la biblioteca");
			roleRepository.save(roleLector);

			TipoDocumento dni = new TipoDocumento("DNI", "Documento nacional de identidad");
			TipoDocumento nie = new TipoDocumento("NIE", "Número de identificación de extrangero");
			TipoDocumento pas = new TipoDocumento("PASAPORTE", "Pasaporte");
			tipoDocumentoRepository.save(dni);
			tipoDocumentoRepository.save(nie);
			tipoDocumentoRepository.save(pas);

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);

			Set<Role> roles = new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ADMIN")));

			Usuario admin = new Usuario("admin", // nombre
					"admin", // apellido1
					"admin", // apellido2
					tipoDocumentoRepository.findByNombre("DNI"), // tipoDocumento
					"00000000T", // documento
					"admin", // username
					"admin", // password
					1, // activo
					roles, new Direccion("CALLE", "Platino", "2", "14", null, "5", "C", "Torrejon de Ardoz", "Madrid",
							"28850"),
					"666777888", "admin@pruebas.son", new Date(), cal.getTime());

			usuarioService.saveUsuario(admin);

			roles = new HashSet<Role>(Arrays.asList(roleRepository.findByRole("LECTOR")));

			Usuario lector = new Usuario("lector", // nombre
					"lector", // apellido1
					"lector", // apellido2
					tipoDocumentoRepository.findByNombre("DNI"), // tipoDocumento
					"00000001R", // documento
					"lector", // username
					"lector", // password
					1, // activo
					roles, new Direccion("CALLE", "Platino", "2", "14", null, "5", "C", "Torrejon de Ardoz", "Madrid",
							"28850"),
					"666555444", "lector@pruebas.son", new Date(), cal.getTime());

			usuarioService.saveUsuario(lector);

			// idiomas -------------------------------------------------------------------------------------------------
			Idioma idioma1 = new Idioma("Español(ES)", "Español tradicional, España");
			idiomaService.save(idioma1);
			Idioma idioma2 = new Idioma("Inglés(EN_US)", "Inglés americano");
			idiomaService.save(idioma2);
			Idioma idioma3 = new Idioma("Ingles(EN_GB)", "Inglés britanico");
			idiomaService.save(idioma3);

			// autores -------------------------------------------------------------------------------------------------
			Autor autor1 = new Autor("Autor1", "Autor1");
			autorService.save(autor1);
			Autor autor2 = new Autor("Autor2", "Autor2");
			autorService.save(autor2);
			Autor autor3 = new Autor("Autor3", "Autor3");
			autorService.save(autor3);

			// generos -------------------------------------------------------------------------------------------------
			Genero genero1 = new Genero("Genero 1", "Genero 1");
			generoService.save(genero1);
			Genero genero2 = new Genero("Genero 2", "Genero 2");
			generoService.save(genero2);
			Genero genero3 = new Genero("Genero 3", "Genero 3");
			generoService.save(genero3);

			// recursos
			// -------------------------------------------------------------------------------------------------
			Recurso recurso1 = new Recurso("Recurso 1", null, new Date(), idioma1, null, 111, "111111111");
			recurso1.addAutores(autor1);
			recurso1.addGeneros(genero1);
			recursoService.save(recurso1);
			Recurso recurso2 = new Recurso("Recurso 2", null, new Date(), idioma2, null, 222, "111111112");
			recurso2.addAutores(autor2);
			recurso2.addGeneros(genero2);
			recursoService.save(recurso2);
			Recurso recurso3 = new Recurso("Recurso 3", null, new Date(), idioma3, null, 333, "111111113");
			recurso3.addAutores(autor3);
			recurso3.addGeneros(genero3);
			recursoService.save(recurso3);

		};
	}

}
