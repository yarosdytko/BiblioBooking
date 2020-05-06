package es.code.urjc.BiblioBookingApplication;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="reservas")
public class Reserva {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private User usuario;
	
	private String fecha;
	private String hora;
	
	public Reserva() {
		
	}
	
	public Reserva(Sala sala, User usuario, String fecha, String hora) {
		this.sala = sala;
		this.usuario = usuario;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public void updateReserva(String hora, String fecha) {
		this.setHora(hora);
		this.setFecha(fecha);
	}

	public long getId() {
		return id;
	}

	public Sala getSalaReservada() {
		return sala;
	}

	public void setSalaReservada(Sala sala) {
		this.sala = sala;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User alumno) {
		this.usuario = alumno;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reserva reserva = (Reserva) o;
		return id == reserva.id &&
				sala.equals(reserva.sala) &&
				usuario.equals(reserva.usuario) &&
				fecha.equals(reserva.fecha) &&
				hora.equals(reserva.hora);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sala, usuario, fecha, hora);
	}
}
