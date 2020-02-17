package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Component
@SessionScope
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAdmin;
	
	private String nombre;
	private String apellido;
	private String userName;
	private String password;
	private boolean usuarioActivo;
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String apellido, String userName, String password) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.userName=userName;
		this.password=password;
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

	public boolean isUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(boolean usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
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
	        idAdmin, nombre, apellido, userName);
	  }

}
