package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;

public class Publicacion {
  Rescate rescate;
  EstadoPublicacion estado;
  Asociacion asociacion;

  Publicacion(Rescate rescate) {
    this.rescate = Objects.requireNonNull(rescate, NOT_NULO.mensaje("rescate"));
    this.estado = EstadoPublicacion.ESPERA;
    //RepositorioPublicaciones.getRepositorioPublicaciones().agregarPublicacion(this);
  }

  void aprobar() {
    this.estado = EstadoPublicacion.ACEPTADA;
  }

  void rechazar() {
    this.estado = EstadoPublicacion.RECHAZADA;
  }

  EstadoPublicacion getEstado() {
    return this.estado;
  }

  Asociacion getAsociacion() {
    return asociacion;
  }

  void buscarAsociacionCercana() {
    asociacion = RepositorioAsociaciones
        .getRepositorioAsociaciones()
        .encontrarMasCercana(rescate.getLugarEncuentro());
  }

  void notificarRescatista(Duenio duenio) {
    rescate.getRescatista().notificarMatchDuenio(rescate, duenio);
  }

}
