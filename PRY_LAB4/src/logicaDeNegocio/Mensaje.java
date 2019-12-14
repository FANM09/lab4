package logicaDeNegocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Mensaje {

    private String numeroEmisor;
    private String numeroReceptor;
    private String mensaje;
    private Date fecha;
    private String hora;
    private String tipoMensaje;

    public Mensaje(String numeroEmisor, String numeroReceptor, String mensaje, String tipoMensaje) {
        this.numeroEmisor = numeroEmisor;
        this.numeroReceptor = numeroReceptor;
        this.mensaje = mensaje;
        setFecha();
        this.hora = getHora();
        this.tipoMensaje = tipoMensaje;
    }

    public String getNumeroEmisor() {
        return numeroEmisor;
    }

    public String getNumeroReceptor() {
        return numeroReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setFecha() {
        Calendar calendario;
        calendario = Calendar.getInstance();
        fecha = calendario.getTime();
    }

    public String getFecha() {
        SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
        return mascara.format(fecha);
    }

    private String getHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String imprimirInformarcionMensajesEnviados() {
        return "Mensaje=" + getMensaje() + ", Número Destino=" + getNumeroReceptor() + ", Fecha=" + getFecha() + ", Hora=" + hora;
    }

    public String imprimirInformarcionMensajesRecibidos() {
        return "Mensaje=" + getMensaje() + ", Número Emisor=" + getNumeroReceptor() + ", Fecha=" + getFecha() + ", Hora=" + hora;
    }
}
