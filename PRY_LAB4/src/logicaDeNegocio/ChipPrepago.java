// Realizado por Gerald Navarro,Fabián Navarro
package logicaDeNegocio;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public Array consultarHistorialMensajes() {
    }
     */
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
                    //Falta añadir mensaje a array de mensajes
                    Mensaje msj = new Mensaje(this.numeroTelefono, pNumeroDestino, pMensaje, "Enviado");
                    this.mensajesSalientes[0] = msj;
                    pChipPrepago.mensajesEntrantes[0] = msj;

                    saldo -= 20;
                    return "Mensaje enviado correctamente";
                }
            }
        } else {
            return "El chip se encuentra desactivado";
        }
    }

    public Mensaje[] verMensajesRecibidos() {
        return null;
    }

    public static int consultarCantidadLineas() {
        return cantidadLineasCreadas;
    }

    private boolean verificarSaldo(int pCantidad) {
        return this.saldo > pCantidad;
    }

}
