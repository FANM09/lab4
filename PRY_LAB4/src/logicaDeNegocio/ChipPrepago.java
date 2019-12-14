// Realizado por Gerald Navarro,Fabián Navarro
package logicaDeNegocio;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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
    private double megabytesDisponibles = 0;
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

    public String consultarHistorialMensajes() {
        System.out.println("Los mensajes enviados son: ");
        for (int i = 0; i < this.mensajesSalientes.length; i++) {
            if (mensajesSalientes[i] == null) {
                break;
            } else {
                System.out.println(mensajesSalientes[i].imprimirInformarcionMensajesEnviados());
            }
        }
        return "Se mostraron todos los mensajes";
    }

    public String consultarHistorialLlamadas() {
        System.out.println("Las llamadas realizadas son: ");
        for (int i = 0; i < this.llamadasSalientes.length; i++) {
            if (llamadasSalientes[i] == null) {
                break;
            } else {
                System.out.println(llamadasSalientes[i].imprimirInformarcionLlamadasRealizadas());
            }
        }
        return "Se mostraron todas las llamadas";
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
            } else {
                System.out.println(mensajesEntrantes[i].imprimirInformarcionMensajesRecibidos());
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
                System.out.println(mensajesSalientes[i].imprimirInformarcionMensajesEnviados());
            }
        }
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
                System.out.println(mensajesSalientes[i].imprimirInformarcionMensajesEnviados());
            }
        }
        System.out.println("Las llamadas al número:" + pNumero + " son: ");
        for (int i = 0; i < this.llamadasSalientes.length; i++) {
            if (llamadasSalientes[i] == null) {
                break;
            } else if (llamadasSalientes[i].getNumeroReceptor().equals(pNumero)) {
                System.out.println(llamadasSalientes[i].imprimirInformarcionLlamadasRealizadas());
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

    public String verActividadMesActual() {
        System.out.println("La actividad del mes es: ");
        String mesLlamada;
        Date fecha;
        Calendar calendario;
        calendario = Calendar.getInstance();
        fecha = calendario.getTime();
        SimpleDateFormat mascara = new SimpleDateFormat("MM");
        String mesActual = String.valueOf(mascara.format(fecha));
        System.out.println("Las llamadas son: ");
        for (int i = 0; i < this.llamadasSalientes.length; i++) {
            if (llamadasSalientes[i] == null) {
                break;
            } else {
                mesLlamada = String.valueOf(llamadasSalientes[i].getFecha().charAt(3));
                mesLlamada = mesLlamada + String.valueOf(llamadasSalientes[i].getFecha().charAt(4));
                if (mesLlamada.equals(mesActual)) {
                    System.out.println(llamadasSalientes[i].imprimirInformarcionLlamadasRealizadas());
                }
            }
        }
        System.out.println("Los mensajes son: ");
        for (int i = 0; i < this.mensajesSalientes.length; i++) {
            if (mensajesSalientes[i] == null) {
                break;
            } else {
                mesLlamada = String.valueOf(mensajesSalientes[i].getFecha().charAt(3));
                mesLlamada = mesLlamada + String.valueOf(mensajesSalientes[i].getFecha().charAt(4));
                if (mesLlamada.equals(mesActual)) {
                    System.out.println(mensajesSalientes[i].imprimirInformarcionMensajesEnviados());
                }
            }
        }
        return "Se mostraron todos los mensajes y llamadas del mes actual";
    }

    private boolean validarMes(String pMes) {
        if (pMes.equals("01") || pMes.equals("02") || pMes.equals("03") || pMes.equals("04") || pMes.equals("05") || pMes.equals("06") || pMes.equals("07") || pMes.equals("08")
                || pMes.equals("09") || pMes.equals("10") || pMes.equals("11") || pMes.equals("12")) {
            return true;
        } else {
            return false;
        }
    }

    public String verActividadMesEspecifico(String pMes) {
        if (!this.estado.equals("Activado")) {
            return "Error,Chip desactivado";
        } else if (!validarMes(pMes)) {
            return "No introdujo un mes correcto";
        } else {
            System.out.println("La actividad del mes indicado es: ");
            String mesLlamada;
            System.out.println("Las llamadas son: ");
            for (int i = 0; i < this.llamadasSalientes.length; i++) {
                if (llamadasSalientes[i] == null) {
                    break;
                } else {
                    mesLlamada = String.valueOf(llamadasSalientes[i].getFecha().charAt(3));
                    mesLlamada = mesLlamada + String.valueOf(llamadasSalientes[i].getFecha().charAt(4));
                    if (mesLlamada.equals(pMes)) {
                        System.out.println(llamadasSalientes[i].imprimirInformarcionLlamadasRealizadas());
                    }
                }
            }
            System.out.println("Los mensajes son: ");
            for (int i = 0; i < this.mensajesSalientes.length; i++) {
                if (mensajesSalientes[i] == null) {
                    break;
                } else {
                    mesLlamada = String.valueOf(mensajesSalientes[i].getFecha().charAt(3));
                    mesLlamada = mesLlamada + String.valueOf(mensajesSalientes[i].getFecha().charAt(4));
                    if (mesLlamada.equals(pMes)) {
                        System.out.println(mensajesSalientes[i].imprimirInformarcionMensajesEnviados());
                    }
                }
            }
            return "Se mostraron todos los mensajes y llamadas del mes indicado";
        }
    }

    private int calcularCosto(int pCodigoPais) {
        int costo = 0;
        if (pCodigoPais == 505) {
            costo = 30;
            return costo;
        }
        if (pCodigoPais == 507) {
            costo = 40;
            return costo;
        }
        if (pCodigoPais == 508) {
            costo = 50;
            return costo;
        }
        if (pCodigoPais == 508) {
            costo = 60;
            return costo;
        }
        if (pCodigoPais == 508) {
            costo = 70;
            return costo;
        } else {
            System.out.println("El código de país no está registrado");
        }
        return costo;
    }

    //Segundo método de enviar mensajes
    public String enviarMensaje(String pMensaje, String pNumeroDestino, ChipPrepago pChipPrepago, int pCodigoPais) {
        if (this.estado.equals("Activado") && pChipPrepago.estado.equals("Activado")) {
            if (!pNumeroDestino.equals(pChipPrepago.numeroTelefono)) {
                return "Error, el número ingresado no existe";
            } else if (pMensaje.length() > 128) {
                return "El mensaje excede los 128 caracteres";
            } else {
                if (!verificarSaldo(calcularCosto(pCodigoPais))) {
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
                    saldo -= calcularCosto(pCodigoPais);
                    return "Mensaje enviado correctamente";
                }
            }
        } else {
            return "El chip se encuentra desactivado";
        }
    }

    //Segundo método para realizar llamadas
    public int realizarLlamada(int pDuracionMinutos, String pNumeroDestino, ChipPrepago pChipPrepago, int pCodigoPais) {
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
                    saldo -= pDuracionMinutos * calcularCosto(pCodigoPais);
                    System.out.println("Llamada realizada correctamente");
                }
            }
        } else {
            System.out.println("Algún chip se encuentra desactivado");
        }
        return saldo;
    }

    public String salvarSaldo() {
        if (this.oportunidadesSalvame == 0) {
            return "Se agotaron las oportunidades";
        } else {
            if (saldo == 0) {
                saldo += 100;
                this.oportunidadesSalvame--;
                return "Éxito";
            } else {
                return "El saldo no es igual a 0";
            }
        }
    }

    public String obtenerHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean urlValidator(String url) {
        /*validación de url*/
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException exception) {
            return false;
        }
    }

    public double navegarWeb(String pUrl) {
        if (urlValidator(pUrl)) {
            System.out.println(this.megabytesDisponibles);
            int numeroRandom = (int) (Math.random() * 8) + 1;
            double kilobytesConsumidos = (double) (numeroRandom * 0.001);
            System.out.println(kilobytesConsumidos);
            this.megabytesDisponibles = (double) (this.megabytesDisponibles - kilobytesConsumidos);
            System.out.println(this.megabytesDisponibles);
            return this.megabytesDisponibles;
        }
        System.out.println("El URL no es válido");
        return this.megabytesDisponibles;
    }

}
