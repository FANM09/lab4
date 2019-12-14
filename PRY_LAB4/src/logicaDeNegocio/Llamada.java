package logicaDeNegocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Llamada {

    private int duracion;
    private String numeroEmisor;
    private String numeroReceptor;
    private String tipoLlamado;
    private Date fecha;
    private String hora;

    public Llamada(int duracion, String numeroEmisor, String numeroReceptor, String tipoLlamado) {
        this.duracion = duracion;
        this.numeroEmisor = numeroEmisor;
        this.numeroReceptor = numeroReceptor;
        this.tipoLlamado = tipoLlamado;
        setFecha();
        this.hora = getHora();
    }

    public int getDuracion() {
        return duracion;
    }

    public String getNumeroEmisor() {
        return numeroEmisor;
    }

    public String getNumeroReceptor() {
        return numeroReceptor;
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

    public String imprimirInformarcionLlamadasRecibidas() {
        return "Duración=" + getDuracion() + ", Número Destino=" + getNumeroEmisor() + ", Fecha=" + getFecha() + ", Hora=" + getHora();
    }

    public String imprimirInformarcionLlamadasRealizadas() {
        return "Duración=" + getDuracion() + ", Número Emisor=" + getNumeroEmisor() + ", Fecha=" + getFecha() + ", Hora=" + getHora();
    }

}
