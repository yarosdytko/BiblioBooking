package es.code.urjc.BiblioBookingApplication;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@RequestMapping("/blocked")
	public String blocked() throws Exception {
		return "blocked";
	}
	
}
