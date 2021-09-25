package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class DatoPersonal {
  private String nombre;
  private String apellido;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_identificacion")
  private TipoIdentificacion tipoIdentificacion;
  @Column(name = "numero_identificacion")
  private String numeroIdentificacion;
  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  public DatoPersonal() {
  }

  public DatoPersonal(
      String nombre,
      String apellido,
      TipoIdentificacion tipoIdentificacion,
      String numeroIdentificacion,
      LocalDate fechaNacimiento
  ) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.tipoIdentificacion = Objects.requireNonNull(
        tipoIdentificacion,
        NOT_NULO.mensaje("tipoIdentificacion")
    );
    this.numeroIdentificacion = Objects.requireNonNull(
        numeroIdentificacion,
        NOT_NULO.mensaje("nroIdentificacion")
    );
    this.fechaNacimiento = Objects.requireNonNull(
        fechaNacimiento,
        NOT_NULO.mensaje("fechaNacimiento")
    );
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
