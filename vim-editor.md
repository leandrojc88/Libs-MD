# Comando

- `vim` - para entrar
- `:help` - ayuda

## Modos de trabajo

**Normal**

- modo normal - la barra inferrior no muestra nada, el nombre ,lineas y caracteres
- `:q` - salir del editor
- `:q!` - sale sin guardar
- `:w` - guardar
- `:wq` - guardar y salir
- `ESC` - retorna modo normal
- `:edit file.txt` - para abrir el archivo desde vim
- `k` - desplazar arriba
- `j` - desplazar abajo
- `l` - derecha
- `h` - izquierda
- `f + letra` - buscar siguiente en la misma linea
- `0` -(cero) inicio de la línea
- `$` - final linea
- `SPACE` - se desplaza de caracter a caracter
- `w` - salta al inicio de la palabra siguiente
- `b` - salta al inicio de la palabra anterior
- `e` - salta al final de la palabra siguiente
- `un# + [comando]` - repite # de veces el valor del comando ej: `10 + j`   baja 10 lineas
- `*` - te busca la siguiente palabra donde estas ubicado
- `%` - salta entre el **(** abierto y el **)** cerado
- `gg` - (dos veces) se va al inicio
- `G` - (G mayúscula) va al final
- `# + G` va al # de la línea
- `x` - borra la letra donde esta el cursor
- `X` - borra la letra de la izquierda
- `r` - remplaza una letra por la letra q deseas cambiar 
- `d + w` - (ctrl+x) corta, borra toda desde la ubicación de la palabra donde estés hasta la siguiente 
- `dd` - la elimina, corta la línea (ctrl+x) 
- `p` - pega la línea debajo de la ubicación 
- `yy` - copiar toda la línea
- `# + yy` - copia el # de líneas desde la línea actual
- `.` - repite el ultimo comando realizado
- `u` - deshacer (ctrl+z)

### Insertar

- `i` - para este modo y en la barra aparecera `--INSERT--` 
- `o` - modo insert y crea una nueva linea al final
- `O` - (mayuscula) modo insert una linea antes de donde estaba
- `ESC` - retorna modo normal

### visual

- `v` - entra al modo vidual, modo que permite hacer selecciones como si fuera el mouse y acciones con las teclas del modo normal

### buscar y remplasar

- `/{palabra}` - busca {palabra} la primera coincidencia

- `n` - va a la siguiente palabra

- `N` - a la palabra anterior

  **--REGEX--**

- `o$` - expresion regular para linea que terminar letra o 

- `:%s/{palabra_buscar}/{palabra_sustituye}/g`  - busca todas las palabras palabra_buscar y las sustituye con palabra_sustituye `/g` para todo el documento sino solo lo hace en la línea actual