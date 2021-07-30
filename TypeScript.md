## Install 

`npm install -g typescript` global

`npm install typescript` local

`tsc --version` versión global, `npx tsc --version` versión de instalación local

## Compilador

1. crear los ficheros.ts con el contenido
2. ejecutar `npx tsc src/ficheros.ts`  o `tsc src/ficheros.ts` 
3. y se creara el `ficeros.js` con el código compilado
4. `node src/ficheros.js` pruebas el fichero

## tsconfig

archivo json, indica que estamos en un proyecto TS, configura el compilador y especifica los archivos principales

`tsc --init` para crear el archivo **tsconfig.json** 

`tsc --watch src/hola.ts` para mantener el proceso que vela cunado existan cambios 

`tsc --watc` compila todos los ficheros **.ts** que estan dentro de **src** 

estructura

```
{
  "compilerOptions": {
    ...
  },
  "include": [
    "src/*.ts" // todo dentro de src
    "src/**/*.ts" // recursivamente
  ],
  "exclude": [
    "src/test.ts"
    "src/*-test.ts"  // todos los que termintn con -test.ts
  ]
}
```

` "outDir": "./dist",  ` salida de los ficheros .js a dist

## Tipos Datos

pueden ser postfifos `: boolean` ejemplo o pueden ser inferidos ejemplo: `let a = true` aquí es boolean por el valor asignado

### boolean 

```typescript
let variable = true
let estas: boolean;
estas = false

// retonrno de de las funcionar
function tieneDescuento(): boolean { return true }
```

### number

```typescript
cantidad = 100;
let cantidad: number = 29;
function cuantosEstudiante(): number {	return 100 }
let hexadecimal = 0xf00d;  // 61453 en hexadecimal
let binario: number = 0b1001 ;  // 9 en binario
let octal: number = 0o755; // 493 en octal
```

### string

```typescript
let nombre = 'leo'
let nombre : string;
nombreCompleto = `${nombre} capde`
```

### tipos especiales

**Any** 

 cuanto se define una variable y no se le asigna ningún valor o tipo es tipo `any` , ***debe ser utilizada lo menos posible***

````typescript
let descrip; // seria como var descrip
let descrip: any;
descrip = 'hola'
descrip = 1
````

***Void***

representa el vacío como en lenguajes como java o c#, no puede retornarse ningún valor en las funciones

```typescript
function mostrar(tipo: string): void{ ... }
```

***Never***

se utiliza para indicar que la función nunca retorna algún valor, siempre algún tipo de error

```typescript
function retornaError(err: string): never {
    throw new Error('Error dos')
    // nuca retorna un valor
}
```

***Null y Undefined***

se debe utilizar `undefined` para los valores no definidos por encima de null

```typescript
let variable: undefined = undefined
let variable: null = null
let variable = null // es tipo any
function returnNull(): null { return null }
```

***Funciones***

`params?: sttring` - el (?) indica que el parámetro string es opcional 

```typescript
function returnNombre(nombre?: string): null { 
	if(nombre)
        return 'hola' + nombre
    else return 'hola'
}
```

### Array

```typescript
let arreglo = ['leo', 'capde']
let arreglo: string[]
let arr = ['leo', 32, true] // array de tipos combianados
let arr: any[] = ['leo', 32, true] // EVITAR EL USO
```

**Array con Tipos Genericos**

```typescript
let arrayGen: Array<string>;  // es igual a `let arrayGen: string[]`

```

### Tuplas

variable con conjunto de datos, estructura que corresponde a ciertos tipos de datos

se puede utilizar los operadores correspondientes al tipo de datos de la posición seleccionada 

``` typescript
let cursos = ['javascript', 100]
let cursos: [string, number] = ['javascript', 100]
cursos = ['django', 45]
cursos[1] // 100

cursos[0].split() //para hcer split al valor de [0]
```

### Tipos de datos Mixtos

para asignar mas de un tipo de dato utilizar el separador `|` 

```js
let cusos : string | number
function getCursos(): string | number{
    
}
// array
let cursos: (string | number) [] = ['Leo', 'leo2', 20]
```

```typescript
type Curso = string | number 
let cusos : Curso
function getCursos(): Curso {
    ...
}
// array
let cursos: Curso[] = ['Leo', 'leo2', 20]
```

### Aserciones de tipo

es la similitud a casting en otros lenguajes

```typescript
let curso: any = 100;
let numeroCurso: number = <number> curso // aqui se hace la assertin <>, casting

type Estudiante = {nombre: string, curso: string}
let estudiante = <Estudiante>{} 
estudiante.curso = 'js'
estudiante.nombre = 'leo'

let stringJson = `{"nombre: "juan", "curso": "js"}`
let objEstudiante: Estudiante = <Estudiante> JSON.parse(stringJson)
```

### Enumeradores

`enum` asigna internamente los valores numéricos de un array para trabajar los valores

```typescript
enum Curso {
    JavaScript,   // 0
    TypeScript,   // 1
    Angular       // 2
}

let curso: Cursos = Cursos.Angular
console.log(curso)   			// 2
console.log(Curso[curso])   	// Angular
```

control sobre el valor de los enum

```typescript
enum Dias {
	lunes, martes, miercole, jueves, viernes
}
enum FinSemana { 
	sabado = 5, domingo = 6
}

enum Meses {
	Enero = 'Ene', Febrero = 'Feb', ....
}
```

### Interfaces

para definir como agrupador de tipos de datos

```typescript
interface ICurso {
    nombre: string 
    id?: number    // opcional
}

let icurso : ICurso = {
    nombre: 'TpeScript',
    id: 1
}

let curso2 = { nombre: 'js' }
```

Herencia en Interfaces

```typescript
interface CursoMaster extends ICurso {
    costo: number;
}

const primerCurso: CursoMaster = {
    nombre: 'master 1',
    id: 5,
    costo: 25.25
}
```

## Clases

```typescript
class EscuelaDigital {
    nombre: string
    cursos: string[] = []
    
    constructor(nombre: string){
        this.nombre = nombre
    }
    
    agregarCurso(curso: string): void {
        this.curso.push(curso)
    }
}
```

### modificadores de acceso

public , private, protected

se puede reducir la definición de la encapsulación de los atributos de la clase, integrando el modificador pribate en los parametros del contructor, de esta forma TS asumira que los parametros son propiedades de la clase y los mapeara como tal 

```typescript
// shorthand de la encapsulacion
class Curso {
    contructor(private id: number, private nombre: string){}
}

// es igual que definir
class Curso {
    private id: number
    private nombre: string
    contructor(id: number, nombre: string){
        this.id = id
     	this.nombre = nombre
    }
}
```

### get set

se puede acceder a los métodos definidos para el `set` y `get` como propiedades de JS

```typescript
class Curso {
    contructor(private _id: number, private _nombre: string){}
    
    get id(): number{
        this._id
    }
    
    set id(id: number){
        this._id = id
    }
}


let curso = new Curso(1, 'TS')
curso.id = 2
curso.nombre = 'JS'  // uso del :SET
console.log(curso.nombre) // uso del :GET
```

### solo lectura

`readonly` - para solo lectura

```typescript
class Curso {
    contructor(private readonly _id: number, private _nombre: string){}
}
```

### Herencia

```typescript
class CursoMath extends Curso{
    contructor(id: number, nombre: string, private _puntuacion: number){
        super(id, nombre) // para la superclase Curso
    }
    
       
    get puntuacion(): number{
        this._puntuacion
    }
    
    set puntuacion(puntuacion: number){
        this._puntuacion = puntuacion
    }
}
```

### Statics atributos y metodos (de las clases)

acceder son necesidad de crear objetos

```typescript
class Curso {
    // para los atributos
    public static typeCurso = 'Precencial'
    
	// para los metodos
    static getTypeCurso (c: Curso){
        return JSON.stringify(c)
    }
}
```

### Abstract class

es un concepto muy general que no tiene mucho sentido crear un objeto desde el mismo es la base de otras clases

```typescript
abstract class Escuela{
	...
    abstract metodo(){ 
        ...
    }
}
```

## Decoradores

Se utiliza para analizar, modificar o reemplazar la definición de una clase. se puede utilizar al nivel de clases, métodos y atributos , en tiempo de ejecución

debemos habilitar `"experimentalDecorators": true` en tsconfig.json

**definicion: ** es una función con un `target` parámetro contenedor de la clase, función o atributo que se va a decorar

`@NameDecorator` por encima de la clase que se va a decorar

### clases

```typescript
function Entidad(target:any){
	console.log('decorar', target)
}

@Entidad // decorando
class Curso {
    // para los atributos
    public static typeCurso = 'Precencial'
    
	// para los metodos
    static getTypeCurso (c: Curso){
        return JSON.stringify(c)
    }
}
```

```typescript
function Entidad(config: any){
	console.log('decorar', target)
    return function(target:any){
        // dinamicamente se asigna `clave` a la clase
        target.claver = config.clave 
    }
}

@Entidad({
    clave: 'POST'
}) // decorando
class Curso {
    // para los atributos
    public static typeCurso = 'Precencial'
    
	// para los metodos
    static getTypeCurso (c: Curso){
        return JSON.stringify(c)
    }
}
```

### funciones

## Archivos .d.ts

> se denominan "declaration files" proveen información sobre los tipos para el compilador de TS, de las librerias o funciones que son de JS vanilla

- `npm init -y` para iniciar proyecto npm
- `npm install lodash --save` instala lodash como dependencia y la salva en package.json

```typescript
import {random} form lodash
function numeroAleatorio(a: number, b:number){
    return random(a, b)
}

console.log('Random =>', numeroAleatorio(2, 6))
```

utilizar .d.ts para definir la librería importada lodash para q compilador TS pueda tener informacion

**typing.d.ts**: en este fichero se redefine los tipos de los parámetros y del retorno la función random para el compilador 

```typescript
declare module 'lodash' {
    export function random(min: number,max: number): number;
}
```

## Modulos

