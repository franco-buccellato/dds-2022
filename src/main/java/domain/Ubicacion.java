package domain;

import java.math.BigDecimal;

public class Ubicacion {
  private String direccion;
  private String codigoPostal;
  private String localidad;
  private BigDecimal latitud;
  private BigDecimal longitud;

  public Ubicacion(String direccion, String codigoPostal, String localidad, BigDecimal latitud, BigDecimal longitud) {
    this.direccion = direccion;
    this.codigoPostal = codigoPostal;
    this.localidad = localidad;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getDireccion() {
    return direccion;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public String getLocalidad() {
    return localidad;
  }

  public BigDecimal getLatitud() {
    return latitud;
  }

  public BigDecimal getLongitud() {
    return longitud;
  }

  public float distanciaA(Ubicacion destino) {
    return (
        valorAbsolutoNumero(
            destino.getLatitud().subtract(this.latitud)
                .subtract(destino.getLongitud().subtract(this.longitud)).floatValue()));
  }

  static float valorAbsolutoNumero(float num){
    return num>=0?num:-num;
  }


}