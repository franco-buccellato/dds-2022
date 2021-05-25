package domain;

import static constants.Mensajes.NOT_NULO;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mascota {
  private TipoMascota tipoMascota;
  private String nombre;
  private String apodo;
  private Double edadAproximada;
  private Sexo sexo;
  private String descripcionFisica;
  private List<Image> fotos;
  private List<Caracteristica> caracteristicas;
  private SituacionMascota situacionMascota;

  public Mascota(TipoMascota tipoMascota, String nombre, String apodo, Double edadAproximada, Sexo sexo, String descripcionFisica, List<Image> fotos, List<Caracteristica> caracteristicas, SituacionMascota situacionMascota) {
    this.tipoMascota = Objects.requireNonNull(tipoMascota, NOT_NULO.mensaje("tipoMascota"));
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apodo = Objects.requireNonNull(apodo, NOT_NULO.mensaje("apodo"));
    this.edadAproximada = Objects.requireNonNull(edadAproximada, NOT_NULO.mensaje("edadAproximada"));
    this.sexo = Objects.requireNonNull(sexo, NOT_NULO.mensaje("sexo"));
    this.descripcionFisica = Objects.requireNonNull(descripcionFisica, NOT_NULO.mensaje("descripcionFisica"));
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.caracteristicas = caracteristicas;
    this.situacionMascota = situacionMascota;
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

  public Sexo getSexo() {
    return this.sexo;
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

  public void setSituacionMascota(SituacionMascota situacionMascota) {
    this.situacionMascota = situacionMascota;
  }

  public SituacionMascota getSituacionMascota() {
    return this.situacionMascota;
  }

  public void addCaracteristica(Caracteristica caracteristica) {
    this.caracteristicas = Objects.isNull(this.caracteristicas)
        ? new ArrayList<>()
        : this.caracteristicas;
    this.caracteristicas.add(caracteristica);
  }
}
