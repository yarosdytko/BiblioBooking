package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@RequestMapping("/register")
	public String registro() {
		return "registro";
	}
	
	@PostMapping("/register/registrar")
	public  String registrar(Model model,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String uname, @RequestParam String email, @RequestParam String passwd) {
		if(alumnos.findByUserName(uname)!=null) {
			model.addAttribute("ruta","/register");
			return "usuario_existe";
		} else {
			alumnos.save(new Alumno(nombre,apellido,uname,email,passwd));
			return "usuario_registrado";
		}
	}

}
