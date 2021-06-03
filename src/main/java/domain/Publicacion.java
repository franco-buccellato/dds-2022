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
    RepositorioPublicaciones.getRepositorioPublicaciones().agregarPublicacion(this);
  }

  Rescate getRescate() {
    return rescate;
  }

  EstadoPublicacion getEstado() {
    return this.estado;
  }

  Asociacion getAsociacion() {
    return asociacion;
  }

  void aprobar() {
    this.estado = EstadoPublicacion.ACEPTADA;
  }

  void rechazar() {
    this.estado = EstadoPublicacion.RECHAZADA;
  }

  void buscarAsociacionCercana() {
    asociacion = RepositorioAsociaciones
        .getRepositorioAsociaciones()
        .encontrarMasCercana(rescate.getLugarEncuentro());
  }

  void notificarRescatista(Duenio duenio) {
    this.getRescate().getRescatista().notificarMatchDuenio(this.getRescate(), duenio);
  }

}
