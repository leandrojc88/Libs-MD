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
