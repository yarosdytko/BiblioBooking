package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservasController {

	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@RequestMapping("/reservas")
	public String reservas(Model model) {
		Alumno alumno = alumnos.findByNameAndApellido("Fernando", "Ozores");
		int totalReservas = (int) reservas.countByAlumno(alumno);
		
		model.addAttribute("totalReservas",totalReservas);
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findByAlumno(alumno));
		}
		return "misreservas_template";
	}
	
	@RequestMapping("/reservas/nueva_reserva")
	public String nueva_reserva() {
		return "nueva_reserva";
	}
	
	@RequestMapping("/reservas/eliminar{idReserva}")
	public String eliminar_reserva(@PathVariable int idReserva) {
		
		reservas.deleteById((long) idReserva);
		
		return "redirect:/reservas";
	}
	
	@RequestMapping("/reservas/editar{idReserva}")
	public String editar(Model model, @PathVariable int idReserva) {
		Reserva r = reservas.findById(idReserva);
		
		model.addAttribute("idReserva", idReserva);
		model.addAttribute("ruta","/reservas");
		model.addAttribute("reserva",r);
		
		return "editar_reserva_template";
	}
	
	@RequestMapping("{ruta}/guardar{idReserva}&{fecha}&{hora}")
	public String editar(Model model, @PathVariable String ruta, @PathVariable int idReserva,@PathVariable String fecha, @PathVariable String hora) {
		
		//reservas.findById(idReserva).updateReserva(hora, fecha);
		
		return "redirect:/reservas";
	}
	
	
	
}
