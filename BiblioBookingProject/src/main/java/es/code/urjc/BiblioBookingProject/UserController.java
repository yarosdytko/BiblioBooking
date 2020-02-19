package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	//private AlumnosRepository alumnos;
	
	@PostConstruct
	public void init() {
		
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		
		return "perfil";
	}
	
	@RequestMapping("/reservas")
	public String reservas() {
		return "reservas";
	}
}
