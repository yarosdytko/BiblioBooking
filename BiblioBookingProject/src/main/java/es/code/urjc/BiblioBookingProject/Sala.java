package es.code.urjc.BiblioBookingProject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idSala;
	
	private int numeroSala;
	private int capacidadMaxima;
	private int numeroMesas;
	private int numeroSillas;
	
	@OneToMany
	private List<Reserva> reservas;
	
	public Sala() {
		
	}
	
	public Sala(int numeroSala, int capacidad, int numMesas, int numSillas) {
		this.setNumeroSala(numeroSala);
		this.setCapacidadMaxima(capacidad);
		this.setNumeroMesas(numMesas);
		this.setNumeroSillas(numSillas);
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public int getNumeroMesas() {
		return numeroMesas;
	}

	public void setNumeroMesas(int numeroMesas) {
		this.numeroMesas = numeroMesas;
	}

	public int getNumeroSillas() {
		return numeroSillas;
	}

	public void setNumeroSillas(int numeroSillas) {
		this.numeroSillas = numeroSillas;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
}
