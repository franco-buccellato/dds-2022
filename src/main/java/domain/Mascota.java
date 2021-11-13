package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity(name = "mascotas")
public class Mascota {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mascota_id")
  private int id;

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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "mascota_id")
  private List<RespuestaCaracteristica> caracteristicas;

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
      List<RespuestaCaracteristica> caracteristicas,
      SituacionMascota situacionMascota
  ) {
    this.tipoMascota = Objects.requireNonNull(tipoMascota, NOT_NULO.mensaje("tipoMascota"));
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apodo = Objects.requireNonNull(apodo, NOT_NULO.mensaje("apodo"));
    this.edadAproximada = Objects.requireNonNull(
        edadAproximada,
        NOT_NULO.mensaje("edadAproximada")
    );
    this.sexo = Objects.requireNonNull(sexo, NOT_NULO.mensaje("sexo"));
    this.descripcionFisica = Objects.requireNonNull(
        descripcionFisica,
        NOT_NULO.mensaje("descripcionFisica")
    );
    this.fotos = Objects.requireNonNull(fotos, NOT_NULO.mensaje("fotos"));
    this.caracteristicas = Objects.isNull(caracteristicas)
        ? new ArrayList<>()
        : caracteristicas;
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

  public List<String> getFotos() {
    return fotos;
  }

  public void setFotos(List<String> fotos) {
    this.fotos = fotos;
  }

  public void addFoto(String foto) {
    fotos.add(foto);
  }

  public List<RespuestaCaracteristica> getCaracteristicas() {
    return caracteristicas;
  }

  public void addCaracteristica(RespuestaCaracteristica caracteristica) {
    caracteristicas.add(caracteristica);
  }

  public void setCaracteristicas(List<RespuestaCaracteristica> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public SituacionMascota getSituacionMascota() {
    return this.situacionMascota;
  }

  public void setSituacionMascota(SituacionMascota situacionMascota) {
    this.situacionMascota = situacionMascota;
  }

//  public List<Caracteristica> getCaracteristicasSeleccionadas() {
//    return caracteristicas
//        .stream()
//        .filter(caracteristica -> !caracteristica.getOpcionesSeleccionas().isEmpty())
//        .collect(Collectors.toList());
//  }

//  public String getTamanio() {
//    Opcion tamanio = caracteristicas
//        .stream()
//        .map(Caracteristica::getOpcionesSeleccionas)
//        .flatMap(Collection::stream)
//        .filter(opcion -> Arrays.asList("Chico", "Mediano", "Grande")
//            .contains(opcion.getDescripcion()))
//        .findFirst()
//        .orElse(new Opcion(""));
//
//    return tamanio.getDescripcion();
//  }
}