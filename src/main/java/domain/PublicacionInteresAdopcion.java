package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntaObligatoriaNoContestadaException;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Entity(name = "publicaciones")
public class PublicacionInteresAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne
  private Duenio interesado;
  @OneToMany
  @JoinColumn(name = "publicacion_interes_id")
  private List<Caracteristica> preguntas;
  @Column(name = "activa")
  private Boolean estaActiva;

  public PublicacionInteresAdopcion(Duenio interesado, List<Caracteristica> preguntas) {
    if (estanCompletasLasPreguntas(preguntas).get()) {
      this.estaActiva = true;
    }
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.preguntas = Objects.requireNonNull(preguntas, NOT_NULO.mensaje("preguntas"));
  }

  public int getId() {
    return id;
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<Caracteristica> getPreguntas() {
    return preguntas;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  private AtomicBoolean estanCompletasLasPreguntas(List<Caracteristica> preguntas) throws PreguntaObligatoriaNoContestadaException {
    AtomicBoolean estanCompletasLasPreguntas = new AtomicBoolean(true);
    preguntas
        .stream()
        .forEach(pregunta -> {
          if (pregunta.getOpcionesSeleccionas().isEmpty()) {
            estanCompletasLasPreguntas.set(false);
            throw new PreguntaObligatoriaNoContestadaException("Falta contestar la pregunta " + pregunta.getDescripcion());
          }
        });
    return estanCompletasLasPreguntas;
  }

  private List<Caracteristica> filtrarPreguntasAdopcion(List<Caracteristica> preguntas) {
    return preguntas.stream()
        .filter(caracteristica -> caracteristica.getAlcanceCaracteristica()
            .stream()
            .anyMatch(alcanceCaracteristica -> alcanceCaracteristica == AlcanceCaracteristica.PREGUNTA_ADOPCION)
        ).collect(Collectors.toList());
  }

  public void enviarBotonDeBaja() {
    // TODO: Definir template con boton
    this.interesado.contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }
}
