package es.code.urjc.BiblioBookingProject;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradoresRepository extends JpaRepository<Administrador, Long> {

	Administrador findByUserNameAndPassword(String userName, String password);
	//Administrador findByName(String name);
	
}
