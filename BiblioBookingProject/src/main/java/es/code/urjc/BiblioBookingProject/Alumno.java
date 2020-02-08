package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long numExpediende;
	
	private String nombre,apellido;
	
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
}

