package aplicacion;

import logicaDeNegocio.ChipPrepago;

public class app {

    public static void main(String[] args) {
        ChipPrepago chip1 = new ChipPrepago("70286975");
        ChipPrepago chip2 = new ChipPrepago("88888888");
        ChipPrepago chip3 = new ChipPrepago("911");
        System.out.println(chip1.activar("Fabián Navarro", 500));
        System.out.println(chip2.activar("Gerald", 600));
        System.out.println(chip3.activar("911", 1000));
        System.out.println(chip1.verCantidadSaldo());
        //System.out.println(chip1.recargarSaldo(500));
        //System.out.println(chip1.enviarMensaje("Hola", "88888888", chip2));
        //System.out.println(chip1.enviarMensaje("Hola2", "88888888", chip2));
        //System.out.println(chip1.enviarMensaje("Hola3", "88888888", chip2));
        //System.out.println(chip1.enviarMensaje("Hola4", "77777777", chip3));
        //System.out.println(chip1.verMensajesEnviados());
        //System.out.println(chip2.verMensajesRecibidos());
        //System.out.println(chip1.consultarActividadSalida("88888888"));
        //System.out.println(chip1.consultarHistorialMensajes());

        System.out.println(chip1.realizarLlamada(20, "911", chip3));
        System.out.println(chip1.realizarLlamada(10, "88888888", chip2));
    }
}
