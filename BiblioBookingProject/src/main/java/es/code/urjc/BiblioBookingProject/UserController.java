package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private AlumnosRepository alumnos;

	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Alumno alumno = alumnos.findByNameAndApellido("Yaroslav", "Dytko");
		model.addAttribute("nombre",alumno.getName());
		model.addAttribute("apellido",alumno.getApellido());
		model.addAttribute("userName",alumno.getUserName());
		model.addAttribute("email",alumno.getEmail());
		
		return "perfil";
	}
	

}
