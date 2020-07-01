# Vuetify

### Instalación

con **vue/cli**  correr el comando `vue add vuetify` 

### Comenzando

al instalar vuetify se adiciona al proyecto el directorio ***plugins/vuetify.js*** donde esta la importación y configuración de vuetify, con el theme con los colores e iconos

material design proporciona una gama de colores,fonts,etc adicional en la ayuda del vuetify

**<v-app>** es la etiqueta dentro de la cual debe estar todo el código de vuetify

### Grid (layout)

**v-container** contenedor para de los grid

 **v-row** fila por cada columna realizada

 **v-col** columnas

### Botons e Iconso

<v-btn> para crear botones con algunas propiedades

<v-icon> para poder trabajar con iconos `<v-icon>mdi-home</v-icon>` 

### Navbar (toolbar)

<v-toolbar> barra de navegación **app** hace que se quede fixed en la paret superior

​	<v-toolbar-tible> titulo de la barra de navegación 

se le puede configurar un navgationDrawer

### Cards

esta dividida en 4 secciones  <v-img> | <v-card-title> | <v-card-text> | <v-card-actions>

el <v-layout> de tener las propiedades `row` y ` wrap` para que trabaje en row y respete el sistema de columnas

el <v-container> debe tener el grid-list-md o aluno otro para la separacion entre las Cards

### Dialogs

son las pantallas modales, se pueden ubicar en cualquier lugar <v-dialog> *inportante: no abusar de ellas* 