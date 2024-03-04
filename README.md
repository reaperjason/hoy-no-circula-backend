# HoyNoCircula

Es una aplicación que permite a los autos registrados consultar si pueden circular en determinada fecha y horario.
Las principales funcionalidades de la aplicación son:
  1) Registrar un vehiculo en el sistema.
  2) Consultar si un vehículo registrado puede circular en la fecha y hora establecidas.

La aplicacion esta dividida en 2 partes que corresponen al backend y frontend, en este repositorio se explicaran los detalles referentes al Backend de la aplicación.
Para consultar sobre el backend de la aplicación dirigirse a: https://github.com/reaperjason/hoy-no-circula-frontend

## Detalles Técnicos

El proyecto se creo con Springboot 3.1.9 usando Maven - java.
Dependencias spingboot:
  spring web
  lombok
  spring boot devtools
  mysql driver
  sping data JPA
jdk 21
La Base de datos es una base de AWS RDS, que permite tener una instancia en la nube:
MySQL 8.0.35
username:root
pass: root1234
endpoint: database-1.cxw4kiemum7t.us-east-2.rds.amazonaws.com

## Recursos

El proyecto tiene varios recursos en línea para que el frontend pueda conectarse directamente sin necesidad de levantar otros servidores locales.
Estos recursos son: 
  1) Base de datos MySQL en el servicio RDS de AWS
  2) Servidor Backend en línea, implementado con EC2 de AWS, con ip publica http://3.19.200.96:8080

El proyecto cuenta con una collection de Postman donde se puede ver la configuracion de los API REST implementados en el servidor Backend, esta colección
se puede encontrar en la carpeta extras.

Los endpoints del API REST son:
POST: http://3.19.200.96:8080/api/v1/car/plate -  Para consultar si un vehículo registrado puede circular en la fecha enviada.
POST: http://3.19.200.96:8080/api/v1/car - Para registrar nuevos vehículos

## Instalacion

Como se explico ya existe un .jar del proyecto corriendo en un servidor de la nube, el frontend se conecta directamente a dicho servidor, por lo que no es necesario correr localmente un servidor backend
o una base de datos.
Sin embargo si se desea correr de forma local el proyecto es importante considerar los detalles técnicos para que el proyecto pueda correr correctamente.
A continuación se detallan los pasos a seguir:
  1) Clonar el proyecto de forma local desde este repositorio, rama master
  2) Asegurarse que a version de jdk sea la 21, se puede ver con java -version, instalar las dependencias necesarias con mvn install
  3) Revisar los application.properties de desarrollo y produccion, deben estar apuntando a la base en la nube: jdbc:mysql://database-1.cxw4kiemum7t.us-east-2.rds.amazonaws.com:3306/db_hoy_no_circula?useSSL=false
  4) Ejecutar el archivo "HoyNoCirculaApplication.java" desde ell editor, o desde consola compilarlo con: javac HoyNoCirculaApplication.java y despues: java HoyNoCirculaApplication
  5) OPCIONAL: Si se desea correr el archivo .jar generado se lo puede ubicar en /extra/hoy-no-circula-0.0.1-SNAPSHOT.jar, usar el comando java -jar hoy-no-circula-0.0.1-SNAPSHOT.jar

Si se desea conectarse a una base de datos Local:
  1) Instalar un servidor de base de datos MySQL
  2) Revisar el directorio /extras/databaseQuery, ahi se encuentran las intrucciones para crear la base de datos y comandos para crear tablas, insertar datos, etc en la nueva base de datos
  3) Cambiar el string de conexión de application.properties para la nueva base de datos.

## Despliegue

Para desplegar nuevamente el proyecto se debe tener instalado maven y utilizar el comando mvn package, lo que generará un jar del proyecto en la carpeta target.
El .jar puede ejecutarse de forma local o en un servidor backend, el comando para correr ese archivo .jar en un servidor ubuntu es: nohup java -jar app.jar -o log.txt &
Revisar la configuración de cors antes del despliegue, el proyecto tiene habilitado el http://localhost:4200/ para la comunicacion con angular.

## Estructura y otros detalles

El proyecto esta estructurado en controller, model, repository, service y utils.
En el controller estan los Endpoints mencionados anteriormente y la configuracion de CORS de cada endpoint.
En Model la principal Entidad es Car, que esta implementada como una Clase de java.
El repository con tiene el DAO para las operaciones con base de datos.
En el service se defninen los servicios como el registro de un vehiculo nuevo,etc.
En utils se encuentra lógica que se puede reutilizar en todo el proyecto, como es la lógica para verificar si un vehículo puede circular o no según su placa
