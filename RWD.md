
# RWD - Responsive Web Design 
> Una web se vea bien en cualquier tamaño de pantalla

## Principios

- `Fluid Grid` -> medidas en porciento(%)
- `Imagenes Flexibles` -> se adapten al contenedor max-width : 100%
- `Media queries` -> @media para los tamaños

---
## Viewport 
etiqueta meta necesaria para q funcione el `RWD` 
es la parte visible del navegador

>scroll no es parte del viewport

```html
  	 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

- `content=` 
	
	- `with = divice-width` -> usa la unidades del dispositivo (pc, movil, etc)
	- `user-scalable = yes|no` -> si se puede hacer zoom (recomendable usar yes)
	- `initial-scale = 1` -> escala inicial debe ser 1
	- `maximum-escale = 2` -> escala maxima de zoom ej 200% = 2

- tamaño del `viewport` != `resolucion` de la pantalla
  
		1px del viewport != 1px real
		pantalla (px) \ devicePixelRatio == 1px viewport
	
	### Ejemplo.

		pantalla(galaxy S5 [1080 x 1920]px ) 
		devicePixelRatio = 3
		viewport( [360 x 640]px )

---
## Media queries `@media`
consulta de medios donde se visualiza la web (pantalla, imprenta,etc)

```css
@media screen|print|... and (condicion){
	 /*declaracion*/
}
```
- `min-width`, `max-width`
- `orientation`: landscape | portrait 
	
	 >`landscape` (ancho > alto)  | `portrait`(alto > ancho)
- `min-aspect-ratio`, `max-aspect-ratio`, `device-aspect-ratio`
	
- `@media print {  }` par aimpresion
- `@media screen and (min-width: 600px)` por encima de 600px aplica
- `@media screen and (orientation:landscape) and (max-width: 1024px)` si es menor que 1024px y `landscape` aplica
- `@media screen and (min-aspect-ratio: 4/3)` si es menor que 1024px y landscape ponlo amarillo

---
## Metodologías

>usar `movile first` en las @media para cumplir con la cascada

- `movile first` -> comenzar con estilo movil e ir subiendo nivel y add estilo y contenido
- `desktop first` -> (no recomendad) comenzar en desktop e ir eliminando contenido
- `content first` -> importante - el contenido es lo mas importante para el diseño							

---

## UM - Unidad de Medidas

> `relativas` -> utilizarlas todo lo posible

- `Porcentaje %`  
	ej permite hacer `<p>` responsive hasta siertas um `min` y `max`
	```css
	p{
		margin: 0 auto;
		width: 70%;
		min-width: 320px;
		max-width: 800px;
	}
	```

- `UM - Fuentes`  
	
	- `em`  
	   > tamaño de funte del contexto definido del ansestro mas cercano, por defecto los navegador ponen 16px al font
	
     	`1em `= 100% tamaño de funte del contexto (herencia)
		
		```css
		body{font-size: 20px;}
		p{font-size: 2em;} /* 40 px 200% de 20px*/
		```
		
		> no usarlo para layouts, Usarlo en componentes internos (paddin, marggion )
		
		 ejemplo  

		```css 
		.button{		
			/* 1em 4em abajo se mantien proporcionales*/
			/* al varira font-size se mantiene las medida proporcionales*/

			font-size: 2em; 
			border: none;					
			border-radius: .25em; 
			background: red;
			color: #fff;
			padding: .5em 1.5em; 
			margin: 2rem;
		}
		```

	- `rem`   		 
	 	> tamaño de funte del root <html> por defecto los navegador ponen 16px al font

		`1rem` = 100% del root `<html>`
		
		> usarlo para layouts

- `Viewports` -> vw, vh, vmin, vmax
	
	`1vh` -> 1% del alto del viewport  
	`1vw` -> 1% del ancho del viewport  
	`1vmin` -> 1% del lado menor del rectangulo formado por el viewport  
	`1vmax` -> 1% del lado mayor del rectangulo formado por el viewport

	> resulve el scroll horizontal con el truco de js calcular cuanto mide y
		pasar el valor a la variable css

---
## Breakpoints 
puntos de quiebre

- `mayor breakpoints` -> cambian mucho el diseño de movil(off canvas) a desktop (cars)
- `minor breakpoints` -> cambios muy especificaos pequeños para arreglar maketacion

> `boustrap` usa los breackpoints (0, 480, 760, 1024) px  
> `fundation` usa los breackpoints (0, 640, 1024, 1200, 1440) px

---
## Media responsive (`Videos`)

- video `css`
	```css
	.video{
		height: 0;
		paddin-bottom: 56.25%; /*da la proporcion de 16:9 de videos*/
		poasition: relative; /* para referenciar al hijo*/					
	}
	
	.video > iframe, /* hijos directos*/
	.video > video {
		position: absolute;
		width: 100%;  /* 3 lineas para q se acomode al padre*/
		height: 100%;
	}
	```
- video `viewport` units
	```css
	.video{
		--unit: 5vmax;
		width: calc(var(--unit) * 16);
		height: calc(var(--unit) * 9);			
	}
	```
	
- video `cssGrid`  
 	>se forma un tablero con cuadrados de 16 x 9 cuadrados

```
​```css
.video{
	display : grid;
	/* 16 columnas ditribuidas =mente*/
	grid-template-columns : repeat(16, 1fr);
	/* 9 filas ditribuidas =mente*/
	grid-template-rows : repeat(9, 1fr);
	position: relative;
}

.video::before{
	content:"";
	display: block;
	/* hace q el alto del paddin-bottom sea = al ancho*/
	paddin-bottom : 100% 
}

.video > iframe, /* hijos directos*/
.video > video {
	position: absolute;
	width: 100%;  /* 3 lineas para q se acomode al padre*/
	height: 100%;
}
​```
```
---
## Media responsive (`Imagenes`)		
```scss
	.img{
	width : 100%;     // se ajusta al contenedor
	max-width : 100%; // se auto escala al contenedor
	height : auto;    // para asegurarse el ajuste 
}
```

  - ### optimizacion dependiendo de las pantallas

	- `background`  menos factible
	
		```scss
		. img {
			@include Funcion Video_cssGrid(16, 9) // esto invoca mixis sass de el codigo de video - para crear grid de 16 x 9
			background : url(img_1.png) center / cover; // para cargar img centrada y  muerte toda la img posicion del bg
			
			@media screen and (min-width : 640px){  // cambia con menos de 640px
				background : url(img_2.png) center / cover;
			}
		}
		```
	
	- `pictuare`
		
		```html
		<pictuare>
			<!-- media -> permite @media como en css -->
			<!-- media -> no existe cascada por tanto debe ordenarse los tamaño de pantalla -->
			<source srcset="img_1.png" media="(min-width : 800px)">  
			<source srcset="img_2.png" media="(min-width : 600px)"> 
			<img src = "img_0.png">
		</pictuare>
		```
	
	- `srcset`  
		 permite cargar diferentes img para tamaño distintos de anchos de imagenes ej. 200w, 400w, 600w

		 ```html
		<img  srcset = "logo.png 200w, logo-2x.png 400w, logo-3x.png 600w" src = "img_1.png">
		 ```

---
## Javascript

> `CSSOM`  objeto de modelo del css en formato ***CamelCase***

- `getComputedStyle(elementoDom)` retorna estilos computados 

	>(ej. 1em = #px)
	`.getComputedStyle(elementoDom).getPropertyValue('height')` lee el alto
	
- `style` todo elemento tiene `elentoDom.style`	
	
	> das estilo `.document.body.background = red; `	
	
- ### para estilos multiples		

		1. cambiar clases `document.body.classList.add('newclassCss')`		
	2. utilizar en casos de hacer calculos variable
		```javascript
		const var_color = 'red';
		const var_size = 2;	

		//con template string
		const stilo = `        
			background : ${var_color};
			color : while;
			border : ${var_size * 2}px solid black;
		`			
		const objetoStilo = {
			background : var_color;
			color : while;
			'border-button' : `${var_size * 2}px solid black`;
		}
		
		// sobreescribe estilo in line <img style="">
		elementDOM.setAttribute('style', stilo)
		```
	
- `Variables css`
	```javascript
	 // leer
	getComputedStyle(elementDOM).getPropertyValue(--variable)
	elementDOM.style.setProperty('--variable', 'Leandro')
	```
```js
	
- `Media query JS`  
solo cuando no se puede hacer en css, en casos q se tengan q hacer calculos
	> no usar window.inerWidth

	```javascript
	// mediaQuery de css
	const mediaBP = matchMedia('(min-width : 800px)') 
	
	// mqList es el objeto q retorna matchMedia()
	const changeColor = mqList => { 
		// detecta si se cumple la mediaQuery (min-width : 800px)
		mqList.matches	
			? document.body.style.background = 'red'
			: document.body.style.background = 'blue'
	}
	
	changeColor(mediaBP) // para q se ejecute por primera ves
	mediaBP.addListener(changeColor) // para escuchar el evento cuando cambie
```