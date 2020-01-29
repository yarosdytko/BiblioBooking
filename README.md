# BiblioBooking
**Nombre de la aplicacion Web:** BiblioBooking

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
