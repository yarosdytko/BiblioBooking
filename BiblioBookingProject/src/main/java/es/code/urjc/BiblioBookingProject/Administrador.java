package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAdmin;
	
	private String nombre;
	private String apellido;
	private String userName;
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String apellido, String userName) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.userName = userName;
	}

}
