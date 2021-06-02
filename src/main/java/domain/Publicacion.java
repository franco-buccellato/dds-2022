package domain;

public class Publicacion {
  RescateSinChapa rescateSinChapa;
  EstadoPublicacion estado;
  Asociacion asociacion;

  Publicacion(RescateSinChapa rescateSinChapa) {
    this.rescateSinChapa = rescateSinChapa;
    this.estado = EstadoPublicacion.ESPERA;
    RepositorioPublicaciones.getRepositorioPublicaciones().agregarPublicacion(this);
    this.buscarAsignacionCercana();
  }

  void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
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

  void buscarAsignacionCercana() {
    asociacion = RepositorioAsociaciones
      .getRrepositorioAsociaciones()
      .encontrarMasCercana(rescateSinChapa.getLugarEncuentro());
  }

  void notificarRescatista(Duenio duenio) {
    rescateSinChapa.getRescatista().notificarMatchDuenio(rescateSinChapa, duenio);
  }

}
