# EC2 - 

# S3 - 

# RDS - Relational Database Service

lanza una instancia de aws por detras pero no es accesible a la configuracion solo desde la consola aws 

1. **crear subnet groups** definir le nombre y las zonas de disponibilidad dependiendo de la región 

2. **crear security group** en EC2/Red y seguridad/Security Groups 

   1. nombre mysql-prubas
   2. reglas de entrada tipo:MySQL/aurora  |  post:3306  |  Source:0.0.0.0/0-poner myIP

3. **crear base datos - RDS/database/create db/** definir todas las configuraciones, 

   utilizar Free/trail para las database propias de pruebas

4. `mysql -h database-pruebas-solyag.culcuah0nl1f.us-east-2.rds.amazonaws.com -uroot -p` comando para conexion ssh 

## Options Groups

aquí se configura los parámetros de mySQL, pq no se puede acceder a la instancia del servidor se hace desde aqui como si fuera dentro del server

## Snapchots

es la forma en la que se crean los **Backups** dependiendo de la configuración se crearan un backup diario con una retención definida en días 

siempre que se quiera restaurar tendrá q ser en una instancia nueva, lo que significa tener un nuevo endpoint y ese deberá cambiarse en la app para que sea utilizado