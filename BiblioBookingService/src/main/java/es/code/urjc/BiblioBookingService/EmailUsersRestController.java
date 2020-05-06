package es.code.urjc.BiblioBookingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class EmailUsersRestController {

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

    public EmailUsersRestController(EmailConfig emailConfig){
        this.emailConfig=emailConfig;
        this.configureMailSender();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUserEmail(@RequestBody User user){
        EmailMessage emailMessage = new EmailMessage(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("BiblioBookingApp");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Bienvenido a BiblioBooking");
        mailMessage.setText(emailMessage.nuevoUsuario());
        mailSender.send(mailMessage);
        LOGGER.info("Usuario "+user.getUsername()+" creado");
        LOGGER.info("Correo de notificacion enviado");
        return user;
    }

    @PutMapping("/")
    public ResponseEntity<User> modifyUser(@RequestBody User user){
        EmailMessage emailMessage = new EmailMessage(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (user!=null){
            mailMessage.setFrom("BiblioBookingApp");
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Cuenta eliminada");
            mailMessage.setText(emailMessage.modificacionUsuario());
            mailSender.send(mailMessage);
            LOGGER.info("Usuario "+user.getUsername()+" datos modificados");
            LOGGER.info("Correo de notificacion enviado");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<User> deleteUserMail(@RequestBody User user){
        EmailMessage emailMessage = new EmailMessage(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (user!=null){
            mailMessage.setFrom("BiblioBookingApp");
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Cuenta eliminada");
            mailMessage.setText(emailMessage.borradoUsuario());
            mailSender.send(mailMessage);
            LOGGER.info("Usuario "+user.getUsername()+" eliminado");
            LOGGER.info("Correo de notificacion enviado");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
