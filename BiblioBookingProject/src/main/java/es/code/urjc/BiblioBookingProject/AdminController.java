package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@Autowired
	private AdministradoresRepository administradores;
	
	@PostConstruct
	public void init() {
		administradores.save(new Administrador("Andres","Lum","AndresAdmin"));
	}

	@RequestMapping("/admin")
	public String index(Model model) {
		
		
		
		return "admin";
	}
	
}

