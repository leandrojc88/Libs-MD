# JS - DOM

> `DOM`	no es parte del lenguaje javascript es una API del navegador

## document

variable global con todo el contenido del docuento
- `document.body`
- `document.head`
- `document.title`
- `document.scripts` todos los javascript
- `document.styleSheets` todos los css
- `document.links` son las etiquetas `<a>`

## Nodos
son los elementos del `html` organisados como estructura de arbol en el DOM
- `nodeName` -> nombre del nodo ej: `"IMG"`
- `nodeType`  
	1. `ELEMENT_NODE`  
	2. `TEXT_NODE`
	
- seleccionar `nodos`
	```javascript 
	document.getElementById("#id") // seleccionar por id
	```
- selectores con `ccs`
	```javascript 
	// retorna lista de nodos q cuanplen con ul li
	document.querySelector( ul li) 

	//seleccion de #id dentro de este el ultimo hijo de li
	document.getElementById("#id").querySelector('li:last-child')
	```

## Colecciones
`document.querySelector( ul li)` retorna una coleccion no un array

`Array.from(document.querySelector( ul li))` convierte la coleccion del `querySelector` a un `array` con la clase prototipe Array

## Atrbutos

```javascript
const elemento = document.getElementById("#id")
elemento.getAttribute('id')
elemento.setAttribute('id', 'valor')

elemento.classList // array class
elemento.classList.add('class1','class2') // add clases
elemento.classList.remove('class1') // remove clases
elemento.classList.toggle('class1') // add/remove clases\
```

## Contenido
- ### para leer
- 
```html
<h1 id="id"> EL <span> DOM </span> </h1>
```
```javascript		
const elemento = document.getElementById("#id")

elemento.textContent // retorna: EL DOM
elemento.innerHTML //retorna: EL <span> DOM </span>
elemento.outerHTML //retorna: <h1 id="id"> EL <span> DOM </span> </h1>
```
- ### para escribir 
```javascript
elemento.textContent = 'hola mundo'
elemento.innerHTML = 'hola <em> mundo</em>' // para meter html
```

## Crear Elementos 
se crea el elemento y se add al body	
```javascript
const profesor = document.createElement('h2')

profesor.textContent = 'LEO'
profesor.classList.add('styleNice')
profesor.id = 1

document.body.appendChild(profesor)
```

## Eventos 
```javascript
const elemento = document.getElementById("#id")

//elemento.addEventListener('eventName','Handler')
elemento.addEventListener('clic', e => {
	alert(e.target.textContent) // retorna el contenido del elemento q se le dio clic
})
```
- evntos `Mouse`
	- `clic`
	- `dblclic`
	- `mouseenter` entrar mouse
	- `mouseleave` salir mause
	- `contextmenu` clic derecho
	- `mousedown`  
	- `mouseup`
	- `mousemove` se mueve dentro de un elemento
	
- eventos `teclado`
	- `keydown`
	- `keyup`
	- `keypress`
	
- evento `formulario`
	- `submit` cuando se envia el form
	- `change` cuando cambia algun elemento de valor
	- `focus`
	- `blur` sale del foco
	- `reset`

- evento `DOM` y `navegador`
	- `DOMContentLoaded` espera a cargar toda la estructura del DOM aunque no se cargen aun todos los datos ej: img, vid
	- `load` espera a que carge TODO
	- `resize`
	- `scroll`
	
- evento `multimedia`
	- `play`
	- `pause`
	- `stop`

- `propagacion de eventos` los eventos se propagan hacia afuera buscando el padre del elemento hasta llegar a window

	```javascript
	document.querySelectorAll('div').forEach(el => {
		el.addEventListener('clic' , e => {
			console.log(`Hiciste clic en ${e.target.id}`)
			e.stopPropagation() // detiene la propagacion hacia el padre				
		})
	})
	```
	
- `delegacion de eventos` detectar evento en el padre para no tener q repetirlo (`n`) veces en los hijos
y despues detectar q que hijo se le aplico el evento

	

# Manipular DOM

## DOM `traversing` (moverse en el DOM)
> retorna distintos elementos `NodeList, HTMLCollection, text, null ...`

- `hijos`
	- `childNodes`
	- `children`
	- `firstChild` puede ser 1er (Elementos, textos, sallto de lineas)
	- `firstElementeCheld` 1er elemento
	- `lastChild`  
	- `lastElementeCheld`
	- `hasChildNodes()` return true si el elemento contiene hijos

- `hermanos`
	- `nextSibling`
	- `nextElemntSibling`
	- `previousSibling`
	- `previousElementSibling`

- `padres`
	- `parenNode`
	- `parenElement`

## Insertar elementos	

- `appendChild` -> inserta elemento al final 

- `insertBefore` -> inserta delante de un elemnto
	parent.insertBefore(nuevoElemento, elemento)

- `inserAdjacent`
	
	- metodos 
		- `inserAdjacentElement(possition, elemento)`
		- `inserAdjacentHTML(possition, html)`
		- `inserAdjacentText(possition, text)`
	
	- posiciones
		- `beforebegin` -> hermano anterior
		- `afterbegin` -> primer hijo
		- `beforeend` -> ultimo hijo
		- `afterend` -> hermano siguiente

	- `parent.replceChield(newchield, oldchield)` remplas hijo
	
- `jQuery like` (insertar)

	- `before()` hermano anterior
	- `after()` hermano siguiente
	- `prepend()` primer hijo
	- `append()` ultimo hijo		
	- `son.replaceWith(newson)` remplasa el son por `newson`

## clonar y eliminar

- `cloneNode(true)` true clona el elemento con todos los descendientes
-` remove()` elimina el elemento

## Creal Elementos
- `createElement('etiqueta')`
- `createDocumentFragment()` guarda elementos en memoria antes de mostrarlos rendimiento optimo

## CSSOM

- `style` (elemento.style.propiedades )  
	las porpiedades con `(-)` en `css` son camelCase en `js`  
	
	> css `flex-direcion` es `flexDirecion` en JS
	
- `matchMedia()` media querys de css
	```javascript
	 macthMedia('(min-witdh: 640px) and (orientations = portrait)')
	```
- `getComputedStyle` retorna los estilos calculados 
	### ejemplo: 
		para un stilo de 2em 
		getComputedStyle(document.body)retorna 32 px

- `getBoundingClientRect()` da la distancia de el punto de origen de un elemento a las esquinas del navegador y el ancho u alto del elemento
	- `getBoundingClientRect().width`
	- `getBoundingClientRect().height`
	- `getBoundingClientRect().top` | `left` | `right` | `button`
	

# Navegador (objeto `windows`)

## Objetos

- `console`

  - navigator -> no es buena ide el uso

- `location` entrega la informacion de la `url`

	- `href` -> url actual, se puede cambiar para ir a otra pag.
	- `host` -> dominio:puerto
	- `hostname` -> dominio
	- `port` -> puerto
	- `protocol` -> protocol(http, https, ftp)
	- `origin` -> protocol + dominio
	- `hash` -> # en la pag
	- `pathname` -> ruta interna luego del ej: dominio /cursos/css
	
- `history` permite navegar en el historial de la url actual
	
	- `length` -> cantidad de history
	- `back()` -> history anterior
	- `forward()` -> history siguiente
	- `go()` -> ir a una history

## Metodos

-` alert('mensaje')`
- `confirm('mensaje')`
- `prompt('msg'`
- `open()`  
  	- `close()` debe hacerse un `open()` primero

## Timers

- `setTimeout(funcion, tiempo)`  ejecuta 1 la funcion pasado el intervalo de tiempo **milisegundos**
	```javascript
	 setTimeout(()=> {console.log('hola')},1000)
	```
- `setInterval(funcion,  timepo)` -> ejecuta la funcion cada vez q pasa el tiempo **milisegundos**
	```javascript
	setInterval(()=> {console.log('hola')},1000)
	clearInterval // lo detiene
	```

## Date

> las operaciones con fechas es en milisegundos



# POO 

## Objectos literales 
>son `ciudadanos de 1ra clase` al igual que las funciones (parametros, ser retornados , usarse anonimamente)
```javascript
const objeto1 = {
	propiedad : valor,
	["propiedad"] : valor,
	metodos => {
		//declaracion
	}
}

//acceder
objeto1.propiedas  | objeto1["propiedad"]
```

## Clases
```javascript
class Usuario {
	constructor(nombre, apellido, email, edad){
		this.nombre = nombre
		this.apellido = apellido
		this.email = email
		this.edad = edad
	}
	
	//metodos
	saludar(parametros){
		document.write('Hola')
		return valor
	}
}

// instancia de usuario
let leandro = new Usuario("Leandro", "Capdesuner", "leandrojc88@gmail.com", 31)
```

## Herencia
```javascript
class Profesor extends Usuario{
	constructor(nombre, apellido, email, edad,experiencia, lenguaje){
		super(nombre, apellido, email, edad)
		this.experiencia = experiencia
		this.lenguaje = lenguaje
	}
}

let profe_1 = new Profesor(
	"Leandro",
	"Rodriguez",
	"leor@cuba.cu",
	58,
	23,
	"portuges"
)
```

## Omitir declaraciones de atributos 
```javascript
- let nombre = e.target.nombre.value
- let edad = e.target.edad.value
```
> no es necesario hacer `nombre: nombre`   cuando `clave: valor` sena iguales
```javascript
let user = {
	nombre,
	edad
}
```

## JSON	
.json inicia con `{ }` la clave va dentro de `"claves"`  permite anidar [`array`] y {`object`}
```javascript
	{
	"nombre": "Leandro",
	"edad": 31,
	"trabajos": [
		{
			"id": 1,
			"nombre": "geocuba"
		},
		{
			"id": 2,
			"nombre": "zapatero"
		},
		{
			"id": 3,
			"nombre": "jovenclub"
		}
	]
}
```

