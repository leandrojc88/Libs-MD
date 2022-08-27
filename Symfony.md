# Comantos Utilies

### crear base de datos

1. configurar en **.env** la base de datos `DATABASE_URL=mysql://root:""@127.0.0.1:3306/db_sym-curso?serverVersion=5.7`

2. `php bin/console doctrine:database:create` - crea la base de datos siguiendo la configuración del **.env**

### crear entidades de la base de datos

1. `php bin/console make:entity` - escribir el nombre de la entidad para crear los ficheros .php, definir los campos que componen la entidad como sus tipos
2. `php bin/console doctrine:schema:update --forece` - actualiza las entidades en las tablas de la base de datos

### crear rutas y controladores

1. crear un controlador `php bin/console make:controller` - crea el controlador y el template

### generar Formularios

1. crear un formulario `php bin/console make:form` - se le especifica el nombre y la entidad a la q se relaciona

# Testing