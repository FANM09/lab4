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

    public void setNumeroEmisor(String numeroEmisor) {
        this.numeroEmisor = numeroEmisor;
    }

    public String getNumeroReceptor() {
        return numeroReceptor;
    }

    public void setNumeroReceptor(String numeroReceptor) {
        this.numeroReceptor = numeroReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
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
        return "Mensaje{" + "mensaje=" + getMensaje() + ", número Destino=" + getNumeroReceptor() + ", fecha=" + getFecha() + ", hora=" + hora + '}';
    }
}
