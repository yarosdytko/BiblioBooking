package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
	@Autowired
	private AdministradoresRepository administradores;
	
	@Autowired
	private EmpleadosRepository empleados;

	@Autowired
	private AlumnosRepository alumnos;
	
	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private SalasRepository salas;
	
	
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("ruta", "admin");
		return "control_template";
	}
	
//Reservas
	
	@RequestMapping("/admin/reservas")
	public String reservas(Model model) {
		model.addAttribute("ruta", "admin");
		int totalReservas = (int) reservas.count();
		model.addAttribute("totalReservas",totalReservas);
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findAll());
		}
		return "reservas_template";
	}
	
	@RequestMapping("/admin/reservas/nueva_reserva")
	public String nueva_reserva(Model model) {
		model.addAttribute("sala",salas.findAll());
		return "nueva_reserva_admin";
	}
	
	@PostMapping("/admin/reservas/nueva_reserva/reservar")
	public String reservar(Model model, @RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido,@RequestParam int numSala ) {
		//Alumno alumno = alumnos.findByNameAndApellido(nombre, apellido);
		//Sala sala = salas.findByNumeroSala(numSala);
		reservas.save(new Reserva(salas.findByNumeroSala(numSala),alumnos.findByNameAndApellido(nombre, apellido),fecha,hora));
		return "redirect:/admin/reservas";
	}
	
	@RequestMapping("/admin/reservas/editar{idReserva}")
	public String editar(Model model, @PathVariable int idReserva) {
		
		model.addAttribute("idReserva", idReserva);
		
		return "editarReserva_admin";
	}
	
	@RequestMapping("/admin/reservas/eliminar{idReserva}")
	public String eliminar_reserva(@PathVariable int idReserva) {
		
		reservas.deleteById((long) idReserva);
		
		return "redirect:/admin/reservas";
	}
	
//Usuarios
	
	@RequestMapping("/admin/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("ruta","admin");
		model.addAttribute("isAdmin",true);
		
		int totalAdministradores = (int) administradores.count();
		model.addAttribute("totalAdministradores",totalAdministradores);
		if(totalAdministradores>0) {
			model.addAttribute("tablaAdministradores",true);
			model.addAttribute("administrador",administradores.findAll());
		}
		
		int totalEmpleados = (int) empleados.count();
		model.addAttribute("totalEmpleados",totalEmpleados);
		if(totalEmpleados>0) {
			model.addAttribute("tablaEmpleados",true);
			model.addAttribute("empleado",empleados.findAll());
		}
		
		
		int totalAlumnos = (int) alumnos.count();
		model.addAttribute("totalAlumnos",totalAlumnos);
		if(totalAlumnos>0) {
			model.addAttribute("tablaAlumnos",true);
			model.addAttribute("alumno",alumnos.findAll());
		}
		
		return "usuarios_template";
	}
	
	@RequestMapping("/admin/usuarios/nuevo_usuario")
	public String nuevo_usuario(Model model) {
		model.addAttribute("ruta","admin");
		return "nuevo_usuario";
	}
	
	@PostMapping("/admin/usuarios/nuevo_usuario/registrar")
	public String resgistrar(Model model,@RequestParam String tipoUsuario, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String uname, @RequestParam String email, @RequestParam String password) {
		
		String tipoUser = tipoUsuario;
		
		if(tipoUser.equals("Alumno")) {
			if(!alumnos.existsByNameAndApellido(nombre, apellido)) {
				alumnos.save(new Alumno(nombre,apellido,uname,email,password));
				return "redirect:/admin/usuarios";
			} else {
				model.addAttribute("ruta","admin");
				return "usuario_existe_template";
			}
		}
		if(tipoUser.equals("Empleado")) {
			if(!empleados.existsByNameAndApellido(nombre, apellido)) {
				empleados.save(new Empleado(nombre,apellido,uname,email,password));
				return "redirect:/admin/usuarios";
			} else {
				model.addAttribute("ruta","admin");
				return "usuario_existe_template";
			}
		}
		if(tipoUser.equals("Administrador")) {
			if(!administradores.existsByNameAndApellido(nombre, apellido)) {
				administradores.save(new Administrador(nombre,apellido,uname,email,password));
				return "redirect:/admin/usuarios";
			} else {
				model.addAttribute("ruta","admin");
				return "usuario_existe_template";
			}
		}
		return "redirect:/admin/usuarios";
	}
	
	//Editar alumno
	@RequestMapping("/admin/usuarios/editar_alumno{expediente}")
	public String editar_alumno(Model model,@PathVariable int expediente) {
		
		Alumno alu = alumnos.getOne((long) expediente);
		model.addAttribute("ruta","admin");
		model.addAttribute("nombre",alu.getName());
		model.addAttribute("apellido",alu.getApellido());
		model.addAttribute("userName",alu.getUserName());
		model.addAttribute("email",alu.getEmail());
		model.addAttribute("bloqueado",alu.isBloqueado());
		
		return "editar_alumno_template";
	}
	
	@PostMapping("/admin/usuarios/editar_alumno{expediente}/guardar")
	public String guardar_alumno(@PathVariable int expediente, @RequestParam String nombre , @RequestParam String apellido,@RequestParam String userName,@RequestParam String email) {
		
		alumnos.actualizarAlumno(nombre, apellido, userName, email,(long) expediente);
		
		return "redirect:/admin/usuarios";
	}
	
	@RequestMapping("/admin/usuarios/bloquear_alumno{expediente}")
	public String bloquear_alumno(@PathVariable int expediente) {
		
		alumnos.bloqueoAlumno(true, expediente);
		
		return "redirect:/admin/usuarios";
	}
	
	@RequestMapping("/admin/usuarios/desbloquear_alumno{expediente}")
	public String desbloquear_alumno(@PathVariable int expediente) {
		
		alumnos.bloqueoAlumno(false, expediente);
		
		return "redirect:/admin/usuarios";
	}
	
	@RequestMapping("/admin/usuarios/eliminar_alumno{expediente}")
	public String eliminar_alumno(@PathVariable int expediente) {
		
		alumnos.deleteById((long) expediente);
		
		return "redirect:/admin/usuarios";
	}
	
	//Editar admin
	@RequestMapping("/admin/usuarios/editar_admin{idAdmin}")
	public String editar_admin(Model model,@PathVariable int idAdmin) {
		
		Administrador adm = administradores.getOne((long) idAdmin);
		model.addAttribute("ruta","admin");
		model.addAttribute("user","admin");
		model.addAttribute("nombre",adm.getName());
		model.addAttribute("apellido",adm.getApellido());
		model.addAttribute("userName",adm.getUserName());
		model.addAttribute("email",adm.getEmail());
		
		return "editar_admin_template";
	}
	
	@PostMapping("/admin/usuarios/editar_admin{idAdmin}/guardar")
	public String guardar_admin(@PathVariable int idAdmin, @RequestParam String nombre , @RequestParam String apellido,@RequestParam String userName,@RequestParam String email) {
		
		administradores.actualizarAdmin(nombre, apellido, userName, email,(long) idAdmin);
		
		return "redirect:/admin/usuarios";
	}
	
	@RequestMapping("/admin/usuarios/eliminar_admin{idAdmin}")
	public String eliminar_admin(@PathVariable int idAdmin) {
		
		administradores.deleteById((long) idAdmin);
		
		return "redirect:/admin/usuarios";
	}
	
	//Editar empleado
	@RequestMapping("/admin/usuarios/editar_empleado{idEmpleado}")
	public String editar_empleado(Model model,@PathVariable int idEmpleado) {
		
		Empleado emp = empleados.getOne((long) idEmpleado);
		
		model.addAttribute("ruta","admin");
		model.addAttribute("user","empleado");
		model.addAttribute("nombre",emp.getName());
		model.addAttribute("apellido",emp.getApellido());
		model.addAttribute("userName",emp.getUserName());
		model.addAttribute("email",emp.getEmail());
		
		return "editar_empleado_template";
	}
	
	@PostMapping("/admin/usuarios/editar_empleado{idEmpleado}/guardar")
	public String guardar_empleado(@PathVariable int idEmpleado, @RequestParam String nombre , @RequestParam String apellido,@RequestParam String userName,@RequestParam String email) {
		
		empleados.actualizarEmpleado(nombre, apellido, userName, email,(long) idEmpleado);
		
		return "redirect:/admin/usuarios";
	}
	
	@RequestMapping("/admin/usuarios/eliminar_empleado{idEmpleado}")
	public String eliminar_empleado(@PathVariable int idEmpleado) {
		
		empleados.deleteById((long) idEmpleado);
		
		return "redirect:/admin/usuarios";
	}
	
//Salas
	
	@RequestMapping("/admin/salas")
	public String salas(Model model) {
		model.addAttribute("ruta", "admin");
		int totalSalas = (int) salas.count();
		model.addAttribute("totalSalas",totalSalas);
		if(totalSalas>0) {
			model.addAttribute("tablaSalas",true);
			model.addAttribute("sala",salas.findAll());
		}
		
		return "salas_template";
	}
	
	@RequestMapping("/admin/salas/nueva_sala")
	public String nueva_sala(Model model) {
		model.addAttribute("ruta","admin");
		return "nueva_sala";
	}
	
	@PostMapping("/admin/salas/nueva_sala/crear")
	public String crear(Model model, @RequestParam int numeroSala, @RequestParam int capacidadMaxima, @RequestParam int numeroMesas, @RequestParam int numeroSillas) {
		if(!salas.existsByNumeroSala(numeroSala)) {
			salas.save(new Sala(numeroSala,capacidadMaxima,numeroMesas,numeroSillas));
			return "redirect:/gestion/salas";
		} else {
			model.addAttribute("ruta","/admin/salas/nueva_sala");
			return "sala_existe";
		}
	}
	
}
