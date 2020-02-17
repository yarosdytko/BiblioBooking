package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired
	private AdministradoresRepository administradores;
	
	@Autowired
	private AlumnosRepository alumnos;
	
	/*@Autowired 
	private EmpleadosRepository empleados;*/
	
	@PostConstruct
	public void init() {
		administradores.save(new Administrador("Ignacio","Perez","NachoPerez","pass123"));
		alumnos.save(new Alumno("Andres","Lum","AndresLum","1234"));
		alumnos.save(new Alumno("Luis","Fernandez","LuisFernandez","5678"));
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("silent", false);
		return "login";
	}

	@PostMapping("/login")
	public String index(Model model, @RequestParam String uname, @RequestParam String passwd) {
		Administrador adm = administradores.findByUserNameAndPassword(uname, passwd);
		Alumno alumno = alumnos.findByUserNameAndPassword(uname,passwd);
		
		model.addAttribute("userOK",false);
		model.addAttribute("userNoOK",false);
		if(adm!=null) {
			model.addAttribute("userOK",true);
			model.addAttribute("nombre",adm.getNombre());
			model.addAttribute("apellido", adm.getApellido());
			model.addAttribute("userName", adm.getUserName());
		}
		if(alumno!=null) {
			model.addAttribute("userOK",true);
			model.addAttribute("nombre",alumno.getNombre());
			model.addAttribute("apellido", alumno.getApellido());
			model.addAttribute("userName", alumno.getUserName());
		} else {
			model.addAttribute("userNoOk",true);
		}
		
		return "login";
		
	}
}
