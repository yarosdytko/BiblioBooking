package es.code.urjc.BiblioBookingService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nueva_reserva")
public class EmailRestController {

    private EmailConfig emailConfig;

    public EmailRestController(EmailConfig emailConfig){
        this.emailConfig=emailConfig;
    }

    @PostMapping
    public void sendEmail(@RequestBody Reserva reservaBody){

        EmailMessage message = new EmailMessage(reservaBody);

        //Creacion de mailSender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //configuracion de mail sender
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(2525);
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        //Creacion de instancia de email
        SimpleMailMessage mail = new SimpleMailMessage();
        //configuracion del objeto email que se enviara
        mail.setFrom("BiblioBookingApp");
        mail.setTo(reservaBody.getUsuario().getEmail());
        mail.setSubject("Nueva reserva de sala");
        mail.setText(message.toString());

        //Envio de email
        System.out.println(message.toString());
        mailSender.send(mail);

    }
}
