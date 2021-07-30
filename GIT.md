
# GIT

## **** buscar gitkraken y sourcetree

## Configuracion
----------
### alcance (system, global, local)
- `git config --system user.name 'leandro'`  
configuracion para todo el sistema *(no recomendada )*

- `git config --global user.name 'leandro'`  
configuracion de forma global con nombre 'Leandro' 

- `git config --local user.name 'leandro'`  
configuracion para distintos repocitorios de forma local *es necesario estar dentro de la ruta del repositorio*

- `git config --local color.ui true` da nueva gama de colores a la consola de Git

### propiedades

- nombre: `git config --global user.name 'leandro'`  
- correo: `git config --global user.email leandrojc88@gmail.com` 
- editor: `git config --global core.editor vim`  

### listar las config
`git config --list`
		
		user.name leandro
		user.emal leandrojc88@gmail.com
		core.editor vim

### .gitignore  
configurar dentro de .git ignore todos los ficheros que no se van a subir al **repo**, usar (www.gitignore.io)[https://www.gitignore.io]

## Start
----------

	 Sistema de control de Versiones
	 Repositorio centralizador de codigo
	 Versiones = cambios en el proyecto
`git` muestra toda la ayuda

### Beneficios
- Historia cambios
- trabajo colaborativo
- Ayuda en conflictos

### Iniciar reprositorio
`git init`

### Estado del proyecto  
`git status`

### Terminal

- `cd folder/folder` moverce por las carpetas ../ retorna en carpeta
- `mkdir [nombre-folder]` crea carpetas
- `ls` ver el contenido de la carpeta `-la` muestra los ocultos
- `clear` limpia la terminal
- `touch [file]` crea archivo
- `rm [file]` elimina archivo
- `rm -rf [dolder]` elimina carpetas
- `vim [file]` crea o edita archivo y permite editarlo con **vim**
  - i - modo de edicion
  - Esc - salir modo edicion
  - :q - cerrar
  - :wq - salvar y cerrar


## 3 stages de Git (Flujo trabajo)
----------

- **Working:** (rojo) donde se editan los ficheros en la pc
- **Staging:** (verde) estado intermedio, donde se administran archivos q paranar a un punto en la linea de tiempo
- **Repo:** donde esta todo el historia de cambios del proyecto

![3-Stages](Git/stages.png)

- `git status` muestra el estados de los archivos (Working, Starging) 
  
	![Estado de archivos](Git/status.png)

- `git add [archivo]` mueve de los archivos de ***Working***  al estado ***Starging***

	- `git add .` add todos los ficheros dentro del directorio q este ubicado
  
```
	git add Ejemplo.txt
```

- `git commit -m` mueve de los archivos de ***Starging***  al estado ***Repo***, *crea un punto en la linea de tiempo*, `-m` es el mensaje del commit
  
```
	git commit -m 'ejemplo de mensaje de git'
```
- `git stash` guarda estado transitorio de los cambios, forma de guardar los cambios sin hacer **commit** tiene q estas **Staging** (add o modificado)

	- `git stash` guarda estado
	- `git stash list` muestra la lista de los stash
	- `git stash drop stash@{1}` para eliminar el stash 1
	- `git stash apply [stash@{3}]` aplica el stash 3 sino se especifica aplica el ultimo stash {0}

- `git cherry-pick [id-commit]` mueve un commit de una rama a la rama actual

- `git checkout [archivo]` retorna el archivo a su estado en el ultimo punto en la linea de tiempo: si se modifica algo.js y se quiere deshacer los cambios se usa este comando

### Etiquetas  
`git tag` etiqueta el estado actual en el repo o un commit
- `git tag 0.5` etuiquetado corto de version
- `git tag -a 0.5 -m 'version estable pel proyecto'`  
  etiquetado con descripcion
- `git tag 0.3 8db33310839002fc...` etiquetando algun comit como 0.3
- `git tag -l`  lista de etiquetas
- `git tag -d 1.0` borra la etiqueta 1.0
- `git tag -f -a 0.1 -m 'renombrando' 8db33310839002fc...` renombrando el tag

## Historial del Repo
---

### git-log

- git log` muestra todo el historial

	![log](Git/log.png)

- `git log -p` la bandera -p muestra todos los detalles de los commit como *parches ( cambios, arreglos, modificacion, correccion)*

	- ejemplo del segundo commit del `git log`
  
	![log](Git/log-p.png)

	- ejemplo 2

   	![log](Git/log-p2.png)
	 	
   	  - WELCOME !!! : la linea eliminada (rojo)
   	  + Nueva linea ... : linea nueva (verde)

- `git log -p -#` muestra los ultimos # de commit 
  
  muestra los ultimos 3 de commit 

  		git log -p -3:
	
- `git log --stat`: muestra una estadistica de todos los archivos (+,-)

	![stat](Git/log--stat.png)

### combinacion 

- `git log --stat -p -#`

- `git log --pretty=format:"%h - %an : %s"`

	retorna
	95c104c - Leandro : Agregar una nueva linea en archivo.txt
	0f1143c - Leandro : se creo archivo.txt
	12f5sd3 - Leandro : se inicio el proyecto

- `git log --oneline`

	retorna
	95c104c Agregar una nueva linea en archivo.txt
	0f1143c se creo archivo.txt
	12f5sd3 se inicio el proyecto

- `git log --graph --decorate`  
 muestra las ramas en ASCII y el nombre de las mismas

![stat](Git/graph-decorate.png)

- `git log --graph --decorate --all --oneline`  	 

![stat](Git/graph-decorate-all.png)

muestra estdisticas y parches de los #

### Banderas

![log-flags](Git/log-flags.png)

git log --pretty=format: 

![log-flags](Git/log-combinaciones.png)

### Diferencias 

`git diff [id-commit]` compara commit actual vs [id-commit]
`git diff [id-commit1] [id-commit2]` compara [id-commit1] vs [id-commit2] 

### Reset
para modificar y descaptar algún commit

- `git reset HEAD file.txt` remueve del Starging el fichero file.txt y lo pasa a Working

- Soft  
 `git reset --soft [id-commit]` resetea al id-commit pero mantiene los ficheros en el Starging listos para hacerles nuevamente commit, el id-commit debe ser al que vamos posicionarnos y de hay en adelante se eliminaran los cambios

 ```
 b7164ba5 (HEAD -> master) release v1.1.0
 74071953 update for v1.1.0
 d222f047 (updates, dev) fixed resueltos
 7244a61f Store Modularizardo, encapsulamiento dentro de {make}
 14c28681 Atualizacion de la estructura de VST y incorporacion de Unidades
 7561525f install vuelidate and create .bats
 0de59a20 Registro de Componenetes *Base* en la instancia de Vue
 039394dc mejora - comportamiento de las Rutas Anidadas 
 7488251d start developement v1.0.1
 ```

 `git reset --soft d222f047` => nos lleva a la linea de tiempo en el commit(d222f047 (updates, dev) fixed resueltos) y eliminar el resto de commit por encima (b7164ba5, 74071953)

- mixed
	`git reset --mixed [id-commit]`	resetea al id-commit y pone los ficheros en el Working hay que `add` y despues commit
	
- hard 
  `git reset --hars [id-commit]` resetea al id-commit y se elimina todo lo q sea posterior al mismo

- reflog

  `git reflog` muestra un historial incluyendo los puntos eliminados con el reset



## Moverse por los commits
---
- `git checkout [id-commit]` 
  retorna el proyecto al estado del `commit` seleccionado
  
		git checkout 0f1143c -> mueve a este commit en el tiempo
		git checkout master -> retorna a estado actual


## Ramas
---

![ramas](Git/branch.png)

- crear ramas  
`git branch nombre-rama` para crear rama  
`git checkout -b nomber-rama` y navega hasta la rama

- renombrar ramas  
`git branch -m old_name new_name`

- moverse de rama  
`git checkout nomber-rama`

- listado de ramas  
  `git branch`  

  
		* master          ->       la q se esta utilizando
		  desarrollo
		  feature1 
- unir ramas  
 `git merge nombre-rama`  
  ubicarce en la rama que va a alar la otra rama 
  
  - ejemplo: para unir la rama *feature1* a *desarrollo* debemos ubicarnos en **desarrollo** y hacer `merge` a feature 1

```git
	git checkout desarrollo
	git merge feature1
```

- borrar ramas  
  `git branch -d nobre-rama` -D fuerza a borrar la rama

- conflicto al hacer `marge`
  
	- se soluciona el conflicto manualmente borrando *(<<<<<<< ======== >>>>>>>)*
	- se hace el add y commit
	- y mech final de ser deseado

```javascript
conflicto en fichero usuarios.js
	
	<<<<<<<<<<< HEAD
	let nombre = 'Leandro'
	=============
	let nombre = 'Antonio'
	>>>>>>>>>>> feature1
```


# **Github**

## Proxy Config
  >`git config --global http.proxy http://proxyuser:proxypwd@proxy.server.com:8080`

Where `proxyuser` and `proxypwd` are your credentials, and `proxy.server.com:8080` is the server name and `port` you connect to.

### JovenClub config
>`git config --global http.proxy http://leandro.garcia:Leovy880708.@proxy.pri.jovenclub.cu:3128`

---
### work-flow

- `git clone ` clona y crear un repositorio en local de algun repo online como (hithub)

		git clone git@github.com:leandrojc88/SwitchLabel.git

- `git remote` enlaza **repo** remoto al local 
  	
	- `git remote add origin https://github.com/leandrojc88/SwitchLabel` enlaza

	- `git remote -v` muestra q se enlazo el repo
	- `git remote remove origin` para eliminar el enlace del repo origin del local

- `git fetch` (no usar) descarga los ficheros del repo remoto(origin) al local asignandole la rama q va a traer(master)

		git fetch origin master
		git merge origin/master --allow-unrelased-histories

- `git pull` sincroniza con el proyecto en github, descarga lo q esta en github, actualizar del repo **origin** su rama ***master***

	```git
	git pull origin master
	git pull origin master --allow-unrelated-histories
	```
	
- `git push` sube los cambios al server remoto

	- `git push origin master` envia la rama master
	- `git push origin master --tags` envia los tags de la rama al servidor

- crear llave ssh  
  -  `ssh-keygen -t rsa -b 4090 -C "leandrojc88@gmail.com"` poner el email de Github darle doble enter
  -  C:\Users\username\ .ssh\ aprarecera el id_rsa y id_rsa.pub 
  -  `tail cd id_rsa.pub` y copiar todo el hash q retorne
			
		
	ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDDnVhMlwsFWKxbpoX42f7bUXOzUppkhYQF8+aFdjhz2cc2yBPF68g+ZucjCaIU7oQnteSCXqnUgqh0Wy7Bg22/3PenkWwzZr+pjrMHtc/toWnUgA2q24m3hyGW7ANe0OTtOPLP80PLEoXsBCb34mb9WREemicUVX4sAXBurzaXdN/ipRUuO5qKR+5+6TgJkBLf6PaDFwmUVNlSbxbFhIAwxMapt1EE0Ms59D0CcwroCj1GMuFOSLHdV9ocIrUzzzy8DpIu6vM6CSRzPdxKNRe4E7ZrW7KkMrZOVKmG97B9t768v18O+iLcglCA09B7KyHDk0UV9Kz3rjx1y1PX8HID LEO@DESKTOP-C5E2ESP
  	
  - copiar el hash y pegarlo en Github / settings / SSH and GpG keys / crerar nuevos SSH una por cada pcs

 ## Interfas
---

### Fork  

crea una copia de un repo dentro de tus repos

- Branch protection for master **bueno para el trabajo en equipo**
  
- `request pull` request review before marging 
  

### Projects
`recomendable` crear 1 proyecto por cada caracteristica grande, uso para organizacion como Trello

### Issues  

para reportar los bugs y sugerencias del proyecto como blog comunicarte como red social

**crear issue_template**

- priemro `create new file`
- nombre tiene q ser `issue_template.md`
- crear la esctructura del issue_template y guardarlo

### milestones

es como se **agrupar** los issues y los pull request

### Pull Request  

para enviar los solicitar q se acepten los camios 

**crear pull_request_template**

- priemro `create new file`
- nombre tiene q ser `pull_request_template.md`
- crear la esctructura del pull_request_template y guardarlo
![ramas](Git/pull-request-template.png)

> `[ ] Frontend... significa checkbox markdown`

## GITLAB

#### error de credenciales en windows

Try below solution which works for me:

1. Go to Windows **Credential Manager**. This is done in a EN-US Windows by pressing the Windows Key and typing 'credential'. In other localized Windows variants you need to use the localized term (See comments for some examples).

   *alternatively* you can use the shortcut `control /name Microsoft.CredentialManager` in the run dialog (WIN+R)

2. Edit the git entry under Windows Credentials, replacing old password with the new one.

## Pages  

host gratuitos solo para paguinas estaticas `Setting/GitHub Pages`
- seleccionar la rama (master) o la q se designe
- CNAME es para enlazar con dominios externos