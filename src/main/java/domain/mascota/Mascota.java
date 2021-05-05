package domain.mascota;

import static constants.Mensajes.NOT_NULO;

import constants.TipoMascota;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mascota {
  private final TipoMascota tipoMascota;
  private final String nombre;
  private final String apodo;
  private Double edadAproximada;
  private final String sexo;
  private final String descripcionFisica;
  private List<Image> fotos;
  private List<Caracteristica> caracteristicas;

  public Mascota(TipoMascota tipoMascota, String nombre, String apodo, Double edadAproximada, String sexo, String descripcionFisica, List<Image> fotos, List<Caracteristica> caracteristicas) {
    this.tipoMascota = Objects.requireNonNull(tipoMascota, NOT_NULO.mensaje("tipoMascota"));
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apodo = Objects.requireNonNull(apodo, NOT_NULO.mensaje("apodo"));
    this.edadAproximada = Objects.requireNonNull(edadAproximada, NOT_NULO.mensaje("edadAproximada"));
    this.sexo = Objects.requireNonNull(sexo, NOT_NULO.mensaje("sexo"));
    this.descripcionFisica = Objects.requireNonNull(descripcionFisica, NOT_NULO.mensaje("descripcionFisica"));
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.caracteristicas = caracteristicas;
  }

  public TipoMascota getTipoMascota() {
    return tipoMascota;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApodo() {
    return apodo;
  }

  public Double getEdadAproximada() {
    return edadAproximada;
  }

  public void setEdadAproximada(Double edadAproximada) {
    this.edadAproximada = edadAproximada;
  }

  public String getSexo() {
    return sexo;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public List<Image> getFotos() {
    return fotos;
  }

  public void setFotos(List<Image> fotos) {
    this.fotos = fotos;
  }

  public void addFoto(Image foto) {
    this.fotos.add(foto);
  }

  public List<Caracteristica> getCaracteristicas() {
    return caracteristicas;
  }

  public void setCaracteristicas(List<Caracteristica> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public void addCaracteristica(Caracteristica caracteristica) {
    this.caracteristicas = Objects.isNull(this.caracteristicas)
        ? new ArrayList<>()
        : this.caracteristicas;
    this.caracteristicas.add(caracteristica);
  }
}
