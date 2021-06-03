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

  void aprobarPublicacion(Publicacion publicacion) {
    publicacion.aprobar();
  }

  void rechazarPublicacion(Publicacion publicacion) {
    publicacion.rechazar();
  }

}
