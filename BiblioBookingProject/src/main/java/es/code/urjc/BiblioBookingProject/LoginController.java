package es.code.urjc.BiblioBookingProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("silent", false);
		return "login";
	}

	@PostMapping("/login")
	public String index(Model model, @RequestParam String uname, @RequestParam String passwd) {
		model.addAttribute("silent", true);
		model.addAttribute("nombre", uname);
		model.addAttribute("passwd", passwd);
		
		return "login";
		
	}
}
