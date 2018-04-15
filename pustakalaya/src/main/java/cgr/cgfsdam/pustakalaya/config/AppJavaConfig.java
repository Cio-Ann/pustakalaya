/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgr.cgfsdam.pustakalaya.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import cgr.cgfsdam.pustakalaya.logging.ExceptionWriter;

@Configuration
@PropertySource(ignoreResourceNotFound=true, value="file:config/db.properties")
public class AppJavaConfig {
	
    @Autowired 
    SpringFXMLLoader springFXMLLoader;

    /**
     * Util para volcar un stacktrace a un String para logs.
     * 
     * @return ExceptionWriter objeto con utils para manejar excepciones.
     */
    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("texts/Bundle");
    }
    
    @Bean
    @Lazy(value = true) //Stage solo se crea despues de cargar el contexto de Sprint
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }

}
