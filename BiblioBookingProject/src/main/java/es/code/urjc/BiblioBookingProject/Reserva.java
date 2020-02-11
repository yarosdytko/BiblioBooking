package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idReserva;
	
	private Sala salaReservada;
	private Alumno alumno;
	
	public Reserva() {
		
	}
	
	public Reserva(Sala sala, Alumno alumno) {
		this.setSalaReservada(sala);
		this.setAlumno(alumno);
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
