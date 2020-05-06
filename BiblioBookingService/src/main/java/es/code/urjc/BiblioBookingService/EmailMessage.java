package es.code.urjc.BiblioBookingService;

public class EmailMessage {

    private Reserva r;

    private User u;

    public EmailMessage(Reserva reserva){
        r=reserva;
    }

    public EmailMessage(User user){
        u=user;
    }

    public String nuevaReserva(){
        return ("Usted ha realizado una nueva reserva de sala con los siguentes detalles\nDatos de reserva: "
                +"\nSala: "+r.getSalaReservada().getNumeroSala()
                +"\nHora: "+r.getHora()
                +"\nFecha(aaaa/mm/dd): "+r.getFecha());
    }

    public String modificacionReserva(){

        return ("Se ha producido la modificacion de su reserva de sala con los siguentes detalles\nDatos de reserva: "
                +"\nSala: "+r.getSalaReservada().getNumeroSala()
                +"\nHora: "+r.getHora()
                +"\nFecha(aaaa/mm/dd): "+r.getFecha());
    }

    public String borradoReserva(){
        return ("Se ha realizado el borrado de su reserva de sala con los siguentes detalles\nDatos de reserva:"
                +"\nSala: "+r.getSalaReservada().getNumeroSala()
                +"\nHora: "+r.getHora()
                +"\nFecha(aaaa/mm/dd): "+r.getFecha());
    }

    public String nuevoUsuario(){
        return ("Bienvenido a la plataforma de BiblioBooking\nSus datos de usuarios son los siguentes: "
                +"\nNombre: "+u.getName()
                +"\nApellido: "+u.getLastname()
                +"\nNombre de usuario: "+u.getUsername()
                +"\nCorreo electronico: "+u.getEmail());
    }

    public String modificacionUsuario(){
        return ("Le informamos que su cuenta de usuario en la plataforma BiblioBooking"
                +"\nHa sido modificada."
                +"\nNuevos datos de la cuenta:"
                +"\nNombre: "+u.getName()
                +"\nApellido: "+u.getLastname()
                +"\nNombre de usuario: "+u.getUsername()
                +"\nCorreo electronico: "+u.getEmail());
    }

    public String borradoUsuario(){
        return ("Le informamos que su cuenta de usuario en la plataforma BiblioBooking"
                +"\nCon los siguentes datos:  "
                +"\nNombre: "+u.getName()
                +"\nApellido: "+u.getLastname()
                +"\nNombre de usuario: "+u.getUsername()
                +"\nCorreo electronico: "+u.getEmail()
                +"\nHa sido eliminada.");
    }
}
