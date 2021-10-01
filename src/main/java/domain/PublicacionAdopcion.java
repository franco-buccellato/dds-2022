package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntasAdopcionSinResponderException;
import domain.templatesNotificacion.InteresadoEnAdoptarTemplate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "publicaciones_adopcion")
public class PublicacionAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publicacion_adopcion_id")
  private int id;
  @ManyToOne(cascade = CascadeType.ALL)
  private Duenio duenio;
  @ManyToOne(cascade = CascadeType.ALL)
  private Mascota mascota;
  @Column(name = "activa")
  private Boolean estaActiva;
  @ManyToOne(cascade = CascadeType.ALL)
  private Asociacion asociacion;
  @ManyToMany
  @JoinTable(
      name = "preguntas_adopcion",
      joinColumns = @JoinColumn(name = "asociacion_id"),
      inverseJoinColumns = @JoinColumn(name = "pregunta_id")
  )
  private List<Pregunta> preguntas;

  private PublicacionAdopcion() {}

  public PublicacionAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion, List<Pregunta> preguntas) {
    this.duenio = Objects.requireNonNull(duenio, NOT_NULO.mensaje("duenio"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.asociacion = Objects.requireNonNull(asociacion, NOT_NULO.mensaje("asociacion"));
    if (this.fueronTodasContestadas(preguntas)) {
      this.preguntas = preguntas;
    } else {
      throw new PreguntasAdopcionSinResponderException("Para generar la publicacion deben contestarse todas las preguntas");
    }
    this.estaActiva = Boolean.TRUE;
  }

  public int getId() {
    return id;
  }

  public Mascota getMascota() {
    return this.mascota;
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  public void notificarInteresAdopcionDe(Duenio adoptante) {
    this.duenio.contactoTitular()
        .notificar(new Notificacion(new InteresadoEnAdoptarTemplate(adoptante)));
  }

  public List<Pregunta> getPreguntas() {
    return this.preguntas;
  }

  private Boolean fueronTodasContestadas(List<Pregunta> preguntas) {
    return preguntas
        .stream()
        .noneMatch(pregunta -> pregunta.getOpcionesSeleccionas().isEmpty());
  }
}
