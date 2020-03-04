package es.code.urjc.BiblioBooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private AlumnosRepository alumnos;

	@RequestMapping("/perfil")
	public String perfil(Model model) {
		List<Alumno> a = alumnos.findAll();
		model.addAttribute("nombre",a.get(0).getName());
		model.addAttribute("apellido",a.get(0).getApellido());
		model.addAttribute("userName",a.get(0).getUserName());
		model.addAttribute("email",a.get(0).getEmail());
		
		return "perfil";
	}
	
}
