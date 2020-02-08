package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlumnosController{
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@PostConstruct
	public void init() {
		alumnos.save(new Alumno("Andres","Lum"));
		alumnos.save(new Alumno("Luis","Fernandez"));
	}
	

	 @RequestMapping("/alumnos")
	 public String index(Model model) {
		 
		 model.addAttribute("alumnos", alumnos.findAll());
		 
		 return "alumnos_template";
	 }
}
