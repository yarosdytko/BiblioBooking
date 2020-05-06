package es.code.urjc.BiblioBookingApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiRestCommands {
	
	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.port}")
	private int port;

    private RestTemplate restTemplate = new RestTemplate();

    public void newReserva(Reserva reserva){

        String url = "http://"+host+":"+port+"/api/reservas/";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.POST,reservaBody,Reserva.class);

    }

    public void modifyReserva(Reserva reserva){

        String url = "http://"+host+":"+port+"/api/reservas/";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.PUT,reservaBody,Reserva.class);

    }

    public void deleteReserva(Reserva reserva){

        String url = "http://"+host+":"+port+"/api/reservas/";

        HttpEntity<Reserva> reservaBody= new HttpEntity<>(reserva);

        restTemplate.exchange(url, HttpMethod.DELETE,reservaBody,Reserva.class);
    }

    public void newUser(User user){

        String url = "http://"+host+":"+port+"/api/usuarios/";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        restTemplate.exchange(url, HttpMethod.POST,userHttpEntity,User.class);
    }

    public void modifyUser(User user){

        String url = "http://"+host+":"+port+"/api/usuarios/";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        restTemplate.exchange(url, HttpMethod.PUT,userHttpEntity,User.class);

    }

    public void deleteUser(User user){
        String url = "http://"+host+":"+port+"/api/usuarios/";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        restTemplate.exchange(url,HttpMethod.DELETE,userHttpEntity, User.class);
    }

}
