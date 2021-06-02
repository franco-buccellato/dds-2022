package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public abstract class Rescate {

  private List<String> fotos;
  private String descripcion;
  private Ubicacion lugarEncuentro;
  private LocalDate fecha;
  private Mascota mascota;
  private Rescatista rescatista;

  public Rescate(List<String> fotos, String descripcion, Ubicacion lugarEncuentro, LocalDate fecha, Mascota mascota, Rescatista rescatista) {
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.lugarEncuentro = Objects.requireNonNull(lugarEncuentro, NOT_NULO.mensaje("lugarEncuentro"));
    this.fecha = Objects.requireNonNull(fecha, NOT_NULO.mensaje("fecha"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.rescatista = Objects.requireNonNull(rescatista, NOT_NULO.mensaje("rescatista"));

    informaRescate();
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

  public void setMascota(Mascota unaMascota) {
    this.mascota = unaMascota;
    mascota.setSituacionMascota(SituacionMascota.EN_HOGAR_TRANSITORIO);
  }

  public Rescatista getRescatista() {
    return rescatista;
  }

  public Boolean ocurrioEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.getFecha().isAfter(fechaInicio) && this.getFecha().isBefore(fechaFin);
  }

  public abstract void informaRescate();
}
