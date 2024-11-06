# Proyecto API Rest - Rencapp

API Rest para la gestión de servicios, trámites y agendamiento de la Dirección de Medio Ambiente (DIMAO) de la Municipalidad de Renca, desarrollada con Java y Spring Boot por la Cohorte 14 de Generation Chile.

## Tabla de Contenidos
- [Descripción General](#descripción-general)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Configuración de Herramientas Externas](#configuración-de-herramientas-externas)
- [Módulos](#módulos)
- [Documentación de la API](#documentación-de-la-api)
- [Colaboradores](#colaboradores)

### Descripción General
Este sistema permite a los vecinos agendar citas, solicitar trámites, subir documentos y recibir notificaciones por mensajería y correo electrónico. De igual manera, permite a los funcionarios y administradores la gestión general de los departamentos y servicios. Cada módulo fue desarrollado por un equipo de trabajo independiente.

### Requisitos
- **Java 11+**: Requerido para ejecutar el proyecto Spring Boot.
- **Maven 3.6+**: Para gestionar las dependencias y la compilación del proyecto.
- **MySQL**: Base de datos para el almacenamiento de usuarios y datos de servicios.
- **Google Cloud Storage**: Almacenamiento de documentos y archivos de los usuarios.
- **SendGrid API**: Para el envío de notificaciones y confirmaciones por correo electrónico.

### Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/rencapp-api.git
   ```
2. Navega hasta el directorio:
   ```bash
   cd RencappRest
   ```
3. Instala las dependencias del proyecto usando Maven:
   ```bash
   mvn install
   ```
4. Configura las credenciales del servidor Mysql en el archivo:
  - Crea una base de datos en MySQL con el nombre rencapp.
  - Configura las credenciales en el archivo `application.properties` en el directorio `src/main/resources`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/rencapp
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA
   ```
6. Configura las variables de entorno necesarias para Google Cloud Storage y SendGrid en el archivo `application.properties` en el directorio `src/main/resources`:
    ```properties
   bucket.name=test-GCP_BUCKET_NAME
   sendgrid.sender.email=SENDER_EMAIL_SENDGRID
   sendgrid.api.key=API_KEY_SENDGIRD
   ```
7. Agrega la ruta de la clave `JSON` en la clase  `GoogleCloudStorageService` en el directorio `src/main/java/rencapp/storage`:
   ```java
   private final String SERVICE_ACCOUNT_JSON_PATH="TU_RUTA_A_CLAVE_JSON"
   ```
8. Para recibir notificaciones por SMS con Twilio, agrega valores a las variables en la clase  `MensajesService` en el directorio `src/main/java/rencapp/email`:
   ```java
   public static final String sid = "TU_SID_TWILIO";
   public static final String token = "TU_TOKEN_TWILIO";
   public static final String remitente = "TU_NUMERO_REMITENTE";
   ```

### Ejecución
Inicia el proyecto con:
 ```bash
   mvn spring-boot:run
 ```
La API estará disponible en `http://localhost:8080`.

### Módulos
- **Autenticación**: Manejo de inicio de sesión y registro de usuarios.
- **Departamentos y servicios**: Gestión de departamentos y servicios.
- **Trámites**: Gestión de trámites y solicitudes.
- **Agendamiento**: Permite a los usuarios crear y administrar citas.
- **Documentos**: Gestión de archivos y documentos.
- **Notificaciones**: Envía notificaciones automáticas para las citas.

### Documentación de la API
La documentación de la API está disponible en `http://localhost:8080/swagger-ui.html` o en el archivo `/docs/index.html`.

### Colaboradores
- Tribu OneBits:
  - [Belén Vidal](https://github.com/belenvidal1)
  - [Branco Moreira](https://github.com/BrancoMI)
  - [Daniel Pérez](https://github.com/Daniel27Perez)
  - [Nadima López](https://github.com/NadimaLopez)
- Tribu PatiDevs:
  - [Estefany Rodríguez](https://github.com/EstefanyRodriguezP)
  - [Eleonor Liguen](https://github.com/mendriLg)
  - [Matías Duran](https://github.com/znatan13)
  - [Matías Carreño](https://github.com/MatiasD20)
- Tribu SaiyaBit:
  - [Benjamin Riquelme](https://github.com/MrBenji20)
  - [Catalina Fuenzalida](https://github.com/catycaaa)
  - [Fabian Vilches](https://github.com/C14Vilches)
  - [Marcelo Gallardo](https://github.com/MarceloGallardo01)
  - [Paulina Vargas](https://github.com/PaulinaaVargas)
  - [Sebastian Quero](https://github.com/QueroSebastian)
- Tribu WebMasterJava:
  - [Duberney Cardona](https://github.com/BuBaBug)
  - [Fabian Canales](https://github.com/FabianCanales)
  - [Manuel Donoso](https://github.com/Manueldonoso07)
  - [Rene Cabello](https://github.com/CabelloMorales)
  - [Sabina Vargas](https://github.com/SabinaVC)
  - [Yessie Neira](https://github.com/YessNC)
- Tribu Quackendars:
  - [Fernando Parra](https://github.com/fernandopmtz)
  - [Maria Fernanda Bonelli](https://github.com/mariabonelli)
  - [Manuel Martinez](https://github.com/manueelmg)
  - [Paula Millan](https://github.com/zmillanpaula)
  - [Valentina Matamala](https://github.com/ValentinaMatamalaGonzalez)
- Tribu AmongBugs:
  - [Carol Arias](https://github.com/AriasCarol)
  - [Donald Vargas](https://github.com/DonaldVargas7)
  - [Felipe Bascuñan](https://github.com/FelipeBascunanA)
  - [Violeta Pino](https://github.com/Viole0416)
