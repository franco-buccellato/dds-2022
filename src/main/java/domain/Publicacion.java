package domain;

public class Publicacion {
  Rescate rescate;
  EstadoPublicacion estado;
  Asociacion asociacion;

  Publicacion(Rescate rescate) {
    this.rescate = rescate;
    this.estado = EstadoPublicacion.ESPERA;
    RepositorioPublicaciones.getRepositorioPublicaciones().agregarPublicacion(this);
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

  void notificarRescatista(Duenio duenio) {
    rescate.getRescatista().notificarMatchDuenio(rescate, duenio);
  }

}
