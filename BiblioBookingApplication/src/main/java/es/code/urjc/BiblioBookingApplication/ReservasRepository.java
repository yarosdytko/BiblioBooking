package es.code.urjc.BiblioBookingApplication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
	long countByUsuario(User user);
	List<Reserva> findByUsuario(User user);
	Reserva findById(long id);

	@Modifying
	@Query("UPDATE Reserva r SET r.fecha = ?1, r.hora = ?2 WHERE r.id = ?3")
	@Transactional
	void updateReserva(String fecha, String hora, long id);
}
