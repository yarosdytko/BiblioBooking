package es.code.urjc.BiblioBookingProject;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController{
	
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		
		if(session.isNew()) {
			model.addAttribute("sesion","Sesion nueva");
		} else {
			model.addAttribute("sesion","Sesion actual");
		}
		
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
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
}
