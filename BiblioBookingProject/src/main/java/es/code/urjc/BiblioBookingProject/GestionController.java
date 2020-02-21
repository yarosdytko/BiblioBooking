package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GestionController {
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private SalasRepository salas;

	@RequestMapping("/gestion")
	public String gestion(Model model) {
		model.addAttribute("ruta", "gestion");
		return "control_template";
	}
	
	@RequestMapping("/gestion/reservas")
	public String reservas(Model model) {
		model.addAttribute("ruta", "gestion");
		int totalReservas = (int) reservas.count();
		model.addAttribute("totalReservas",totalReservas);
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findAll());
		}
		return "reservas_template";
	}
	
	@RequestMapping("/gestion/reservas/nueva_reserva")
	public String nueva_reserva(Model model) {
		return "nueva_reserva";
	}
	
	@PostMapping("/gestion/reservas/nueva_reserva/reservar")
	public String reservar(@RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido) {
		Alumno alumno = alumnos.findByNameAndApellido(nombre, apellido);
		Sala sala = salas.findByNumeroSala(1);
		reservas.save(new Reserva(sala,alumno,fecha,hora));
		return "redirect:/gestion/reservas";
	}
	
	@RequestMapping("/gestion/reservas/editar{idReserva}")
	public String editar(Model model, @PathVariable int idReserva) {
		
		model.addAttribute("idReserva", idReserva);
		model.addAttribute("ruta","/gestion/reservas");
		return "editarReserva";
	}
	
	@RequestMapping("/gestion/reservas/eliminar{idReserva}")
	public String eliminar_reserva(@PathVariable int idReserva) {
		
		reservas.deleteById((long) idReserva);
		
		return "redirect:/gestion/reservas";
	}
	
	@RequestMapping("/gestion/salas")
	public String salas(Model model) {
		model.addAttribute("ruta", "gestion");
		int totalSalas = (int) salas.count();
		model.addAttribute("totalSalas",totalSalas);
		if(totalSalas>0) {
			model.addAttribute("tablaSalas",true);
			model.addAttribute("sala",salas.findAll());
		}
		
		return "salas_template";
	}
	
	@RequestMapping("/gestion/salas/nueva_sala")
	public String nueva_sala(Model model) {
		model.addAttribute("ruta","gestion");
		return "nueva_sala";
	}
	
	@PostMapping("/gestion/salas/nueva_sala/crear")
	public String crear(Model model, @RequestParam int numeroSala, @RequestParam int capacidadMaxima, @RequestParam int numeroMesas, @RequestParam int numeroSillas) {
		
		
		if(!salas.existsByNumeroSala(numeroSala)) {
			salas.save(new Sala(numeroSala,capacidadMaxima,numeroMesas,numeroSillas));
			return "redirect:/gestion/salas";
		} else {
			model.addAttribute("ruta","/gestion/salas/nueva_sala");
			return "sala_existe";
		}
	}
	
	@RequestMapping("/gestion/salas/eliminar{numeroSala}")
	public String eliminar_sala(@PathVariable int numeroSala) {
		
		Sala sala = salas.findByNumeroSala(numeroSala);
		salas.delete(sala);
		
		return "redirect:/gestion/salas";
	}
	
	@RequestMapping("/gestion/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("ruta","gestion");
		model.addAttribute("isAdmin",false);
		int totalAlumnos = (int) alumnos.count();
		model.addAttribute("totalAlumnos",totalAlumnos);
		if(totalAlumnos>0) {
			model.addAttribute("tablaAlumnos",true);
			model.addAttribute("alumno",alumnos.findAll());
		}
		
		return "usuarios_template";
	}
	
	@RequestMapping("/gestion/usuarios/eliminar{numExpediente}")
	public String eliminar_usuario(@PathVariable int numExpediente) {
		
		alumnos.deleteById((long) numExpediente);
		
		return "redirect:/gestion/usuarios";
	}
}
