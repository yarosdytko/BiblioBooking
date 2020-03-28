package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

	@Autowired
	private ApiRestCommands apiRestCommands;

	@Autowired
	private UserRepository users;

	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("ruta", "admin");
		return "control_template";
	}
	
//Usuarios
	//vista general de usuarios
	@RequestMapping("/admin/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("ruta","admin");
		model.addAttribute("isAdmin",true);
		
		int totalAdministradores = users.countUsersByRoles("ROLE_ADMIN");
		int totalEmpleados = users.countUsersByRoles("ROLE_EMPLEADO");
		int totalAlumnos = users.countUsersByRoles("ROLE_ALUMNO");;

		model.addAttribute("totalAdministradores",totalAdministradores);
		if(totalAdministradores>0) {
			model.addAttribute("tablaAdministradores",true);
			model.addAttribute("administrador",users.findUsersByRoles("ROLE_ADMIN"));
		}

		model.addAttribute("totalEmpleados",totalEmpleados);
		if(totalEmpleados>0) {
			model.addAttribute("tablaEmpleados",true);
			model.addAttribute("empleado",users.findUsersByRoles("ROLE_EMPLEADO"));
		}

		model.addAttribute("totalAlumnos",totalAlumnos);
		if(totalAlumnos>0) {
			model.addAttribute("tablaAlumnos",true);
			model.addAttribute("al",users.findUsersByRoles("ROLE_ALUMNO"));
		}
		
		return "usuarios_template";
	}

	//nuevo usuario
	@RequestMapping("/admin/usuarios/nuevo_usuario")
	public String nuevo_usuario(Model model) {
		model.addAttribute("ruta","admin");
		return "nuevo_usuario";
	}
	
	@PostMapping("/admin/usuarios/nuevo_usuario/registrar")
	public String resgistrar(Model model,@RequestParam String tipoUsuario, @RequestParam String name, @RequestParam String lastname, @RequestParam String username, @RequestParam String email, @RequestParam String password) {

		if(!users.existsByNameAndLastname(name,lastname) && !users.existsByUsername(username)){
			if(tipoUsuario.equals("Alumno")){
				User user = new User(name,lastname,username,email,password,"ROLE_USER","ROLE_ALUMNO");
				apiRestCommands.newUser(user);
				users.save(user);
				//return "redirect:/admin/usuarios";
			}
			if (tipoUsuario.equals("Empleado")){
				User user = new User(name,lastname,username,email,password,"ROLE_USER","ROLE_EMPLEADO");
				apiRestCommands.newUser(user);
				users.save(user);
			}
			if (tipoUsuario.equals("Administrador")){
				User user = new User(name,lastname,username,email,password,"ROLE_USER","ROLE_ADMIN");
				apiRestCommands.newUser(user);
				users.save(user);
			}
			return "redirect:/admin/usuarios";
		} else {
			return "usuario_existe_template";
		}
	}
	//Editar usuarios
	@RequestMapping("/admin/usuarios/editar_{user}/{id}")
	public String editar_admin(Model model, @PathVariable String user, @PathVariable int id) {
		User u = users.findById((long)id);
		model.addAttribute("user",user);
		if (user.equals("alumno")){
			model.addAttribute("alumno",true);
			model.addAttribute("blocked",u.isBlocked());
		} else {
			model.addAttribute("alumno",false);
		}

		model.addAttribute("ruta","admin");
		model.addAttribute("id",id);
		model.addAttribute("name",u.getName());
		model.addAttribute("lastname",u.getLastname());
		model.addAttribute("username",u.getUsername());
		model.addAttribute("email",u.getEmail());
		
		return "editar_user_template";
	}

	//guardar cambios
	@PostMapping("/admin/usuarios/editar_{user}/guardar{id}")
	public String guardar_user(@PathVariable int id, @RequestParam String name, @RequestParam String lastname, @RequestParam String username, @RequestParam String email, @PathVariable String user) {
		
		users.updateUser(name, lastname, username, email,(long) id);
		
		return "redirect:/admin/usuarios";
	}

	//eliminar usuario
	@RequestMapping("/admin/usuarios/eliminar_{user}/{id}")
	public String eliminar_user(@PathVariable int id) {

		User user = users.findById(id);

		apiRestCommands.deleteUser(user);

		users.delete(user);
		
		return "redirect:/admin/usuarios";
	}

	//desbloquear alumno
	@RequestMapping("/admin/usuarios/desbloquear_alumno/{id}")
	public String unblock_alumno(@PathVariable int id) {

		users.blockUserALumno(false,id);

		return "redirect:/admin/usuarios";
	}

	//bloquear alumno
	@RequestMapping("/admin/usuarios/bloquear_alumno/{id}")
	public String block_alumno(@PathVariable int id) {

		users.blockUserALumno(true,id);

		return "redirect:/admin/usuarios";
	}
}
