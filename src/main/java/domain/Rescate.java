package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name = "rescates")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_rescate")
public abstract class Rescate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rescate_id")
  Long id;

  @Transient
  private List<String> fotos;

  @Column(name = "rescate_descripcion")
  private String descripcion;

  @OneToOne
  @JoinColumn(name = "ubicacion_id")
  private Ubicacion lugarEncuentro;

  @Column(name = "fecha_nacimiento")
  private LocalDate fecha;

  @OneToOne
  @JoinColumn(name = "mascota_id")
  private Mascota mascota;

  @OneToOne
  @JoinColumn(name = "rescatista_id")
  private Rescatista rescatista;

  public Rescate() {
  }

  public Rescate(
      List<String> fotos,
      String descripcion,
      Ubicacion lugarEncuentro,
      LocalDate fecha,
      Mascota mascota,
      Rescatista rescatista
  ) {
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.lugarEncuentro = Objects.requireNonNull(
        lugarEncuentro,
        NOT_NULO.mensaje("lugarEncuentro")
    );
    this.fecha = Objects.requireNonNull(fecha, NOT_NULO.mensaje("fecha"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.rescatista = Objects.requireNonNull(rescatista, NOT_NULO.mensaje("rescatista"));
    mascota.setSituacionMascota(SituacionMascota.EN_HOGAR_TRANSITORIO);
  }

  /*Getters & Setters*/
  public List<String> getFotos() {
    return fotos;
  }

  public void addFoto(String foto) {
    fotos.add(foto);
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public Rescatista getRescatista() {
    return rescatista;
  }

  public Boolean ocurrioEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.getFecha().isAfter(fechaInicio) && this.getFecha().isBefore(fechaFin);
  }

  public abstract void informaRescate();
}
