package es.code.urjc.BiblioBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SalasRepository extends JpaRepository<Sala, Long>  {
	
	Sala findByNumeroSala(int numeroSala);
	boolean existsByNumeroSala(int numeroSala);

	@Modifying
	@Query("UPDATE Sala s SET s.capacidadMaxima = ?1, s.numeroMesas = ?2, s.numeroSillas = ?3 WHERE s.idSala = ?4")
	@Transactional
	void updateSala(int capacidad, int mesas, int sillas, long idSala);
	
}
