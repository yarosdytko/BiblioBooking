package es.code.urjc.BiblioBookingApplication;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class DataBaseLoader {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SalasRepository salasRepository;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dll;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void init() {

		if (!dll.equals("none")) {

			if (userRepository.count() == 0) {
				LOGGER.info("Creando usuarios en base de datos");
				//si no hay ningun usuario se crearan unos cuantos usuarios por defecto

				userRepository.save(new User("admin", "admin", "admin", "admin@mail.com", "1234", "ROLE_ADMIN", "ROLE_USER"));
				userRepository.save(new User("empleado", "empleado", "empleado", "empleado@mail.com", "1234", "ROLE_EMPLEADO", "ROLE_USER"));
				userRepository.save(new User("alumno", "alumno", "alumno", "alumno@mail.com", "1234", "ROLE_ALUMNO", "ROLE_USER"));
			}

			if (salasRepository.count() == 0) {
				//si no hay ninguna sala creada , se crearan unas cuantas
				LOGGER.info("Creando salas en base de datos");
				salasRepository.save(new Sala(1, 10, 2, 10));
			}
		} else {
			LOGGER.info("Base de datos contiene datos. No ha sido necesario realizar acciones con base de datos");
		}
	}
}
