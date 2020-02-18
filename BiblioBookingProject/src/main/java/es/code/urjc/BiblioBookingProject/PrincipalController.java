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
	
	@Autowired
	private ReservasRepository reservas;
	
	@Autowired 
	private SalasRepository salas;
	
	@PostConstruct
	public void init() {
		administradores.save(new Administrador("Ignacio","Perez","NachoPerez","1234"));
		alumnos.save(new Alumno("Andres","Lum","AndresLum","andresLum@mail.com","1234"));
		alumnos.save(new Alumno("Luis","Fernandez","LuisFernandez","luFernandez@mail.com","1234"));
		Alumno alumno = new Alumno("David","Dminguez","DDominguez","d.dominguez@mail.com","1234");
		alumnos.save(alumno);
		empleados.save(new Empleado("Juan","Lopez","JuanLopez","1234"));
		
		Sala sala1 = new Sala(10,2,5);
		
		salas.save(sala1);
		reservas.save(new Reserva(sala1,alumno));
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
		generarMenu(model, sesion);
		return "reservas";
	}
	
	@RequestMapping("/reservas/mis_reservas")
	public String mis_reservas(Model model, HttpSession sesion) {
		generarMenu(model, sesion);
		
		return "reservas";
	}
	
	@RequestMapping("/reservas/gestion_reservas")
	public String gestion_reservas(Model model, HttpSession sesion) {
		generarMenu(model, sesion);
		
		model.addAttribute("reservas",reservas.findAll());
		
		return "gestion_reservas";
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
		//generarMenu(model, sesion);
		
		return "login";
	}

	@PostMapping("/login")
	public void login(Model model,@RequestParam String uname, @RequestParam String passwd, HttpSession sesion) {
		Administrador adm = administradores.findByUserNameAndPassword(uname, passwd);
		Alumno alumno = alumnos.findByUserNameAndPassword(uname,passwd);
		Empleado empleado = empleados.findByUserNameAndPassword(uname, passwd);
		
		if(adm!=null) {
			sesion.setAttribute("nombre",adm.getNombre());
			sesion.setAttribute("apellido", adm.getApellido());
			sesion.setAttribute("userName", adm.getUserName());
			sesion.setAttribute("userType", "administrador");
			admin(model,sesion);
		} else if(alumno!=null) {
			sesion.setAttribute("nombre",alumno.getNombre());
			sesion.setAttribute("apellido", alumno.getApellido());
			sesion.setAttribute("userName", alumno.getUserName());
			sesion.setAttribute("userType", "alumno");
			reservas(model,sesion);
		} else if(empleado!=null){
			sesion.setAttribute("nombre", empleado.getNombre());
			sesion.setAttribute("apellido", empleado.getApellido());
			sesion.setAttribute("userName", empleado.getUserName());
			sesion.setAttribute("userType", "empleado");
			gestion(model,sesion);
		} else {
			sesion.setAttribute("userType", "visitante");
			model.addAttribute("userNoOk",true);
			login(model,sesion);
		}
	}
	
	@PostMapping("/register")
	public String index(Model model,@RequestParam String nombre, @RequestParam String apellido, String uname, String email, String passwd, HttpSession sesion) {
		
		if(alumnos.findByUserName(uname)!=null) {
			model.addAttribute("username", uname);
			return "existing_user";
		} else {
			alumnos.save(new Alumno(nombre,apellido,uname,email,passwd));
			model.addAttribute("username",uname);
			return "register_ok";
		}
		
	}
	@RequestMapping("/admin")
	public String admin(Model model, HttpSession sesion) {
		
		generarMenu(model, sesion);
		
		return "gestion";
	}
	
	@RequestMapping("/gestion")
	public String gestion(Model model, HttpSession sesion) {
		
		generarMenu(model, sesion);
		
		return "gestion";
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
