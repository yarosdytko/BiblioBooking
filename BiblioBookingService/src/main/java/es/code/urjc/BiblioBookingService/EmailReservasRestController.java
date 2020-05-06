package es.code.urjc.BiblioBookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservas")
public class EmailReservasRestController {

    private final EmailConfig emailConfig;

    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    //rutina de configuracion de correo
    private void configureMailSender(){
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(2525);
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());
    }

    public EmailReservasRestController(EmailConfig emailConfig){
        this.emailConfig=emailConfig;
        this.configureMailSender();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva newReservaEmail(@RequestBody Reserva reserva){

        EmailMessage emailMessage = new EmailMessage(reserva);

        //Creacion de instancia de email
        SimpleMailMessage mail = new SimpleMailMessage();

        //configuracion del objeto email que se enviara
        mail.setFrom("BiblioBookingApp");
        mail.setTo(reserva.getUsuario().getEmail());
        mail.setSubject("Nueva reserva de sala");
        mail.setText(emailMessage.nuevaReserva());

        //Envio de email
        mailSender.send(mail);
        LOGGER.info("Reserva creada");
        LOGGER.info("Correo enviado");
        return reserva;

    }

    @PutMapping("/")
    public ResponseEntity<Reserva> modifiedReservaEmail(@RequestBody Reserva reserva){
        EmailMessage emailMessage = new EmailMessage(reserva);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (reserva!=null){
            mailMessage.setFrom("BiblioBookingApp");
            mailMessage.setTo(reserva.getUsuario().getEmail());
            mailMessage.setSubject("Modificacion de reserva");
            mailMessage.setText(emailMessage.modificacionReserva());
            mailSender.send(mailMessage);
            LOGGER.info("Reserva actualizada");
            LOGGER.info("Correo enviado");
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Reserva> deleteReserva(@RequestBody Reserva reserva){
        EmailMessage emailMessage = new EmailMessage(reserva);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if(reserva!=null){
            mailMessage.setFrom("BiblioBookingApp");
            mailMessage.setTo(reserva.getUsuario().getEmail());
            mailMessage.setSubject("Borrado de reserva");
            mailMessage.setText(emailMessage.borradoReserva());
            mailSender.send(mailMessage);
            LOGGER.info("Reserva eliminada");
            LOGGER.info("Correo enviado");
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
