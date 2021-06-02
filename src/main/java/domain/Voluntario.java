package domain;

public class Voluntario {

  Usuario usuario;
  Asociacion asociacion;

  public Voluntario(Usuario usuario, Asociacion asociacion) {
    this.usuario = usuario;
    this.asociacion = asociacion;
  }

  void aprobarPublicacion(Publicacion publicacion) {
    publicacion.aprobar();
  }

  void rechazarPublicacion(Publicacion publicacion) {
    publicacion.rechazar();
  }

}
