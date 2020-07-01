# CSS Grid

## Layout
  distribusion del contenido en la pantalla (titulo arriba, baner a la derecha etc)

## Aliniacion de los Items

- `justify-content` y `align-items` funciona = q en flexbox

## Modelos de Layout

- `block layout` distribusion por defecto para todos los elementos border box
- `inline layout` para presentacion de texto (spam etc)
- `table layout` para las tablas columnas + filas + heders
- `positioned layout` posicionamiento de elementos sin demasiada interaccion con el resto (position: absolute, relative, fixed)
	- `flex box` flexible box layout: redimensionar de forma fluida
	- `grid layout` presentar elementos relativos a una cuadricula

## Elementos de Grid

- `grid line` las lines q forman las cuadriculas
- `grid tracks` las columnas y las filas completas
- `grid celda` es una celda normal
- `grid area` cualquier area rectangular dividida por lineas

### Templetes

- `grid-template-columns` y `grid-template-rows` permite crear el  template en filas y columnas

  - `grid-template-columns`: [rows|auto] / [columns|auto]
  ```css
   .container {
  	display: grid;		
  	/* cuatro columnas de 100px*/
  	grid-template-columns: 100px 100px 100px 100px 100px;	
  	/*5 filas filas de 100px*/
  	grid-template-rows: 100px 100px 100px 100px 100px;
  	/*3 row y 2 columns*/
  	grid-template: 100px 100px 20px / 10px 10px
  }
  ```

- `grid-gap`  separacion entre celdas

   ```css
   grid-gap: 10px;
   ```

- `fr` -> fraccion (para calcular lo q queda)
  ```css
  { /* columna 1 en 10% y columna2 lo que resta mas en gap de 10 px*/
  	grid-template-columns: 10% 1fr;
  	grid-gap: 10px;
  
    /*columna 1 en 10%, columna2 1/3 y columnas3 2/3 del resto mas en gap de 10 px*/
  grid-template-columns: 10% 1fr 2fr 
  	grid-gap: 10px	
  }
  ```

- `min-content`   que mida lo mínimo de contenido posible

  - `grid-template-columns: min-content`

- `max-content`  que mida lo máximo de contenido posible

- `fit-content(400px)` usa el `min-content` como mínimo y como `max-content` los 400px

- `minmax(200px, 300px)` mínimo y máximo tamaño

- `auto-fit`  crea (n)tracks automaticas de un tamaño (x) mientras exista contenido

  ```css
  /* crea (n)columnas de 200px mientras se pueda*/
  {grid-template-columns: repeat(auto-fit, 200px) }
  ```

- `auto-fill`   crea (n)tracks automaticas de un tamaño (x) aun sin contenido siempre

  ```css
  /*columnas RWD  min 200px y ajustables*/
  {grid-template-columns: repeat(auto-fit, minmax(200px,1fr))}
  ```

- `repeat()`  repite cuantas veces indiques

  ```css
  /*ambas lineas hacen lo mismo*/
  {grid-template-columns: 100px 100px 100px 100px 100px; 
  grid-template-columns: repeat(5,100px); 
  
  /*repite el auto-ajuste(para q calcule cuantas pueda), con una medida a la columna (de minimo 100px y maximo de 1fr lo q se pueda)*/
      grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); }
  ```

### Grid-area

- `grid area`  se define las `rows` (divididas por `' '`) y las `columnas` por ***`espacio`*** dentro de las rows

  ```css
  {/* rows contiene 4 column header
     row contiene 1 comuna aside 3 main
     rows contiene 4 column footer*/
   grid-template-area: 'header header header header'
  					  'aside main main main'				
  					  'footer footer footer footer';	
  /* sitribuir las alturas*/
  grid-template-rows: 2em 1fr 2em;
  }
  
  /*<header> se posiciona en el area header*/
  .header{
  	gird-area: header
  }
  
  /*<header> se posiciona en el area header*/
  .aside{
  	gird-area: header
  }
  ```

- `grid-area`: shorthand para `grid-row`, `grid-column`
  	
  `grid-area` : [grid-row-start] `/` [grid-column-start] `/` [grid-row-end] `/` [grid-column-end] 	
### Position


* `grid-position` 
	
	- `grid-column` : [star] / [end]
	- `grid-row`	: [star] / [end]
	  - star -> indica la linea en que se emiesa
		- end -> indica la linea en que se termina
		- span -> cuenta tracks envez de column o row
		
	
	```css
	p {/*comienza linea 2 y termina*/
		grid-column : 2 / span 5;  track 5
	  	grid-row : 1 / 4;
	
		/*si elimina el inicio empieza donde esta de manera natural*/
	    grid-column : span 5;
	}
	```

### Alineacion


* `Aliniacion Items` (es lo q dibuja realmente el navegador)
	
	.. `item` es el contenido del track

	- Aliniacion items en `X` (inline) respecto al track
		
		- `justify-items` para el contenedor
		- `justify-self` para el items
		- `valores` : [start | center | end]
		
		```css
		.container{
			justify-items: start;
		}
		.item:nth-child(2){
			justify-self: center;
		}
		```
		
	- Aliniacion items en `Y` (block) respecto al track
		
		- `align-items` para el contenedor
		- `align-self` para el items
		- `valores` : [top | center | button]
	
	- se puede usar `margin` para consumir el espacio 
		```css
		body {margin: auto;
		/* auto para left and right*/
		margin: 0 auto;
		/* ubica el item completamente a la derecha*/
		    margin-left: auto;}
		```
		
	
* `Aliniacion Tracks` (no existe en el DOM - navegador lo usa para saber donde poner los items)
	
	- Aliniacion track en `X` (inline)
		
		- `justify-content` para el contenedor
		- `valores` : [start | center | end | space-between | space-around | space-evenly]

			- `space-between` -> espacio dentro de los elementos
			- `space-around` -> espacio a cada lado de los elementos como un margin-left y margin-right
			- `space-around` -> espacio a cada lado de los elementos pero con el mismo ancho como si se colapsaran los margenes left y right
		
		```css
		.container{
			justify-items: start;
		}
		.item:nth-child(2){
			justify-self: center;
		}
		```
		
	- Aliniacion items en `Y` (block)
		
		- `align-content` para el contenedor
		- `valores` : [stretck | top | center | button | space-around | space-evenly]
			- `stretck` -> todo el espacio
	
	- se puede usar `margin` para consumir el espacio 
		```css
		body {margin: auto;
		/*auto para left and right*/
		margin: 0 auto; 
		/*ubica el item completamente a la derecha	*/
		margin-left: auto;}
		```

## Grid placement


* `grid explicito` es el q definimos con `grid-template-columns` y `grid-template-rows`

* `grid implisito` si por error o concientemente pones un elemento fuera del template se van a crear esos tracks adicionales 	

  - `grid-auto-flow: [column,row] [dense]` 
  	- `[row | column]` -> define el sentido del grid (como flex-direction)

  	- `dense` -> rellena los espacios generados

  - `grid-auto-row`: 100px define el tamaño del (row generado implisito)
  - `grid-auto-column`: 200px define el tamaño del (column generado implisito)

  