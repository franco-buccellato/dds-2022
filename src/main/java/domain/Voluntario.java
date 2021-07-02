package domain;

public class Voluntario {

  private Usuario usuario;
  private Asociacion asociacion;

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

  public void aprobarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.aprobar();
  }

  public void rechazarPublicacion(PublicacionRescate publicacionRescate) {
    publicacionRescate.rechazar();
  }

}
