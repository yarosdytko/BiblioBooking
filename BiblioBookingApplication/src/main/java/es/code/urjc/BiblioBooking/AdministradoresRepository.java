package es.code.urjc.BiblioBooking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdministradoresRepository extends JpaRepository<Administrador, Long> {

	Administrador findByUserNameAndPassword(String userName, String password);
	boolean existsByNameAndApellido(String name, String apellido);
	
	@Modifying
	@Query("UPDATE Administrador a SET a.name = ?1, a.apellido = ?2, a.userName = ?3 , a.email = ?4 WHERE a.idAdmin = ?5")
	@Transactional
	void actualizarAdmin(String name, String apellido, String userName, String email, long idAdmin);
	
}
