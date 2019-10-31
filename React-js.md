# React js

## Definición

> Biblioteca(Librería) para construir **interfaces**, el propósito es la Vista

### Virtual DOM

`Document Object Model`: estructura del árbol del html en el navegaor

`virtual DOM`: copia exacta del DOM en memoria, que funciona para conocer el estado de la paguina y que parte del DOM a cambiado en el documento y de esta manera rendereza solo esa parte de la jerarquía de elementos en el DOM real

### Componentes

> porciones de código que se pueden reutilizar, un componente se pueden reutilizar uno dentro de otro	

## Instalar

### Pasos

1. tener `nodejs` version *lts* y `npm`

2. instalar **Create React App**: libreria que configura toda la estructura del projecto `Reactjs`

   `npx create-react-app nombrecarpeta` para instalar el projecto react en la dir `nombrecarpeta` sin instalar la libreria

### Yarn

gestor de dependencias como nmp creado por facebook para react

`npm install -g yarn` para instalarlo desde npm (-g lo instala de forma global)

`yarn start` inicia el proyecto en el browser

- **instalar complemento para navegador react developer tools** adiciona una nueva pestaña en las developer tools del navegador para *react* 

## Package.json

> objeto json donde estan los detalles y config del proyecto (name, )

### Dependencias

librerías requeridas para que funcione el proyecto, descarga las dependencias declaradas en el objeto recursivamente ej: [react, react-dom, react-scripts]

```json
"dependencies": {
    "react": "^16.8.6",
    "react-dom": "^16.8.6",
    "react-scripts": "3.0.1"
  },
```

`react` core de react

`react-dom` para usar el virtual dom

`react-scripts` para usar los scripts de react en la consola

### Scripts

listado de scripts que se pueden ejecutar en la CLI, `propiedad: valor`, se ejecutan con comano `npm nombre_script`

```json
"scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
```

`npm start` inicia un servidor de desarrollo, con auto refersh del navegador 

`npm run build` para crear el build del proyecto para producción

### Integracion con librerias

`npm i ed-grid` instala dependencia ed-grid

`npm i ed-grid edteam-style-guides node-sass` instala ed-grid,  edteam-style-guides, node-sassde en el server react

> ver si no se cambia `i` por `add`

## JSX

> (javascript xtended)sintaxis creada por facebook para extender javascript, JS + XML, no es HTML es JSX 
>
> `const App = () =><h1> Hola mundo </h1>`

### Reglas

- siempre tiene apertura y cierre <>    </>
- solo se retorna un elemento padre, dentro puede tener la sintaxis q sea
- apoyarse en los fragment para retornar mas de un elemento
- `img` siempre se cierran `/>` 
- `class` es **className**
- `for`  **htmlFor**
- no se se pueden usar instrucciones `if`, `else` o `while` 
- **El resto de la sintaxis HTML es valida en JSX**

### ReactDOM.render

`ReactDOm.render(elemento, dondehtml)` renderiza un elemento creado en `dondehtml` elemento del html 

```javascript
// codigo h1 creado en JSX lo inserta en div "root"
ReactDOm.render(<h1>hola mundo</h1>, getElementById("root"))
```

### Importar JSX

​		index.js

​		para importar componente JSX <App /> siempre usar /> y un espacio <App />

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
// para importar componente JSX <App /> siempre usar /> y un espacio <App />
ReactDOM.render(<App />, document.getElementById('root'));
```

​		App.js

```javascript
import React from 'react';
function App() {
  return (
    <div>
     Hola Mundo
    </div>
  );
}
export default App; // permite exportar para su uso
```

### Fragmentos

se usa como padre para no ensuciar con `div`, no se renderiza en el navegador : `<Fragment>` o `<>`

```javascript
function App() {
  return (
  <Fragment>
    <div>Hola Mundo </div>
    <div>Hola Mundo 2</div>
   </Fragment>
  );
}

function App() {
  return (
  <>
    <div>Hola Mundo </div>
    <div>Hola Mundo 2</div>
   <>
  );
}
```

### E







