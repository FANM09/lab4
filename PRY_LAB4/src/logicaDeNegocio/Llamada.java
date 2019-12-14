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

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNumeroEmisor() {
        return numeroEmisor;
    }

    public void setNumeroEmisor(String numeroEmisor) {
        this.numeroEmisor = numeroEmisor;
    }

    public String getNumeroReceptor() {
        return numeroReceptor;
    }

    public void setNumeroReceptor(String numeroReceptor) {
        this.numeroReceptor = numeroReceptor;
    }

    public String getTipoLlamado() {
        return tipoLlamado;
    }

    public void setTipoLlamado(String tipoLlamado) {
        this.tipoLlamado = tipoLlamado;
    }

    private void setFecha() {
        Calendar calendario;
        calendario = Calendar.getInstance();
        fecha = calendario.getTime();
    }

    private String getFecha() {
        SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
        return mascara.format(fecha);
    }

    private String getHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Llamada{" + "duracion=" + duracion + ", numero=" + numeroEmisor + ", fecha=" + getFecha() + ", hora=" + getHora() + '}';
    }

}
