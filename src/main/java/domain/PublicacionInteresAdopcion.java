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
  private List<PreguntaInteresAdopcion> preguntas;

  @Column(name = "activa")
  private Boolean estaActiva;

  public PublicacionInteresAdopcion() {
  }

  public PublicacionInteresAdopcion(Duenio interesado, List<PreguntaInteresAdopcion> preguntas) {
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

  public List<PreguntaInteresAdopcion> getPreguntas() {
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

  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
    return this.cumpleConPreferencias(publicacion.getMascota().getCaracteristicas())
           && this.cumpleConComodidades(publicacion.getPreguntas());
  }

  public Boolean cumpleConComodidades(List<PreguntaPublicacionAdopcion> comodidades) {
    List<PreguntaInteresAdopcion> preguntasComodidad = this.getPreguntasSegun(AlcancePregunta.PREGUNTA_COMODIDAD);

    return comodidades
        .stream()
        .allMatch(comodidad -> preguntasComodidad.stream()
            .anyMatch(pregunta -> pregunta.tieneMismaRespuesta(comodidad))
        );
  }

  public Boolean cumpleConPreferencias(List<MascotaCaracteristica> mascotaCaracteristicas) {
    return getPreguntasSegun(AlcancePregunta.PREGUNTA_PREFERENCIA)
        .stream()
        .allMatch(
            preferencia -> mascotaCaracteristicas.stream().anyMatch(
                mascotaCaracteristica -> mascotaCaracteristica.tieneMismaRespuesta(preferencia)
            )
        );
  }

  private List<PreguntaInteresAdopcion> getPreguntasSegun(AlcancePregunta alcance) {
    return this.getPreguntas()
        .stream()
        .filter(pregunta -> pregunta.getPregunta().getAlcancePregunta().equals(alcance))
        .collect(Collectors.toList());
  }
}
