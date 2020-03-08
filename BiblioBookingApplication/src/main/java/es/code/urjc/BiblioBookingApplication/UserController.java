package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Autowired
	private UserRepository usuarios;

	@RequestMapping("/perfil")
	public String perfil(Model model, HttpServletRequest request) {

		User user = usuarios.findByName(request.getUserPrincipal().getName());

		model.addAttribute("nombre",user.getName());
		model.addAttribute("apellido",user.getLastname());
		model.addAttribute("userName",user.getUsername());
		model.addAttribute("email",user.getEmail());
		
		return "perfil";
	}


	
}
