package es.code.urjc.BiblioBookingProject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
	long countByAlumno(Alumno alumno);
	List<Reserva> findByAlumno(Alumno alumno);
	Reserva findById(long id);
}
