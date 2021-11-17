package domain;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {
  @Id
  @Column(name = "ubicacion_id")
  @GeneratedValue
  Long id;

  @JsonProperty("direccion")
  @Column(name = "ubicacion_direccion")
  private String direccion;
  @Column(name = "ubicacion_codigo_postal")
  private String codigoPostal;
  @Column(name = "ubicacion_localidad")
  private String localidad;
  @JsonProperty("lat")
  @Column(name = "ubicacion_latitud")
  private BigDecimal latitud;
  @JsonProperty("long")
  @Column(name = "ubicacion_longitud")
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

  public Ubicacion(
      String direccion,
      String codigoPostal,
      String localidad
  ) {
    this.direccion = direccion;
    this.codigoPostal = codigoPostal;
    this.localidad = localidad;
    this.latitud = this.obtenerLatitud();
    this.longitud = this.obtenerLongitud();
  }

  private BigDecimal obtenerLongitud() {
    //TODO: obtener coordenada de alguna API externa
    return BigDecimal.valueOf(Math.random());
  }

  private BigDecimal obtenerLatitud() {
    //TODO: obtener coordenada de alguna API externa
    return BigDecimal.valueOf(Math.random());
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
    BigDecimal difLatitud = destino.getLatitud().subtract(this.getLatitud());
    BigDecimal difLongitud = destino.getLongitud().subtract(this.getLongitud());

    double kilometros = Math.sqrt(difLatitud.pow(2).add(difLongitud.pow(2)).doubleValue()) * 100;
    return Math.round(kilometros);
  }
}