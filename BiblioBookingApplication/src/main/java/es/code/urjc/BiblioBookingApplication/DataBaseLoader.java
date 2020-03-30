package es.code.urjc.BiblioBookingApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseLoader {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalasRepository salasRepository;
	
	@PostConstruct
	public void init() {

		if (userRepository.count() == 0) {
			//si no hay ningun usuario se crearan unos cuantos usuarios por defecto

			userRepository.save(new User("Yaroslav", "Dytko", "ydytko", "ydytko@mail.com", "1234", "ROLE_ADMIN", "ROLE_USER"));
			userRepository.save(new User("Juan", "Ozores", "jozores", "jozores@mail.com", "1234", "ROLE_EMPLEADO", "ROLE_USER"));
			userRepository.save(new User("Carlos", "Diaz", "cdiaz", "cdiaz@mail.com", "1234", "ROLE_ALUMNO", "ROLE_USER"));
		}

		if (salasRepository.count() == 0) {
			//si no hay ninguna sala creada , se crearan unas cuantas

			salasRepository.save(new Sala(1, 10, 2, 10));
			salasRepository.save(new Sala(2, 13, 3, 15));
			salasRepository.save(new Sala(3, 5, 1, 5));
		}
	}
}
