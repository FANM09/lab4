// Realizado por Gerald Navarro,Fabián Navarro

package logicaDeNegocio;

public class ChipPrepago {
  String numeroTelefono = null;
  int saldo = 0;
  int CODIGO_PAIS = 506;
  String estado = null;
  int oportunidadesSalvame = 0;
  int megabytesDisponibles = 0;
  String duenioChip = null;
  
  static int cantidadLineasCreadas = 0;
  
  public ChipPrepago(String pNumeroTelefono){
      numeroTelefono = pNumeroTelefono;
      saldo = 0;
      CODIGO_PAIS = 506;
      estado = "Desactivado";
      oportunidadesSalvame = 0;
      megabytesDisponibles = 0;
      duenioChip = null;
      cantidadLineasCreadas += 1;
  }
  
  public String activar(String pDuenio,int pCantidadMegabytes){
      saldo = 1000;
      estado = "Activado";
      oportunidadesSalvame = 3;
      megabytesDisponibles = pCantidadMegabytes;
      duenioChip = pDuenio;
      return "La línea ha sido activada correctamente";
  }
  
}
