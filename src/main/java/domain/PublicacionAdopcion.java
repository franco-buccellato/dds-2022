package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.PreguntasAdopcionSinResponderException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PublicacionAdopcion {
  private final UUID idPublicacion;
  private Duenio duenio;
  private Mascota mascota;
  private Boolean estaActiva;
  private Asociacion asociacion;
  private List<Caracteristica> preguntasAdopcion;

  public PublicacionAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion, List<Caracteristica> preguntasAdopcion) {
    this.idPublicacion = UUID.randomUUID();
    this.duenio = Objects.requireNonNull(duenio, NOT_NULO.mensaje("duenio"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.asociacion = Objects.requireNonNull(asociacion, NOT_NULO.mensaje("asociacion"));
    if (this.fueronTodasContestadas(preguntasAdopcion)) {
      this.preguntasAdopcion = preguntasAdopcion;
    } else {
      throw new PreguntasAdopcionSinResponderException("Para generar la publicacion deben contestarse todas las preguntas");
    }
    this.estaActiva = Boolean.TRUE;
  }

  public UUID getIdPublicacion() {
    return idPublicacion;
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

  public List<Caracteristica> getPregutasAdopcion() {
    return this.preguntasAdopcion;
  }

  private Boolean fueronTodasContestadas(List<Caracteristica> preguntasAdopcion) {
    return !preguntasAdopcion
        .stream()
        .anyMatch(caracteristica -> caracteristica.getOpcionesSeleccionas().isEmpty());
  }

  public Boolean cumpleConCaracteristicas(List<Caracteristica> caracteristicasCumplir) {
    return caracteristicasCumplir
            .stream()
            .allMatch(caracteristicaCumplir ->
                    this.preguntasAdopcion
                            .stream()
                            .anyMatch(caracteristicaCumplir::tienenMismasOpciones)
            );
  }
}
