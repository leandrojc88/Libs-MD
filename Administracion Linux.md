# Admin Server Linux

## Tips - notas

***UTIL: -- Instalar vim y zsh--***

- zsh - es un plugin que te pinta en colores la consola con colores para las carpetas y archivos etc...

  1. `sudo apt-get install zsh` para instalar el paquete `sudo yum install zsh` para el caso de Amazon Linux

  2. `wget --no-check-certificate https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh -O - | sh`  importante para instalar **omyzsh**

- linux todo es un fichero (los directorios son ficheros especiales) por lo tanto cuando montamos unidades HHD lo toma como un fichero donde puede leer y escribir

- los demon (demonios) son servicios que se levantan al reiniciarse el sistema de forma automática ejemplo ssh_config | sshd_config - la `d` identifica el demon 

- siempre reiniciar el servicio para que tenga efecto la configuración

- `&&` concatena comandos {comando1} && {comando2} && {comandoN}cuando termina el 1 se ejecuta el 2

## metodos de acceso ssh

>  ssh - secure shell para acceder al servidor linux

1. `ssh -l {usuario} -p {puerto}{equpo-remoto}` este metodo pide user an pass

2. `ssh {usuario}@{equipo-remoto}`  por key per

3. `ssh -i {rutal/key.pem} {usuario}@{equipo-remoto}` para las keyper provadas poderlas utilizar

   para linux|max restringir a los permisos a la keyper `sudo chmod 0644 /ruta/key.pem`

- `ssh -o TCPKeepAlive=yes -o ServerAliveInternal=100` para mantener vivo el server y no se caiga cada cierto tiempo

## >> comandos básicos

> en ***/bin/*** están todos los vinarios que se pueden utilizar como comandos

- `ls` - lista de directorios y ficheros
  - `ls -lah` - (a) para ver ocultos, (h) para mas detalles de ficheros
- `cd` - cambiar directorio ; con la `cd /` -> vamos a la raíz de todo el sistema Linux ; sin la / es en la carpeta actual
- `mkdir` - crear directorio
- `touch` - crear archivo `touch archive.txt archive2.txt` 
- `rm` - eliminar fichero
- `rm -R carpeta` para hacer eliminado recursivo
- `pwd` - ver en la ruta que estas ubicado ej: /home/ubuntu
- `nano` - editor basico de linux `nano archivo.txt`  ctrl+x salir; Y|N guardar
- `histoy` - muestra el historial de navegacion Arrow up | arrow down
- `sudo su` - super user do ; cambia a root
- `cp` - copiar archivos `cd /home/ubuntu/popy.txt /home/` 
- `mv` - mover archivos `sudo mv /home/ubuntu/popy.txt /home/` o si se encuentra en el directorio `mv archivo.txt directorio/`
- `ps` - muestra los procesos
- `chmod` - permisos de los ficheros y directorios `chmod 700 /dir/`
- `cat` - lee los archivos ej. de configuracion no lo abre como nano solo lo lee en la shell
- `passwd` - para cambiar la contraseña
- `man` - para ver la ayuda de los comando existentes `man ssh` - help ssh `man hier` ayuda de todos los directorios
- `reboot` - para reiniciar solo como root
- `fdisk -l` - lista las particiones del sistema como root
- `sudo apt-get install tree` 
  - `tree` muestra en el directorio que se este el listado de subdirectorios y archivos en formato de arbol
- `grep -R "descrip" {dir}` buscador en {dir} la "descrip"

## estructura de directorios Linux

-  **Estáticos**: Contiene archivos que no cambian sin la intervención del administrador ([root](Root.html)), sin embargo, pueden ser leídos por cualquier otro usuario. (`/bin, /sbin, /opt, /boot, /usr/bin`...)

-  **Dinámicos**: Contiene archivos que son cambiantes, y pueden leerse y escribirse (algunos sólo por su respectivo usuario y el [root](Superusuario.html)). 

  Contienen configuraciones, documentos, etc. Para estos directorios, es  recomendable una copia de seguridad con frecuencia, o mejor aún,  deberían ser montados en una partición aparte en el mismo disco, como  por ejemplo, montar el directorio /home en otra partición del mismo  disco, independiente de la partición principal del sistema; de esta  forma, puede repararse el sistema sin afectar o borrar los documentos de los usuarios. (`/var/mail, /var/spool, /var/run, /var/lock, /home`...)

-  **Compartidos**: Contiene archivos que se pueden encontrar en un ordenador y utilizarse en otro, o incluso compartirse entre usuarios. ` (/var/mail, /opt, /home, etc...)`
-  **Restringidos**: Contiene ficheros que no se pueden compartir, solo son modificables por el administrador. (`/etc, /boot, /var/run, /var/lock`...)

| Directorio                    | Descripción Simple                                           |
| ----------------------------- | ------------------------------------------------------------ |
| **[/](Directorio_raíz.html)** | *Jerarquía primaria*, la raíz o *root*, y [directorio raíz](Directorio_raíz.html) o contenedor de todo el sistema de jerarquía. |
| **/bin/**                     | Aplicaciones [binarias](Ejecutable.html) de comando que son esenciales para que estén disponibles para una sesión de *usuario único*, o bien, para todos los usuarios ([multiusuario](Multiusuario.html)). Incluyen, por ejemplo, [cat](Cat_(Unix).html), [ls](Ls.html), [cp](Cp_(Unix).html), [rm](Rm_(Unix).html), mkdir, etc. |
| **/boot/**                    | Archivos cargadores de [arranque](Arranque_(informática).html) (por ejemplo, los [núcleos](Núcleo_(informática).html), el [initrd](Initrd.html)). *A menudo en una partición o disco aparte*. donde esta el grub |
| **/dev/**                     | Contiene archivos especiales de bloques y caracteres  asociados a dispositivos hardware. Aquí encontramos todos los  dispositivos físicos del sistema (todo nuestro hardware). Y archivos  importantes  (por ejemplo, [/dev/null](_dev_null.html)), discos duros |
| **/etc/**                     | Contiene archivos de configuración del sistema específicos del *[Host](Host.html)* de todo el sistema. Ha habido controversia sobre el significado del  nombre, en las primeras versiones del Documento de Implementación de  UNIX de los laboratorios Bell, **/etc** se conoce como el directorio **/etcetra**, todo lo que históricamente este directorio consideró que no pertenecía  en otra parte (sin embargo, restringe la ESF / etc a los archivos de  configuración estáticos y no puede contener archivos binarios). Desde la publicación de la documentación temprana, el nombre de la guía ha sido  re-designados de varias maneras. Interpretaciones más recientes incluyen Backronyms como "Configuración de texto editable". |
| */etc/opt/*                   | Archivos de configuración para los programas alojados dentro del directorio `/opt`. |
| */etc/X11/*                   | Archivos de configuración para el [X Window System](X_Window_System.html), versión 11. |
| */etc/sgml/*                  | Archivos de configuración para [SGML](SGML.html).            |
| */etc/xml/*                   | Archivos de configuración para [XML](XML.html).              |
| **/home/**                    | Contiene los directorios de trabajo de todos los usuarios, excepto el del [superusuario](Superusuario.html) (administrador, root). Contiene archivos guardados, ajustes personales, etc. *A menudo es instalada en un disco o partición separada*. Cada usuario tiene su propio directorio dentro de esta carpeta. |
| **/lib/**                     | Contiene todas las [bibliotecas](Biblioteca_(programación).html) (mal traducidas como *librerías*) esenciales compartidas de los programas alojados, es decir, para los [binarios](Ejecutable.html) en `*/bin/*` y `*/sbin/*`. Contiene también las bibliotecas para el núcleo. |
| **/media/**                   | Contiene los *puntos de montaje* de los medios extraíbles de almacenamiento, tales como lectores de [CD-ROM](CD-ROM.html) (aparecido en la versión 2.3 de *FHS*), [Pendrives](Pendrive.html) ([memoria USB](Memoria_USB.html)), e incluso sirve para *montar* otras particiones del mismo [disco duro](Disco_duro.html), como por ejemplo, alguna partición que sea utilizada por otro [sistema operativo](Sistema_operativo.html). |
| **/mnt/**                     | [Sistema de archivos](Sistema_de_archivos.html) [montados](Montar_(informática).html) temporalmente. Es una directorio semejante a `/media`, pero es usado mayoritariamente por los usuarios. Sirve para *montar* [discos duros](Disco_duro.html) y particiones de forma temporal en el sistema; no necesita contraseña, a diferencia del directorio `/media`. |
| **/opt/**                     | Contiene [Paquetes de programas](Paquete_de_software.html) opcionales de aplicaciones estáticas, es decir, que pueden ser  compartidas entre los usuarios. Dichas aplicaciones no guardan sus  configuraciones en este directorio; de esta manera, cada usuario puede  tener una configuración diferente de una misma aplicación, de manera que se comparte la aplicación pero no las configuraciones de los usuarios,  las cuales se guardan en su respectivo directorio en `/home`. |
| **/proc/**                    | Contiene principalmente archivos de texto, [sistema de archivos](Sistema_de_archivos.html) virtuales que documentan al [núcleo](Núcleo_(informática).html) y el estado de los [procesos](Proceso_(informática).html) en [archivos de texto](Archivo_de_texto.html) (por ejemplo, `*uptime*, *network*`). |
| **/root/**                    | [Directorio raíz](Directorio_raíz.html) del usuario [root](Root.html). Funciona como las carpetas en `/home`, pero en este caso, es solo para el superusuario (administrador del sistema). |
| **/sbin/**                    | Sistema de [binarios](Ejecutable.html) esencial, comandos y programas exclusivos del [superusuario](Superusuario.html) (root), por ejemplo, *[init](Init.html)*, *[route](Route.html)*, *[ifup](Ifup.html)*). Un usuario puede ejecutar alguno de estas aplicaciones de comandos, si  tiene los permisos suficientes, o bien, si tiene la contraseña del  superusuario. |
| **/srv/**                     | Lugar específico de datos que son servidos por el sistema. los ficheros de apache, correo, ftp |
| **/sys/**                     | Evolución de proc. [Sistema de archivos](Sistema_de_archivos.html) virtuales que documentan al [núcleo](Núcleo_(informática).html) pero localizados de forma jerarquizada. En proc se disponen de forma anárquica. Su nombre correcto es [Sysfs](https://en.wikipedia.org/wiki/Sysfs). |
| **/tmp/**                     | Archivos temporales (véase también */var/tmp*). Aquí generalmente se guardan los archivos temporales guardados -por ejemplo- por el [navegador](Navegador_web.html) de internet. |
| **/usr/**                     | *jerarquía secundaria* de los datos de usuario; contiene la mayoría de las utilidades y aplicaciones [multiusuario](Multiusuario.html), es decir, accesibles para todos los usuarios. En otras palabras,  contiene los archivos compartidos, pero que no obstante son de *sólo lectura*. Este directorio puede incluso ser compartido con otras [computadoras](Computadora_electrónica.html) de [red](Red_de_área_local.html) local. |
| /usr/bin/                     | [Comandos](Comando_(informática).html) [binarios](Ejecutable.html) no-administrativos para todos los usuarios. Son de solo lectura, pero  pueden tener su propia configuración para cada usuario en `/home`. |
| /usr/include/                 | Archivos de cabecera (Header files o Include files), es decir, archivos de inclusión estándar. |
| /usr/lib/                     | [bibliotecas](Biblioteca_(programación).html) compartidas de los [binarios](Ejecutable.html) en `/usr/bin/`. Algunos ejecutables comparten las mismas librerías que comparten las  demás aplicaciones, de manera que generalmente no hay dos librerías  idénticas en un mismo sistema, lo cual ahorra memoria y proporciona más  orden. |
| /usr/sbin/                    | Sistema de binarios no esenciales; por ejemplo, [demonios](Demonio_(informática).html) para varios servicios de red. Es decir, contiene programas que no  proporcionan una interfaz de usuario y gerneralmente se ejecutan al  inicio del sistema o en ciertas circunstancias. No son directamente  manejados por el usuario mientras se ejecutan, aunque sí pueden ser  configurados antes de que sean ejecutados. |
| /usr/share/                   | Arquitectura independiente y compartida de datos. En  otras palabras, contiene los datos compartidos que no dependen de la  arquitectura del sistema. Esto puede incluir imágenes, sonidos, etc.,  para la disponibilidad en el [sistema](Sistema_operativo.html) y sus aplicaciones. Pueden ser plantillas, por ejemplo, aunque generalmente son archivos que el sistema utiliza directamente. |
| /usr/src/                     | [Códigos fuente](Código_fuente.html) de algunas aplicaciones. Al igual que `/mnt`, esta carpeta es manejada por los usuarios directamente para que éstos  puedan guardar en él el código fuente de programas y bibliotecas y así  puedan accederlo fácilmente, sin problemas con permisos. Permite que el  código fuente tenga un espacio propio, accesible pero apartado de todos  los usuarios. |
| /usr/X11R6/                   | Sistema [X Window System](X_Window_System.html), Versión 11, Release 6. Este directorio se relaciona con el entorno gráfico. |
| /usr/local/                   | *Jerarquía terciaria* para los datos locales, específicos a este [host](Host.html). Usualmente tiene subdirectorios, por ejemplo `bin/`, `lib/`, `share/`, de datos compartidos de *sólo lectura* específicos del [ordenador](Ordenador.html) o [servidor](Servidor.html) que los comparte. |
| **/var/**                     | Archivos variables, tales como *logs*, archivos *[spool](Spooling.html)*, [bases de datos](Bases_de_datos.html), archivos de [e-mail](E-mail.html) temporales, y algunos archivos temporales en general. Generalmente  actúa como un registro del sistema. Ayuda a encontrar los orígenes de un problema. |
| /var/cache/                   | Memoria [caché](Caché_(informática).html) de las aplicaciones, aunque también se utiliza el directorio  `/tmp` para lo mismo. |
| /var/crash/                   | Se depositan datos e información, referentes a las caídas o errores del [sistema operativo](Sistema_operativo.html). Es más específico que `/var` en general. |
| /var/games/                   | Datos variables de los juegos del sistema. Este  directorio no es imprescindible y muchas veces es omitido por las  propias aplicaciones de juegos, pues utilizan la carpeta de usuario en `/home` para guardar datos variables como configuraciones, por poner un  ejemplo. De todas maneras, los juegos de gnome utilizan este directorio. |
| /var/lock/                    | Archivos *Lock*. Archivos que hacen el seguimiento de los recursos que se utilizan actualmente. |
| /var/log/                     | Archivos de registro, *Log*. Varios registros, *logs*.       |
| /var/mail/                    | Buzón correos o mensajes de los usuarios. Si no utiliza  cifrado, generalmente se utiliza entonces la carpeta personal para la  misma labor por parte de programas que manejen correos electrónicos. |
| /var/opt/                     | Posee los datos variables de /opt.                           |
| /var/run/                     | Información reciente. Trata acerca del funcionamiento del sistema desde el último [arranque](Arranque_(informática).html). Por ejemplo, los usuarios actualmente registrados o logueados, que han ingresado; y los [demonios](Demonio_(informática).html) que están en ejecución. |
| /var/spool/                   | Bobinas o carretes ([Spool](Spooling.html)), de tareas a la espera de ser procesados (por ejemplo, colas de impresión y correo no leído). |
| /var/spool/mail/              | Ubicación de los correos de usuario desaprobados. Si no  utiliza cifrado, generalmente se utiliza entonces la carpeta personal  para la misma labor por parte de programas que manejen correos  electrónicos. |
| /var/tmp/                     | Archivos temporales que, a diferencia de `/tmp`, no se borran entre sesiones o reinicios del sistema, pero que de todas maneras siguen siendo prescindibles. |

### notas sobre directorios

- **/bin/** no puede contener sub-directorio, solo para binarios

- **/dev ** unidades Permanentes

  se montan unidades como

  -  cdrom, 
  - un hhd como **sda, sdb, etc...** en maquinas o server convencionales y aws como **(xvda, xvdb, tec...)** 
  - **xvda1, xvda2,etc..** particiones de los discos
  -  lpx - para las impresoras

- **/etc/** todas las configuraciones de todas las aplicaciones están aquí (apache, nginx)

  Ej: configurar etc/ssh/ssh**d**_config - es el demonio de ssh_config por eso la `d` 

  `sudo nano sshd_config` y en el fichero se configura todo los parametros del ssh....

- **/mnt** unidades Temporales (usb, hhd externos) pueden ser archivos compartidos

- **/media** unidades Temporales (usb, cd) pero no son archivos compartidos directos a la estacion de trabajo

- **/opt/** donde se contienen los archivos y directorios que no son del SO (chrome, otras app, es como *Archivo de programas* en windows)

- **/proc** se almacena en ram, pq es lo que se esta procesando en el momento

- **/sbin/** no puede contener sub-directorio, solo para binarios como el **/bin/** pero solo para el superuser

- **/tmp/** se limpia cuando se reinicia

- **/var/** en algunos casos cuando tenemos algún error aquí en los log del propio sistema operativo esta descrito el error con la posible solución `nano /var/log/syslog`

## Actualizaciones de Paquetes

- `sudo apt-get update` re-sincroniza los índices de los paquetes, y la lista de repositorios, siempre se ejecuta antes de upgrade
- `sudo apt-get upgrade` instala las versione nuevas de los paquetes instalados, no elimina paquetes ni dependencias

## Instalar and remove Paquetes

`sudo apt-get install {paquete1} {paquete2} {paqueteN}`  instalar

`sudo apt-get remove {paquete1} {paquete2} {paqueteN}`  desintalar paquete pero las dependencias se mantienen y las config

`sudo apt-get purge {paquete1} {paquete2} {paqueteN}` desintala pero tambien elimina las dependencias y las config elimina completo

`sudo apt-get autoremov` limpia los paquetes y dependencia que quedaron de otras eliminaciones

## Alias

crea alias para comandos

`alias namealias='sudo apt-get update && sudo apt-get upgrade'`

`namealias` se ejecuta los comandos guardados aqui

`alias` muestra todas las aleas creadas

para hacerlos permanente deben guardarse en la carpeta del `vim /usuario/bashrc` editar el fichero y crear dentro las alias

## Variables de entornos

variable que contienen valores que pueden ser utilizados por distintos programas, como las del sistema HOME, MAIL, PATH, SHLVL, SHELL, LANG, USER, siempre el signo de $VARIABLE

`echo` para ver valor de variable `echo $SHELVL` 

crear `LEO='mi nombre'`  pintar `echo $LEO`

los vinarios que se deseen ejecutar deben estar en la var $PATH la ruta o ejecutar con toda la ruta completa

**persistencia** se crean en los archivos de inicio del shell

- `/etc/profile` Todos tendran acceso a esta variable no es aconsejable este metodo
- `$HOME/.profile` al inicio de la sesión del usuario
- `$HOME/.bashrc` para todos los shell incluyendo al shell de inicio

## Administración de Usuarios

- `useradd -m {usuario}` crea un usuario -m (para crearlo con la carpeta en /home); crearle el passwors- `passwd usuari`
- `adduser -g {group} {user}` crear usuario en un grupo
- `userdel{user}` eliminar el usuario
- `vin /etc/passwd` para ver todos los usuarios y las configuraciones
- `groupadd {group}` crear grupo
- `groupdel {group}` eliminar grupo
- `vin /etc/group` para ver todos los grupos y las configuraciones
- `usermod -G {group, group2...} {user}` vincula un usuario y un grupo existente
- `adduser {usuario} sudo` para asignarlo a la lista de usuarios que pueden utilizar sudo

## Permisos de los Archivos

![image-20210313221514172](Administracion Linux\permisos.png)

- `'-'` denota un archivo regular
- `'d'` denota un [directorio](Directorio.html)
- `'b'` denota un archivo especial de bloques
- `'c'` denota un archivo especial de caracteres
- `'l'` denota un [enlace simbólico](Enlace_simbólico.html)
- `'p'` denota un tubo nombrado
- `'s'` denota un socket de dominio

Cada uno de los tres caracteres representa los permisos de lectura, escritura y ejecución respectivamente:

- `'r'` si el [bit](Bit.html) de lectura está asignado, `'-'` en caso contrario.
- `'w'` si el bit de escritura está asignado, `'-'` en caso contrario.
- `'x'` si el bit de ejecución está asignado, `'-'` en caso contrario.

Estos son algunos ejemplos de notación simbólica:

1. `"-rwxr-xr-x"` para un  archivo regular que tiene todos los permisos asignados para su  propietario y solo permisos de lectura y ejecución para el grupo de  usuarios del archivo y el resto de los usuarios. Ningún usuario, salvo  el propietario, puede modificar los contenidos del archivo.
2. `"crw-rw-r--"` para un archivo especial  de caracteres que tiene permisos de lectura y escritura para su  propietario y grupo de usuarios y solo permiso de lectura para el resto  de los usuarios.
3. `"dr-x------"` para un directorio que tiene permisos de lectura y ejecución únicamente para su propietario.

![image-20210313222753433](Administracion Linux\sistema octal.png)

![image-20210313222859950](Administracion Linux\sistema octal wikipedia.png)

- `chown {user} {archivo}` para cambiar el propietario del archivo
- `chmod {permisos} {fichero}` cambiar permisos a un fichero

## Enlaces

todos los archivos en el sistema se encuentran representados por un Inode(Bloque de información de los archivos), el cual tiene la capacidad de asociarse con un nombre

`ls -li` li muestra la informacion del fichero del Inode ej: 278028

**fisicos: ** Guarda toda la informacion de un solo Inodo, cada archivo siempre contiene lo mismo que el otro enlazado

`ln {fichero_origina} {fuchero_crear}`

**Simbolicos: ** la referencia es mediante el nombre del fichero original

`ln -s {fichero_origina} {fichero_simbolico}`

![image-20210313235321009](Administracion Linux\enlacens.png)

## Apache

> modular, multiplataforma, codigo abierto popular

instalar 

- `sudo apt-get install apache2` 
- `sudo add-apt-repository ppa:ondrej/apache2` de un repositorio particular
- `sudo apt-get remove apache2` elimina apache
- `sudo apt-get remove apache2-bin` elimina apache binarios
- `apache2 -v` version
- `sudo service apache2 status` saber si el apache esta corriendo
- **/var/www/html** dierccion de los sitios de apache como en Xampp httpdoc

### modulos multiprocesamiento

### configuración

**/etc/apache2/** ruta de configuración para apache

- apache2.conf

  ```
  ...
  Timeout 300 - ponerlo 60
  ...
  KeepAlive On - mantener vivo el hilo
  ...
  <directives/>
  ```

- ports.conf

### Virtual Host

procesar dos subdominios en un mismo servidor , repetir el proceso por cada subdominio

1. `cd /etc/apache2/site-available` ls - para  ver ficheros de configuración de los ***virtualhost 000-default.conf*** 

   ```
   <virutlaHost :80>
   	ServerName www.example.com
   	DocumentRoot /var/www/html
   	...
   </virutlaHost>
   ```

2. `cp 000-default.conf name.domine.conf` copiar el archivo en el nuevo ***name.domine.conf*** 

   ```
   <virutlaHost :80>
   	ServerName name.domine.com
   	# alias www.namedomine.com // ejemplo de alias
   	
   	DocumentRoot /var/www/html/rutaproyecto
   	...
   </virutlaHost>
   ```

3. crear index.html o el sitio en ***/var/www/html/rutaproyecto***

4. `a2ensite {archivo_configuracion.conf}` publicar lo que hay en ***/site-aviable*** en ***/site-enable*** seria

   `a2ensite name.domine.conf`  comando para ejecutar el ***name.domine.conf*** creado

5. `  sudo systemctl reload apache2` para reiniciar el apache

## Nginx

servidor web, puede ser proxy reverso, soporta FastCGI, no soporta PHP

- `sudo apt-get install nginx` para instalarlo
- `sudo add-apt-repository ppa:nginx/stable` de un repositorio particular

### configuración

***/etc/nginx/*** donde estan las configuraciones de nginx

- nginx.conf

  ```
  user www-data;
  worker_processes outp;  // capaciadad de gestionar theads
  ...
  http {
  	keepalive_time_out 65;
  	gzip on;
  }
  
  ```

### Virtual Host

- site-available
- site-enabled

el enlace simbolico entre estos directorios se hacen mnualmente 

![image-20210314203649512](Administracion Linux\enlaces simbilocos nginx.png)

1. `cd /etc/nginx/site-available` ls - para  ver ficheros de configuración de los ***default*** 

2. crear un fichero propio para el **dominio.nuevo** y crear la configuración del virtual host

   ```
   server {
   	lister 80;
   	server_name diminio.com;
   	root /var/www/html/dominio.nuevo;
   	index index.html;
   	
   	error_log /var/log/nginx/dominio.nuevo_error.log
       access_log /var/log/nginx/dominio.nuevo_access.log
   }
   ```

3. `ln -s /etc/nginx/site-available/dominio.nuevo /etc/nginx/site-enable/dominio.nuevo ` crear enlace simbólico

4. crear index.html o el sitio en ***/var/www/html/dominio.nuevo***

5.  `service nginx restart` para reiniciar el nginx

## Apache vs Nginx

![image-20210314225829991](Administracion Linux\Apache vs nginx.png)

## LAMP

> Linux Apache MySQL PHP

1. install Apache `sudo apt-get install apache2`

2. instalar MySQL `sudo apt-get instal mysql-server`

3. instalar php `sudo apt-get install php7.0`

4. instalar modulo de apache para php `sudo apt-get install libapache2-mod-php7.0`

5. instalar modulo php para mysql `sudo apt-get install php7.0-mysql`  

6. copiar proyecto a la direccion **/var/www/proyect**

7. configurar los ficheros de conexion a base datos

8. conectar base de datos `mysql -u root -p` -u para el ussuari, -p para poner el password

9. crear base de datos `create database namebd` 

   1. `show databases`
   2. `show tables` 

10.  `use dbname` para seleccionar la base de datos

11. `source backup.sql` para restaurar backup

12. `vim /etc/apache2/mods-available/dor.conf` poner el index.php al inicio de la lista

13. `service apache2 restart`

14. ```
    wget --no-check-certificate https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh -O - | sh 
    ```

## Certificados SSL

> certificado que autentica la identidad de un sitio web y cifra con tecnología SSL la información al servidor

certificado SSL contiene:

- Nombre del titular del certificado
- Número de serie del certificado
- una copia de la clave publica del titular del certificado
- la firma digital de la autoridad que emite el certificado

### instalar certificados Certbot + lets-encrypt

https://www.digitalocean.com/community/tutorials/how-to-secure-apache-with-let-s-encrypt-on-ubuntu-20-04-es

1. `sudo add-apt-repository ppa:certbot/certbot` installar cerbot

2. `sudo apt install python-certbot-apache` modulo python para apache de certbot

3. **cd /etc/apache2/sites-available/** configurar el dominio|s, como vimos en la sesión de apache con todos los pasos

4. opcional (sudo ufw status) para verificar firewall `sudo ufw allow 'Apache Full'`  `sudo ufw delete allow 'Apache'`

5. `sudo certbot --apache -d nuevodominio.com -d www.nuevodominio.com`

   `sudo certbot --apache -d premium.nuevodominio.com`  obtener los certificados SSL precionar 2 si deseamos la redirecion de www a no-www

6. `sudo certbot renew --dry-run`  verificar la renovación automática de Certbot

7. **ESTE PASO NO SE SI ES EL (7 o 4,5) ** MODIFICARL EL FICHERO **subd.dominio.com-le-ssl.conf** con los valores, si no funciona correr el paso 5 y 6

   ```python
   <VirtualHost *:443>
           
           ServerAdmin programacion.grupohorizontes@gmail.com
           DocumentRoot /var/www/html/site-ejemplo/public/
           DirectoryIndex /index.php
   
           <Directory /var/www/html/site-ejemplo/public/>
           AllowOverride None
           Order Allow,Deny
           Allow from All
           FallbackResource /index.php
           </Directory>
   
   
           # ServerAdmin programacion.grupohorizontes@gmail.com
   #       DocumentRoot /var/www/html/ringetlbackend/
   #       ServerName app.solyag.online
   #       ServerAlias www.app.solyag.online
   
           # Available loglevels: trace8, ..., trace1, debug, info, notice, warn,
           # error, crit, alert, emerg.
           # It is also possible to configure the loglevel for particular
           # modules, e.g.
           #LogLevel info ssl:warn
   ```

sudo certbot --apache -d app.solyag.online -d www.app.solyag.online

sudo certbot --apache -d app.solyag.online -d www.app.solyag.online



sudo certbot --apache -d miacargo.do -d www.miacargo.do