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
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setUserName(userName);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}