package es.code.urjc.BiblioBookingApplication;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames="salas")
public interface SalasRepository extends JpaRepository<Sala, Long>  {

	@SuppressWarnings("unchecked")
	@CacheEvict(value="salas",allEntries=true)
	Sala save(Sala sala);

	@Cacheable
	Sala findByNumeroSala(int numeroSala);

	@Cacheable
	boolean existsByNumeroSala(int numeroSala);

	@Cacheable
	Sala getById(long id);

	@Cacheable
	List<Sala> findAll();

	long count();

	@CacheEvict(value="salas",allEntries=true)
	@Modifying
	@Query("UPDATE Sala s SET s.capacidadMaxima = ?1, s.numeroMesas = ?2, s.numeroSillas = ?3 WHERE s.id = ?4")
	@Transactional
	void updateSala(int capacidad, int mesas, int sillas, long id);

	@CacheEvict(value="salas",allEntries=true)
	@Modifying
	@Query("DELETE Sala s WHERE s.id = ?1")
	@Transactional
	void borrarSala(long id);
}
