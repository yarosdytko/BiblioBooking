package es.code.urjc.BiblioBookingProject;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController{
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/informacion")
	public String informacion() {
		return "informacion";
	}
	
	
}
