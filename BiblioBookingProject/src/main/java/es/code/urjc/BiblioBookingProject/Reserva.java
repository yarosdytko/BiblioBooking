package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idReserva;
	
	@OneToOne
	private Sala salaReservada;
	
	@ManyToOne
	private Alumno alumno;
	
	private String fecha;
	private String hora;
	
	public Reserva() {
		
	}
	
	public Reserva(Sala sala, Alumno alumno, String fecha, String hora) {
		this.salaReservada = sala;
		this.alumno = alumno;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public void updateReserva(String hora, String fecha) {
		this.setHora(hora);
		this.setFecha(fecha);
	}
	
	public Sala getSalaReservada() {
		return salaReservada;
	}

	public void setSalaReservada(Sala salaReservada) {
		this.salaReservada = salaReservada;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	
}
