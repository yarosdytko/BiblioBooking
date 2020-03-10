package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReservasController {

	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private UserRepository users;

	@Autowired
	private SalasRepository salas;
	
	@RequestMapping("/reservas")
	public String reservas(Model model, HttpServletRequest request) {

		User user = users.findByName(request.getUserPrincipal().getName());
		int totalReservas = (int) reservas.countByUsuario(user);
		
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findByUsuario(user));
		} else {
			model.addAttribute("tablaReservas",false);
			totalReservas = 0;
		}
		model.addAttribute("totalReservas",totalReservas);
		return "misreservas_template";
	}

	@RequestMapping("/reservas/nueva_reserva")
	public String nueva_reserva(Model model) {

		model.addAttribute("sala",salas.findAll());
		return "nueva_reserva_alumno";
	}

	@PostMapping("/reservas/nueva_reserva/reservar")
	public String reservar_alumno(@RequestParam String fecha, @RequestParam String hora, @RequestParam int numSala,HttpServletRequest request) {

		reservas.save(new Reserva(salas.findByNumeroSala(numSala),users.findByName(request.getUserPrincipal().getName()),fecha,hora));
		return "redirect:/reservas";
	}

	@RequestMapping("/reservas/eliminar_reserva{id}")
	public String alumno_eliminar_reserva(@PathVariable int id) {
		
		reservas.deleteById((long) id);
		return "redirect:/reservas";
	}

	@RequestMapping("/reservas/editar_reserva{id}")
	public String editar(Model model, @PathVariable int id) {
		Reserva r = reservas.findById(id);
		model.addAttribute("id",id);
		model.addAttribute("numeroSala",r.getSalaReservada().getNumeroSala());
		model.addAttribute("fecha",r.getFecha());
		model.addAttribute("hora",r.getHora());
		
		return "editar_reserva_template";
	}


	@PostMapping("/reservas/editar_reserva/guardar")
	public String editar(@RequestParam int id,@RequestParam String fecha, @RequestParam String hora) {
		
		reservas.updateReserva(fecha,hora, id);
		
		return "redirect:/reservas";
	}

	//metodos de gestion de reservas por parte de admin y personal de la biblioteca

	@RequestMapping("/{ruta}/reservas")
	public String admin_reservas(Model model, @PathVariable String ruta) {
		model.addAttribute("ruta", ruta);
		int totalReservas = (int) reservas.count();
		model.addAttribute("totalReservas",totalReservas);
		if(totalReservas>0) {
			model.addAttribute("tablaReservas",true);
			model.addAttribute("reserva",reservas.findAll());
		}
		return "reservas_template";
	}

	@RequestMapping("/{ruta}/reservas/nueva_reserva")
	public String nueva_reserva(Model model, @PathVariable String ruta) {
		model.addAttribute("ruta",ruta);
		model.addAttribute("sala",salas.findAll());
		return "nueva_reserva";
	}

	@PostMapping("/{ruta}/reservas/nueva_reserva/reservar")
	public String reservar(Model model,@RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido, @RequestParam int numSala ) {

		reservas.save(new Reserva(salas.findByNumeroSala(numSala),users.findByNameAndLastname(nombre,apellido),fecha,hora));
		return "redirect:/{ruta}/reservas";
	}

	@RequestMapping("/{ruta}/reservas/editar_reserva{id}")
	public String editar_reserva(Model model, @PathVariable String ruta,@PathVariable int id) {
		Reserva r = reservas.findById(id);

		model.addAttribute("ruta", ruta);
		model.addAttribute("idReserva", id);
		model.addAttribute("numeroSala",r.getSalaReservada().getNumeroSala());
		model.addAttribute("nombreAlumno",r.getUsuario().getName());
		model.addAttribute("apellidoAlumno", r.getUsuario().getLastname());
		model.addAttribute("fecha",r.getFecha());
		model.addAttribute("hora",r.getHora());

		return "editar_reserva_gestion_template";
	}

	@PostMapping("/{ruta}/reservas/editar_reserva{id}/guardar")
	public String guardar_reserva(@PathVariable int id, @RequestParam String fecha, @RequestParam String hora){

		reservas.updateReserva(fecha,hora,(long) id);

		return "redirect:/{ruta}/reservas";
	}

	@RequestMapping("/{ruta}/reservas/eliminar_reserva{id}")
	public String eliminar_reserva(@PathVariable int id) {

		reservas.deleteById((long) id);

		return "redirect:/{ruta}/reservas";
	}
}
