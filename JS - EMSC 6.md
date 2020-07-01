# EMSC 6 - (2015)

## Variables  
  se declaran con `let`(sustituye el var) y `const` (para las constantes)

  ```javascript
	let contador = 0
	const NOMBRE_TABLA_DB = 'Users'
  ```

## Funciones (`=>`)

1. una sola linea
	```javascript
	const myfunction = (a,b) => a + b
	```

2. multiples expreciones
 con parametros con valores por defecto
	```javascript
	const myfunction = (a = 1,b) =>{	
		let c = 5
		return a + b + c 
	}
	```

3. con Operador de expancion (...parametrosName)  
	```javascript 
	parametrosName (es un Array[])
	const mysuma = (...parName) =>{	
		let suma = 0;
		for(let i=0; i< parName.length; i++)
			suma += parName[i];
		return suma 
	}
	```

### Funciones ciudadanos de primera clase

- pueden ser `almacenadas` en variables o constantes
	 	```javascript
	const myfunction = (a,b) => a + b
	 // guarda el codigo dentro de la constante (c)
	const c = console.log 
	c('Hola mundo')  // imprime hola mundo
	```
- pueden ser pasadas como `argumentos` de otras expreciones (anonimas) `callbacks`
	```javascript
	let sumaFina = myfunction(myfunction(5,7), 2)
	
	setTimeout(()=> {
		alert('Hola mundo')
	},2000)
	```
- pueden ser `retornadas` por otras functions
	- closures
	```javascript 
	 function sumar(x){
		
		return function(y){
			return x + y;
		}
	}		
	// lo mismo q 
	const sumar = x => y => x+y
	
	//closures
	
	function aumentar(){
		let numero = 0
		return function(){
			numero++
			console.log(numero)
		}
	}
	const incrementar = aumentar()
	incrementar() // 1
	incrementar() // 2
	incrementar() // 3
	incrementar() // 4
	incrementar() // 5
	```
	
- pueden tener `metodos` o `propiedades` base de la `POO`

## this  
```javascript
 refiere al `ambito` del objeto sino es el scope mas alto (global)
	javascript
	let user = {
		nombre: 'leo',
		edad: 31,
		getEdad(){
			console.log(this.edad) // es 31 -- sin el this lo busca fuera (undefined)
		}
	}
```

## Arrays

  desestructuracion 

> usar metodos antes que `for` o `for of`  
	`array.filter(() => {}) `   
	`array.map(() => {})`
```javascript
let arr = ['hola','este', 'curso',1]
let [var1, var2, var3, var4] = arr

objPersona = {noombre:"leo", edad:31}
let {nombre = "LOKO", edad} = objPersona // asigna el valor "LOKO" por defecto en caso de q no exista en el objPersona

c.log(var1) // return 'hola'
c.log(var2) // return 'este'
c.log(var3) // return 'curso'
c.log(var4) // return '1'

// oredenar array menor a mayor
let number = [2,20, 10, 54, 13]
number.sort((a,b) => a - b ) // procedimiento de burbuja
```

- spread operator `(...)` 	

	saca los valores de `array` y los trabaja como valores expandidos individualmente
	```javascript
	let numbers = [1,2,3,4,5]     |    ...number = 1,2,3,4,5
	```
- para duplicar elementos de array 
`Set` elimina los objetos duplicados en array y los retorna como `objeto{}`  
`(...)` -> los saca y los trabaja como valor individulas   
`[]` los combierte nuevamente en array
	```javascript
	const removeDuplicates = arr => [...new Set(arr)]
	```
## Math

```javascript
Math.min(1,3,56)
Math.max(1,3,56)

let numbers = [1,2,3,4,5]
Math.min(...numbers)
Math.max(...numbers)
```

## Objetos 
```javascript
let perros = {
	nombre : 'guffi',
	edad : 7,
	sexo : macho,
	correr(){			
		// interpolar ${}
		console.log(`${this.nombre} correr`)  
	}
}

delete perros.edad // borra la propiedad
perros.feliz = "si"  // add propiedad
```