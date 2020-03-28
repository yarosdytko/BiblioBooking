package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseLoader {

    @Autowired
    private UserRepository users;

    @Autowired
    private SalasRepository salas;

    @Autowired
    private ReservasRepository reservas;

    @PostConstruct
    public void init() {

        users.save(new User("Yaroslav","Dytko","ydytko","ydytko@mail.com","1234","ROLE_ADMIN","ROLE_USER"));
        users.save(new User("Juan","Ozores","jozores","jozores@mail.com","1234","ROLE_ADMIN","ROLE_USER"));

        users.save(new User("Ignacio","Val","ival","ival@mail.com","1234","ROLE_USER","ROLE_EMPLEADO"));
        users.save(new User("Ruben","Lopez","rlopez","rlopez@mail.com","1234","ROLE_USER","ROLE_EMPLEADO"));
        users.save(new User("Luis","Castellon","lcastellon","lcastellon@mail.com","1234","ROLE_USER","ROLE_EMPLEADO"));

        users.save(new User("Adrian","Castrejon","acastrejon","acastrejon@mail.com","1234",false,"ROLE_USER","ROLE_ALUMNO"));
        users.save(new User("Silvio","Hun","shun","shun@mail.com","1234",false,"ROLE_USER","ROLE_ALUMNO"));
        users.save(new User("Carlos","Diaz","cdiaz","cdiaz@mail.com","1234",false,"ROLE_USER","ROLE_ALUMNO"));

        salas.save(new Sala(1,10,2,10));
        salas.save(new Sala(2,10,3,10));
        salas.save(new Sala(3,15,4,15));
        salas.save(new Sala(4,6,2,6));
        salas.save(new Sala(5,5,1,5));

        reservas.save(new Reserva(salas.findByNumeroSala(1), users.findByusername("cdiaz"),"2020-05-22","09:00"));
        reservas.save(new Reserva(salas.findByNumeroSala(1), users.findByusername("cdiaz"),"2020-04-16","12:00"));
        reservas.save(new Reserva(salas.findByNumeroSala(1), users.findByusername("cdiaz"),"2020-05-01","17:00"));
    }

}
