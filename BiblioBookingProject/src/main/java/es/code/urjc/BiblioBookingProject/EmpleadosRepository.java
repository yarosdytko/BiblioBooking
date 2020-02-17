package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleado, Long> {

	Empleado findByUserNameAndPassword(String userName, String password);
	
}
