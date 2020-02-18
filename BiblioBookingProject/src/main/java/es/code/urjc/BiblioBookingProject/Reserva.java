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
	
	public Reserva() {
		
	}
	
	public Reserva(Sala sala, Alumno alumno) {
		this.salaReservada = sala;
		this.alumno = alumno;
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
	
	
	
}
