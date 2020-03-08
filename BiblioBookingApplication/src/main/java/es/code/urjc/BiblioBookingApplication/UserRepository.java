package es.code.urjc.BiblioBookingApplication;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
    User findByusername(String username);
    User findByRoles(String role);
    User findById(long id);
    User findByNameAndLastname(String name, String lastname);
    boolean existsByNameAndLastname(String name, String lastname);
    boolean existsByUsername(String username);

    int countUsersByRoles(String role);
    Iterable<User> findUserByRoles(String role);

    @Modifying
    @Query("UPDATE User u SET u.name = ?1, u.lastname = ?2, u.username = ?3 , u.email = ?4 WHERE u.id = ?5")
    @Transactional
    void updateUser(String nombre, String apellido, String userName, String email, long idAdmin);

    @Modifying
    @Query("UPDATE User u SET u.name = ?1, u.lastname = ?2, u.username = ?3 , u.email = ?4, u.blocked = ?5 WHERE u.id = ?6")
    @Transactional
    void updateUserAlumno(String nombre, String apellido, String userName, String email, boolean bloqueado, long idAdmin);

    @Modifying
    @Query("UPDATE User u SET u.blocked = ?1 WHERE u.id = ?2")
    @Transactional
    void blockUserALumno(boolean blocked, long id);

}
