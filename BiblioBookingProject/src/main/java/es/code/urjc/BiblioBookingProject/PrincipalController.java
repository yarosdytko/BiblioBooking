package es.code.urjc.BiblioBookingProject;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrincipalController{
	
	@Autowired
	private AdministradoresRepository administradores;
	
	@Autowired
	private AlumnosRepository alumnos;
	
	@Autowired 
	private EmpleadosRepository empleados;
	
	@PostConstruct
	public void init() {
		administradores.save(new Administrador("Ignacio","Perez","NachoPerez","1234"));
		alumnos.save(new Alumno("Andres","Lum","AndresLum","1234"));
		alumnos.save(new Alumno("Luis","Fernandez","LuisFernandez","1234"));
		empleados.save(new Empleado("Juan","Lopez","JuanLopez","1234"));
	}
	
	@RequestMapping("/")
	public String index(Model model, HttpSession sesion) {
		
		generarMenu(model, sesion);
		
		return "index";
	}
	
	@RequestMapping("/informacion")
	public String informacion(Model model, HttpSession sesion) {
		
		generarMenu(model,sesion);
		return "informacion";
	}
	
	@RequestMapping("/contacto")
	public String contacto(Model model, HttpSession sesion) {
		generarMenu(model,sesion);
		return "contacto";
	}
	
	@RequestMapping("/reservas")
	public String reservas(Model model, HttpSession sesion) {
		return "reservas";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model, HttpSession sesion) {
		
		generarMenu(model,sesion);
		if(sesion.getAttribute("userType")!="visitante") {
			model.addAttribute("usuarioData",true);
			model.addAttribute("nombre", sesion.getAttribute("nombre"));
			model.addAttribute("apellido", sesion.getAttribute("apellido"));
			model.addAttribute("nombreUsuario", sesion.getAttribute("userName"));
			model.addAttribute("tipoUsuario", sesion.getAttribute("userType"));
		} else {
			model.addAttribute("usuarioData",false);
		}
		
		return "perfil";
	}
	
	@RequestMapping("/register")
	public String register(Model model, HttpSession sesion) {
		generarMenu(model,sesion);
		return "register";
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpSession sesion) {
		if(sesion.getAttribute("userType")!="visitante") {
			
		}
		model.addAttribute("silent", false);
		return "login";
	}

	@PostMapping("/login")
	public String index(Model model,@RequestParam String uname, @RequestParam String passwd, HttpSession sesion) {
		Administrador adm = administradores.findByUserNameAndPassword(uname, passwd);
		Alumno alumno = alumnos.findByUserNameAndPassword(uname,passwd);
		Empleado empleado = empleados.findByUserNameAndPassword(uname, passwd);
		
		/*model.addAttribute("userOK",false);
		model.addAttribute("userNoOK",false);*/
		if(adm!=null) {
			sesion.setAttribute("nombre",adm.getNombre());
			sesion.setAttribute("apellido", adm.getApellido());
			sesion.setAttribute("userName", adm.getUserName());
			sesion.setAttribute("userType", "administrador");
		}
		if(alumno!=null) {
			sesion.setAttribute("nombre",alumno.getNombre());
			sesion.setAttribute("apellido", alumno.getApellido());
			sesion.setAttribute("userName", alumno.getUserName());
			sesion.setAttribute("userType", "alumno");
		} 
		if(empleado!=null){
			sesion.setAttribute("nombre",alumno.getNombre());
			sesion.setAttribute("apellido", alumno.getApellido());
			sesion.setAttribute("userName", alumno.getUserName());
			sesion.setAttribute("userType", "empleado");
			
		} else {
			sesion.setAttribute("userType", "visitante");
			model.addAttribute("userNoOk",true);
			
		}
		return "login";
	}
	
	@PostMapping("/register")
	public String index(Model model,@RequestParam String nombre, @RequestParam String apellido, String uname, String email, String passwd, HttpSession sesion) {
		
		
		
		return "register";
	}
	
	private void generarMenu(Model model, HttpSession sesion) {
		model.addAttribute("login",true);
		if(sesion.getAttribute("userType")=="administrador") {
			model.addAttribute("alumno",false);
			model.addAttribute("noVisitante",true);
			model.addAttribute("login",false);
			model.addAttribute("empleado",false);
			model.addAttribute("admin",true);
		}
		if(sesion.getAttribute("userType")=="alumno") {
			model.addAttribute("alumno",true);
			model.addAttribute("noVisitante",true);
			model.addAttribute("login",false);
			model.addAttribute("empleado",false);
			model.addAttribute("admin",false);
		}
		if(sesion.getAttribute("userType")=="empleado") {
			model.addAttribute("alumno",false);
			model.addAttribute("noVisitante",true);
			model.addAttribute("login",false);
			model.addAttribute("empleado",true);
			model.addAttribute("admin",false);
		}
		
	}
}
