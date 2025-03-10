package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name = "mascotas")
public class Mascota {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mascota_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_mascota")
  private TipoMascota tipoMascota;

  private String nombre;
  private String apodo;

  @Column(name = "edad_aproximada")
  private Double edadAproximada;

  @Enumerated(EnumType.STRING)
  private Sexo sexo;

  @Column(name = "descripcion_fisica")
  private String descripcionFisica;

  @ElementCollection
  @CollectionTable(
      name = "mascotas_fotos",
      joinColumns = @JoinColumn(name = "mascota_id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"mascota_id", "foto"})
  )
  @Column(name = "foto", nullable = false)
  private List<String> fotos;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "mascota_id")
  private List<RespuestaCaracteristicaMascota> caracteristicas;

  @Enumerated(EnumType.STRING)
  @Column(name = "situacion_mascota")
  private SituacionMascota situacionMascota;

  public Mascota() {
  }

  public Mascota(
      TipoMascota tipoMascota,
      String nombre,
      String apodo,
      Double edadAproximada,
      Sexo sexo,
      String descripcionFisica,
      List<String> fotos,
      List<RespuestaCaracteristicaMascota> caracteristicas,
      SituacionMascota situacionMascota
  ) {
    this.tipoMascota = Objects.requireNonNull(tipoMascota, NOT_NULO.mensaje("tipoMascota"));
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apodo = Objects.requireNonNull(apodo, NOT_NULO.mensaje("apodo"));
    this.edadAproximada = Objects.requireNonNull(edadAproximada, NOT_NULO.mensaje("edad"));
    this.sexo = Objects.requireNonNull(sexo, NOT_NULO.mensaje("sexo"));
    this.descripcionFisica = Objects.requireNonNull(
        descripcionFisica,
        NOT_NULO.mensaje("descripcionFisica")
    );
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.caracteristicas = Objects.isNull(caracteristicas) ? new ArrayList<>() : caracteristicas;
    this.situacionMascota = situacionMascota;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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
    this.edadAproximada = Objects.requireNonNull(edadAproximada, NOT_NULO.mensaje("edad"));
  }

  public Sexo getSexo() {
    return this.sexo;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public List<String> getFotos() {
    return fotos;
  }

  public void setFotos(List<String> fotos) {
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
  }

  public void addFoto(String foto) {
    fotos.add(foto);
  }

  public List<RespuestaCaracteristicaMascota> getCaracteristicas() {
    return caracteristicas;
  }

  public void addCaracteristica(RespuestaCaracteristicaMascota caracteristica) {
    caracteristicas.add(caracteristica);
  }

  public void setCaracteristicas(List<RespuestaCaracteristicaMascota> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public SituacionMascota getSituacionMascota() {
    return this.situacionMascota;
  }

  public void setSituacionMascota(SituacionMascota situacionMascota) {
    this.situacionMascota = situacionMascota;
  }

  public String getTamanio() {
    Opcion tamanio = this.getCaracteristicas()
        .stream()
        .map(RespuestaPregunta::getSelecciones)
        .flatMap(Collection::stream)
        .filter(Opcion::esOpcionTamanio)
        .findFirst()
        .orElse(new Opcion(""));

    return tamanio.getDescripcion();
  }

  public JSONObject toJson() {
    JSONObject jsonMascota = new JSONObject();
    jsonMascota.put("id", this.id);
    jsonMascota.put("tipo_mascota", this.tipoMascota.getLabel());
    jsonMascota.put("nombre", this.nombre);
    jsonMascota.put("apodo", this.apodo);
    jsonMascota.put("edad", this.edadAproximada);
    jsonMascota.put("sexo", this.sexo.getLabel());
    jsonMascota.put("descripcion", this.descripcionFisica);
    JSONArray jsonCaracteristicas = new JSONArray();
    jsonCaracteristicas.addAll(this.caracteristicas);
//    TODO: HERE
//    jsonMascota.put("caracteristicas", this.caracteristicas.stream().map(caracteristica -> caracteristica.toJson()).collect(Collectors.toList()));

    return jsonMascota;
  }
}