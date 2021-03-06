package es.code.urjc.BiblioBookingApplication;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="salas")
public class Sala {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private int numeroSala;
	private int capacidadMaxima;
	private int numeroMesas;
	private int numeroSillas;
	
	@OneToMany(mappedBy = "sala",cascade = CascadeType.ALL)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sala sala = (Sala) o;
		return id == sala.id &&
				numeroSala == sala.numeroSala &&
				capacidadMaxima == sala.capacidadMaxima &&
				numeroMesas == sala.numeroMesas &&
				numeroSillas == sala.numeroSillas &&
				reservas.equals(sala.reservas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroSala, capacidadMaxima, numeroMesas, numeroSillas, reservas);
	}
}
