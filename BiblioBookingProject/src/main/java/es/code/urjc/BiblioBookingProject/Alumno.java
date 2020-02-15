package es.code.urjc.BiblioBookingProject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Component
@SessionScope
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long numExpediende;
	
	private String nombre,apellido;
	private boolean usuarioLogueado;
	
	@OneToMany
	private List<Reserva> reservas;
	
	public Alumno() {
		
	}
	
	public Alumno(String nombre, String apellido) {
		this.setApellido(apellido);
		this.setNombre(nombre);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean usuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(boolean usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
}
