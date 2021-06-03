package domain;

import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonProperty;

public class Ubicacion {
  @JsonProperty("direccion")
  private String direccion;
  private String codigoPostal;
  private String localidad;
  @JsonProperty("lat")
  private BigDecimal latitud;
  @JsonProperty("long")
  private BigDecimal longitud;

  public Ubicacion() {
  }

  public Ubicacion(
      String direccion,
      String codigoPostal,
      String localidad,
      BigDecimal latitud,
      BigDecimal longitud
  ) {
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

  public double distanciaA(Ubicacion destino) {
    return Math.sqrt(
        (destino.getLatitud().subtract(this.getLatitud()).pow(2).add(
            destino.getLongitud().subtract(this.getLongitud()).pow(2))
        ).doubleValue()
    );
  }
}