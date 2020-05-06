package es.code.urjc.BiblioBookingApplication;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames="reservas")
public interface ReservasRepository extends JpaRepository<Reserva, Long> {

	long countByUsuario(User user);

	@SuppressWarnings("unchecked")
	@CacheEvict(allEntries=true)
	Reserva save(Reserva reserva);

	@CacheEvict(allEntries=true)
	void deleteById(long id);

	@Cacheable
	List<Reserva> findByUsuario(User user);

	@Cacheable
	List<Reserva> findBySala(Sala sala);

	@Cacheable
	List<Reserva> findAll();

	Reserva findById(long id);

	@CacheEvict(value="reservas",allEntries=true)
	@Modifying
	@Query("UPDATE Reserva r SET r.fecha = ?1, r.hora = ?2 WHERE r.id = ?3")
	@Transactional
	void updateReserva(String fecha, String hora, long id);
}
