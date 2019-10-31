# AJAX

## Protocolo Http

### definicion

> conjunto de reglas que permite intercambiar informacion entre un `servidor` y `cliente` , es el [protocolo](Protocolo_de_comunicaciones.html) de comunicación que permite las transferencias de información en la [World Wide Web](World_Wide_Web.html). 
>
>  Es un protocolo orientado a transacciones y sigue el esquema  petición-respuesta entre un cliente y un servidor. El cliente (se le  suele llamar "agente de usuario", en inglés *user agent*) realiza una petición enviando un mensaje, con cierto formato al  servidor. El servidor (se le suele llamar un servidor web) le envía un  mensaje de respuesta. 

### Metodos

- GET 

  Pide una representación del recurso especificado. Por seguridad **no debería** ser usado por aplicaciones que causen efectos ya que transmite  información a través de la URI agregando parámetros a la URL. La  petición puede ser simple, es decir en una línea o compuesta de la  manera que muestra el ejemplo.  

  Ejemplo:

  ​	 `GET /images/logo.png HTTP/1.1` obtiene un recurso llamado logo.png 

  Ejemplo con parámetros:

   	`/index.php?page=main&lang=es` 

- POST

   Envía los datos para que sean procesados por el recurso identificado.  Los datos se incluirán en el cuerpo de la petición. Esto puede resultar  en la creación de un nuevo recurso o de las actualizaciones de los  recursos existentes o ambas cosas. 

  Ejemplos: `Formularios`

- PUT

   ***Utilizarlo para actualizar formularios*** Sube, carga o realiza un upload de un recurso especificado (archivo), es el camino más eficiente para subir archivos a un servidor, esto es  porque en POST utiliza un **mensaje multiparte** y el  mensaje es decodificado por el servidor. En contraste, el método PUT te  permite escribir un archivo en una conexión socket establecida con el  servidor. La desventaja del método PUT es que los servidores de hosting compartido no lo tienen habilitado.

   Ejemplo: 

  ​	 `PUT /path/filename.html HTTP/1.1` 

-  DELETE 

  Borra el recurso especificado.

-  CONNECT 

  ***peticiones entre dominios diferentes*** Se utiliza para saber si se tiene acceso a un host, no necesariamente la petición llega al servidor

### Codigos de Respuesta

```javascript
xhr.addEventListener('load',e => {
	switch(e.target.status){//retorna el codigo del estado    
        case 200:
            console.log("respuesta correcta")
            break;
        case 401:
            console.log("no esta autorizado")
            break;
        case 404:
            console.log("no existe la paguina")
            break;
    }
})
```

- 1xx: Respuestas informativas 

- 2xx: Peticiones correctas

  **200 - OK**

  Respuesta estándar para peticiones correctas.

   **201 - Created**

   La petición ha sido completada y ha resultado en la creación de un nuevo recurso.

   **202 - Accepted**

   La petición ha sido aceptada para procesamiento, pero  este no ha sido completado. 

- 3xx: Redirecciones 

- 4xx Errores del cliente 

   **403 - Forbidden**

   La solicitud fue legal, pero el servidor rehúsa  responderla dado que el cliente no tiene los privilegios para hacerla.  En contraste a una respuesta *401 No autorizado*, la autenticación no haría la diferencia.

  **404 - Not Found**

   Recurso no encontrado. Se utiliza cuando el servidor web no encuentra la página o recurso solicitado.

- 5xx Errores de servidor 

### Cabezeras

> son los parámetros que se envían en una petición o respuesta HTTP al cliente o al servidor para proporcionar información esencial sobre  la transacción en curso. Estas cabeceras proporcionan información  mediante la sintaxis '**Cabecera: Valor'** 

###  Cabeceras de petición

esisten muchos mas...

| Nombre          | Descripción                                                  | Ejemplo                                                | Estado               |
| --------------- | ------------------------------------------------------------ | ------------------------------------------------------ | -------------------- |
| Accept          | Content-Types (tipos de contenido) que se aceptan.           | `Accept: text/plain`                                   | Permanente           |
| Accept-Charset  | Conjunto de caracteres que se aceptan.                       | `Accept-Charset: utf-8`                                | Permanente           |
| Accept-Encoding | Lista de codificaciones que se aceptan.                      | `Accept-Encoding: gzip, deflate`                       | Permanente           |
| Accept-Language | Idiomas que que se aceptan.                                  | `Accept-Language: en-US`                               | Permanente           |
| Accept-Datetime | Versión de la hora y fecha que se aceptan.                   | `Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT`       | Provisional          |
| Cookie          | Una cookie enviada previamente por el servidor usando Set-Cookie | `Cookie: $Version=1; Skin=new;`                        | Permanente: Estándar |
| Date            | La fecha y la hora de la petición                            | `Date: Tue, 15 Nov 1994 08:12:31 GMT`                  | Permanente           |
| Host            | El nombre de dominio o dirección IP                          | `Host: en.wikipedia.org:8080` `Host: en.wikipedia.org` | Permanente           |

## XMLHTTPRequest

### Definicion

> es un objeto **javascrip**, proporciona una forma facil de obtener informacion de una **ULR** sin tener que recargar la paguina. Una paguina web puede utilizar solo una parte de paguina sin tener que interrumpir lo que el usuario esta haciendo, es muy utilizado en la programacion con AJAX

ejemplo:

```javascript
const b = document.getElemntById("boton")
const c = document.getElemntById("divContainer")

b.addEventListener('click', evt =>{
    const xhr = new XMLHttpRequest()
    // open(metodo, url, azyncrono)
    xhr.open('GET','/data.html',true) 
    
    // feedback q hacer cuando responda el load
    xhr.addEventListener('load',e => {
        c.innerHTML = e.target.responseText() //cargar el texto del data.html dentro del div
    })
    
    //ejecutar el XMLHttpRequest
    xhr.send()
})

```

### Estados

| Value | State            | Description                                                  |
| :---- | ---------------- | ------------------------------------------------------------ |
| 0     | UNSENT           | todavia no se llamo a open()                                 |
| 1     | OPENED           | todavia no se llamo a send()                                 |
| 2     | HEADERS_RECEIVED | send() ya fue invocado, y los encabezados y el estado estan disponibles |
| 3     | LOADING          | descargando; responseText contine informacion parcial        |
| 4     | DONE             | la operacion esta terminada                                  |

ej. loading...

```javascript
const l = document.getElemntById("divLoading")
// 0 = UNSENDT
l.style.display = 'none'

b.addEventListener('click', evt =>{
    const xhr = new XMLHttpRequest() 
    l.style.display = 'block' // mostrar el mensaje de cargando...
    xhr.open('GET','/data.html',true) 
    xhr.addEventListener('load',e => {
        
        //4 = DONE
        l.style.display = 'none'// una ves terminada la carga ocultar el cargando...
        c.innerHTML = e.target.responseText()
    })   
    
    xhr.send()
    //0 = OPENED
})

```



### Procesar Data

- AHAH

  > es procesar dato HTML que esta en el servidor y ponerlo en la vista, **tecnica antigua (obsoleta)**

  ```javascript
  const xhr = new XMLHttpRequest() 
  xhr.open('GET','/data.html',true)
  ```

- JSON

  cargar contenido JSON de responseText del servidor viene como un "String"

  ```javascript
  const xhr = new XMLHttpRequest() 
  xhr.open('GET','/data.json',true)
  
  //convierte los datos de responseText en Json
  const datos = JSON.parse(e.target.responseText)
  ```

## Callbacks

> función dentro de otra función para realizar un proceso de manera síncrona

```html
<div id="myContent"></div>
<button id="btn">ejecutar</button>
```

```javascript
const setText = data =>{
    myContent.textContent = data
}

//--------se simula un proceso sincrono
const getData = callback =>{
    setText('Solicitud autorizada')
    setTimeout(() => {
        // aki se llama realmente
        callback(true) //ejecuta la funcion, la persona si esta autorizada
    }, 1000)
}
const showDatas = callback =>{
    setText('Esperando a mostrar informacion')
    setTimeout(() => {
        // aki se llama realmente
        callback({name:'carol'}) //ejecuta la funcion, con los parametros de nombre
    }, 1000)    
}

btn.addEventListener('click', () => {
    // aqui se define el codigo de los callbacks el cuerpo de las funciones
    getData(autorizacion => {
        if(autorizacion){
         	showDatas(user => {
           		setText(user.name)
        	})   
        }        
    })    
})
```

## Promesas(Promise)

> permiten hacer ejecuciones de procesos síncronos evitando los **callbacks** de forma mucha mas organizada, se ejecuta tan pronto termine el proceso anterior
>
>  **valor futuro** (también llamado una *promesa*) es un remplazo para un resultado que todavía no está disponible 

​		`const myFirstPromise = new Promise((resolve, reject) => {}`

- `resolve` (resolver)   la ejecución fue resuelta y se retorna el valor del obj.  resolve()
- `reject` (rechazar)  se rechaza la promise
- `then()` cuando la Promise se ejecuta correctamente `resolve` 

```javascript
var promise1 = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve('foo'); // valor futuro que retornara la promesa a los 3s
  }, 3000);
});
promise1.then(value => {
  console.log(value);
  // expected output: "foo"
});
console.log(promise1);
// expected output: [object Promise]
```

```javascript
const setText = data =>{
    myContent.textContent = data
}

//--------se simula un proceso sincrono con Promise
// se definesn las funciones q usan Promese
const getData = () =>{
    return new Promise((resolve, reject) => { // 2 funcines para resuelto y rechasado
        setText('Solicitud autorizada')
    	setTimeout(() => {
       		//ejecuta la funcion resolve de la promesa para el caso correcto
        	resolve(true) 
	    }, 1000)
    })   
}

const showDatas = () =>{
    return new Promise((resolve, reject) => { // 2 funcines para resuelto y rechasado
        setText('Esperando a mostrar informacion')
        setTimeout(() => {
            resolve({name:'carol'}) 
        }, 1000)  
    })
}

btn.addEventListener('click', () => {
    // se ejecutan las Promise
    // cuando se ejecuta [.then(parametro)] parametro = valor [getData->resolve(valor)]
    // en ele ej. autorizacion = true | user = {name:'carol'}
    getData()
    	.then(autorizacion => {
        if(autorizacion){
            return showDatas()
        }
      })
        .then(user => {
           		setText(user.name)
      })
})
        
        
        
```

- `async` se usa antes de la ejecución de la función donde se valla a usar Promise para poder usar `await` 

- `await` para esperar q se ejecute la promesa

  ```javascript
  btn.addEventListener('click', async () => {
      //ejemplo anterior con async y await
      let user = null
      const autorizacion = await getData()// await espera a que se retorne el valor
      if(autorizacion){
          user = await showDatas() // espera a obtener el valor de la promesa
      }
      setText(user.name)      
  })
  ```

- ejemplo libreria AJAX

  ```javascript
  const ajax = request => {
      return new Promise((resolve, reject) => { // devuelvo una promesa
          const xhr = XMLHttpRequest()
      	xhr.open(request.method, request.url, true)
          xhr.addEventListener('load', e => {
          // retorna el valor de e.target en el futuro cuando sea resuelta
              resolve(e.target)             
          })
          xhr,send()
      }    
  }
  
  // ejecuta la promesa async 
  btn.addEventListener('click', async evnt =>{
  	const req = {method: 'GET', url: 'http://url.com' }                        
  	// ejecuta la promesa y espera por su retorno e.target
  	const response = await ajax(req)
      switch(response.status){
          case 200:
              JSON.parse(response.responseText)
      }
  })
  ```

## Alternativas

### Axios 

> [axios github](https://github.com/axios/axios) repositorio para usar **axios** 

```javascript
axios.get('/user?id=54825')
	.then(response => {
    	console.log(response)
	})
	.catch(error => {
    	console.log(error)
	})

// --------------params--------------
axios.get('/user', {
    params:{
        ID: 54825
    }
})

//--------------use async await--------------
const func = async () => {
    try{
        const response = await axios.get('/user?id=54825')
        console.log(response)
    }catch(error){
        console.log(error)
    }
}
```

### Fetch

> remplazo moderno para **XMLHttpRequest** con **Promise** inclido

```javascript
const loadFetch = () => {
    fetch(url)
    	.then(response => response.json()) // 1ra respuesta debe se convierte a JSON
    	.then(response => {			// 2da respuesta tenemos ya los datos para procesarlos	
       	console.log(response)
    })
}

//-------------- async await --------------
const loadFetch = async () => {
     const response = await fetch(url)
     switch(response.status){
        case 200:
            const data = await response.json()) // 1ra respuesta debe se convierte a JSON
            console.log(data)
    }
}
//--------------enviar contenido ----------------
const persona = {
    name : "Leo",
    age : 31,
    active : true
}

const myHeaders = new Headers()
myHeaders.append('Content-type', 'application/json')
myHeaders.append('Authorization', 'Bearer 5456sd455s16q56d1s6q4d65s5')

const myConfig = {
    method: 'POST',
    header : myHeaders,
    body: persona
}

fetch(url, myConfig)
    .then(response => response.json()) 
    .then(response => {			
    console.log(response)
})
```

# WebSockets

## definicion 

>  es una tecnología que proporciona un canal de comunicación bidireccional y [full-duplex](Duplex_(telecomunicaciones).html) sobre un único [socket](Socket_de_Internet.html) [TCP](Transmission_Control_Protocol.html). Está diseñada para ser implementada en [navegadores](Navegador_web.html) y [servidores web](Servidor_web.html) (Chats, Juegos ) uso en TIEMPO REAL
>
> es un Objeto JS

### Ciclo de Vida

- conexión
- estado actual
- envio y recepcion de mensajes
- cierre

### Conexión

- el primer envió es mediante http,
- para conectar tener en cuenta el certificado ssl
- siempre es una conexión vía `GET` no puede ser otro método parámetros `url?clave=valor&clave2=valor2`  

```javascript
// para protocolo http
const ws = new WebSocket("ws://www.ejemplo.com/socketserver")
// para los protocolos https
const wss = new WebSocket("wss://www.ejemplo.com/socketserver")
```

## Eventos

registrar eventos del WS

- `onopen()` que de hacer cuando se conecta
- `onmessage()` que hacer cuando se recibe mensaje del servidor
- `onerror()` gestionar los errores 

## Metodos

- `send()` para enviar la informacion al server tipo de datos : `text/plain`, `json` , `bobj`, `byte` 
- `close()` para cerrar la conexion

## ejemplo

```javascript
let ws = null

const setText = data => {
    const msg = `<div>${data}</div>`
    chat.insertAdjacentHTML('beforeend', msg)
}

btnConnect.addEventListener('click', e =>{
    ws = new WedSocket('ws://echo.websoket.org')
    ws.onopen = () => setText('Conectado')
    ws.onclose = () => setText('Desconectado')
    ws.onerror = e => setText(e)
    ws.onmessage = e => {
        setText(e.data)
    }
})

btnDesconnect.addEventListener('click', e =>{
    ws.close()
})

// encia mensaje del textfild txtMes
btnSend.addEventListener('click', e =>{
    ws.send(txtMes.value)
    // JSON
    const msg = {
        name: "leo",
        message: "saludo"
    }
    ws.send(JSON.stringify(msg))
})
```

## ping pong

> técnica para evitar que se muera la conexión cliente-servidor al esperar un tiempo demasiado largo

```javascript
// se define q se pruebe la conexion cada 1 min
// haciendo un ping
setInterval( () => {
	ws.send(JSON.stringify({type: 'ping'}))
},60000)   // 60 se  1min

// se resive la respuesta del pong para saver q la conexion esta viva
// se puede Omitir
ws.onmessage = e => {
    if(e.data = 'pong')
        console.log('conexion viva')
}
```



