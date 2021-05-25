package domain;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class Rescate{
  private List<Image> fotos;
  private String descripcion;
  private Ubicacion lugarEncuentro;
  private LocalDate fecha;
  private Mascota mascota;

  public Rescate(List<Image> fotos, String descripcion, Ubicacion lugarEncuentro, LocalDate fecha) {
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.descripcion = Objects.requireNonNull ( descripcion, NOT_NULO.mensaje ( "descripcion" ) );
    this.lugarEncuentro = Objects.requireNonNull ( lugarEncuentro, NOT_NULO.mensaje ( "lugarEncuentro" ) );
    this.fecha = Objects.requireNonNull ( fecha, NOT_NULO.mensaje ( "fecha" ) );
  }

  /*Getters & Setters*/
  public List<Image> getFotos() {
    return fotos;
  }

  public void addFoto(Image foto) {
    this.fotos.add (foto);
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public Ubicacion getLugarEncuentro() {
    return this.lugarEncuentro;
  }

  public LocalDate getFecha() {
    return this.fecha;
  }

  public void setMascota(Mascota unaMascota){
    this.mascota = mascota;
    mascota.setSituacionMascota(SituacionMascota.EN_HOGAR_TRANSITORIO);
  }

  public Boolean encontradaEntre(LocalDate fechaInicio, LocalDate fechaFin){
    return this.getFecha().isAfter(fechaInicio) && this.getFecha().isBefore(fechaFin);
  }
}
