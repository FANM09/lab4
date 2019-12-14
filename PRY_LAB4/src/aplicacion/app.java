package aplicacion;

import logicaDeNegocio.ChipPrepago;

public class app {

    public static void main(String[] args) {
        ChipPrepago chip1 = new ChipPrepago("70286975");
        ChipPrepago chip2 = new ChipPrepago("88888888");
        System.out.println(chip1.activar("Fabián Navarro", 500));
        System.out.println(chip2.activar("Gerald", 600));
        System.out.println(chip1.recargarSaldo(500));
        System.out.println(chip1.enviarMensaje("Hola", "88888888", chip2));
        //System.out.println(chip1.verCantidadSaldo());
        System.out.println(chip1.transferirSaldo(1460, "88888888", chip2));
        //System.out.println(chip2.verCantidadSaldo());
        //System.out.println(ChipPrepago.consultarCantidadLineas());
    }
}
