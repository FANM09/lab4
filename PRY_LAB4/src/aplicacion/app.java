package aplicacion;

import logicaDeNegocio.ChipPrepago;

public class app {

    public static void main(String[] args) {
        ChipPrepago chip1 = new ChipPrepago("70286975");
        ChipPrepago chip2 = new ChipPrepago("88888888");
        ChipPrepago chip3 = new ChipPrepago("911");

        System.out.println(chip1.activarChipPrepago("Fabián Navarro", 500));
        System.out.println(chip2.activarChipPrepago("Gerald", 600));
        System.out.println(chip3.activarChipPrepago("911", 1000));
        System.out.println(chip2.enviarMensaje("Hello", "70286975", chip1));
        System.out.println(chip2.enviarMensaje("Hello2", "70286975", chip1));
        System.out.println(chip1.verCantidadSaldo());
        System.out.println(chip1.recargarSaldo(500));
        System.out.println(chip1.enviarMensaje("Hola", "88888888", chip2));
        System.out.println(chip1.enviarMensaje("Hola4", "911", chip3));
        System.out.println(chip1.verMensajesEnviados());
        System.out.println(chip2.verMensajesRecibidos());
        System.out.println(chip1.consultarActividadSalida("88888888"));
        System.out.println(chip1.realizarLlamada(20, "911", chip3));
        System.out.println(chip1.realizarLlamada(10, "88888888", chip2));
        System.out.println(chip1.realizarLlamada(15, "88888888", chip2));

        System.out.println(chip1.consultarHistorialLlamadas());
        System.out.println(chip1.consultarHistorialMensajes());

        System.out.println(chip1.verMensajesRecibidos());

        System.out.println(chip1.verActividadMesActual());

        System.out.println(chip1.verActividadMesEspecifico("12"));
        
        
        System.out.println(chip1.navegarWeb("https://www.google.com/"));
    }
}
