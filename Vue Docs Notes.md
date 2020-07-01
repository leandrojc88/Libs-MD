# Vue Docs Notes

## Introduccion

v-bind: 0 :-> enlaza atributos 

 Las **directivas** tienen el prefijo `v-` para indicar que son atributos especiales proporcionados por Vue, y como puede haber adivinado, aplican un comportamiento reactivo especial al DOM representado. Aquí, básicamente se dice “mantener el atributo `title` de este elemento actualizado con la propiedad `message` en la instancia de Vue”. 

 `v-`. Se espera que los valores de atributo de la directiva sean **una única expresión de JavaScript** 

podemos vincular datos no solo con el texto y los atributos, sino también con la **estructura** del DOM. Además, Vue también proporciona un potente sistema de efectos de transición que puede aplicar automáticamente [efectos de transición](http://localhost:4000/v2/guide/transitions.html) cuando los elementos son insertados, actualizados o eliminados por Vue. 

 Para permitir que los usuarios interactúen con su aplicación, podemos usar la directiva `v-on` para adjuntar escuchas de eventos que invocan métodos en nuestras instancias de Vue `<button v-on:click="reverseMessage">Mensaje invertido</button>`

 Vue también proporciona la directiva `v-model` que hace que el enlace bidireccional entre los controles de los formularios y el estado de la aplicación sea muy sencilla 

```js
<div id="app-6">  
    <p>{{ message }}</p>  
	<input v-model="message">
 </div>
var app6 = new Vue({  
    el: '#app-6',  
    data: {    message: 'Hola Vue!'  }})
```

## La instancia Vue

Cada aplicación de Vue se comienza creando una nueva **Instancia de Vue** con la función `Vue`:

`var vm = new Vue({  // opciones})`

Una aplicación Vue está conformada por una **instancia Vue raíz** creada con `new Vue`, opcionalmente organizada dentro de un árbol de componentes reusables anidados. Por ejemplo, el árbol de una aplicación TO-DO podría verse así:

```
Root Instance

└─ TodoList   

├─ TodoItem   

	  ├─ DeleteTodoButton   

	  └─ EditTodoButton   

└─ TodoListFooter      

  	 ├─ ClearTodosButton      

  	 └─ TodoListStatistics
```

todos los componentes de Vue son también instancias de Vue, por lo tanto aceptan el mismo objeto options (excepto para unas pocas específicas opciones de raíz). 

Cuando una instancia Vue es creada, agrega todas las propiedades encontradas en su objeto `data` al **sistema de reactividad** de Vue. Cuando los valores de estas propiedades cambian, la vista “reaccionará”, actualizándose para coincidir con los nuevos valores. 

 las instancias de Vue exponen una serie de métodos y propiedades de instancia útiles. Estos tienen el prefijo `$` para diferenciarlos de las propiedades definidas por el usuario. Por ejemplo: vm.$data === data , vm.$el , vm.$watch()

 Cada instancia de Vue pasa a través de una serie de pasos de inicialización cuando es creada - por ejemplo, se necesita configurar la observación de datos, compilar la plantilla, montar la instancia en el DOM y actualizar el DOM cuando cambian los datos. En el camino, también se ejecutan funciones llamadas **hooks del ciclo de vida**, lo que brinda a los usuarios la oportunidad de agregar su propio código en etapas específicas. 

 También hay otros hooks que se llamarán en diferentes etapas del ciclo de vida de la instancia, como `created`,  [`mounted`](http://localhost:4000/v2/api/#mounted), [`updated`](http://localhost:4000/v2/api/#updated), y [`destroyed`](http://localhost:4000/v2/api/#destroyed). Todos los hooks del ciclo de vida se llaman en el contexto `this` apuntando a la instancia de Vue que lo invoca. 

>  No usar [arrow functions](https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Functions/Arrow_functions) en una propiedad de opciones o respuesta, como `created: () => console.log(this.a)` or `vm.$watch('a', newValue => this.myMethod())`. Dado que las arrow functions están vinculadas al contexto padre, `this` no será la instancia de Vue como lo esperaría, lo que suele producir errores, como ‘Uncaught TypeError: Cannot read property of undefined`o`Uncaught TypeError: this.myMethod is not a function`. 

## Sintaxis de Template

 la directiva `v-bind` se usa para actualizar de forma reactiva un atributo HTML: `<a v-bind:href="url"> ... </a>`  Aquí href es el argumento, que le dice a la directiva `v-bind` que vincule el atributo `href` del elemento al valor de la expresión `url`. 

 Los modificadores son postfijos especiales indicados por un punto, que indican que una directiva debe estar vinculada de alguna manera especial. Por ejemplo, el modificador `.prevent` le dice a la directiva `v-on` que llame a `event.preventDefault()` en el evento activado: `<form v-on:submit.prevent="onSubmit"> ... </form>` 

## Propiedades Computadas y Observadores

Las expresiones en el *template* son muy convenientes, pero están diseñadas para operaciones simples. Poner demasiada lógica en sus *templates* puede hacerlos grandes, complejos y difíciles de mantener. Por ejemplo:

```
<div id="example">  {{ message.split('').reverse().join('') }}</div>
```

En este punto, el *template* ya no es simple y declarativo. Debe mirarlo por un segundo antes de darse cuenta de que muestra `message` al revés. El problema se agrava cuando desea incluir el mensaje invertido en su *template* más de una vez.

Es por eso que para cualquier lógica compleja, deberia usar una **propiedad computada**.

 las **propiedades computadas se almacenan en caché según sus dependencias** 

 ¿Por qué necesitamos caché? Imagina que tenemos una costosa propiedad computada **A**, que requiere hacer un bucle a través de una gran matriz y hace muchos cálculos. Entonces podemos tener otras propiedades computadas que a su vez dependen de **A**. Sin caché, estaríamos ejecutando el captador de **A**muchas veces más de lo necesario. En los casos en que no desee el almacenamiento en caché, utilice un método en su lugar. 

## Renderizacion condicional

## [`v-if` vs `v-show`](http://localhost:4000/v2/guide/conditional.html#v-if-vs-v-show)

`v-if` es una renderización condicional “real” ya que garantiza que los eventos y componentes secundarios dentro del bloque condicional sean debidamente destruidos y recreados durante la alternancia.

`v-if` también es **lazy**: si la condición es falsa en la representación inicial, no se hará nada. El bloque condicional no se procesará hasta que la condición se convierta en true por primera vez.

En comparación, `v-show` es mucho más simple: el elemento siempre se representa independientemente de la condición inicial, con alternancia basada en CSS.

En general, `v-if` tiene costos de alternancia más altos, mientras que`v-show`tiene costos de renderización iniciales más altos. Por lo tanto, prefiera `v-show` si necesita cambiar algo muy a menudo, y prefiera`v-if` si es poco probable que la condición cambie en el tiempo de ejecución.

 Usando `v-if` y `v-for` juntos **no es recomendado**. Vea la [guía de estilo](http://localhost:4000/v2/style-guide/#Avoid-v-if-with-v-for-essential) para mayor información. 

## Renderizado de Lista

v-for para un objeto

```js
<div v-for="(value, key, index) in object">  
    {{ index }}. {{ key }}: {{ value }}
</div>
```

# Componentes

