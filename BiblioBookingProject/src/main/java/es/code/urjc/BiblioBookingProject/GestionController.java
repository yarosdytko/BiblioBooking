package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	public void init() {
		Alumno alumno = new Alumno("Yaroslav","Dytko","y.dytko","y.dytko.2017@alumnos.urjc.es","1234");
		alumnos.save(alumno);
		Sala sala = new Sala(1,10,2,5);
		salas.save(sala);
		reservas.save(new Reserva(sala,alumno,"2020-02-22","10:20"));
		reservas.save(new Reserva(sala,alumno,"2020-02-22","13:20"));
		
	}
	

	@RequestMapping("/gestion")
	public String gestion() {
		return "gestion";
	}
	
	@RequestMapping("/gestion/reservas")
	public String reservas(Model model) {
		
		int totalReservas = (int) reservas.count();
		model.addAttribute("totalReservas",totalReservas);
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findAll());
		}
		return "gestion_reservas";
	}
	
	@RequestMapping("/gestion/reservas/nueva_reserva")
	public String nueva_reserva(Model model) {
		return "nueva_reserva";
	}
	
	@PostMapping("/gestion/reservas/nueva_reserva/reservar")
	public String reservar(Model model, @RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido) {
		Alumno alumno = alumnos.findByNameAndApellido(nombre, apellido);
		Sala sala = salas.findByNumeroSala(1);
		reservas.save(new Reserva(sala,alumno,fecha,hora));
		return "redirect:/gestion/reservas";
	}
	
	@RequestMapping("/gestion/reservas/editar{idReserva}")
	public String editar(Model model, @PathVariable int idReserva) {
		
		model.addAttribute("idReserva", idReserva);
		
		return "editarReserva";
	}
	
	@RequestMapping("/gestion/reservas/eliminar{idReserva}")
	public String eliminar(@PathVariable int idReserva) {
		
		reservas.deleteById((long) idReserva);
		
		return "redirect:/gestion/reservas";
	}
}
