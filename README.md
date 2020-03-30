# BiblioBooking
**Nombre de la aplicacion Web:** BiblioBooking

# Indice
1. [Fase 1](#fase1)
2. [Fase 2](#fase2)
3. [Fase 3](#fase3)


## Fase1
**Descripcion:** La tematica elegida para este proyecto web es la de implementar un portal de reservas de salas en una biblioteca(como por ej la del campus), de esta manera los alumnos tendrian una herramienta que les permitiria realizar y gestionar sus reservas de sala en una biblioteca de manera rapida y comoda.

**Funcionalidades:** La funcionalidad **publica** trata de ofrecerle al usuario informacion general sobre la biblioteca(distribucion, horarios, direccion, etc), de esta manera cualquier persona que acceda a la web puede tener toda la informacion de interes sobre el lugar, por otra parte la funcionalidad **privada** requerira que el usuario se de de alta en el sistema, de esta manera un usuario registrado podra tener acceso a las funcionalidades de hacer reservas de sala, consultar disponibilidade de salas, gestionar(modificar,  cancelar) sus reservas actuales.

**Tipos de usuario:** se podria diferenciar 4 tipos de usuarios: 

 - Visitante - no esta registrado, solo tiene acceso a parte publica de la aplicacion.
 - Alumno - usuario alumno registrado, tiene acceso a las funcionalidades de hacer reserva, consultar disponibilidad y gestionar sus reservas, ademas de las funcionalidades basicas de configuracion sobre su perfil(cambio de contraseña, informacion personal, etc), este tipo de usuario solo podra ver y gestionar sus propias reservas, no tendra acceso a los datos de otros usuarios.
 - Empleado - este tipo de usuario es para los empleados de la biblioteca, esta pensado con el mismo fin que los empleados reales de la biblioteca, ya que un alumno puede presentarse en persona y hacer una reserva en el lugar, en este caso el Empleado seria quien gestiona la reserva en la aplicacion, tambien este tipo de usuarios tiene acceso a poder ver todas las reservas sobre toda la biblioteca y tambien tiene acceso a modificar o eliminar cualquier reserva de cualquier alumno. Los empleados no podran darse de alta por si mismos, los da de alta el Administrador, tambien estos usuarios podran bloquear las cuentas de usuarios tipo Alumno en ciertos casos.
 - Administrador - este tipo de usuario tiene acceso total a todas las funcionalidades de la aplicacion, puede crear usuarios de tipo Empleado y de tipo Alumno si es necesario. Este tipo de usuario puede gestionar todas las cuentas de los usuarios anteriores, ya sea modificar sus perfiles, datos de acceso o directamente bloquear o eliminarlos del sistema.
 
**Entidades del sistema:** el sistema tendras las siguentes entidades:
 - sala - sobre una sala se podra hacer una(o mas) reserva/s.
 - reserva - dada una hora y fecha se podra iniciar una reserva.
 - alumno - realiza la reserva.
 - empleado - gestiona y controla las reservas, realiza reservas en persona 
 - administrador - gestiona el funcionamiento y los usuarios de la aplicacion
 
 **Servicio interno:** el servicio interno se encargara de enviar informacion al usuario en el momento de hacer la reserva, cuando se hace alguna modificacion sobre la reserva ya realizada o cuando se cancela alguna reserva, ademas tambien enviara comunicaciones al usuario en caso de realizar alguna modificacion en su perfil o si su perfil ha sido bloqueado por algun motivo.

Integrantes de grupo: Yaroslav Dytko

Correo: y.dytko.2017@alumnos.urjc.es

Github: yarosdytko ([https://github.com/yarosdytko](https://github.com/yarosdytko))

## Fase2
Diagrama de navegacion
![Diagrama de navegacion](https://github.com/yarosdytko/BiblioBooking/blob/master/diagrama_de_navegacion.png)
La aplicacion esta estructurada alrededor de un menu de navegacion principal desde el cual se accede a todos los recursos y funcionalidades
A continuacion vienen detalladas algunas capturas de las vistas principales de la aplicacion.
- inicio - una pantalla de bienvenida al usuario presentando informacion general sobre la aplicacion
![inicio](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/index.png)
- informacion - presenta informacion general sobre la biblioteca ![informacion](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/informacion.png)
- Mis reservas - el alumno puede ver sus reservas actuales, editarlas o crear una reserva nueva
![mis_reservas](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/mis_reservas.png)
- Nueva reserva - en esta ventana el alumno puede hacer una reserva nueva
![nueva_reserva](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/nueva_reserva.png)
- Mi perfil - muesta los datos del perfil de usuario, en futuras implementaciones el usuario podra editar agunos de sus datos
![mi_perfil](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/mi_perfil.png)
- Login - pantalla de login para acceso a la parte privada de la aplicacion(en esta fase no se usa)
![login](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/login.png)
- Registrarse - formulario de registro para alumnos
![registrarse](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/register.png)
- Panel administrador - panel de control para administradores, permite realizar gestiones con reservas, usuarios y salas
![panel_admin](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/panel_admin.png)
- Gestion - panel de control para empleados de la biblioteca, permite gestionar reservas, salas y ususarios(bloquear)
![gestion](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/gestion.png)
- Nuevo usuario - accesible desde panel de administrador, permite crear usuarios de tipo Empleado y Alumno, en futuro se implementara tambien para crear Administradores
![nuevo_usuario_admin](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/nuevo_usuario_admin.png)
- Usuarios - vista accesible desde panel de administrador que permite ver y editar a todos los usuarios de tipo Alumno y Empleado
![usuarios_admin](https://github.com/yarosdytko/BiblioBooking/blob/master/capturas/usuarios_admin.png)

**Diagrama de clases de la aplicacion**
![class_diagram](https://github.com/yarosdytko/BiblioBooking/blob/master/diagrama%20de%20clases.png)

**Diagrama entidad relacion de BBDD**
![diagrama_bbdd](https://github.com/yarosdytko/BiblioBooking/blob/master/bbdd_esquema.png)

## Fase3
**Diagramas UML de clases y templates**

Diagrama de clases y templates de la aplicacion
![ ](https://github.com/yarosdytko/BiblioBooking/blob/master/BiblioBookingApplicationDiagramaDeClases.png)

Diagrama de clases del servicio interno
![ ](https://github.com/yarosdytko/BiblioBooking/blob/master/BiblioBookingServiceDiagramaDeClases.png)

**Maquina virtual**
Para desplegar la aplicacion, el servicio iterno y la bbdd, usare una maquina virtual de ubuntu-server 18.04, como software de virtualizacion usare virtualbox+vagran, los pasos de instalacion de virtualbox y vagrant los obviare ya que no entran dentro del ambito del proyecto.

**Creacion de maquina virtual:**

Creo el directorio para la maquina virtual.

>mkdir -p ~/vagrant/bibliobooking
>cd ~/vagrant/bibliobooking

inicializar el fichero de configuracion para la maquina virtual.
>vagrant init ubuntu/bionic64

al lanzar ese comando se creara en el directorio un arhivo llamado Vagrantfile
el siguente paso es abrir ese archivo con cualquier editor de texto y descomentar la linea con el siguente contenido:
> config.vm.network "private_network", ip: "192.168.33.10"

al descomentar esa linea se habilitara en la maquina virtual una interfaz de red con la ip indicada(que se puede cambiar si es necesario), esta sera la direccion ip por la cual se accedera a la pagina web de la aplicacion.
una vez guardado el archivo arrancamos la maquina virtual con el comando 
>vagrant up
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTExOTAzMzIyNDhdfQ==
-->