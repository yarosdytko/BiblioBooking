package es.code.urjc.BiblioBookingService;

public class EmailMessage {

    private Reserva r;

    public EmailMessage(Reserva reserva){
        r=reserva;
    }

    @Override
    public String toString() {
        return ("Usted ha realizado una nueva reserva de sala con los siguentes detalles\nNumero de sala: "
                +r.getSalaReservada().getNumeroSala()
                +"\nHora: "+r.getHora()
                +"\nFecha: "+r.getFecha());
    }
}
