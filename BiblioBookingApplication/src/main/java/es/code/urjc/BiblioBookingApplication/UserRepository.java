package es.code.urjc.BiblioBookingApplication;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<User, Long> {

    @SuppressWarnings("unchecked")
	@CacheEvict(value="users",allEntries=true)
    User save(User user);

    @CacheEvict(value="users",allEntries=true)
    void delete(User user);

    User findByName(String name);

    User findByusername(String username);

    User findById(long id);

    User findByNameAndLastname(String name, String lastname);

    boolean existsByNameAndLastname(String name, String lastname);

    boolean existsByUsername(String username);

    int countUsersByRoles(String role);

    @Cacheable
    List<User> findUsersByRoles(String role);

    @CacheEvict(value="users",allEntries=true)
    @Modifying
    @Query("UPDATE User u SET u.name = ?1, u.lastname = ?2, u.username = ?3 , u.email = ?4 WHERE u.id = ?5")
    @Transactional
    void updateUser(String nombre, String apellido, String userName, String email, long idAdmin);

    @CacheEvict(value="users",allEntries=true)
    @Modifying
    @Query("UPDATE User u SET u.name = ?1, u.lastname = ?2, u.username = ?3 , u.email = ?4, u.blocked = ?5 WHERE u.id = ?6")
    @Transactional
    void updateUserAlumno(String nombre, String apellido, String userName, String email, boolean bloqueado, long idAdmin);

    @CacheEvict(value="users",allEntries=true)
    @Modifying
    @Query("UPDATE User u SET u.blocked = ?1 WHERE u.id = ?2")
    @Transactional
    void blockUserALumno(boolean blocked, long id);

}
