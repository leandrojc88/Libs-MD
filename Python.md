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
lista2 = ("leandro", "camilo","otro")
lista3 = (1, 'leo',True, 10.5)

lista[0] = 5
```

Métodos

lista.append() | lista.delete() | lista[poss] = elemento | lista.pop()

### Diccionarios {clave:val}

son elementos de `clave: valor` 

```python
dicc = {'nombre': 'leandro', 'edad': 31}

print(dicc[nombre]) # retorna leandro
print(dicc.values) # leandro, 31
print(dicc.keys) # nombre, edad
```

### Funciones

importar

1. `import random` - importa todo el codigo de la libreria random
2. `from random import sqr` - importa sqr de la libreria random

## POO

en python todo es un objeto los **metodos** no tienen que retornar un valor si modificar propiedades, **funciones** retornar un valor

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

### Constructor

```python
def ___init___(self, nombre, edad, pais):
    self.nombre = nombre
    self.edad =edad
    self.pais = pais
```

### Herencia

soporta herencia multiple, toma como constructor inicial el de la Izquierda

```python
class Estudiante(Persona, Empleado):   #herencia multiple
    escuela = ""
```

## Escritura y Lectura de Ficheros

### modos

lectura y escritura `archivo = open('datos.txt', 'w')` `w` - modo de escritura si no existe lo crea `r` - modo lectura `a` - modo append para ir agregando contenido al archivo

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

## Excepciones (regex)

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

