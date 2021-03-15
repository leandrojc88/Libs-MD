# Patrones de Diseños

Define :

- nombre
- Problema
- Solución 

## Anti patrón

patrón que nos guía a una mala solución 

negativo > positivo

## Creacionales

>  Participan en la creación de instancias de objetos

### Singlenton

> **instancia única** permite restringir la creación de [objetos](Programación_orientada_a_objetos.html) pertenecientes a una [clase](Clase_(programación_orientada_a_objetos).html) o el valor de un tipo a un único objeto. Su intención consiste en garantizar que una clase sólo tenga una [instancia](Instancia_(informática).html) y proporcionar un punto de acceso global a ella.

**ejemplo uso común** 

conexión de base de datos

leer configuraciones

**Pasos**

1. crear instancia `static` de la propia clase para que sea única 
2. crear constructor privado para que no pueda ser referenciado desde otro paquete
3. metodo publico que crea la instancia y si ya excite la retorna

**Ejemplo**

```java
public class MySinglenton {
    String x;
    // crear intancia `static` de la propia clase para que sea unica 
    private static MySinglenton mySinglenton;
    
    //crear constructor privado para que no pueda ser referenciado desde otro paquete
    private MySinglenton(String x){
        this.x = x;
    } 
    
    //metodo publico que crea la instancia y si ya excite la retorna
    public static getMySinglenton(String x){
        if(mySinglenton == null)
            mySinglenton = new MySinglenton(x);
         return mySinglenton;
    }
}
```

utilizar

```java
// utilizando metodos estaticos para crear la instancia unica
MySinglenton a = MySinglenton.getMySinglenton("a")
MySinglenton b = MySinglenton.getMySinglenton("b")

print(a.getX()) // retorna a
print(b.getX()) // retorna a , porque es una unica instancia
```

### Factory (Fabricación)

> Crear instancias de objetos de otras clases, encapsular como se creara determinado objetos
>
> ***Crear Objetos concretos en tiempo de Ejecución
>
> se deriva en **Factory Method** y **Abstract Factory**
>
> Clases que implementan al menos un método de creación. pueden ser de esta misma clase o de otras

### Factory Method

> define una interfaz para crear un objeto, las subclases definen que clase se va a instanciar

**ejemplo uso común** 

class Calendar en java, class NumberFormat en java

para realizar un pago (tranferencia bancari, paypal, etc...)

Conexiones de ORM (postgres, sqlite)

**partes**

- Producto - define la `interface ` que define el método de fabricación
- Producto Concreto - implementa la interface del producto (poner el código)
- Creador - declara los métodos para fabricar y retorna un objeto del tipo producto
- Creador Concreto - redefine el método de fabricación para devolver la instancia del producto concreto

**Ejemplo** Conexión a diferentes bases de datos

```java
// PRODUCTO
public interface DBAdapter {
    Connection getConnection();
}
```

```java
//PRODUCTO CONCRETO
public class MySQLAdapter implements DBAdapter{
    @Override
    public Connection getConnection() {
        Connection connection = null;
        return connection;
    }    
}
```

```java
//Producto Concreto
public class PostgresAdapter implements DBAdapter{
    @Override
    public Connection getConnection() {
        Connection connection = null;
        return connection;
    }
}
```

```java
// CREADOR y CREADOR CONCRETO
public class DBFactory {
    public enum DBType{ MySQL, Oracle, Postgres }
    
    public static DBAdapter getDBAdapter(DBType type){
        switch(type){
            case MySQL:
                return new MySQLAdapter();                
            case Oracle:
                return new OracleAdapter();
            case Postgres:
                return new PostgresAdapter();
            default:
                throw new IllegalArgumentException("No soportado");
        }
    }
}
```

utilizar

```java
public class main {
    public static void main(String[] args) {
        DBAdapter dBAdapter = DBFactory.getDBAdapter(DBFactory.DBType.MySQL);
        Connection connection = dBAdapter.getConnection();
    }
}
```

### Abstract Factory

> fabricar fabricas?, permite crear fabricas de fabricas para crear productos concretos
>
> adiciona un nivel mas de abstracción

**partes**

- Producto - define la `interface ` que define el método de fabricación
- Producto Especifico - interface una herencia del producto para especificar 
- Producto Concreto - implementa la interface del producto (poner el código)
- Creador - declara los métodos para fabricar y retorna un objeto del tipo producto
- Creador Concreto - redefine el método de fabricación para devolver la instancia del producto concreto
- Abstrac Factory - fabrica los Creadores y lo retorna y proporciona método abstracto para obtener el producto específico de los creadores

**ejemplo** - creación de una fabrica de vehículos (carros y motocicletas)

interface - Productos

```java
//Producto
public interface Vehiculo {
    public abstract int getWheels();
    public abstract int getSeats();
}

//Producto Espesifico
public interface Car extends Vehiculo{
    public int getDoors();
}

// Producto Especifico
public interface Motorbike extends Vehiculo{
    public String getType();
}
```

Productos Concreto - Motorbike

```java
//Producto Concreto
public class CruiseMotorbike implements Motorbike{
    @Override
    public String getType() {
        return "Cruse!";
    }
    @Override
    public int getWheels() {
        return 2;
    }
    @Override
    public int getSeats() {
        return 2;
    }
}

// producto Concreto
public class SportMotorbike implements Motorbike{
    @Override
    public String getType() {
        return "Sport!";
    }
    @Override
    public int getWheels() {
        return 2;
    }
    @Override
    public int getSeats() {
        return 1;
    }  
}
```

Productos Concreto - Car

```java
// Producto Concreto
public class FamilyCar implements Car{
    @Override
    public int getDoors() {
        return 5;
    }
    @Override
    public int getWheels() {
        return 4;
    }
    @Override
    public int getSeats() {
        return 4;
    }
}

// Producto Concreto
public class LuxuryCar implements Car{
    @Override
    public int getDoors() {
        return 2;
    }
    @Override
    public int getWheels() {
        return 4;
    }
    @Override
    public int getSeats() {
        return 2;
    }  
}
```

Abstrac Factory

```java
public abstract class VehicleAbstractFactory {
    
    public static final int CarFactory = 1;
    public static final int MotorbikeFactory = 2;
    
    public abstract Vehiculo getVehiculo(int type);
    // retorna unas instancia de si misma
    public static VehicleAbstractFactory getFactory(int type){
        switch(type){
            case CarFactory:
                return new CarFactory();
            case MotorbikeFactory:
                return new MotorbikeFactory();
        }
        return null;
    }
}
```

Factory - Creador concreto hereda de AbstracFactory para implementar el getVehiculo()

```java
public class CarFactory extends VehicleAbstractFactory{
    public static final int FAMILY = 1;
    public static final int LUXURY = 2;

    @Override
    public Vehiculo getVehiculo(int type) {
        switch(type){
        case FAMILY:
            return new FamilyCar();
        case LUXURY:
            return new LuxuryCar();
        }
        return null;
    }
}

public class MotorbikeFactory extends VehicleAbstractFactory{
    public static final int SPORT = 1;
    public static final int CRUISE = 2;
    
    @Override
    public Vehiculo getVehiculo(int type) {
        switch(type){
            case SPORT:
                return new SportMotorbike();
            case CRUISE:
                return new CruiseMotorbike();
        }
        return null;
    } 
}
```

### Builder

> permite crear objetos y decirle que debe hacer una ves construido
>
> Crear flujos de trabajos de manera dinámica, distintos flujos de trabajos sin crear proyectos diferentes
>
> separa la creación de un objeto complejo de sus representaciones, el proceso de construcción puede crear representaciones diferentes

**ejemplo uso común** 

Crear flujos de trabajos de manera dinámica

Formularios de Symfony

En las QuerysBuilder de Doctrine

**partes**

- Builder 

   interfaz abstracta para crear productos.

-  Concrete Builder 

   implementación del Builder

   construye y reúne las partes necesarias para construir los productos

-  Director 

   construye un objeto usando el patrón Builder 

-  Producto 

   El objeto complejo bajo construcción

**ejemplo**

```java
/** "Producto" */
class Pizza {
    private String masa = "";
    private String salsa = "";
    private String relleno = "";
 
    public void setMasa(String masa)     { this.masa = masa; }
    public void setSalsa(String salsa)     { this.salsa = salsa; }
    public void setRelleno(String relleno) { this.relleno = relleno; }
}
 
 
/** "Abstract Builder" */
abstract class PizzaBuilder {
    protected Pizza pizza;
 
    public Pizza getPizza() { return pizza; }
    public void crearNuevaPizza() { pizza = new Pizza(); }
 
    public abstract void buildMasa();
    public abstract void buildSalsa();
    public abstract void buildRelleno();
}

 
/** "ConcreteBuilder" */
class HawaiPizzaBuilder extends PizzaBuilder {
    public void buildMasa()   { pizza.setMasa("suave"); }
    public void buildSalsa()   { pizza.setSalsa("dulce"); }
    public void buildRelleno() { pizza.setRelleno("chorizo+alcachofas"); }
}
 
/** "ConcreteBuilder" */
class PicantePizzaBuilder extends PizzaBuilder {
    public void buildMasa()   { pizza.setMasa("cocida"); }
    public void buildSalsa()   { pizza.setSalsa("picante"); }
    public void buildRelleno() { pizza.setRelleno("pimienta+salchichón"); }
}
 
 
/** "Director" */
class Cocina {
    private PizzaBuilder pizzaBuilder;
 
    public void setPizzaBuilder(PizzaBuilder pb) { pizzaBuilder = pb; }
    public Pizza getPizza() { return pizzaBuilder.getPizza(); }
 // builder Patron
    public void construirPizza() {
       pizzaBuilder.crearNuevaPizza();
       pizzaBuilder.buildMasa();
       pizzaBuilder.buildSalsa();
       pizzaBuilder.buildRelleno();
    }
}
 
 
/** Un cliente pidiendo una pizza. */
class BuilderExample {
    public static void main(String[] args) {
        Cocina cocina = new Cocina();
        PizzaBuilder hawai_pizzabuilder = new HawaiPizzaBuilder();
        PizzaBuilder picante_pizzabuilder = new PicantePizzaBuilder();
 
        cocina.setPizzaBuilder( hawai_pizzabuilder );
        cocina.construirPizza(); //ejecutando el flujo del builder
 
        Pizza pizza = cocina.getPizza();
    }
}

/** 
 *  2da opción para el abstract builder quizá más transparente para su uso.
 *  Dentro del crear se llaman los métodos build.
 *  Es válido siempre y cuando no se necesite alterar
 *  el orden del llamado a los "build's".
 */
abstract class OtroPizzaBuilder {
    protected Pizza pizza;
 
    public Pizza getPizza() { return pizza; }
    public void crearNuevaPizza() { 
           pizza = new Pizza(); 
           buildMasa();
           buildSalsa();
           buildRelleno();
    }
 
    public abstract void buildMasa();
    public abstract void buildSalsa();
    public abstract void buildRelleno();
}

/** "Director" */
class OtraCocina {
    private OtroPizzaBuilder pizzaBuilder;
 
    public void setPizzaBuilder(OtroPizzaBuilder pb) { pizzaBuilder = pb; }
    public Pizza getPizza() { return pizzaBuilder.getPizza(); }
 
    public void construirPizza() {
       pizzaBuilder.crearNuevaPizza();
       //notar que no se necesita llamar a cada build.
    }
}
```

### Prototype

> objetivo es clonar un objeto,  tiene como finalidad crear nuevos objetos duplicándolos, clonando una instancia creada previamente. 

**ejemplos uso común**

- objetos repetitivos
- clonar es más rápido que crear
- evitar subclases
- Constructor es lo opuesto al Prototipo
- Setear valores específicos

**partes**

- Cliente  
- Prototype - clase abstract que contiene propiedades con sus getters y setter, y un método abstract clone que retorna el mismo tipo de la clase(Prototype)
- Prototipe Concreto - implementar el método clone retornando la clase con subvalores

**ejemplo**

```java
// Prototype
public abstract class PrototypeItems {
    
    String x;
    String y;
    String z;

    public String getX() {return x;}
    public void setX(String x) {this.x = x;}
    public String getY() {return y;}
    public void setY(String y) {this.y = y;}
    public String getZ() {return z;}
    public void setZ(String z) {this.z = z;}
    
    public abstract PrototypeItems clone();
}


//Concrete Prototype
public class ItemA extends PrototypeItems{
    public ItemA(String x, String y, String z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public PrototypeItems clone() {
        return new ItemA(this.x, this.y, this.z);
    } 
}

//Concrete Prototype
public class ItemB extends PrototypeItems{
    public ItemB(String x, String y, String z){
        this.x = x;
        this.y = y;
        this.z = z;
    } 
    @Override
    public PrototypeItems clone() {
        return new ItemB(this.x, this.y, this.z);
    }
}

// CLiente
ItemA uno = new ItemA("x-a", "y-a", "z-a");
PrototypeItems clonado = uno.clone();
System.err.println("uno - " + uno.getX());
System.err.println("clonado - " + clonado.getX());
```

## Estructurales

> Trabaja sobre las estructuras y composición

### Adapter

>  Convierte la interfaz de una clase en otra interfaz que el cliente espera. *Adapter* permite a las clases trabajar juntas, lo que de otra manera no podría hacerlo debido a sus interfaces incompatibles. 

**ejemplos uso común**

- Se desea usar una clase existente, y su interfaz no se iguala con la necesitada.
- Cuando se desea crear una clase reusable que coopera con  clases no relacionadas, es decir, las clases no tienen necesariamente  interfaces compatibles.

**partes**

- **Target** define la interfaz específica del dominio que *Client* usa.
- **Client** colabora con la conformación de objetos para la interfaz *Target*.
- **Adaptee** define una interfaz existente que necesita adaptarse
- **Adapter** adapta la interfaz de *Adaptee* a la interfaz *Target*

**ejemplo**

```java
public abstract class Bombilla {

    public abstract String encender();

    public abstract String apagar();

    public abstract String aumentarIntencidad();
}


public class BombillaModerna extends Bombilla {

    @Override
    public String encender() {
        return "encendido moderno";
    }

    @Override
    public String apagar() {
        return "apagado moderno";
    }

    @Override
    public String aumentarIntencidad() {
        return "aumentado moderno";
    }
}

public class BombillaClasica extends Bombilla{

    @Override
    public String encender() {
        return "encendido clasico";
    }

    @Override
    public String apagar() {
        return "apagado clasico";
    }

    @Override
    public String aumentarIntencidad() {
        return "aumento clasico";
    }    
}

public class Vela {
    
    public String prender(){
        return "pegar candela";
    }
    
    public String apagar(){
        return "soplarla";
    }    
    // no existe aumentar !!    
}

// Adaptador
public class BombillaAdapter extends Bombilla {

    // objeto que se adaptara
    Vela vela = new Vela();

    @Override
    public String encender() {
        return vela.prender();
    }

    @Override
    public String apagar() {
        return vela.apagar();
    }

    @Override
    public String aumentarIntencidad() {
        return vela.prender() + " meterle gasolina";
    }
}

 public static void main(String[] args) {
        Bombilla[] lista_bombillas = new Bombilla[3];
        lista_bombillas[0] = new BombillaModerna();
        lista_bombillas[1] = new BombillaClasica();
        lista_bombillas[2] = new BombillaAdapter();

        for (int i = 0; i < lista_bombillas.length; i++) {
            System.out.println(lista_bombillas[i].encender());
        }
    }
```

### Facade (Fachada)

>   **Fachada** (*Facade*) es un tipo de [patrón de diseño](Patrón_de_diseño.html) estructural. Viene motivado por la necesidad de estructurar un entorno  de programación y reducir su complejidad con la división en subsistemas, minimizando las comunicaciones y dependencias entre estos. 
>
>  Proveer una interfaz simple a un subsistema complejo

**ejemplos uso común**

-  Se aplicará el patrón fachada cuando se necesite proporcionar una interfaz simple para un subsistema complejo 
-  cuando se quiera estructurar varios subsistemas en capas, ya que las fachadas serían el punto de entrada a cada nivel 
-  Otro escenario proclive para su aplicación surge de la necesidad de  desacoplar un sistema de sus clientes y de otros subsistemas, haciéndolo más independiente, portable y reutilizable  

**partes**

- **Fachada** (*Facade*): conoce  qué clases del subsistema son responsables de una determinada petición, y delega esas peticiones de los clientes a los objetos apropiados del  subsistema.
- **Subclases** (*ModuleA*, *ModuleB*, *ModuleC*...): implementan la funcionalidad del subsistema. Realizan el trabajo  solicitado por la fachada. No conocen la existencia de la fachada.

**ejemplo**

```java
public class Impresora {
 		private String tipoDocumento;
 		private String hoja;
    	private boolean color;
 		private String texto;
 		public String getTipoDocumento() {return tipoDocumento;}
 		 public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento;}
 		public void setHoja(String hoja) {this.hoja = hoja;}
 		public String getHoja() {return hoja;}
 		public void setColor(boolean color) {this.color = color;}
 		public boolean getColor() {return color;}
 		public void setTexto(String texto) {this.texto = texto;}
 		public String getTexto() {return texto;}
 		public void imprimir() {impresora.imprimirDocumento();}
 }
```

Facade

```java
public class FachadaImpresoraNormal {
 		Impresora impresora;
 		public FachadaImpresoraNormal(String texto) {
 		 		super();
 		 		impresora= new Impresora();
 		 		impresora.setColor(true);
 		 		impresora.setHoja("A4");
 		 		impresora.setTipoDocumento("PDF");
 		 		impresora.setTexto(texto);
 		 }

 		public void imprimir() {
 		 		impresora.imprimirDocumento();
 		 }

 }
```

```java
public class PrincipalCliente2 {

    public static void main(String[] args) {

    // con fachada
    FachadaImpresoraNormal fachada1= new FachadaImpresoraNormal("texto1");
    fachada1.imprimir();
    FachadaImpresoraNormal fachada2= new FachadaImpresoraNormal("texto2");
    fachada2.imprimir();

    // sin fachada
    Impresora i3 = new Impresora();
    i3.setHoja("a4");
    i3.setColor(true);
    i3.setTipoDocumento("excel");
    i3.setTexto("texto 3");
    i3.imprimirDocumento();
    }
}
```

### Proxy

>  proporcionar un intermediario de un objeto para controlar su acceso. 

**ejemplos uso común**

-  Proxys de acceso a internet

**partes**

-  La clase **Sujeto**: define una interfaz común para el *proxy* (`Proxy`) y el objeto real (de la clase `SujetoReal`), de tal modo que se puedan usar de manera indistinta.
-  La clase **SujetoReal**: clase del objeto real que el *proxy* representa. 

### Bridge ***

### Decoractor

>  El [patrón](Patrón_de_diseño.html) **Decorator** responde a la necesidad de añadir dinámicamente funcionalidad a un  Objeto. Esto nos permite no tener que crear sucesivas clases que hereden de la primera incorporando la nueva funcionalidad, sino otras que la  implementan y se asocian a la primera. 

**ejemplo común**

Un ejemplo para poder ver la aplicabilidad del patrón decorador podría ser el siguiente:

- Disponemos de una herramienta para crear  interfaces gráﬁcas, que permite añadir funcionalidades como bordes o  barras de desplazamiento a cualquier componente de la interfaz.
- Una posible solución sería utilizar la herencia para  extender las responsabilidades de la clase. Si optamos por esta  solución, estaríamos haciendo un diseño inflexible (estático), ya que el cliente no puede controlar cuándo y cómo decorar el componente con esa  propiedad.
- La solución está en encapsular dentro de otro objeto,  llamado Decorador, las nuevas responsabilidades. El decorador redirige  las peticiones al componente y, además, puede realizar acciones  adicionales antes y después de la redirección. De este modo, se pueden  añadir decoradores con cualidades añadidas recursivamente.

**participantes**

-  **Componente** Deﬁne la interfaz para los objetos que pueden tener responsabilidades añadidas.

- **Componente Concreto** Deﬁne un objeto al cual se le pueden agregar responsabilidades adicionales.

- **Decorador** Mantiene una referencia al componente asociado. Implementa  la interfaz de la superclase Componente delegando en el componente  asociado.

- **Decorador Concreto** Añade responsabilidades al componente.

**ejmplo**

```java
public abstract class Componente{
    abstract public void operacion();
 }
 
 public class ComponenteConcreto extends Componente{
    public void operacion(){
        System.out.println("ComponenteConcreto.operacion()");
    }
 }
 
 public abstract class Decorador extends Componente{
    private Componente _componente;
 
        public Decorador(Componente componente){
                _componente = componente;
        }
 
        public void operacion(){
                _componente.operacion();
        }
 }
 
 public class DecoradorConcretoA extends Decorador{
        private String _propiedadAñadida;
 
        public DecoradorConcretoA(Componente componente){
            super(componente);
        }
 
        public void operacion(){
                super.operacion();
                _propiedadAñadida = "Nueva propiedad";
                System.out.println("DecoradorConcretoA.operacion()");
        }
 }
 
 public class DecoradorConcretoB extends Decorador{
    public DecoradorConcretoB(Componente componente){
        super(componente);
    }
 
        public void operacion(){
                super.operacion();
                comportamientoAñadido();
                System.out.println("DecoradorConcretoB.operacion()");
        }
 
        public void comportamientoAñadido(){
                System.out.println("Comportamiento B añadido");
        }
 }
 
 public class Cliente{
        public static void main(String[] args){
                ComponenteConcreto c = new ComponenteConcreto();
                DecoradorConcretoA d1 = new DecoradorConcretoA(c);
                DecoradorConcretoB d2 = new DecoradorConcretoB(d1);
                d2.operacion();
        }
 }
```

## Comportamiento

>Comportamiento del sistema

 

###  Memento

> es un [patrón de diseño](Patrón_de_diseño.html) cuya finalidad es almacenar el estado de un objeto (o del sistema  completo) en un momento dado de manera que se pueda restaurar en ese  punto de manera sencilla. Para ello se mantiene almacenado el estado del objeto para un instante  de tiempo en una clase independiente de aquella a la que pertenece el  objeto 

**ejemplo común**

- Se usa este patrón cuando se quiere poder restaurar el sistema desde  estados pasados y por otra  parte, es usado cuando se desea facilitar el hacer y deshacer de determinadas operaciones 

**participantes**

-  **Originator** (Originador) es una clase que cambia de estado
-  **Caretaker** (Portero) se encarga de registrar los cambios del Originador
- **Memento** (Recuerdo) guarda el objeto a salvaguardar. 

**ejemplo**

```java
class Memento 
{
    private String state; 
    public Memento(String stateToSave)
    {
        state = stateToSave; 
    }
    public String getSavedState()
    {
        return state; 
    }
}
 
class Originator 
{
    private String state;
    /* lots of memory consumptive private data that is not necessary to define the 
     * state and should thus not be saved. Hence the small memento object. */

    public void set(String state) 
    { 
       System.out.println("Originator: Setting state to "+state);
       this.state = state; 
    }

    public Memento saveToMemento() 
    { 
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state); 
    }

    public void restoreFromMemento(Memento m) 
    {
        state = m.getSavedState(); 
        System.out.println("Originator: State after restoring from Memento: "+state);
    }
}   
 
class Caretaker {
   private ArrayList<Memento> savedStates = new ArrayList<Memento>();
 
   public void addMemento(Memento m) { savedStates.add(m); }
   public Memento getMemento(int index) { return savedStates.get(index); }
}   
 
class MementoExample {
   public static void main(String[] args) {
       Caretaker caretaker = new Caretaker();

       Originator originator = new Originator();
       originator.set("State1");
       originator.set("State2");
       caretaker.addMemento( originator.saveToMemento() );
       originator.set("State3");
       caretaker.addMemento( originator.saveToMemento() );
       originator.set("State4");

       originator.restoreFromMemento( caretaker.getMemento(1) );
   }
}

/**
 * Memento pattern: Copy the information into a another class for recovery in the future if necessary
 * @author Asenior
 *
 */

public class MementoPattern {
	public void main(String args[]){
		RegularClass regularClass = new RegularClass();
		regularClass.setData("First Report");
		System.out.println(regularClass.data);
		regularClass.makeBackup();
		regularClass.setData("Second Report");
		System.out.println(regularClass.data);
		regularClass.recoverBackup();
		System.out.println(regularClass.data);
	}
	
	public class Memento{
		public String memoryData;
		
		public Memento(String data){
			this.memoryData=data;
		}
		
		public String recoverOldInformation(){
			return memoryData;
		}
	}
	
	public class RegularClass{
		Memento memento;
		String data;
		public RegularClass(){
			
		}
		public void setData(String data){
			this.data = data;
		}
		public void makeBackup(){
			 memento = new Memento(data);
		}
		public void recoverBackup(){
			data = memento.recoverOldInformation();
		}
	}
}

```

