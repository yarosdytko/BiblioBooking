package es.code.urjc.BiblioBookingService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class EmailUsersRestController {

    private EmailConfig emailConfig;

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

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

    @PostMapping("/nuevo_usuario")
    public void newUserEmail(@RequestBody User user){
        EmailMessage emailMessage = new EmailMessage(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("BiblioBookingApp");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Bienvenido a BiblioBooking");
        mailMessage.setText(emailMessage.nuevoUsuario());
        mailSender.send(mailMessage);
    }

    @PostMapping("/eliminar_usuario")
    public void deleteUserMail(@RequestBody User user){
        EmailMessage emailMessage = new EmailMessage(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("BiblioBookingApp");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Cuenta eliminada");
        mailMessage.setText(emailMessage.borradoUsuario());
        mailSender.send(mailMessage);
    }

}
