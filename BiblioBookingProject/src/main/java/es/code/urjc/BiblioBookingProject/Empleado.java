package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEmpleado;
	
	private String nombre;
	private String apellido;
	private String userName;
	
	public Empleado() {
		
	}
	
	public Empleado(String nombre, String apellido, String userName) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.userName = userName;
	}
}
