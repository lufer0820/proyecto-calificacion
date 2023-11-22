# Proyecto de Software de Calificación de Alumnos.


# Descripción
En el entorno educativo de un colegio, surge la necesidad de crear un software que facilite la gestión de notas de los estudiantes. Este software permitirá el registro, seguimiento y cálculo de las calificaciones de los estudiantes en diferentes asignaturas, contribuyendo a una administración más eficiente y a una comunicación transparente de los padres y tutores.

# Detalles del problema
El colegio busca una solución para gestionar las calificaciones de los estudiantes de manera más efectiva. El software deberá ser capaz de registrar las notas asignadas por los profesores en distintas asignaturas y presentarlas de manera organizada para su análisis y seguimiento. Además, deberá brindar la posibilidad de generar informes para los padres y tutores.


## Base de datos
En este caso, la base de datos es hecha mediante un SGBD como MySQL. Esto debido a que primero, la estructura de los datos no es horizontal. En cambio, es una información que necesita ser estructurada sí o sí por lo que es descartado usar bases de datos no relacionales (NoSQL). Otra de las razones es que constantemente va a estar escribiendo datos.

### ¿Por qué usar SQL y no las NoSQL en este proyecto?
Cuando se usan bases de datos no relacionales como MongoDB, las funciones de estos SGBD son más que todo para la lectura de datos y evitar escribir tantas consultas por lo que puede ahorrar tiempo en ejecución.

Como se dijo anteriormente, dado que se necesita constantemente estar escribiendo datos, se toman bases de datos SQL como MySQL o Postgre. En este caso, se tomará MySQL dado que es el lenguaje estructurado en bases de datos más famosa por el momento.

### Razones de la estructura de la base de datos
La base de datos actualmente contiene 7 tablas de las cuales, se puede tomar la información de las tablas de la siguiente manera:
 * Personas es una tabla que guarda la información tanto de los estudiantes como los profesores. Por cuestiones de normalización y evitar redundancia, se crea esta tabla y las tablas de profesores y estudiantes guardan solo la información específica.
 * Hay una relación entre la tabla de estudiante y materia que genera una tabla terciaria para tener una tabla que guarde la información de los registros específicamente en vez de generar tablas caóticas en el caso de los estudiantes o de la materia.
 * Lo mismo pasa con las notas de los estudiantes y las tareas. En este caso, la tabla Notas guarda la información de la nota y un comentario que el profesor decida poner.
 * Hay triggers que hacen que la base de datos guarde unos datos predeterminados si no son ingresados dentro del rango correcto (Protocolo ACID. *Consistencia*). Por ejemplo, la tabla *Notas* es una tabla que tiene un trigger que evita que el usuario ponga una nota menor a 0 o mayor a 5.

El modelo entidad relación es el siguiente:

![Diagrama entidad relación del proyecto](https://github.com/AgenteAgherse/software-calificacion/blob/main/Database/MER.png)

> Nota: Se cambia el atributo tipo_identificacion de INT a VARCHAR.

### Consideraciones a tener en cuenta.
Para evitar errores al momento de eliminar o actualizar algún registro dentro del programa, por favor, vaya al editor de código donde maneja los SCHEMA de MySQL (PHPMyAdmin, MySQL Workbench, etc) y establezca la variable SET SQL_SAFE_UPDATES con un valor de 0.
```
    SET SQL_SAFE_UPDATES=1;
```

# Proyecto
El proyecto actualmente usa los servicios que provee el framework de Spring Framework.
Las librerías necesarias dentro del proyecto son las siguientes:
* JPA Repository
* Lombok
* MySQL Connector
* Spring Boot
* Spring Security
* Spring Web Services
* Thymeleaf
* Springdoc

El proyecto a su vez, está dividido por diferentes carpetas las cuales van a ser descritas a continuación:
* **Security**: Carpeta donde se guarda la configuración del login y la encriptación de los usuarios y contraseñas de los profesores. Hay que tener en cuenta que con la versión 6.1.5 de Spring Security, la configuración de la seguridad cambia y se hace obligatorio el uso de funciones lambda.
* **Model, Service y Controller**: Son carpetas que crean objetos de las diferentes entidades de la base de datos, hacen uso del repositorio y brinda dirección dentro del sitio respectivamente. Estas carpetas son separadas para cumplir con el patrón estructural MVCS.
* **Repository**: Carpeta que guarda los interfaces que conectan el modelo con el repositorio JPA (Para operaciones con la base de datos).

## Consideraciones
El proyecto al incluir la información por Thymeleaf en vez de enviar la información por medio de un JSON (el frontend y el backend es netamente en Spring), va a mostrar las salidas en Postman en formato de HTML.
![Imagen del index sin haber iniciado sesión](https://github.com/AgenteAgherse/software-calificacion/blob/main/Database/inicio(wo_login).png)

En la imagen anterior, se muestra el formulario del login dado que Spring Security está funcionando sin problemas. Dado que la configuración no permite en ninguna de las opciones iniciar la sesión, se procede a llenar la información de inicio de sesión en el navegador.
![Formulario de inicio de sesión](https://github.com/AgenteAgherse/software-calificacion/blob/main/Database/form_login.png)

![Index](https://github.com/AgenteAgherse/software-calificacion/blob/main/Database/index.png)


### Consideraciones a tener en cuenta
El proyecto en Spring está usando Thymeleaf como motos de Templates y por ende, la anotación que se usa para mostrar la información en estos casos es 
```
@Controller
```
Esto hace que la documentación presentada en Swagger no muestre información de los diferentes métodos de cada uno de estos elementos ya que no tiene un principio REST.
Si desea ver la información en Swagger, tiene que cambiar dicha anotación por un 
```
@RestController
```
Sin embargo, tenga en cuenta lo siguiente. Si toma en cuenta esta anotación, la salida va a ser de tipo String y no ResponseEntity por lo que no va a mostrar información alguna acerca de los recursos traídos.

