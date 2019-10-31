# CSS

## Imagen ajustada totalmente al contenedor sin deformacion
```css
img {
	width: 100%; /* ajusta 100% al contenediro ancho*/
	height: 100%; /* ajusta 100% al contenediro alto*/
	object-fit: cover; /* ajusta al contenedir evitando la deformacion*/
}
```
## variables
---------------

- las variables tienen `Herencia` y `cascada`
- pueden utilizarse en otros archivos
- declaracion: 
```css
  --color: red; /*declaracion*/
    color: var(--color); /*invocar*/
```

- `:root`  pseudoclase nivel superior declara las variables globales

- `var(--color, red);` sino existe la var(--color) usa el red;

* `scope` de DOM
	
	```
	<body>
		<article>
			<p> 
				
			</p>
		</article>
	</body>
	```
	
## JS
- `getComputedStyle(h1)`  
		 retorna todos los calores calculados de el h1
	
- `getComputedStyle(h1).getPropertyValue('--color-text')`   
	 retorna el valor computado del color
	
- ` h1.style.setProperty('--color-text', 'red')`  
	asigna el color red a la variable `--color-text`
	
- `getComputedStyle(document.documentElement)`  
	 se obtiene el nivel de `:root`


​	
​	
​	
​	
​	
​	
​	
​	
​	
​	
​	
​	
​	