// Realizado por Gerald Navarro,Fabián Navarro
package logicaDeNegocio;

public class ChipPrepago {

    private String numeroTelefono = null;
    private int saldo = 0;
    private int CODIGO_PAIS = 506;
    private String estado = null;
    private int oportunidadesSalvame = 0;
    private int megabytesDisponibles = 0;
    private String duenioChip = null;
    private Llamada[] llamadasEntrantes;
    private Llamada[] llamadasSalientes;
    private Mensaje[] mensajesEntrantes;
    private Mensaje[] mensajesSalientes;

    private static int cantidadLineasCreadas = 0;

    public ChipPrepago(String pNumeroTelefono) {
        numeroTelefono = pNumeroTelefono;
        saldo = 0;
        CODIGO_PAIS = 506;
        estado = "Desactivado";
        oportunidadesSalvame = 3;
        megabytesDisponibles = 0;
        duenioChip = null;
        llamadasEntrantes = new Llamada[10];
        llamadasSalientes = new Llamada[10];
        mensajesEntrantes = new Mensaje[10];
        mensajesSalientes = new Mensaje[10];
        cantidadLineasCreadas += 1;
    }

    public String activar(String pDuenio, int pCantidadMegabytes) {
        if (pDuenio.equals("") || pCantidadMegabytes == 0) {
            return "Error debe ingresar los datos requeridos";
        } else {
            saldo = 1000;
            estado = "Activado";
            megabytesDisponibles = pCantidadMegabytes;
            duenioChip = pDuenio;
            return "La línea ha sido activada correctamente";
        }
    }

    public int recargarSaldo(int pMonto) {
        if (this.estado.equals("Activado")) {
            if (pMonto > 0) {
                saldo += pMonto;
                System.out.println("Recarga exitosa");
            } else {
                System.out.println("El saldo ingresado es incorrecto");
            }
            return saldo;
        } else {
            System.out.println("El chip se encuentra desactivado");
        }
        return saldo;
    }

    /*
    public Array consultarHistorialLlamadas() {
    }
     */
    public String consultarHistorialMensajes() {
        System.out.println("Los mensajes enviados son: ");
        for (int i = 0; i < this.mensajesSalientes.length; i++) {
            if (mensajesSalientes[i] == null) {
                break;
            } else {
                System.out.println(mensajesSalientes[i].toString());
            }
        }
        //return this.mensajesEntrantes[0].toString();
        return "Se mostraron todos los mensajes";

    }

    public int verCantidadSaldo() {
        if (this.estado.equals("Desactivado")) {
            System.out.println("El chip se encuentra desactivado");
            return this.saldo;
        } else {
            return this.saldo;
        }
    }

    public String transferirSaldo(int pMonto, String pNumeroDestino, ChipPrepago pChipPrepago) {
        if (this.estado.equals("Activado") && pChipPrepago.estado.equals("Activado")) {
            if (pNumeroDestino.equals(pChipPrepago.numeroTelefono)) {
                if (this.saldo == 0) {
                    return "¡Error!, no posee saldo en su cuenta";
                } else {
                    if (!verificarSaldo(pMonto + 5)) {
                        return "Monto a recargar mayor que su saldo";
                    } else {
                        pChipPrepago.saldo += pMonto;
                        saldo -= pMonto;
                        saldo -= 5;
                        return "Saldo transferido exitosamente";
                    }
                }
            } else {
                return "El número ingresado es inválido";
            }
        } else {
            return "El chip se encuentra desactivado";
        }
    }

    public String enviarMensaje(String pMensaje, String pNumeroDestino, ChipPrepago pChipPrepago) {
        if (this.estado.equals("Activado") && pChipPrepago.estado.equals("Activado")) {
            if (!pNumeroDestino.equals(pChipPrepago.numeroTelefono)) {
                return "Error, el número ingresado no existe";
            } else if (pMensaje.length() > 128) {
                return "El mensaje excede los 128 caracteres";
            } else {
                if (!verificarSaldo(20)) {
                    return "No tiene suficiente saldo para enviar el mensaje";
                } else {
                    Mensaje msj = new Mensaje(this.numeroTelefono, pNumeroDestino, pMensaje, "Enviado");

                    //guarda los mensajes que se envian en el chip prepago que los envío
                    this.mensajesSalientes[9] = this.mensajesSalientes[8];
                    this.mensajesSalientes[8] = this.mensajesSalientes[7];
                    this.mensajesSalientes[7] = this.mensajesSalientes[6];
                    this.mensajesSalientes[6] = this.mensajesSalientes[5];
                    this.mensajesSalientes[5] = this.mensajesSalientes[4];
                    this.mensajesSalientes[4] = this.mensajesSalientes[3];
                    this.mensajesSalientes[3] = this.mensajesSalientes[2];
                    this.mensajesSalientes[2] = this.mensajesSalientes[1];
                    this.mensajesSalientes[1] = this.mensajesSalientes[0];
                    this.mensajesSalientes[0] = msj;

                    //guarda los mensajes que se recibidos en el chip prepago que los recibió
                    pChipPrepago.mensajesEntrantes[9] = pChipPrepago.mensajesEntrantes[8];
                    pChipPrepago.mensajesEntrantes[8] = pChipPrepago.mensajesEntrantes[7];
                    pChipPrepago.mensajesEntrantes[7] = pChipPrepago.mensajesEntrantes[6];
                    pChipPrepago.mensajesEntrantes[6] = pChipPrepago.mensajesEntrantes[5];
                    pChipPrepago.mensajesEntrantes[5] = pChipPrepago.mensajesEntrantes[4];
                    pChipPrepago.mensajesEntrantes[4] = pChipPrepago.mensajesEntrantes[3];
                    pChipPrepago.mensajesEntrantes[3] = pChipPrepago.mensajesEntrantes[2];
                    pChipPrepago.mensajesEntrantes[2] = pChipPrepago.mensajesEntrantes[1];
                    pChipPrepago.mensajesEntrantes[1] = pChipPrepago.mensajesEntrantes[0];
                    pChipPrepago.mensajesEntrantes[0] = msj;
                    saldo -= 20;
                    return "Mensaje enviado correctamente";
                }
            }
        } else {
            return "El chip se encuentra desactivado";
        }
    }

    public String verMensajesRecibidos() {
        System.out.println("Los mensajes recibidos son: ");
        for (int i = 0; i < this.mensajesEntrantes.length; i++) {
            if (mensajesEntrantes[i] == null) {
                break;
            } else if (mensajesEntrantes[i].getNumeroEmisor().equals(this.numeroTelefono)) {
                System.out.println(mensajesEntrantes[i].toString());
            }
        }
        //return this.mensajesEntrantes[0].toString();
        return "Se mostraron todos los mensajes";
    }

    public String verMensajesEnviados() {
        System.out.println("Los mensajes enviados son: ");
        for (int i = 0; i < this.mensajesSalientes.length; i++) {
            if (mensajesSalientes[i] == null) {
                break;
            } else if (mensajesSalientes[i].getNumeroEmisor().equals(this.numeroTelefono)) {
                System.out.println(mensajesSalientes[i].toString());
            }
        }
        //return this.mensajesEntrantes[0].toString();
        return "Se mostraron todos los mensajes";
    }

    public static int consultarCantidadLineas() {
        return cantidadLineasCreadas;
    }

    private boolean verificarSaldo(int pCantidad) {
        return this.saldo >= pCantidad;
    }

    //faltan las llamadas
    public String consultarActividadSalida(String pNumero) {
        System.out.println("Los mensajes al número:" + pNumero + " son: ");
        for (int i = 0; i < this.mensajesSalientes.length; i++) {
            if (mensajesSalientes[i] == null) {
                break;
            } else if (mensajesSalientes[i].getNumeroReceptor().equals(pNumero)) {
                System.out.println(mensajesSalientes[i].toString());
            }
        }
        return "Se mostraron todos los mensajes y llamadas";
    }

    public int realizarLlamada(int pDuracionMinutos, String pNumeroDestino, ChipPrepago pChipPrepago) {
        if (this.estado.equals("Activado") && pChipPrepago.estado.equals("Activado")) {
            if (!pNumeroDestino.equals(pChipPrepago.numeroTelefono)) {
                System.out.println("El número de detino es incorrecto");
                return saldo;
            } else {
                if (pNumeroDestino.equals("911")) {
                    Llamada llamada = new Llamada(pDuracionMinutos, this.numeroTelefono, pNumeroDestino, "Saliente");
                    //guarda los mensajes que se envian en el chip prepago que los envío
                    this.llamadasSalientes[9] = this.llamadasSalientes[8];
                    this.llamadasSalientes[8] = this.llamadasSalientes[7];
                    this.llamadasSalientes[7] = this.llamadasSalientes[6];
                    this.llamadasSalientes[6] = this.llamadasSalientes[5];
                    this.llamadasSalientes[5] = this.llamadasSalientes[4];
                    this.llamadasSalientes[4] = this.llamadasSalientes[3];
                    this.llamadasSalientes[3] = this.llamadasSalientes[2];
                    this.llamadasSalientes[2] = this.llamadasSalientes[1];
                    this.llamadasSalientes[1] = this.llamadasSalientes[0];
                    this.llamadasSalientes[0] = llamada;

                    //guarda los mensajes que se recibidos en el chip prepago que los recibió
                    pChipPrepago.llamadasEntrantes[9] = pChipPrepago.llamadasEntrantes[8];
                    pChipPrepago.llamadasEntrantes[8] = pChipPrepago.llamadasEntrantes[7];
                    pChipPrepago.llamadasEntrantes[7] = pChipPrepago.llamadasEntrantes[6];
                    pChipPrepago.llamadasEntrantes[6] = pChipPrepago.llamadasEntrantes[5];
                    pChipPrepago.llamadasEntrantes[5] = pChipPrepago.llamadasEntrantes[4];
                    pChipPrepago.llamadasEntrantes[4] = pChipPrepago.llamadasEntrantes[3];
                    pChipPrepago.llamadasEntrantes[3] = pChipPrepago.llamadasEntrantes[2];
                    pChipPrepago.llamadasEntrantes[2] = pChipPrepago.llamadasEntrantes[1];
                    pChipPrepago.llamadasEntrantes[1] = pChipPrepago.llamadasEntrantes[0];
                    pChipPrepago.llamadasEntrantes[0] = llamada;
                    saldo = saldo;
                    System.out.println("Llamada realizada correctamente");
                } else if (!verificarSaldo(calcularCosteLlamada(pDuracionMinutos))) {
                    System.out.println("No tiene saldo para realizar la llamada");
                    return saldo;
                } else {
                    Llamada llamada = new Llamada(pDuracionMinutos, this.numeroTelefono, pNumeroDestino, "Saliente");
                    //guarda los mensajes que se envian en el chip prepago que los envío
                    this.llamadasSalientes[9] = this.llamadasSalientes[8];
                    this.llamadasSalientes[8] = this.llamadasSalientes[7];
                    this.llamadasSalientes[7] = this.llamadasSalientes[6];
                    this.llamadasSalientes[6] = this.llamadasSalientes[5];
                    this.llamadasSalientes[5] = this.llamadasSalientes[4];
                    this.llamadasSalientes[4] = this.llamadasSalientes[3];
                    this.llamadasSalientes[3] = this.llamadasSalientes[2];
                    this.llamadasSalientes[2] = this.llamadasSalientes[1];
                    this.llamadasSalientes[1] = this.llamadasSalientes[0];
                    this.llamadasSalientes[0] = llamada;

                    //guarda los mensajes que se recibidos en el chip prepago que los recibió
                    pChipPrepago.llamadasEntrantes[9] = pChipPrepago.llamadasEntrantes[8];
                    pChipPrepago.llamadasEntrantes[8] = pChipPrepago.llamadasEntrantes[7];
                    pChipPrepago.llamadasEntrantes[7] = pChipPrepago.llamadasEntrantes[6];
                    pChipPrepago.llamadasEntrantes[6] = pChipPrepago.llamadasEntrantes[5];
                    pChipPrepago.llamadasEntrantes[5] = pChipPrepago.llamadasEntrantes[4];
                    pChipPrepago.llamadasEntrantes[4] = pChipPrepago.llamadasEntrantes[3];
                    pChipPrepago.llamadasEntrantes[3] = pChipPrepago.llamadasEntrantes[2];
                    pChipPrepago.llamadasEntrantes[2] = pChipPrepago.llamadasEntrantes[1];
                    pChipPrepago.llamadasEntrantes[1] = pChipPrepago.llamadasEntrantes[0];
                    pChipPrepago.llamadasEntrantes[0] = llamada;
                    saldo -= calcularCosteLlamada(pDuracionMinutos);
                    System.out.println("Llamada realizada correctamente");
                }
            }
        } else {
            System.out.println("Algún chip se encuentra desactivado");
        }
        return saldo;
    }

    private int calcularCosteLlamada(int pDuracionMinutos) {
        int costo = 0;
        costo = pDuracionMinutos * 30;
        return costo;
    }
}
