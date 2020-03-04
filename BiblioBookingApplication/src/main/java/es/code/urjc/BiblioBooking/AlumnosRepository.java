package es.code.urjc.BiblioBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlumnosRepository extends JpaRepository<Alumno, Long> {
	
	Alumno findByNameAndApellido(String name, String apellido);
	Alumno findByUserNameAndApellido(String userName, String apellido);
	Alumno findByUserNameAndPassword(String userName, String password);
	Alumno findByUserName(String userName);
	boolean existsByNameAndApellido(String name, String apellido);
	
	@Modifying
	@Query("UPDATE Alumno a SET a.name = ?1, a.apellido = ?2, a.userName = ?3 , a.email = ?4 WHERE a.expediente = ?5")
	@Transactional
	void actualizarAlumno(String name, String apellido, String userName, String email, long expediente);

	@Modifying
	@Query("UPDATE Alumno a SET a.bloqueado = ?1 WHERE a.expediente = ?2")
	@Transactional
	void bloqueoAlumno(boolean bloqueado, long expediente);

}
