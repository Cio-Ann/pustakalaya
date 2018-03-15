# Pustakalaya

## Proyecto de fin de ciclo  del Ciclo Formativo de Grado Médio en Desarrollo de Aplicaciones Multiplataforma.

## Descripcion
Proyecto de una aplicación de escritorio en la que se gestiona los fondos disponibles de una biblioteca, así como la afiliación de los usuarios, y los prestamos y reservas realizdos por estos.

## Tecnologías
Para la gestión de dependencias, y construcción de aplicaciones se utiliza Gradle.
Para la creación de esta aplicación se ha utilizado la tecnología JavaFx como GUI.
Para la persistencia se ha utilizado Hibernate + JPA conectado a MySql
Como framework se utiliza Spring, tanto para la injeccion de dependencias como con el uso de algunos de los proyectos de spring.
* Spring Data - Integración de JPA e Hibernate
* Spring Security - Autenticación y autorización de usuarios
* Spring Mail - Envío de notificaciones vía eMail
* Spring Batch -  ejecución de tareas programadas



### Algunos datos
En el proyecto Java FX utiliza el contexto de Spring para la injección de dependencias.
Otro punto importante es que se utiliza Spring Security en una aplicación de escritorio, cuando en la mayoría de documentación y ejemplos se utiliza solo en proyectos web.
