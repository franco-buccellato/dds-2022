package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import domain.exception.PreguntaObligatoriaNoContestadaException;
import domain.templatesNotificacion.InteresadoEnAdoptarTemplate;

@Entity(name = "publicaciones_adopcion")
public class PublicacionAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publicacion_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  private Duenio duenio;

  @ManyToOne(cascade = CascadeType.ALL)
  private Mascota mascota;

  @Column(name = "activa")
  private Boolean estaActiva;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "asociacion_id")
  private Asociacion asociacion;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "publicacion_adopcion_id")
  private List<SeleccionPublicacionAdopcion> seleccionesAdopcion;

  public PublicacionAdopcion() {
  }

  public PublicacionAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion, List<SeleccionPublicacionAdopcion> seleccionesAdopcion) {
    this.chequearTodasPreguntasRespondidas(asociacion, seleccionesAdopcion);
    this.id = new Random().nextLong();
    this.duenio = Objects.requireNonNull(duenio, NOT_NULO.mensaje("duenio"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.asociacion = Objects.requireNonNull(asociacion, NOT_NULO.mensaje("asociacion"));
    this.seleccionesAdopcion = Objects.requireNonNull(
        seleccionesAdopcion, NOT_NULO.mensaje("seleccionesAdopcion")
    );
    this.estaActiva = Boolean.TRUE;
  }

  public Long getId() {
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

  public Asociacion getAsociacion() {
    return this.asociacion;
  }

  public void notificarInteresAdopcionDe(Duenio adoptante) {
    this.duenio.contactoTitular()
        .notificar(new Notificacion(new InteresadoEnAdoptarTemplate(adoptante)));
  }

  public List<SeleccionPublicacionAdopcion> getSeleccionesAdopcion() {
    return this.seleccionesAdopcion;
  }

  public void chequearTodasPreguntasRespondidas(Asociacion asociacion, List<SeleccionPublicacionAdopcion> seleccionesAdopcion) {
    boolean todasRespondidas = asociacion.getPreguntasAdopcion()
        .stream()
        .filter(Pregunta::getObligatoria)
        .allMatch(pregunta -> seleccionesAdopcion.stream().anyMatch(
            seleccionPublicacionAdopcion -> seleccionPublicacionAdopcion.esDeMismaPregunta(pregunta)
        ));

    if (!todasRespondidas) {
      throw new PreguntaObligatoriaNoContestadaException(
          "Es obligatorio responder todas las preguntas que la asociacion requiera"
      );
    }
  }
}
