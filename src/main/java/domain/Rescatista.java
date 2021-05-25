package domain;

import java.time.LocalDate;
import java.util.Objects;

import static constants.Mensajes.NOT_NULO;

public class Rescatista {
  private String nombre;
  private String apellido;
  private TipoIdentificacion tipoIdentificacion;
  private String numeroIdentificacion;
  private LocalDate fechaNacimiento;
  private Rescate rescate;
  private Contacto contacto;
  private Ubicacion direccion;

  public Rescatista(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion, LocalDate fechaNacimiento, Rescate rescate, Contacto contacto, Ubicacion direccion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
    this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, NOT_NULO.mensaje("numeroIdentificacion"));
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
    this.rescate = Objects.requireNonNull(rescate, NOT_NULO.mensaje("mascotaEncontrada"));
    this.contacto = Objects.requireNonNull(contacto, NOT_NULO.mensaje("contacto"));
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public TipoIdentificacion getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public String getNumeroIdentificacion() {
    return numeroIdentificacion;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public Rescate getRescate() {
    return rescate;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public Ubicacion getDireccion() {
    return direccion;
  }
}