package domain;

public class Voluntario {

  Usuario usuario;
  Asociacion asociacion;

  public Voluntario(Usuario usuario, Asociacion asociacion) {
    this.usuario = usuario;
    this.asociacion = asociacion;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  void aprobarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.aprobar();
  }

  void rechazarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.rechazar();
  }

}
