package servicio.viewmodel;

import domain.HogarTransito;

import java.math.BigDecimal;

public class HogarTransitoVM {
  private String nombre;
  private String telefono;
  private String direccion;
  private String codigoPostal;
  private String localidad;
  private BigDecimal latitud;
  private BigDecimal longitud;

  public HogarTransitoVM(HogarTransito hogarTransito) {
    this.nombre = hogarTransito.getNombre();
    this.telefono = hogarTransito.getTelefono();
    this.direccion = hogarTransito.getUbicacion().getDireccion();
    this.codigoPostal = hogarTransito.getUbicacion().getCodigoPostal();
    this.latitud = hogarTransito.getUbicacion().getLatitud();
    this.longitud = hogarTransito.getUbicacion().getLongitud();
  }

  public String getNombre() {
    return nombre;
  }

  public String getTelefono() {
    return telefono;
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
}
