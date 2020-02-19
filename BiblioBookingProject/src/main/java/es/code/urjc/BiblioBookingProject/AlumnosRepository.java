package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnosRepository extends JpaRepository<Alumno, Long> {
	
	Alumno findByNameAndApellido(String name, String apellido);
	Alumno findByUserNameAndApellido(String userName, String apellido);
	Alumno findByUserNameAndPassword(String userName, String password);
	Alumno findByUserName(String userName);
	
}
