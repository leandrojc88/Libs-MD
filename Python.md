## Variables & Tipos Datos

no tienen un tipo especifico `a=1` , `a='hola'` 

### string

```python
a = 'Leo'
a1 = "2"
a2 = """hola 3"""

print('tengo {} años y me llamao {}'.format(a1,a))
```

### flotante(float)

```python
b = 12.35
```

> no existen las constantes

### type hint

indica el tipo de dato que espera algún parámetro de una función y el que debe ser retornado

`def metodo(a:int) -> int:`  este método recibe un int y retorna un int

### Casting

`a = "12"`

`int(a)` 

### Boolean

se definen el mayúscula `x = True` `y = False`

### Comentarios

`#` - una linea

`'''` - multilinea

help(function | class | modules) - retorna el comentario como ayuda

```python
class Area:
    """ descripcion del la clase """

    def loguitud():
        """ descripcion del metodo """
        pass
    
    def otro_metodo():
        """ descripcion metodo """

help(Area)
```



### Tuplas (val,val)

son los datos que guardan otras informaciones mas complejas, es un conjunto, definidos por () y dentro (,) , **No puede cambiar de valor** 

```python
tuplas = (1,2,3)
tupla2 = ("leandro", "camilo","otro")
tupla3 = (1, 'leo',True, 10.5)

tuplas[0]
```

retornar bloques de datos

```python
def definir_datos():
    nombre = "Leandro"
    ci = 88070800280
    ciudad = "Pinar del Rio"
    return (nombre,ci,ciudad)
    
def retornar_datos(datos):
    print(datos[0])
    print(datos[1])
    print(datos[2])
    
retornar_datos(definir_datos())
```

### Listas [val,val]

pueden ser modificables

```python
lista = [1,2,3]
lista2 = ["leandro", "camilo","otro"]
lista3 = [1, 'leo',True, 10.5]

lista[0] = 5
```

**Métodos**

lista.append() | lista.delete() | lista[poss] = elemento | lista.pop()

### Diccionarios {clave:val}

son elementos de `clave: valor` 

```python
dicc = {'nombre': 'leandro', 'edad': 31}
autor = {'nombre' : 'leo'}
print(dicc[nombre]) # retorna leandro
print(dicc.values) # leandro, 31
print(dicc.keys) # nombre, edad
```

**Métodos**

dicc.update(autor)   = actualiza los campos de dicc con el de autor

### Funciones

importar

1. `import random` - importa todo el codigo de la libreria random
2. `from random import sqr` - importa sqr de la libreria random

## Estructura de Control

### if / elif /else

```python
if leo > 20 and True or False:
	print('Leo')
elif leo = 25:
	print('Leo 25')
else
	print('no leo')
```

### while

```python
while (x > 0)
	print('x mas y')
```

### for

```python
x = 1
for x in range(1, 10, 2): #1=inicio, 10=fin, 2=pasos
    print(x)

palabras = "Leandro"
for letra in palabras:
    print(letra)   
```

### continue, pass, else

continue: Salta a la próxima vuelta de bucle

pass: retorna `null` en la vuelta de bucle

else: el mismo objetivo que en un condicional

### funciones

```python
def new_funcion(param):
    print(param)
    return param
```

### Generadores

funciones que no utilizan la instrucción `return` en cambia usan `yield`, construyendo un objeto generador iterable en el cual se va almacenado los valores de uno en uno según se ejecute la función generadora y permanece en stand by hasta la próxima ejecución pasando el control del flujo al algoritmo donde se realizo la llamada

```python
def generador_func(param):
    num = 1
    while num < param:
        yield num

value_gener = generador_func(20)
print(next(value_gener))
print('codigo aqui')
print(next(value_gener))
print('codigo aqui')
print(next(value_gener))
```

**yield from** para el caso de utilizar ciclos anidados en el generador

```python
def devuelve_city(*citys):
    for city in citys:
        yield from city #for anidado simplificado
        
variable = devuelve_city("Madrid","Barcelona")
print(next(variable)) # improme M
print(next(variable)) # improme a
print(next(variable)) # improme d
```



## POO

en Python ***todo*** es un objeto

 los ***métodos*** no tienen que retornar un valor si modificar propiedades,

las ***funciones*** retornar siempre un valor

### Clases, Metodos

```python
class Persona:
    nombre = ""
    edad = 0
    pais = ""
    
    def saludar(self):
        print("hola",self.nombre)

leo = Persona()
leo.saludar()
```

`self` - hace referencia al mismo objeto

#### encapsular

**encapsular variables ** `self.__varname` : encapsula `varnema` para que solo sea accesible desde dentro de la clase

**encapsular  métodos**  `def __metodo_encapsulado(self, param)` para evitar que sea accesible desde el exterior de la clase

### Constructor

```python
def ___init___(self, nombre, edad, pais):
    self.nombre = nombre
    self.edad =edad
    self.pais = pais
```

### Herencia

soporta herencia múltiple, toma como constructor inicial el de la Izquierda

```python
class Estudiante(Persona, Empleado):   #herencia multiple
    escuela = ""
```

#### super(),  isInstance()

**super()** : hace referencia a la instancia del padre

```python
class Empleado(Persona):
    def __init__(self, salario, antiguedad):
        super().__init__("antonio", 55)
```

**isinstance() : ** principio de sustitución una clase hija es siempre tipo de una clase padre

```python
manuel = Empleado(1500, 5)
isinstance(manuel, Empleado) # True
isinstance(manuel, Persona) # True - principio de sustitución

manuel = Persona("manuel", 50)
isinstance(manuel, Empleado) # False
```

### Polimorfismo

muchas formas en un mismo objeto

```python
class moto:
    def desplazamiento(self):
        print("desplazo en 2 ruedas")
class coche:
    def desplazamiento(self):
        print("desplazo en 4 ruedas")
        
def desplazamiento_vehiculo(vehiculo): # polimorfismo no se conoce el tipo
    vehiculo.desplazamiento()

car = coche()
desplazamiento_vehiculo(car)
```

## Módulos

archivos con extensión .py o .pyc, posen su propio espacio de nombre, con variables, funciones o otros módulos dentro del mismo, utilidad Organizar y Reutilizar el código

importar matematicas.py

```python
import matematicas
matematicas.suma(1, 2)
matematicas.resta(1, 2)

from matematicas import suma
suma(1, 2)

from matematicas import *
suma(1, 2)
resta(1, 2)
```

## Paquetes

directorios o carpetas donde se almacenan los módulos relacionados entre si, para organizar y reutilizar módulos, para crearlo se crea una carpeta con un archivo en su interior **`__init__.py`**  

:file_folder: calculos
​	:file_cabinet: `__init__.py`
​	:file_cabinet: calculos_generales.py
​	:file_folder: basico
​		:file_cabinet: `__init__.py`
​		:file_cabinet: redondeo.py

```python
from calculos.calculos_generales import sumar
from calculos.basico.redondeo import redondear
```

### redistribuibles

**setup.py** - información del paquete a distribuir

```python
from setuptools import setup

setup(
    name="paquetecalculos",
    version="1.0",
    description="ejemplo de paquete",
    author="Leandro",
    author_email="leandrojc88@gmail.com",
    packages=["calculos", "calculos.redondeo"]
)
```

**instalar** 

1. ubicarse en la consola en la dirección del archivo **setup.py** y ejecutar: `python setup.py sdist` , crea las carpetas **paquetecalculos.egg-info** y **dist** 

2. el directorio **dist/paquetecalculos-1.0.tar.gz** es el paquete para distribuir e instalar

3. ubicándose en el directorio **disc** podemos instalar el paquete en la pc 

   `pip3 install paquetecalculos-1.0.tar.gz`

4. para desinstalar `pip3 install paquetecalculos`

## Funciones Lambda

es una función anónima que se utilizan para abreviar, de una sola línea como las `arrow function de js` 

se definen : `lambda param1, paramn: operacion_a_retornar`

```python
area = lambda base, altura: base * altura / 2
al_cubo = lambda numero: numero**3
destacar = lambda valor:'!{}!'.format(valor)
```

## Funciones de Orden superior

permiten utilizar el paradigma de programación funcional

### filter()

verifica que los elementos de la lista cumplen una condición y retorna un iterador

```python
# ejemplo con labmda y filter
numero = [2,4,6,8,10,12,14,16,18]
# funcion list() lista el resultado de filter
print(list(filter(lambda numero_par: numero_par % 2 == 0, numero )))

#ejemplo con objetos
salario_altos = filter(lambda empleado: empleado.salario > 5000, list_empleados)
for salario in salario_altos:
    print(salario)
```

### map()

aplica una función a cada elemento de la lista iterable, y retorna la nueva lista

```python
def calculo_comision(empleado):
    empleado.salario *= 1.03
    return empleado
lista_comision = map(calculo_comision, list_empleados)
for comision in lista_comision:
    print(comision)
```

## Funciones Decoradores

funciones que añaden funcionalidades a otras funciones, decoran otras funciones

**estructura** funciones(A,B y C) donde A recibe como parámetro a B y retorna C, un decorador retorna una función 

```python
def func_decoradora(func_param):    
    def func_interna(*args, **kwargs):
        print("decorando antes")     
        
        #funcion que se esta decorando,
        # *args -> multiples params, **kwargs -> multiples paramas con clave="valor"
        func_param(*args, **kwargs) 
        
        print("decorando despues")
    return func_interna

@func_decoradora     #asi se le aplica la funcion decoradora a suma
def suma(num1, num2):
    print(num1 + num2)
   
@func_decoradora      #asi se le aplica la funcion decoradora a resta
def resta(num1, num2):
    print(num1 - num2)

@func_decoradora   #asi se le aplica la funcion
def multiplicar(num1, num2):
    print(5 * 6)
    
suma(1, 2)
resta(3, 1)
multiplicar(num1=1, num2=6) # utilizando clave=valor,  **kwargs
```

## Escritura y Lectura de Ficheros

### modos

lectura y escritura `archivo = open('datos.txt', 'w')`

 `w` - modo de escritura si no existe lo crea

 `r` - modo lectura

 `a` - modo append para ir agregando contenido al archivo

`archivo.close()` para cerrar

### escribir y leer

```python
# Escribir
archivo = open('datos.txt','a') #modifica contenido
archivo.write('leandro\n')
archivo.write('Jose')

#Leer
archivo = open('datos.txt','r')
lineas = archivo.readline()
while linea != "":
    print(linea)
    linea = archivo.readline()
archivo.close()
```

### XML

```python
from xml.etree.ElementTree import parse
doc = parse('archivo.xml')
for elemento in doc.fineall('nombre'):
    print(elemento.text)
```

### JSON 

```python
import json
with open('archivo.json') as file:
    datos = json.load(file)
```

### CSV

> Los archivos **CSV** (del inglés *comma-separated values*) son un tipo de documento en [formato abierto](Formato_abierto.html) sencillo para representar datos en forma de tabla, en las que las  columnas se separan por comas (o punto y coma en donde la coma es el [separador decimal](Separador_decimal.html): Argentina, España, Brasil...) y las filas por saltos de línea. 

```python
import csv

#Escribir
csv_doc = open('archivo.csv','w')
csv_data = csv.writer(csv_doc)
listas = [['landro',880708],['julio',95471],['pepe',546892]]
for elemento in listas:
    csv_data.writerow(elemento)
csv_doc.close()

#LEER
doc = open('archivo.csv','r')
documento = csv.reader(doc)
for (nombre,numero) in documento:
    print(nombre) #imprime el nombre leido
    print(numero) #imprime el numero leido
doc.close()
```

## Hilos

permite el paralelismo hasta cierto punto, su objetivo es realizar un bloque de tareas, TODO tiene el hilo principal que es el #0 `hilo 0` , es el encargado de todas las actualizaciones de las interfaces graficas UI, los hilos se clonan y mandan hacer otros procesos como hilos secundarios, se cierran automáticamente cuando terminan su función 

```python
import threading
import time

class Hilo(threading.Thread):
    def run(self):
        print("Iniciando hilo - {}".format(self.getName()))
        time.sleep(1)
        print("terminado - {}".format(self.getName()))

if __name__ == "__main__":
    for x in range(9):
        hilo = Hilo(name="Hilo - {}".format(x+1))
        hilo.start()
        time.sleep(.5)
```

## Excepciones 

es un error en tiempo de ejecución y la forma de manejarlas mediante una captura de excepciones 

**try / except ** bloque para tratar las excepciones 

**finally** dentro esta el código que se va a ejecutar siempre ocurra una excepción o no

```python
try:
	return num1 / num2
except ZeroDivisionError:
    return "no se puede dividir entre cero"
except:
    return "ocurrio un error"
finally:
    print("mostrar siempre")
```

**lanzar excepciones** 

`raise ClassException("custom description")` para lanzar excepciones

```python
def calcular(num):
    if num < 0:
        raise ValueError("el # no puede ser negativo")
    else:
        return num
    
try:
	valor = calcular(-6)
except ValueError as valorNegativo:
    print(valorNegativo)
```



## Expresiones Regulares (regex)

>  es una secuencia de [caracteres](Carácter_(tipo_de_dato).html) que forma un patrón de búsqueda, principalmente utilizada para la [búsqueda de patrones](Búsqueda_de_patrones.html) de cadenas de caracteres u operaciones de sustituciones. En informática, las expresiones regulares proporcionan una manera muy flexible de buscar o reconocer cadenas de texto.  

```python
import re

#busca r-caracter por caracter la letra "g" en la (palabra)
print(re.search(r"g","paranguaticurijarou"))
# Resultado - <re.Match object; span=(5, 6), match='g'>

#busca por un patron de encontrar un [\d\d] -> (# de dos digitos)
patron = re.compile("\d\d")
print(patron.search("parangqwdqwdwuati12curijarqwdqwdqwdqwdou"))
#Resultado - <re.Match object; span=(17, 19), match='12'>

#buscar una letra minuscula[a-z] seguida de #[0-9], seguido de (.)punto, despues lo que sea (*) y sea fin de linea ($)
if re.search("[a-z][0-9].*$"," asdadqwdqwd asdqwd asdadw a1dfr"):
    print("encontrado!!!")

#sutituir un elemento con otro
print(re.sub(r"\d","-","asdfasdwevwewv8dwdwdw9dwd1d"))
#retorna - asdfasdwevwewv-dwdwdw-dwd-d
```

## Test - doctest

un tipo de pruebas unitarias de python, comienzan con >>> dentro de los comentarios de las funciones y esperan un resultado en el salto de línea siguiente 

```python
def areaTringulo(base, altura):
    """
    Calcula el area del trinagulo
    
    >>>areaTringulo(3, 9)
    'El area del triangulo es: 9.0'
    
    >>>areaTringulo(4, 5)
    'El area del triangulo es: 10.0'
    
    """
    return 'El area del triangulo es: '+ str(base * altura / 2)
import doctest
doctest.testmod()
```

**verificar correo**

```python
def verificarEmail(userEmail):
    """ 
    Verifica el email contenga una `@`, que no este al final y no contenga mas de una

    >>> verificarEmail('leandrojc@gmail.com')
    True

    >>> verificarEmail('leandrojcgmail.com')
    False

    >>> verificarEmail('leandrojcgmail.com@')
    False
    """
    arroba = userEmail.count('@') 
    if arroba != 1 or userEmail.rfind('@') == len(userEmail)-1:
        return False
    else:
        return True

import doctest
doctest.testmod()
```

**avanzado** 

utilización del **...** para identacion e ignorar código intermedio 

```python
import math
def raizCuadrada(listaNumero):
    """ 
    devuelve la raiz cuadrade de una lista en otra nueva

    >>> lista=[]
    >>> for i in [4, 9, 16]:
    ...     lista.append(i)
    >>> raizCuadrada(lista)
    [2.0, 3.0, 4.0]

    en el caso de un valor negativo
    >>> lista=[]
    >>> for i in [4, -9, 16]:
    ...     lista.append(i)
    >>> raizCuadrada(lista)
    Traceback (most recent call last):
        ...
    ValueError: math domain error
    
    """
    return [math.sqrt(n) for n in listaNumero]

import doctest
doctest.testmod()
```

## Generar Ejecutable

