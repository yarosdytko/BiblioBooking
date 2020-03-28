package es.code.urjc.BiblioBookingApplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiRestCommands {

    private final String RESERVAS_URL = "http://localhost:8082/api/reservas/";
    private final String USUARIOS_URL = "http://localhost:8082/api/usuarios/";

    private RestTemplate restTemplate = new RestTemplate();

    public void newReserva(Reserva reserva){

        String url = RESERVAS_URL+"nueva_reserva";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.POST,reservaBody,Void.class);

    }

    public void modifyReserva(Reserva reserva){

        String url = RESERVAS_URL+"modificacion_reserva";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.POST,reservaBody,Void.class);

    }

    public void deleteReserva(Reserva reserva){

        String url = RESERVAS_URL+"borrado_reserva";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.POST,reservaBody,Void.class);
    }

    public void newUser(User user){

        String url = USUARIOS_URL+"nuevo_usuario";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        restTemplate.exchange(url, HttpMethod.POST,userHttpEntity,Void.class);
    }

    public void deleteUser(User user){
        String url = USUARIOS_URL+"eliminar_usuario";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        restTemplate.exchange(url,HttpMethod.POST,userHttpEntity,Void.class);
    }

}
