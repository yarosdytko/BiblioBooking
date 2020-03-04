package es.code.urjc.BiblioBooking;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReservasController {

	@Autowired
	private ReservasRepository reservas;
	
	@Autowired
	private AlumnosRepository alumnos;

	@Autowired
	private SalasRepository salas;
	
	@RequestMapping("/reservas")
	public String reservas(Model model) {
		List<Alumno> a = alumnos.findAll();
		//Alumno alumno
		int totalReservas;
		if(!a.isEmpty()){
			Alumno alumno = a.get(0);
			totalReservas = (int) reservas.countByAlumno(alumno);

			if(totalReservas>0) {
				model.addAttribute("tablaReservas",true);
				model.addAttribute("reserva",reservas.findByAlumno(alumno));
			}
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
	public String reservar_alumno(Model model,@RequestParam String fecha, @RequestParam String hora, @RequestParam String nombre, @RequestParam String apellido, @RequestParam int numSala ) {

		reservas.save(new Reserva(salas.findByNumeroSala(numSala),alumnos.findByNameAndApellido(nombre, apellido),fecha,hora));
		return "redirect:/reservas";
	}
	
	@RequestMapping("/reservas/eliminar_reserva{idReserva}")
	public String alumno_eliminar_reserva(@PathVariable int idReserva) {
		
		reservas.deleteById((long) idReserva);
		
		return "redirect:/reservas";
	}
	
	@RequestMapping("/reservas/editar_reserva{idReserva}")
	public String editar(Model model, @PathVariable int idReserva) {
		Reserva r = reservas.findById(idReserva);
		
		model.addAttribute("idReserva", idReserva);
		model.addAttribute("numeroSala",r.getSalaReservada().getNumeroSala());
		model.addAttribute("fecha",r.getFecha());
		model.addAttribute("hora",r.getHora());
		
		return "editar_reserva_template";
	}
	
	@RequestMapping("/reservas/editar_reserva{idReserva}/guardar")
	public String editar(Model model, @PathVariable int idReserva,@RequestParam String fecha, @RequestParam String hora) {
		
		reservas.updateReserva(fecha,hora,(long) idReserva);
		
		return "redirect:/reservas";
	}

	//metodos de gestion de reservas por parte de admin y personal de la biblioteca

	@RequestMapping("/{ruta}/reservas")
	public String admin_reservas(Model model, @PathVariable String ruta) {
		/*if(ruta.equals("admin")){
			model.addAttribute("ruta", "admin");
		} else {
			model.addAttribute("ruta", "gestion");
		}*/
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
		//Alumno alumno = alumnos.findByNameAndApellido(nombre, apellido);
		//Sala sala = salas.findByNumeroSala(numSala);

		reservas.save(new Reserva(salas.findByNumeroSala(numSala),alumnos.findByNameAndApellido(nombre, apellido),fecha,hora));
		return "redirect:/{ruta}/reservas";
	}

	@RequestMapping("/{ruta}/reservas/editar_reserva{idReserva}")
	public String editar_reserva(Model model, @PathVariable String ruta,@PathVariable int idReserva) {
		Reserva r = reservas.findById(idReserva);

		model.addAttribute("ruta", ruta);
		model.addAttribute("idReserva", idReserva);
		model.addAttribute("numeroSala",r.getSalaReservada().getNumeroSala());
		model.addAttribute("nombreAlumno",r.getAlumno().getName());
		model.addAttribute("apellidoAlumno", r.getAlumno().getApellido());
		model.addAttribute("fecha",r.getFecha());
		model.addAttribute("hora",r.getHora());

		return "editar_reserva_gestion_template";
	}

	@PostMapping("/{ruta}/reservas/editar_reserva{idReserva}/guardar")
	public String guardar_reserva(@PathVariable int idReserva, @RequestParam String fecha, @RequestParam String hora){

		reservas.updateReserva(fecha,hora,(long) idReserva);

		return "redirect:/{ruta}/reservas";
	}

	@RequestMapping("/{ruta}/reservas/eliminar_reserva{idReserva}")
	public String eliminar_reserva(@PathVariable int idReserva) {

		reservas.deleteById((long) idReserva);

		return "redirect:/{ruta}/reservas";
	}
}
