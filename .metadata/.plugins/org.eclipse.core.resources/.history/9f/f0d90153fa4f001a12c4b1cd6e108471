package es.code.urjc.BiblioBookingProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long numeroSala;
	
	private int capacidadMaxima;
	private int numeroMesas;
	private int numeroSillas;
	
	public Sala() {
		
	}
	
	public Sala(int capacidad, int numMesas, int numSillas) {
		this.capacidadMaxima = capacidad;
		this.numeroMesas = numMesas;
		this.numeroSillas = numSillas;
	}
}
