package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnosRepository extends JpaRepository<Alumno, Long> {
	
	Alumno findByUserNameAndPassword(String userName, String password);
	
}
