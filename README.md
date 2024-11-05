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
1. Clona el repositorio.
2. Ejecuta mvn install para instalar las dependencias.

### Ejecución
Inicia el proyecto con mvn spring-boot:run. La API estará disponible en http://localhost:8080.

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
  - [Branco Moreira]
  - [Daniel Pérez]
  - [Nadima López]
- Tribu PatiDevs:
  - [Estefany Rodríguez]
  - [Eleonor Liguen]
  - [Matías Duran]
  - [Matías Carreño]
- Tribu SaiyaBit:
  - [Benjamin Riquelme]
  - [Catalina Fuenzalida]
  - [Fabian Vilches]
  - [Marcelo Gallardo]
  - [Paulina Vargas]
  - [Sebastian Quero]
- Tribu WebMasterJava:
  - [Duberney Cardona]
  - [Fabian Canales]
  - [Manuel Donoso]
  - [Rene Cabello]
  - [Sabina Vargas]
  - [Yessie Neira]
- Tribu Quackendars:
  - [Fernando Parra]
  - [Maria Fernanda Bonelli]
  - [Manuel Martinez]
  - [Paula Millan]
  - [Valentina Matamala]
- Tribu AmongBugs:
  - [Carol Arias]
  - [Donald Vargas]
  - [Felipe Bascuñan]
  - [Violeta Pino]
