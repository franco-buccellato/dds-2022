package domain;

import java.time.LocalDate;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class DatoPersonal {
  private String nombre;
  private String apellido;
  private TipoIdentificacion tipoIdentificacion;
  private String numeroIdentificacion;
  private LocalDate fechaNacimiento;


  public DatoPersonal(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion, LocalDate fechaNacimiento) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
    this.numeroIdentificacion = Objects.requireNonNull(numeroIdentificacion, NOT_NULO.mensaje("nroIdentificacion"));
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
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
}
