package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

	@Autowired
	private ApiRestCommands apiRestCommands;
	
	@Autowired
	private UserRepository users;
	
	@RequestMapping("/register")
	public String registro() {
		return "registro";
	}
	
	@PostMapping("/register/registrar")
	public  String registrar(Model model,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String uname, @RequestParam String email, @RequestParam String passwd) {
		if(users.findByusername(uname)!=null) {
			model.addAttribute("ruta","/register");
			return "usuario_existe";
		} else {

			User user = new User(nombre,apellido,uname,email,passwd,"ROLE_USER","ROLE_ALUMNO");

			apiRestCommands.newUser(user);

			users.save(user);
			return "usuario_registrado";
		}
	}

}
