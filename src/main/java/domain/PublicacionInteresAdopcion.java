package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntaObligatoriaNoContestadaException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class PublicacionInteresAdopcion {
  private final UUID idPublicacion;
  private Duenio interesado;
  private List<Caracteristica> preguntas;
  private Boolean estaActiva;

  public PublicacionInteresAdopcion(Duenio interesado, List<Caracteristica> preguntas) {
    if (!estanCompletasLasPreguntas(preguntas).get()) {
      this.estaActiva = true;
    }
    // TODO: Definir si aca se define el id o por PK con persistencia
    this.idPublicacion = UUID.randomUUID();
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.preguntas = Objects.requireNonNull(preguntas, NOT_NULO.mensaje("preguntas"));
  }

  public UUID getIdPublicacion() {
    return idPublicacion;
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

  private List<Caracteristica> filtrarPreguntasAdopcion(List<Caracteristica> pregutas) {
    return preguntas.stream()
        .filter(caracteristica -> caracteristica.getAlcanceCaracteristica()
            .stream()
            .anyMatch(alcanceCaracteristica -> alcanceCaracteristica == AlcanceCaracteristica.PREGUSTA_ADOPCION)
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
