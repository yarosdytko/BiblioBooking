package es.code.urjc.BiblioBookingService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservas")
public class EmailReservasRestController {

    private EmailConfig emailConfig;

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

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

    @PostMapping("/nueva_reserva")
    public void newReservaEmail(@RequestBody Reserva reserva){

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

    }

    @PostMapping("/modificacion_reserva")
    public void modifiedReservaEmail(@RequestBody Reserva reserva){
        EmailMessage emailMessage = new EmailMessage(reserva);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("BiblioBookingApp");
        mailMessage.setTo(reserva.getUsuario().getEmail());
        mailMessage.setSubject("Modificacion de reserva");
        mailMessage.setText(emailMessage.modificacionReserva());
        mailSender.send(mailMessage);

    }

    @PostMapping("/borrado_reserva")
    public void deleteReserva(@RequestBody Reserva reserva){
        EmailMessage emailMessage = new EmailMessage(reserva);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("BiblioBookingApp");
        mailMessage.setTo(reserva.getUsuario().getEmail());
        mailMessage.setSubject("Borrado de reserva");
        mailMessage.setText(emailMessage.borradoReserva());
        mailSender.send(mailMessage);

    }
}
