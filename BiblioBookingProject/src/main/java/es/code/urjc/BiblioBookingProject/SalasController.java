package es.code.urjc.BiblioBookingProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalasController {

    @Autowired
    private SalasRepository salas;

    @RequestMapping("/{ruta}/salas")
    public String salas(Model model, @PathVariable String ruta) {
        model.addAttribute("ruta", ruta);
        int totalSalas = (int) salas.count();
        model.addAttribute("totalSalas",totalSalas);
        if(totalSalas>0) {
            model.addAttribute("tablaSalas",true);
            model.addAttribute("sala",salas.findAll());
        }

        return "salas_template";
    }

    @RequestMapping("/{ruta}/salas/nueva_sala")
    public String nueva_sala(Model model, @PathVariable String ruta) {
        model.addAttribute("ruta",ruta);
        return "nueva_sala";
    }

    @PostMapping("/{ruta}/salas/nueva_sala/crear")
    public String crear_sala(Model model, @PathVariable String ruta,@RequestParam int numeroSala, @RequestParam int capacidadMaxima, @RequestParam int numeroMesas, @RequestParam int numeroSillas) {
        if(!salas.existsByNumeroSala(numeroSala)) {
            salas.save(new Sala(numeroSala,capacidadMaxima,numeroMesas,numeroSillas));
            return "redirect:/{ruta}/salas";
        } else {
            model.addAttribute("ruta",ruta);
            return "sala_existe";
        }
    }

    @RequestMapping("/{ruta}/salas/editar_sala{idSala}")
    public String editar_sala(Model model, @PathVariable String ruta, @PathVariable int idSala) {
        Sala s = salas.getOne((long) idSala);

        model.addAttribute("ruta", ruta);
        model.addAttribute("numeroSala",s.getNumeroSala());
        model.addAttribute("sala", s);
        model.addAttribute("capacidad",s.getCapacidadMaxima());
        model.addAttribute("mesas",s.getNumeroMesas());
        model.addAttribute("sillas",s.getNumeroSillas());

        return "editar_sala_template";
    }

    @PostMapping("/{ruta}/salas/guardar_sala{idSala}/guardar")
    public String guardar_sala(@PathVariable int idSala, @RequestParam int capacidad, @RequestParam int mesas, @RequestParam int sillas){
        salas.updateSala(capacidad,mesas,sillas,(long) idSala);

        return "redirect:/{ruta}/salas";
    }

    @RequestMapping("/{ruta}/salas/eliminar_sala{idSala}")
    public String eliminar_sala(@PathVariable int idSala){
        salas.deleteById((long) idSala);

        return "redirect:/{ruta}/salas";
    }
}
