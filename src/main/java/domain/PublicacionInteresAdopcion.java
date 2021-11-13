package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;


@Entity(name = "publicaciones")
public class PublicacionInteresAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publicacion_id")
  private int id;

  @OneToOne
  private Duenio interesado;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "publicacion_id")
  private List<RespuestaInteresAdopcion> preguntas;

  @Column(name = "activa")
  private Boolean estaActiva;

  public PublicacionInteresAdopcion() {
  }

  public PublicacionInteresAdopcion(Duenio interesado, List<RespuestaInteresAdopcion> preguntas) {
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.preguntas = Objects.requireNonNull(preguntas, NOT_NULO.mensaje("preguntas"));
    this.estaActiva = Boolean.TRUE;
    this.enviarBotonDeBaja();
  }

  public int getId() {
    return id;
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<RespuestaInteresAdopcion> getPreguntas() {
    return preguntas;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  public void enviarBotonDeBaja() {
    this.interesado.contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

//  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
//    return this.cumpleConPreferencias(publicacion.getMascota().getCaracteristicas())
//           && this.cumpleConComodidades(publicacion.getPreguntasAdopcion());
//  }

//  public Boolean cumpleConComodidades(List<RespuestaPublicacionAdopcion> comodidades) {
//    List<RespuestaInteresAdopcion> preguntasComodidad = this.getPreguntasSegun(AlcancePregunta.PREGUNTA_COMODIDAD);
//
//    return comodidades
//        .stream()
//        .allMatch(comodidad -> preguntasComodidad.stream()
//            .anyMatch(pregunta -> pregunta.tieneMismaRespuesta(comodidad))
//        );
//  }

  public Boolean cumpleConPreferencias(List<RespuestaCaracteristica> respuestaCaracteristicas) {
    return getPreguntasSegun(AlcancePregunta.PREGUNTA_PREFERENCIA)
        .stream()
        .allMatch(
            preferencia -> respuestaCaracteristicas.stream().anyMatch(
                respuestaCaracteristica -> respuestaCaracteristica.tieneMismaRespuesta(preferencia)
            )
        );
  }

  private List<RespuestaInteresAdopcion> getPreguntasSegun(AlcancePregunta alcance) {
    return this.getPreguntas()
        .stream()
        .filter(pregunta -> pregunta.getPregunta().getAlcancePregunta().equals(alcance))
        .collect(Collectors.toList());
  }
}
