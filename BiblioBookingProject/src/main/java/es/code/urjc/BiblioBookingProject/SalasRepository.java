package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalasRepository extends JpaRepository<Sala, Long>  {
	
	Sala findByNumeroSala(int numeroSala);
	
}
