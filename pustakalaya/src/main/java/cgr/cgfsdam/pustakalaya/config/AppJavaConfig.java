package cgr.cgfsdam.pustakalaya.config;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javafx.stage.Stage;

@Configuration
public class AppJavaConfig {

	@Autowired
	SpringFXMLLoader springFXMLLoader;

	@Bean
	public ResourceBundle resourceBundle() {

		return ResourceBundle.getBundle("texts/Bundle");
	}

	@Bean
	@Lazy(value = true) // Stage solo se crea despues de cargar el contexto de Sprint
	public StageManager stageManager(Stage stage) throws IOException {

		return new StageManager(springFXMLLoader, stage);
	}

}
