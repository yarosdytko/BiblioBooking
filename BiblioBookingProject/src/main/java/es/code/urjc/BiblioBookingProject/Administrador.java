package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrador {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long idAdmin;
	
	private String name;

	private String apellido;
	private String userName;
	private String email;
	private String password;
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String apellido, String userName, String email, String password) {
		this.name=nombre;
		this.apellido=apellido;
		this.email=email;
		this.userName=userName;
		this.password=password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	  @Override
	  public String toString() {
	    return String.format(
	        "Customer[id=%d, firstName='%s', lastName='%s', username='%s']",
	        idAdmin, name, apellido, userName);
	  }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
