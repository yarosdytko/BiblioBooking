package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuariosController {

    @Autowired
    private AlumnosRepository alumnos;

    @Autowired
    private EmpleadosRepository empleados;

    @Autowired
    private AdministradoresRepository administradores;

    @RequestMapping("/{ruta}/usuarios/editar_alumno{expediente}")
    public String editar_alumno(Model model, @PathVariable String ruta, @PathVariable int expediente) {

        Alumno alu = alumnos.getOne((long) expediente);
        model.addAttribute("ruta",ruta);
        model.addAttribute("nombre",alu.getName());
        model.addAttribute("apellido",alu.getApellido());
        model.addAttribute("userName",alu.getUserName());
        model.addAttribute("email",alu.getEmail());
        model.addAttribute("bloqueado",alu.isBloqueado());

        return "editar_alumno_template";
    }

    @PostMapping("/{ruta}/usuarios/editar_alumno{expediente}/guardar")
    public String guardar_alumno(@PathVariable int expediente, @RequestParam String nombre , @RequestParam String apellido, @RequestParam String userName, @RequestParam String email) {

        alumnos.actualizarAlumno(nombre, apellido, userName, email,(long) expediente);

        return "redirect:/{ruta}/usuarios";
    }

    @RequestMapping("/{ruta}/usuarios/bloquear_alumno{expediente}")
    public String bloquear_alumno(@PathVariable int expediente) {

        alumnos.bloqueoAlumno(true, expediente);

        return "redirect:/{ruta}/usuarios";
    }

    @RequestMapping("/{ruta}/usuarios/desbloquear_alumno{expediente}")
    public String desbloquear_alumno(@PathVariable int expediente) {

        alumnos.bloqueoAlumno(false, expediente);

        return "redirect:/{ruta}/usuarios";
    }

    @RequestMapping("/{ruta}/usuarios/eliminar_alumno{expediente}")
    public String eliminar_alumno(@PathVariable int expediente) {

        alumnos.deleteById((long) expediente);

        return "redirect:/{ruta}/usuarios";
    }
}
