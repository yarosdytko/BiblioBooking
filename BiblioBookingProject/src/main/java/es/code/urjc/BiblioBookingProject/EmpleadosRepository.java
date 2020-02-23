package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmpleadosRepository extends JpaRepository<Empleado, Long> {

	boolean existsByNameAndApellido(String name, String apellido);
	
	@Modifying
	@Query("UPDATE Empleado a SET a.name = ?1, a.apellido = ?2, a.userName = ?3 , a.email = ?4 WHERE a.idEmpleado = ?5")
	@Transactional
	void actualizarEmpleado(String name, String apellido, String userName, String email, long idEmpleado);
}
