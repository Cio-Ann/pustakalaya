package cgr.cgfsdam.pustakalaya;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import cgr.cgfsdam.pustakalaya.config.StageManager;
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
	 * Método de inicio de java.
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
}
