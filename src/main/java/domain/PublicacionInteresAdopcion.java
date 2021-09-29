package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntaObligatoriaNoContestadaException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class PublicacionInteresAdopcion {
  private Duenio interesado;
  private List<Pregunta> preguntas;
  private Boolean estaActiva;

  public PublicacionInteresAdopcion(Duenio interesado, List<Pregunta> preguntas) {
    if (estanCompletasLasPreguntas(preguntas).get()) {
      this.estaActiva = true;
    }
    this.interesado = Objects.requireNonNull(interesado, NOT_NULO.mensaje("interesado"));
    this.preguntas = Objects.requireNonNull(preguntas, NOT_NULO.mensaje("preguntas"));
  }

  public Duenio getInteresado() {
    return interesado;
  }

  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }

  private AtomicBoolean estanCompletasLasPreguntas(List<Pregunta> preguntas) throws PreguntaObligatoriaNoContestadaException {
    AtomicBoolean estanCompletas = new AtomicBoolean(true);
    preguntas
        .stream()
        .forEach(pregunta -> {
          if (pregunta.getOpcionesSeleccionas().isEmpty()) {
            estanCompletas.set(false);
            throw new PreguntaObligatoriaNoContestadaException("Falta contestar la pregunta " + pregunta.getDescripcion());
          }
        });
    return estanCompletas;
  }

  public void enviarBotonDeBaja() {
    // TODO: Definir template con boton
    this.interesado.contactoTitular().notificar(new Notificacion(null));
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean cumpleConPublicacionAdopcion(PublicacionAdopcion publicacion) {
    return cumpleConComodidades(publicacion.getComodidadesMascota())
      && cumpleConPreferencias(publicacion.getMascota().getCaracteristicas());
  }

  public Boolean cumpleConComodidades(List<Pregunta> comodidades) {
    return getPreguntasSegun(AlcancePregunta.PREGUNTA_COMODIDAD)
        .stream()
        .allMatch(preguntaCumplir ->
            comodidades
                .stream()
                .anyMatch(preguntaCumplir::tienenMismasOpciones)
        );
  }

  public Boolean cumpleConPreferencias(List<Caracteristica> caracteristicas) {
    return caracteristicas
        .stream()
        .allMatch(caracteristica ->
            getPreguntasSegun(AlcancePregunta.PREGUNTA_PREFERENCIA)
                .stream()
                .anyMatch(pregunta -> caracteristica.tienenMismasOpciones(pregunta))
        );
  }

  private List<Pregunta> getPreguntasSegun(AlcancePregunta alcance) {
    return preguntas
        .stream()
        .filter(pregunta -> pregunta.getAlcancePregunta().equals(alcance))
        .collect(Collectors.toList());
  }
}
