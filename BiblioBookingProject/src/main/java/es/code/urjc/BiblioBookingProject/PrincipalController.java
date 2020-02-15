package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController{
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@PostConstruct
	public void init() {
		alumnos.save(new Alumno("Andres","Lum"));
		alumnos.save(new Alumno("Luis","Fernandez"));
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/informacion")
	public String informacion() {
		return "informacion";
	}
	
	@RequestMapping("/contacto")
	public String contacto() {
		return "contacto";
	}
	
	@RequestMapping("/reservas")
	public String reservas() {
		return "reservas";
	}
	
	@RequestMapping("/perfil")
	public String perfil() {
		return "perfil";
	}

	@RequestMapping("/alumnos")
	public String alumnos(Model model) {
		 
		model.addAttribute("alumnos", alumnos.findAll());
		 
		return "alumnos_template";
	}
}
