# Angular 11

## install

- nodejs
- angular cli

npm install -g @angular/cli

### install bootstrap

- `npm install bootstrap`

- incluir en **angular.json** las configuraciones del bootstrap

  ```json
   "styles": [
       "src/styles.css",
       "node_modules/bootstrap/dist/css/bootstrap.min.css"
   ],
  "scripts": ["node_modules/bootstrap/dist/js/bootstrap.min.js"]
  ```

## Crea un espacio de trabajo y una aplicación inicial

ng new my-app

## Ejecutar la aplicación

```
cd my-app 
ng serve --open | --o
```

## Estructura de Angular

![image-20211014135530703](angular\image-20211014135530703.png)

![image-20211014162146442](angular\image-20211014162146442.png)

- **main.ts** - punto de inicio de la app
- **index.html** - html inicial de la app, utilizado para la pagina ASP inicial
- **style.css** - estilo inicial de la app
- **app** - carpeta del componente raíz 
  - **app.module.ts** - definición del modulo inicial
  - **app.component.ts** - definicion de componente app
  - **app.component.html** - html del componente app
  - **app.component.css** - estilo del componente app

**app.module.ts**  - Decorador @NgModule y clase AppModule

- `declarations ` : array donde se registran todos los componentes del modulo
- 

```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```





## Estructura de Componentes

![image-20211014160529688](angular\image-20211014160529688.png)

- app - ejemplo del componente App
  - app.component.ts
  - app.component.html
  - app.component.css
    - registrarlo en el modulo o módulos que se va a utilizar

**app.component.ts**

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-root', // como en vuejs define el selector para utilizarlo <app-root>
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cursoStartApp';
}
```

**app.component.html**

```html
<div class="card card-small" (click)="selection.value = 'component'" tabindex="0">
        <svg class="material-icons" 
             xmlns="http://www.w3.org/2000/svg" 
             width="24" height="24" viewBox="0 0 24 24">
            <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
    </svg>
      <span>New Component {{ title }}</span>
    </div>
```

### creacion automacita con CLI

`ng generate component nameComponent` o `ng g c` crear un nuevo componente por la línea de comandos

```bash
CREATE src/app/empleado/empleado.component.html (23 bytes)
CREATE src/app/empleado/empleado.component.spec.ts (640 bytes)
CREATE src/app/empleado/empleado.component.ts (283 bytes)
CREATE src/app/empleado/empleado.component.css (0 bytes)
UPDATE src/app/app.module.ts (480 bytes)
```

### componente in line

todo en el mismo archivo **.ts** cuando son componentes muy simples

`ng generate component nameComponent -s -t` o `ng g c name -s -t`- crear un nuevo componente por la línea de comandos, -t template inline y -s style inline (dentro del fichero ts)

```bash
CREATE src/app/empleado-inline/empleado-inline.component.spec.ts (683 bytes)
CREATE src/app/empleado-inline/empleado-inline.component.ts (291 bytes)
UPDATE src/app/app.module.ts (596 bytes)
```

```typescript
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-empleado-inline',
  template: `
    <p>
      empleado-inline works!
    </p>
  `,
  styles: [`
        p {background-color: red;}
  `]
})
export class EmpleadoInlineComponent implements OnInit {

  constructor() { }
  ngOnInit(): void {
  }
}
```

## Interpolación

incorporar texto dinámico en los componentes, permite todos os operadores de JS

```html
<h4>Datos del Empleado</h4>
<p>Nombre {{ nombre }} {{ apellidos }}</p>
<p>es menor de edad {{edad > 18? 'SI':'NO'}}</p>
```

```typescript
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {
  nombre="Leandro";
  apellidos="Capdesuner"
  edad=33
  constructor() { }
}
```

#varname - identifica una variable de plantilla un identificador para elgun componente en la plantilla

```html
 <input #customerInput>{{customerInput.value}}
```



## Property Binding

vincular una propiedad de `.ts` a un objeto `html`, ***dirección del .TS ==> HTML***

`[value] = "namePropertyTS"` crea un binding para la propiedad namePropertyTS con el property del componente value, [] es : de vuejs

```html
<input #empresa type="text" (keyup)="0" [disabled]="disableProperty">
```

![image-20211014175356144](angular\image-20211014175356144.png)

## Event Binding

binding para eventos, pueden ***dirección del HTML ==> .TS***

`(click)="onClickEvent"` equivalente al @click= de vuejs

```html
<button (click)="onClear($event, empresa)">Limpiar</button>
<input type="radio" name="login" value="Si" (click)="onChangeRadio($event)">si
<input type="radio" name="login" value="No" (click)="onChangeRadio($event)">no
```

```typescript
onClear(event: Event, value: any) {
    value.value = ""
}

onChangeRadio(event: Event) {
    const selectRario: HTMLInputElement = <HTMLInputElement>event.target;
    if (selectRario.value === "Si")
      this.data_radio = "Seleciono - SI"
    if (selectRario.value === "No")
      this.data_radio = "Seleciono - NO"
}
```

![image-20211014182150455](angular\image-20211014182150455.png)

## Two way binding (binding bidireccional)

dirección tanto del  ***dirección bidireccional HTML <==> .TS***, es iguala el `v-model=` de vuejs

***app.module.ts*** importar `import { FormsModule } from '@angular/forms';` e incluirlo en el array d eimportaciones `imports: [  BrowserModule, FormsModule],`

```typescript
// HTML
<input type="text" [(ngModel)]="titleApp">

//.TS
export class AppComponent {  
  titleApp = 'cursoStartApp';
}
```

![image-20211014185301683](angular\image-20211014185301683.png)

## Directivas

elementos que se le aplican a las etiquetas HTML de la plantilla y que le añaden funcionalidad, para modificar la estructura del DOM, modificar apariencia

> en vuejs serian los v-if, v-for, v-model, :class, :style 

![image-20211014224300897](angular\image-20211014224300897.png)

- **ngIf** - condicional if  **else** el else va seguido del condicional dentro de las " " y hace referencia en este caso a el identificador de #sinRegistrar del <ng-template> 

  ```html
  <p *ngIf="registrado else sinRegistrar">Usuario registrado! {{usuario}} {{password}}</p>
  
  <ng-template #sinRegistrar>
    <p>sin registrar</p>
  </ng-template>
  ```

- **<ng-template>** - los valores que están dentro de este tag no carga por defecto

- **ngFor** - for

  ```html
  <div *ngFor="let item of items; let i=index">
    {{i + 1}} - {{item.name}}
  </div>
  ```

  ```typescript
    items: Array<Object>;
  
    constructor() {
      this.items = [
        { name: "prier titulo" },
        { name: "segundo titulo" },
        { name: "tercer titulo" },
        { name: "cuarto titulo" }
      ]
    }
  ```

- **ngClass** y **ngStyle** - directivas para modificar el estilo vuejs= :class y :style

  ```html
  <!-- CSS -->
  .para-director {
      color: red;
      font-weight: bold;
      text-decoration: underline;
  }
  
  <!-- HTML -->
  <p *ngIf="registrado else sinRegistrar">Usuario registrado! {{usuario}} {{password}} es un
    <span [ngStyle]="{color: cargo== 'dir' ?'red': 'black'}">{{cargo}}</span>
      
    <span [ngClass]="{'para-director': cargo=='dir2'}">{{cargo}}</span>
  </p>
  ```

  

## Comunicación entre componentes

**@Input** - pasar informacion del padre al hijo , como las props de vuejs

```typescript
//*************** Padre *************
// HTML 
<app-empleado-hijo *ngFor="let item of items; let i=index" [index]="i" [nameHijo]="item.name">
</app-empleado-hijo>

//*************** Hijo *************
// TS Hijo
export class EmpleadoHijoComponent implements OnInit {
  @Input() index:number;
  @Input() nameHijo:string;
  constructor() { }
  ngOnInit(): void {
  }
}

// html
<div>{{index}} => {{nameHijo}}</div>
```

**@Output** - pasar del Hijo al Padre, como el $emit() de vuejs

Hijo

```typescript
// HTML
<button (click)="emitCaracteristicas(caract.value)" class="btn btn-primary">nueva</button>

// TS
@Output() empleadoEmit = new EventEmitter<string>() ;

emitCaracteristicas(envio:string){
  this.empleadoEmit.emit(envio)
}
```

Padre

```typescript
// HTML
<app-detalle-empleado (empleadoEmit)="onAddCaracteristica($event)"></app-detalle-empleado>

// TS
onAddCaracteristica(newCaracteristica: string) {
    this.listCaracteristica.push(newCaracteristica);
  }
```

## Servicios

clase que implementa datos o tareas comunes para varios componentes y puede ser inyectado en los componentes cuando sea necesario angular lo hará de forma automática

![image-20211015162728889](angular\image-20211015162728889.png)

1.  **ng g s name** - genera un nuevo servicio 

2. service-empleados.service.ts - decorador @Injectable para la clase que define el servicio

   ```typescript
   import { Injectable } from '@angular/core';
   

    @Injectable({
      providedIn: 'root'
    })
    export class ServiceEmpleadosService {

      constructor() { }

      mostrarMsg(msg: string) {
        alert('msg: ' + msg)
      }
    }
   ```

3. registrar el servicio en la app en `providers: [ServiceEmpleadosService],` de **app.module.ts** 

4. utilización, inyección a través del constructor

   ```typescript
   import { Component } from '@angular/core';
   import { ServiceEmpleadosService } from './service-empleados.service';
   
   @Component({
     selector: 'app-root',
     templateUrl: './app.component.html',
     styleUrls: ['./app.component.css']
   })
   export class AppComponent {
   
     constructor(private servicioMsgEmpleado: ServiceEmpleadosService) { }
   
     registrar(event: Event) {
       this.servicioMsgEmpleado.mostrarMsg("vamos a registrar!")
       this.registrado = true;
     }
   }
   ```

### Data Service

servicio con datos para poder inyectar en los componentes que lo necesiten

### servicios anidados

es simplemente inyectar la dependencia de un servicio en otro

```typescript
import { Injectable } from "@angular/core";
import { ServiceEmpleadosService } from "./service-empleados.service";

@Injectable()
export class LoadService {
    items: Object[] = [{ name: "prier titulo" },{ name: "segundo titulo" },{ name: "tercer titulo" }]

    constructor(private serviceMsg: ServiceEmpleadosService){}

    addNewItem(item: Object) {
        this.serviceMsg.mostrarMsg("desde servicio")
        this.items.push(item);
    }
}
```

## Routing

el componente principal **app.component.ts** es el encargado de gestionar el ruting de la aplicación

![image-20211015175050275](angular\image-20211015175050275.png)

```html
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="#">Home</a>
        <a class="nav-link" href="/proyect">Proyecto</a>
        <a class="nav-link" href="/quienes-somos">Pricing</a>
      </div>
    </div>
  </div>
</nav>
<router-outlet></router-outlet>
```

**app.module.ts**

```typescript
import { HomeComponentComponent } from './home-component/home-component.component';
import { ProyectComponentComponent } from './proyect-component/proyect-component.component';
import { QuienesComponentComponent } from './quienes-component/quienes-component.component';
import { RouterModule, Routes } from '@angular/router';

const router: Routes = [
  { path: '', component: HomeComponentComponent },
  { path: 'proyect', component: ProyectComponentComponent },
  { path: 'quienes-somos', component: QuienesComponentComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    EmpleadoHijoComponent,
    DetalleEmpleadoComponent,
    HomeComponentComponent,
    ProyectComponentComponent,
    QuienesComponentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(router)
  ],
  providers: [ServiceEmpleadosService, LoadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

## Redux

![image-20211017103551136](angular\image-20211017103551136.png)

### Flujo de la Informacion

![image-20211017103804559](angular\image-20211017103804559.png)

### install

`npm install @ngrx/store` instalar las librerias redux para store

**contador.actions.ts** - constastes y clases para reducer

```typescript
import { Action } from "@ngrx/store"

export const INCREMENTAR = '[Contador] Incrementar'
export const DECREMENTAR = '[Contador] Decrementar'

export class IncrementAction implements Action {
    readonly type = INCREMENTAR
}
export class DecrementAction implements Action {
    readonly type = DECREMENTAR
}
```

**contador.reducer.ts** - reducer que modifica el viejo estado y retorna el nuevo

```typescript
import { Action } from "@ngrx/store";
import { DECREMENTAR, INCREMENTAR } from "./contador.actions";

export function contadorReducer(state: number = 10, actions: Action) {
    switch (actions.type) {
        case INCREMENTAR:
            return state + 1;
        case DECREMENTAR:
            return state - 1;
        default:
            return state;
    }
}
```

**app.module.ts** - registrar el store con la estructura esperada

```typescript
import { StoreModule } from '@ngrx/store';
import { contadorReducer } from './contador/contadorReducer';

// deve ser implementada en un fichero de modelos app.store.model.ts
export interface AppSate {
    contador: number
}

@NgModule({
  declarations: [
    AppComponent,
    HijoComponent,
    NietoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StoreModule.forRoot({ contador: contadorReducer })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

**app.component.ts** - componente que ejecuta(dispatch) el reducer para modificar el store, el método subcribe() permite suscribirse a todo el state o .select() a una propiedad especifica del stete, convertir las variables en reactivas como en vuejs

```typescript
import { IncrementAction, DecrementAction } from './contador/contador.actions';
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppSate } from './app.store.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  contador: number;

  constructor(private store: Store<AppSate>) {
      
   // subcribirse a todo el state
   this.store.subscribe(state => { this.contador = state.contador })

   // obtener una propiedad especifica del state = contador
   this.store.select('contador').subscribe(contadorState => this.contador = contadorState)
  }

  incrementar() {    this.store.dispatch(new IncrementAction())  }
  decrementar() {    this.store.dispatch(new DecrementAction())  }
}
```

### Dev Tools

- `npm install @ngrx/store-devtools --save`

- isntall from Browsers

- confing in **app.module.ta**

  ```typescript
    imports: [
    	...
      StoreModule.forRoot({ contador: contadorReducer }),
      StoreDevtoolsModule.instrument({
        maxAge: 25, 			// Retain last 25 state
        logOnly: environment.production 	// restrit to prodoction mode
      })
  ]
  ```

  ### Eferctos

  ![image-20211021002908178](angular\image-20211021002908178.png)