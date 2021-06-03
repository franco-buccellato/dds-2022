package domain;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class HogarTransito {

  @JsonIgnore
  int total;

  @JsonIgnore
  int offset;

  @JsonProperty("hogares")
  public List<HogarTransito> getHogares() {
    return this.hogares;
  }

  public void setHogares(List<HogarTransito> hogares) {
    this.hogares = hogares;
  }

  List<HogarTransito> hogares;

  @JsonProperty("id")
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  String id;

  @JsonProperty("nombre")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  String nombre;

  @JsonProperty("ubicacion")
  public Ubicacion getUbicacion() {
    return this.ubicacion;
  }

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  Ubicacion ubicacion;

  @JsonProperty("telefono")
  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  String telefono;

  @JsonProperty("admisiones")
  public AdmisionMascota getAdmisiones() {
    return this.admisiones;
  }

  public void setAdmisiones(AdmisionMascota admisiones) {
    this.admisiones = admisiones;
  }

  AdmisionMascota admisiones;

  @JsonProperty("perros")
  public boolean getPerros() {
    return this.perros;
  }

  public void setPerros(boolean perros) {
    this.perros = perros;
  }

  boolean perros;

  @JsonProperty("gatos")
  public boolean getGatos() {
    return this.gatos;
  }

  public void setGatos(boolean gatos) {
    this.gatos = gatos;
  }

  boolean gatos;

  @JsonProperty("capacidad")
  public int getCapacidad() {
    return this.capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  int capacidad;

  @JsonProperty("lugares_disponibles")
  public int getLugaresDisponibles() {
    return this.lugaresDisponibles;
  }

  public void setLugares_disponibles(int lugaresDisponibles) {
    this.lugaresDisponibles = lugaresDisponibles;
  }

  int lugaresDisponibles;

  @JsonProperty("patio")
  public boolean getPatio() {
    return this.patio;
  }

  public void setPatio(boolean patio) {
    this.patio = patio;
  }

  boolean patio;

  @JsonProperty("caracteristicas")
  public List<String> getCaracteristicas() {
    return this.caracteristicas;
  }

  public void setCaracteristicas(List<String> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  List<String> caracteristicas;
}


