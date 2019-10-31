
# JQuery 

>no es parte del lenguaje javascript es una API del navegador

## Objeto jquery

* `jQyue === $` -> el objeto jQuery es igual al `$` y contienen la funcion inicial de `JQuery`

* `$.ready(document){ codigo }`  o  `$(funtion (){ codgo })` -> para detectar cuando se cargan los recursos y esta lista para desarrollarse la app

* `$` -> selector `jQuery` retorna un jQuery object (`$`)

	- `$('selector')` selector usa los selectores `css` y retorna un [array] + otros de jQuery
	- `$('selector', contexto)` selector usa los selectores `css` y retorna un [array] + otros de jQuery dentro del (contexto)
	
		### ejemplo
		```javascript
		$('h1') | $('#app-heder h1') | $('h1', heder) | 
		$('h1, h2') //todos los h1 y h2
		
		$heder = heder //$ delante para elementos JQuery object
		$selection = $([ document, heder ])
		$(':input') // retorna todos los <input> <text> <textarea> etc...
		$(':selected')// todos los elementos seleccionado
		$(':enabled')// todos los elementos q esten habilitados para introducir text o otra accion
		$(':text') | $(':chechbox')
		$('#id-elemto').find('h1')
		$('heder').has('h1')// los heder q tengan dentro una clase h1
		$('#id-elemto').not('h1') // los heder q no tengan dentro una clase h1
		$('p').filter('.text') // los p q tengan la clase .text
		$('p').filter('.text')// los p q tengan la clase .text
				.has('a') // y contengan un <a>
				.eq(2)// el 3ro de ellos
		```
	
* `jQuery` Object
	
	- guarda la seleccion realizada con `$('')`
	- permite la compatibilidad entre los navegadores
	- `$h1 = $('h1')` |  `$h1b = $('h1')` | no son el mismo elemento aunque referencien al mismo en el DOM
	
* Crear Elementos
	
	```javascript
	-$('elelmento', {propiedades:valores}) 
	
	let a = $('<a>',{
		html: 'Ir a platzi',
		href: 'https//platzi.com',
		target: '_blank'		
	})
	
	$('body').append(a)
	
	a.apendTo('body') //  es = que arriba
	```
	
* `getter` y `setter` funcionan sobre toda la seleccion
	
	- `a.attr('href')` retorna la dir
	- `a.attr({href: 'google.com'})` cambia la `href` por google.com de toda la seleccion (`a`)
	
* Clases
	
	-`$('h1').addClass('danger')` add clase al `h1`
	-$('h1').removeClass('danger') elimina clase `h1`
	-$('h1').toggleClass('danger') toggle clases a quita y pone `h1`
	
* `JS` las funciones son objetos y pueden ser usadas en los parametros
	
	- `Objetos`
	```javascript 
	function calc(){
		valor = 12
		
		funtion suma(){
			
			
		}
	}
	```
	
	- `callbacks`
	```javascript
	function suma (a, b){
		return a + b
	}
	
	function operador(operacion, a, b){
		retunr operacion(a,b)
	}
	
	operador(suma,a,b)
	```
	- `callbacks` Asincronos 

## Eventos

```javascript
 $('#my-buuton').click(funtion(){alert('CLic')})
 $('#my-buuton').on('click',funtion(){alert('CLic')})
 
 // escucha los 2 eventos
 $('#my-buuton').on('click change',funtion(){alert('CLic o cambiaron accion')})

 // multiples eventos
 $('#my-buuton').on({ 
		'click ': funtion(){alert('CLic o cambiaron accion')},
		'change': funtion(){alert('CLic o cambiaron accion')},
		'mouseover': funtion(){alert('CLic o cambiaron accion')}
}) 
```

- `propagacion de eventos`

	add eventos en (a) que esten dentor de #ul-id
	
	```javascript
	$('#ul-id').on("click","a",function(event){ 
		$(this).text()   //convierte el this en Objeto JQuery
	})	
	```
- `triggers` disparar el evento (`n`) de un objeto :(  no e suna buena practica
	```javascript
	$('#my-button').trigger('click')
	$('#my-button').click()
	```

## Ajax

```javascript
$.ajax({
	url: "http://showstv/api",
	data: {
		id: 1
	}
	success: function (data, textStarus, xhr){		
		
	}
})
```

- `promise` resuelve el problema de los `callbacks` anidados en horizontal

  ```javascript
  $.ajax("http://showstv/api")
   .then(function(data, textStarus, xhr){
  		//codigo de function
  	})
  ```

## localStorage

persistencia de datos en local

- `localStorage.datos = JSON.stringify(datos)` guarda en local (parecidoa  kukies) los datos del JSON en formato string
- `JSON.parse(datos)`los datos son convertido formato json



























