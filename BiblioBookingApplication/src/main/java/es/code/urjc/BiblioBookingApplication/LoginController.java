package es.code.urjc.BiblioBookingApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class LoginController {

	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/login")
	public String login() {

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

		Objects.requireNonNull(cacheManager.getCache("salas")).invalidate();
		Objects.requireNonNull(cacheManager.getCache("reservas")).invalidate();
		Objects.requireNonNull(cacheManager.getCache("users")).invalidate();

		request.getSession().invalidate();


		return "redirect:/login";
	}
/*
	@RequestMapping("/blocked")
	public String blocked() throws Exception {
		return "blocked";
	}
 */
}
