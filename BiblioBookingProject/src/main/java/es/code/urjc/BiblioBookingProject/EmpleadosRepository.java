package es.code.urjc.BiblioBookingProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleado, Long> {

	boolean existsByNameAndApellido(String name, String apellido);
}
