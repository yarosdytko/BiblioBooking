package es.code.urjc.BiblioBooking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
	long countByAlumno(Alumno alumno);
	List<Reserva> findByAlumno(Alumno alumno);
	Reserva findById(long id);

	@Modifying
	@Query("UPDATE Reserva r SET r.fecha = ?1, r.hora = ?2 WHERE r.idReserva = ?3")
	@Transactional
	void updateReserva(String fecha, String hora, long idReserva);
}
