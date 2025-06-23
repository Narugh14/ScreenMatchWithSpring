# 🎬 ScreenMatchWithSpring - Buscador de Películas y Series en CLI
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1%2B-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Herramienta de línea de comandos (CLI) para buscar información de películas y series usando Spring Boot. Consulta datos cinematográficos desde tu terminal integrando con la API de OMDB.

## ✨ Características
Búsqueda de películas y series por título

- Visualización de detalles completos: año, duración, género, etc.
- Sistema de favoritos para guardar tus títulos preferidos
- Integración con API de OMDB
- Formato de salida claro y organizado

## ⚙️ Prerrequisitos
- Java 17 o superior

- Maven 3.6+

- API key de OMDB (gratuita)

## 🚀 Instalación
Clona el repositorio:
```
bash
git clone https://github.com/Narugh14/ScreenMatchWithSpring.git
```
Construye la aplicación:
```
bash
cd ScreenMatchWithSpring
mvn clean package
```
🔧 Configuración
Obtén una API key gratuita en OMDB API

Crea un archivo application.properties en src/main/resources:
```
properties
omdb.api.key=TU_API_KEY
```
## 🖥️ Uso
Ejecuta la aplicación con el comando:
```
bash
java -jar target/ScreenMatch-*.jar
```
Flujo de la aplicación:
Menú principal:
```
-
Bienvenido a ScreenMatch!
Elige una opción:
1. Buscar película/serie
2. Ver favoritos
3. Salir
Búsqueda por título:
-
Ingresa el nombre de la película/serie:


> Matrix
Resultados de búsqueda:
-
Resultados:
1. The Matrix (1999)
2. The Matrix Reloaded (2003)
3. The Matrix Revolutions (2003)
Selecciona un número para ver detalles o 0 para volver:
Detalles del título:
-
Título: The Matrix
Año: 1999
Duración: 136 min
Género: Action, Sci-Fi
Director: Lana Wachowski, Lilly Wachowski
Actores: Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss
Sinopsis: A computer hacker learns from mysterious rebels about the true nature of his reality...
IMDB Rating: 8.7

¿Agregar a favoritos? (S/N):
```
## 🧩 Comandos disponibles
Comando	Descripción
- Buscar	Inicia búsqueda por título
- Ver favoritos	Muestra títulos guardados como favoritos
- Salir	Finaliza la aplicación

## 🧪 Ejecución en IDE
- Ejecuta la clase principal: com.screenmatch.ScreenMatchApplication desde tu IDE con Spring Boot.

## 🌳 Estructura del Proyecto
```
src/
├── main/
│   ├── java/com/screenmatch/
│   │   ├── main/             # Lógica principal
│   │   │   ├── Menu.java
│   │   │   ├── Search.java
│   │   │   └── Favorites.java
│   │   ├── models/           # Modelos de datos
│   │   │   ├── TitleData.java
│   │   │   └── EpisodeData.java
│   │   ├── services/         # Servicios
│   │   │   ├── APIConsumer.java
│   │   │   └── DataConverter.java
│   │   └── ScreenMatchApplication.java
│   └── resources/
│       └── application.properties
└── test/
```
## 🔍 Ejemplo de Búsqueda
```
bash
> Selecciona: 1 (Buscar película/serie)
> Ingresa título: Inception
> Resultados:
  1. Inception (2010)
  2. Inception: The Cobol Job (2010)
  3. Inception: Jump Right Into the Action (2010)
> Selecciona: 1

Título: Inception
Año: 2010
Duración: 148 min
Género: Action, Adventure, Sci-Fi
Director: Christopher Nolan
Actores: Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page
Sinopsis: A thief who steals corporate secrets through dream-sharing technology...
IMDB Rating: 8.8
```
## 🤝 Cómo Contribuir
Haz fork del proyecto

Crea tu rama (git checkout -b feature/nueva-funcionalidad)

Haz commit de tus cambios (git commit -m 'Add some feature')

Haz push a la rama (git push origin feature/nueva-funcionalidad)

Abre un Pull Request

## 📄 Licencia
Distribuido bajo la licencia MIT. Ver LICENSE para más detalles.
