package es.code.urjc.BiblioBookingApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GestionController {
	
	@Autowired
	private ApiRestCommands apiRestCommands;
	
	@Autowired
	private UserRepository usuarios;
	
	@Autowired ReservasRepository reservas;

	@RequestMapping("/gestion")
	public String gestion(Model model) {
		model.addAttribute("ruta", "gestion");
		return "control_template";
	}

	@RequestMapping("/gestion/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("ruta","gestion");
		model.addAttribute("isAdmin",false);
		int totalAlumnos = (int) usuarios.countUsersByRoles("ROLE_ALUMNO");
		model.addAttribute("totalAlumnos",totalAlumnos);
		if(totalAlumnos>0) {
			model.addAttribute("tablaAlumnos",true);
			model.addAttribute("al",usuarios.findUsersByRoles("ROLE_ALUMNO"));
		}
		
		return "usuarios_template";
	}

	@RequestMapping("/gestion/usuarios/nuevo_usuario")
	public String nuevo_usuario(Model model) {

		return "nuevo_usuario_gestion";
	}

	@PostMapping("/gestion/usuarios/nuevo_usuario/registrar")
	public String resgistrar(Model model, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String uname, @RequestParam String email, @RequestParam String password) {
		if(!usuarios.existsByNameAndLastname(nombre,apellido)) {
			
			User user = new User(nombre,apellido,uname,email,password,"ROLE_USER","ROLE_ALUMNO");
			
			apiRestCommands.newUser(user);
			
			usuarios.save(user);
			return "redirect:/gestion/usuarios";
		} else {
			model.addAttribute("ruta","gestion");
			return "usuario_existe_template";
		}
	}

	@RequestMapping("/gestion/usuarios/editar_{user}/{id}")
	public String editar_alumno(Model model, @PathVariable String user, @PathVariable int id) {
		User u = usuarios.findById((long)id);

		model.addAttribute("user",user);
		model.addAttribute("alumno",true);
		model.addAttribute("blocked",u.isBlocked());
		model.addAttribute("ruta","gestion");
		model.addAttribute("id",id);
		model.addAttribute("name",u.getName());
		model.addAttribute("lastname",u.getLastname());
		model.addAttribute("username",u.getUsername());
		model.addAttribute("email",u.getEmail());

		return "editar_user_template";
	}

	//guardar cambios
	@PostMapping("/gestion/usuarios/editar_{user}/guardar{id}")
	public String guardar_user(@PathVariable int id, @RequestParam String name, @RequestParam String lastname, @RequestParam String username, @RequestParam String email, @PathVariable String user) {

		usuarios.updateUser(name, lastname, username, email,(long) id);

		User u = usuarios.findById((long)id);

		apiRestCommands.modifyUser(u);

		return "redirect:/gestion/usuarios";
	}

	//eliminar usuario
	@RequestMapping("/gestion/usuarios/eliminar_{user}/{id}")
	public String eliminar_user(@PathVariable int id) {
		
		User user = usuarios.findById(id);
		
		apiRestCommands.deleteUser(user);
		
		List<Reserva> r = reservas.findByUsuario(user);
		
		if(!r.isEmpty()) {
			for(int i=0;i<r.size();i++) {
				
				reservas.deleteById(r.get(i).getId());
			}
		}

		usuarios.delete(user);

		return "redirect:/gestion/usuarios";
	}

	//desbloquear alumno
	@RequestMapping("/gestion/usuarios/desbloquear_alumno/{id}")
	public String unblock_alumno(@PathVariable int id) {

		usuarios.blockUserALumno(false,id);

		return "redirect:/gestion/usuarios";
	}

	//bloquear alumno
	@RequestMapping("/gestion/usuarios/bloquear_alumno/{id}")
	public String block_alumno(@PathVariable int id) {

		usuarios.blockUserALumno(true,id);

		return "redirect:/gestion/usuarios";
	}
}
