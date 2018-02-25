package cgr.cgfsdam.pustakalaya;

//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;

//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;

import cgr.cgfsdam.pustakalaya.config.StageManager;
//import cgr.cgfsdam.pustakalaya.model.users.Direccion;
//import cgr.cgfsdam.pustakalaya.model.users.Role;
//import cgr.cgfsdam.pustakalaya.model.users.TipoDocumento;
//import cgr.cgfsdam.pustakalaya.model.users.Usuario;
//import cgr.cgfsdam.pustakalaya.repository.users.RoleRepository;
//import cgr.cgfsdam.pustakalaya.repository.users.TipoDocumentoRepository;
//import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;
import cgr.cgfsdam.pustakalaya.view.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class PustakalayaApplication extends Application {

	protected ConfigurableApplicationContext springContext;
	protected StageManager stageManager;

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

	protected void displayInitialScene() {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	private ConfigurableApplicationContext springBootApplicationContext() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PustakalayaApplication.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return builder.run(args);
	}
	/*
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	@Autowired
	UsuarioService usuarioService;
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	//inserto los roles por defecto
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
			
			Set<Role> roles= new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ADMIN")));
			
	    	Usuario admin = new Usuario(
	    			"admin",	//nombre 
	    			"admin", 	//apellido1
	    			"admin", 	//apellido2
	    			tipoDocumentoRepository.findByNombre("DNI"), 		//tipoDocumento
	    			"00000000T",//documento 
	    			"admin",	//username 
	    			"admin", 	//password
	    			1, 			//activo
	    			roles, 
	    			new Direccion("CALLE", "Platino", "2", "14", null, "5", "C", "Torrejon de Ardoz", "Madrid", "28850"), 
	    			"666777888", 
	    			"admin@pruebas.son", 
	    			new Date(), 
	    			cal.getTime()
			);
	    	
	    	usuarioService.saveUsuario(admin);

			roles= new HashSet<Role>(Arrays.asList(roleRepository.findByRole("LECTOR")));
			
	    	Usuario lector = new Usuario(
	    			"lector",	//nombre 
	    			"lector", 	//apellido1
	    			"lector", 	//apellido2
	    			tipoDocumentoRepository.findByNombre("DNI"), 		//tipoDocumento
	    			"00000001R",//documento 
	    			"lector",	//username 
	    			"lector", 	//password
	    			1, 			//activo
	    			roles, 
	    			new Direccion("CALLE", "Platino", "2", "14", null, "5", "C", "Torrejon de Ardoz", "Madrid", "28850"), 
	    			"666555444", 
	    			"lector@pruebas.son", 
	    			new Date(), 
	    			cal.getTime()
			);
	    	
	    	usuarioService.saveUsuario(lector);
	    	
	    	
	      };
	   }
	*/
}
