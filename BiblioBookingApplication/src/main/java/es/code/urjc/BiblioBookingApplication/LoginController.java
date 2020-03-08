package es.code.urjc.BiblioBookingApplication;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {

		//CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		//model.addAttribute("token", token.getToken());
		return "login";
	}



	@RequestMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}
	
}
