package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntasAdopcionSinResponderException;
import domain.templatesNotificacion.InteresadoEnAdoptarTemplate;

import java.util.List;
import java.util.Objects;

public class PublicacionAdopcion {
  private Duenio duenio;
  private Mascota mascota;
  private Boolean estaActiva;
  private Asociacion asociacion;
  private List<Pregunta> comodidadesMascota;

  public PublicacionAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion, List<Pregunta> comodidadesMascota) {
    this.duenio = Objects.requireNonNull(duenio, NOT_NULO.mensaje("duenio"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.asociacion = Objects.requireNonNull(asociacion, NOT_NULO.mensaje("asociacion"));
    if (this.fueronTodasContestadas(comodidadesMascota)) {
      this.comodidadesMascota = comodidadesMascota;
    } else {
      throw new PreguntasAdopcionSinResponderException("Para generar la publicacion deben contestarse todas las preguntas");
    }
    this.estaActiva = Boolean.TRUE;
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

  public List<Pregunta> getComodidadesMascota() {
    return this.comodidadesMascota;
  }

  private Boolean fueronTodasContestadas(List<Pregunta> comodidadesMascota) {
    return !comodidadesMascota
        .stream()
        .anyMatch(comodidad -> comodidad.getOpcionesSeleccionas().isEmpty());
  }
}
