package es.code.urjc.BiblioBookingProject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long numExpediende;
	
	private String nombre,apellido,userName,email,password;
	
	@OneToMany(mappedBy="alumno")
	private List<Reserva> reservas;
	
	public Alumno() {
		
	}
	
	public Alumno(String nombre, String apellido, String userName,String email, String password) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.userName=userName;
		this.email = email;
		this.password=password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

