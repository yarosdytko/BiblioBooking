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
	private AlumnosRepository alumnos;
	
	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private SalasRepository salas;
	
	@Autowired
	private EmpleadosRepository empleados;
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("ruta", "admin");
		return "control_template";
	}
	
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
		return "nueva_reserva_admin";
	}
	
	@PostMapping("/admin/reservas/nueva_reserva/reservar")
	public String reservar(Model model, @RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido) {
		Alumno alumno = alumnos.findByNameAndApellido(nombre, apellido);
		Sala sala = salas.findByNumeroSala(1);
		reservas.save(new Reserva(sala,alumno,fecha,hora));
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
	
	@RequestMapping("/admin/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("ruta","admin");
		model.addAttribute("isAdmin",true);
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
	public String nuevo_usuario() {
		return "nuevo_usuario";
	}
	
	@PostMapping("/admin/usuarios/nuevo_usuario/registrar")
	public String resgistrar(Model model,@RequestParam String tipoUsuario, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String uname, @RequestParam String email, @RequestParam String password) {
		
		String tipoUser = tipoUsuario;
		//System.out.println(tipoUser);
		
		if(tipoUser.equals("Alumno")) {
			if(!alumnos.existsByNameAndApellido(nombre, apellido)) {
				alumnos.save(new Alumno(nombre,apellido,uname,email,password));
				return "redirect:/admin/usuarios";
			} else {
				model.addAttribute("ruta","/admin/usuarios/nuevo_usuario");
				return "usuario_existe";
			}
		}
		if(tipoUser.equals("Empleado")) {
			if(!empleados.existsByNameAndApellido(nombre, apellido)) {
				empleados.save(new Empleado(nombre,apellido,uname,email,password));
				return "redirect:/admin/usuarios";
			} else {
				model.addAttribute("ruta","/admin/usuarios/nuevo_usuario");
				return "usuario_existe";
			}
		}
		return "redirect:/admin/usuarios";
	}
	
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
